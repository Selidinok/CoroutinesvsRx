package com.example.android.cache.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.domain.core.base.BaseDao
import com.example.android.cache.entity.RepositoryEntity

@Dao
abstract class RepositoryDao : BaseDao<RepositoryEntity>() {

    @Query("SELECT * FROM RepositoryEntity")
    protected abstract fun getRawRepositories(): LiveData<List<RepositoryEntity>>

    fun getRepositories() = getRawRepositories()

}