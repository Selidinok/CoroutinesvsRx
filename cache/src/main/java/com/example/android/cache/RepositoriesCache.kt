package com.example.android.cache

import android.arch.lifecycle.LiveData
import com.domain.core.base.BaseCacheRepository
import com.example.android.cache.db.RepositoryDao
import com.example.android.cache.entity.RepositoryEntity
import com.example.android.cache.expired_cache.CacheDao


class RepositoriesCache(
    private val repositoryDao: RepositoryDao,
    cashDao: CacheDao
) : BaseCacheRepository(cashDao) {

    override fun getEntityName() = RepositoryEntity::class.java.simpleName

    fun getRepositories(): LiveData<List<RepositoryEntity>> = repositoryDao.getRepositories()

    fun saveRepositories(repositories: List<RepositoryEntity>) = operationWithCache(repositories) {
        repositoryDao.bulkInsert(*repositories.toTypedArray())
    }

}