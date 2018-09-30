package com.domain.cache

import android.arch.lifecycle.LiveData
import com.domain.cache.db.RepositoryDao
import com.domain.core.base.BaseCacheRepository
import com.domain.core.cash.CashDao
import com.domain.entities.cash.RepositoryEntity


class RepositoriesCache(
    private val repositoryDao: RepositoryDao,
    cashDao: CashDao
) : BaseCacheRepository(cashDao) {

    override fun getEntityName() = RepositoryEntity::class.java.simpleName

    fun getRepositories(): LiveData<List<RepositoryEntity>> = repositoryDao.getRepositories()

    fun saveRepositories(repositories: List<RepositoryEntity>) = operationWithCache(repositories) {
        repositoryDao.bulkInsert(*repositories.toTypedArray())
    }

}