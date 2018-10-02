package com.example.android.cache.base

import com.domain.core.constants.AppConstants.CASH_EXPIRATION_TIME
import com.example.android.cache.expired.CacheDao
import com.example.android.cache.expired.CacheEntity

/**
 * Created by User on 22:18 29.09.2018.

 */
abstract class BaseCacheRepository(
    private val cacheDao: CacheDao
) {

    abstract fun getEntityName(): String

    /**
     * Return true if last update was later than CASH_EXPIRATION_TIME
     */
    fun isExpired(): Boolean {
        val cash = cacheDao.getCash(getEntityName())
        return if (cash == null) {
            true
        } else {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = cash.expirationTime
            currentTime - lastUpdateTime > CASH_EXPIRATION_TIME
        }
    }

    protected fun <T> operationWithCache(entity: T, operation: (T) -> Unit) {
        updateCash()
        operation(entity)
    }

    fun updateCash() {
        val cache = CacheEntity(0, getEntityName(), createExpirationTime())
        cacheDao.insert(cache)
    }


    private fun createExpirationTime() = System.currentTimeMillis() + CASH_EXPIRATION_TIME

}
