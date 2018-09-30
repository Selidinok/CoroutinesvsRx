package com.domain.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.domain.core.cash.CashDao
import com.domain.core.cash.CacheEntity
import com.domain.entities.cash.RepositoryEntity

@Database(entities = arrayOf(CacheEntity::class, RepositoryEntity::class), version = 1)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
    abstract fun cashDao(): CashDao
}