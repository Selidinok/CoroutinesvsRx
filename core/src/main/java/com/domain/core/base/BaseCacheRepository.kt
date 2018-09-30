package com.domain.core.base

import com.domain.core.cash.CacheEntity
import com.domain.core.cash.CashDao
import com.domain.core.constants.AppConstants.CASH_EXPIRATION_TIME

/**
 * Created by User on 22:18 29.09.2018.

 */
abstract class BaseCacheRepository(
    private val cashDao: CashDao
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
        updateCash()
        operation(entity)
    }

    fun updateCash() {
        val cache = CacheEntity(0, getEntityName(), createExpirationTime())
        cashDao.insert(cache)
    }


    private fun createExpirationTime() = System.currentTimeMillis() + CASH_EXPIRATION_TIME

}
