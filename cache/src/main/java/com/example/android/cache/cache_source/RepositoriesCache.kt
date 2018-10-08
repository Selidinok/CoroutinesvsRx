package com.example.android.cache.cache_source

import android.arch.lifecycle.LiveData
import com.example.android.cache.base.BaseCacheSource
import com.example.android.cache.base.CacheDao
import com.example.android.cache.db.RepositoryDao
import com.example.android.cache.entity.RepositoryEntity
import timber.log.Timber


class RepositoriesCache(
    private val repositoryDao: RepositoryDao,
    cashDao: CacheDao
) : BaseCacheSource(cashDao) {

    override fun getEntityName() = RepositoryEntity::class.java.simpleName

    fun getRepositories(): LiveData<List<RepositoryEntity>> = repositoryDao.getRepositories()

    fun saveRepositories(repositories: List<RepositoryEntity>) = operationWithCache(repositories) {
        val ids = repositoryDao.bulkInsert(*repositories.toTypedArray())
        Timber.d("$ids")
    }

}