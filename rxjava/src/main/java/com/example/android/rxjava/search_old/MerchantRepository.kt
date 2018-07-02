package com.example.android.rxjava.search_old

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import nl.ippies.model.data.database.PersistentDatabase
import nl.ippies.toothpick.qualifier.database.CacheQualifier
import nl.ippies.toothpick.qualifier.database.MerchantQualifier
import nl.ippies.entity.database.Cache
import nl.ippies.entity.database.MerchantEntity
import nl.ippies.entity.domain.Deal
import nl.ippies.entity.domain.Merchant
import nl.ippies.model.data.server.IppiesApi
import nl.ippies.model.system.SchedulersProvider
import javax.inject.Inject

class MerchantRepository @Inject constructor(
        private val api: IppiesApi,
        private val schedulers: SchedulersProvider,
        @MerchantQualifier private val merchantDatabase: PersistentDatabase<MerchantEntity>,
        @CacheQualifier private val cache: PersistentDatabase<Cache>
) {
    private val mapperFromServer = MapperMerchantFromServer()
    private val mapperFromStorage = MapperMerchantFromStorage()
    private val mapperServerToStorage = MapperMerchantServerToStorage()
    private val mapperDealFromServer = MapperDealFromServer()
    private val expirationTime: Long = 1000 * 60 * 60 * 6

    fun searchInMerchant(keyword: String) =
            api.searchInMerchants(keyword)
                    .map {
                        it.map(mapperServerToStorage)
                    }
                    .map {
                        it.map(mapperFromStorage)
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun getMerchants() = cache.findByCache(merchantDatabase.type.toString())
            .flatMap { cache ->
                if (System.currentTimeMillis() - cache.createdTime < expirationTime) {
                    return@flatMap getMerchantsFromDatabase()
                } else {
                    return@flatMap getMerchantsFromServer()
                            .doOnSuccess {
                                updateCache(cache)
                            }
                }
            }
            .onErrorResumeNext {
                return@onErrorResumeNext getMerchantsFromServer()
                        .doOnSuccess {
                            updateCache(null)
                        }
            }

    fun refreshMerchants() = cache.findByCache(merchantDatabase.type.toString())
            .flatMap { cache ->
                return@flatMap getMerchantsFromServer()
                        .doOnSuccess {
                            updateCache(cache)
                        }
            }
            .onErrorResumeNext {
                return@onErrorResumeNext getMerchantsFromServer()
                        .doOnSuccess {
                            updateCache(null)
                        }
            }

    private fun getMerchantsFromDatabase() = merchantDatabase.getMerchantsSortedByName()
            .map { it.map { mapperFromStorage(it) } }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    private fun getMerchantsFromServer() = api.merchants()
            .map { it.map { mapperServerToStorage(it) } }
            .doOnSuccess {
                merchantDatabase.removeAllResults()
                        .subscribe(
                                {
                                    merchantDatabase.addListResult(it).subscribe({}, {})
                                },
                                {}
                        )
            }
            .map {
                it.map(mapperFromStorage)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getMerchantsByCategory(id: String) =
            api.merchantsByCategory(id)
                    .map {
                        it.merchants.map(mapperFromServer)
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun getMerchant(id: String) =
            api.merchant(id)
                    .flatMap {
                        return@flatMap Single.just(Pair(mapperFromServer(it), it.deals.map(mapperDealFromServer)))
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun getMerchantFromCache(id: String) =
            merchantDatabase.getMerchantById(id)
                    .map {
                        mapperFromStorage(it)
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun getMerchantForSure(id: String) =
            merchantDatabase.getMerchantById(id)
                    .map {
                        mapperFromStorage(it)
                    }
                    .onErrorResumeNext {
                        return@onErrorResumeNext api.merchant(id)
                                .flatMap {
                                    return@flatMap Single.just(mapperFromServer(it))
                                }
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun addToFavorites(merchant: Merchant) =
            api.addMerchantToFavorites(merchant.idMerchant)
                    .doOnComplete {
                        merchantDatabase.getMerchantById(merchant.idMerchant)
                                .subscribe(
                                        {
                                            it.isFavorite = true
                                            merchantDatabase.update(it).subscribe({}, {})
                                        },
                                        {}
                                )
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun removeFromFavorites(merchant: Merchant) =
            api.removeMerchantFromFavorites(merchant.idMerchant)
                    .doOnComplete {
                        merchantDatabase.getMerchantById(merchant.idMerchant)
                                .subscribe(
                                        {
                                            it.isFavorite = false
                                            merchantDatabase.update(it).subscribe({}, {})
                                        },
                                        {}
                                )
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    fun getFavorites() =
            api.favoriteMerchants()
                    .map {
                        it.map(mapperFromServer)
                    }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())

    private fun updateCache(cacheItem: Cache?) {
        if (cacheItem != null) {
            cache.deleteCacheByName(cacheItem.name)
                    .subscribe(
                            {
                                cache.addResult(Cache(merchantDatabase.type.toString()))
                                        .subscribe({}, {})
                            },
                            {}
                    )
        } else {
            cache.addResult(Cache(merchantDatabase.type.toString()))
                    .subscribe({}, {})
        }
    }

    fun clearData() {
        merchantDatabase.removeAllResults().subscribe({}, {})
        cache.removeAllResults().subscribe({}, {})
    }
}