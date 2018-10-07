package com.example.android.cache.base

import com.domain.core.constants.AppConstants.CASH_EXPIRATION_TIME
import com.example.android.cache.expired_cache.CacheEntity
import timber.log.Timber

/**
 * Created by User on 22:18 29.09.2018.

 */
abstract class BaseCacheSource(
    private val cashDao: CacheDao
) {

    abstract fun getEntityName(): String

    /**
     * Return true if last update was later than CASH_EXPIRATION_TIME
     */
    fun isExpired(): Boolean {
        val cash = cashDao.getCash(getEntityName())
        return if (cash == null) {
            true
        } else {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = cash.expirationTime
            currentTime - lastUpdateTime > CASH_EXPIRATION_TIME
        }
    }

    protected fun <T> operationWithCache(entity: T, operation: (T) -> Unit) {
        Timber.d("Start save")
        updateCash()
        operation(entity)
    }

    fun updateCash() {
        val cache = CacheEntity(0, getEntityName(), createExpirationTime())
        Timber.d("Start update cache")
        cashDao.insert(cache)
    }


    private fun createExpirationTime() = System.currentTimeMillis() + CASH_EXPIRATION_TIME

}
