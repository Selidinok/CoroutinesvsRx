package com.example.android.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.android.cache.entity.RepositoryEntity
import com.example.android.cache.expired.CacheDao
import com.example.android.cache.expired.CacheEntity

@Database(entities = arrayOf(CacheEntity::class, RepositoryEntity::class), version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
    abstract fun cashDao(): CacheDao
}