package com.cashback.data.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cashback.data.entities.cash.RepositoryEntity

@Database(entities = arrayOf(RepositoryEntity::class), version = 1)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}