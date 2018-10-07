package com.example.android.cache.expired

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by User on 22:04 29.09.2018.

 */
@Entity
data class CacheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tableName: String,
    val expirationTime: Long
)
