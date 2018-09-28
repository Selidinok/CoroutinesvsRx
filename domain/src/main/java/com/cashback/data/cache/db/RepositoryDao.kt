package com.cashback.data.cache.db

import android.arch.persistence.room.*
import com.cashback.data.entities.cash.RepositoryEntity

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getRepositories(): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositoryEntity: RepositoryEntity)

    @Delete
    fun delete(repositoryEntity: RepositoryEntity)
}