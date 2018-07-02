package com.example.android.rxjava.search_old

import io.reactivex.Single
import nl.ippies.entity.database.Cache
import nl.ippies.entity.database.DealEntity
import nl.ippies.entity.database.MerchantEntity
import nl.ippies.model.data.database.PersistentDatabase
import nl.ippies.model.data.server.IppiesApi
import nl.ippies.model.system.SchedulersProvider
import nl.ippies.toothpick.qualifier.database.CacheQualifier
import nl.ippies.toothpick.qualifier.database.DealQualifier
import nl.ippies.toothpick.qualifier.database.MerchantQualifier
import javax.inject.Inject

class DealRepository(
        private val api: IppiesApi,
        private val schedulers: SchedulersProvider,
        private val cache: PersistentDatabase<Cache>,
        private val dealDatabase: PersistentDatabase<DealEntity>,
        private val merchantDatabase: PersistentDatabase<MerchantEntity>
) {

    private val mapperDealFromServer = MapperDealFromServer()
    private val mapperDealFromStorage = MapperDealFromStorage()
    private val mapperDealFromServerToStorage = MapperDealFromServerToStorage()
    private val mapperMerchantFromStorage = MapperMerchantFromStorage()
    private val expirationTime: Long = 1000 * 60 * 60

    fun refreshDeals() = cache.findByCache(dealDatabase.type.toString())
            .flatMap { cache ->
                return@flatMap getDealsFromServer()
                        .doOnSuccess {
                            updateCache(cache)
                        }
            }
            .onErrorResumeNext {
                updateCache(null)
                return@onErrorResumeNext getDealsFromServer()
            }

    fun getDeals() = cache.findByCache(dealDatabase.type.toString())
            .flatMap { cache ->
                if (System.currentTimeMillis() - cache.createdTime < expirationTime) {
                    return@flatMap getDealsFromDatabase()
                } else {
                    return@flatMap getDealsFromServer()
                            .doOnSuccess {
                                updateCache(cache)
                            }
                }
            }
            .onErrorResumeNext {
                return@onErrorResumeNext getDealsFromServer()
                        .doOnSuccess {
                            updateCache(null)
                        }
            }

    private fun getDealsFromDatabase() = dealDatabase.getAllResults()
            .map { it.map { mapperDealFromStorage(it) } }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    private fun getDealsFromServer() = api.deals()
            .map { it.map { mapperDealFromServerToStorage(it) } }
            .flatMap {
                dealDatabase.removeAllResults()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.ui())
                        .subscribe(
                                {
                                    dealDatabase.addListResult(it)
                                            .subscribeOn(schedulers.io())
                                            .observeOn(schedulers.io())
                                            .subscribe({}, {})
                                },
                                {}
                        )

                return@flatMap Single.just(it.map { mapperDealFromStorage.invoke(it) })
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    private fun updateCache(cacheItem: Cache?) {
        if (cacheItem != null) {
            cache.deleteCacheByName(cacheItem.name)
                    .subscribe(
                            {
                                cache.addResult(Cache(dealDatabase.type.toString()))
                                        .subscribeOn(schedulers.io())
                                        .observeOn(schedulers.io())
                                        .subscribe({}, {})
                            },
                            {})
        } else {
            cache.addResult(Cache(dealDatabase.type.toString()))
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.io())
                    .subscribe({}, {})
        }
    }

    fun getDealsByMerchant(
            id: String
    ) = api.dealsByMerchant(id)
            .map {
                it.map(mapperDealFromServer)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun search(
            keyword: String
    ) = api.searchInDeals(keyword)
            .map {
                it.map(mapperDealFromServer)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getMerchantByDeal(id: String) = merchantDatabase.getMerchantById(id)
            .map { mapperMerchantFromStorage(it) }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}