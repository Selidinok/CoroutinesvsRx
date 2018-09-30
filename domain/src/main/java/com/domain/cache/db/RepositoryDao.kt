package com.domain.cache.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.domain.core.base.BaseDao
import com.domain.entities.cash.RepositoryEntity

@Dao
abstract class RepositoryDao : BaseDao<RepositoryEntity>() {

    @Query("SELECT * FROM RepositoryEntity")
    protected abstract fun getRawRepositories(): LiveData<List<RepositoryEntity>>

    fun getRepositories() = getRawRepositories().getDistinct()

}