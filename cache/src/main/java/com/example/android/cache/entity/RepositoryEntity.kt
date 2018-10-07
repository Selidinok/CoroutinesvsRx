package com.example.android.cache.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RepositoryEntity(
    @PrimaryKey
    var id: Int,
    var nodeId: String,
    var name: String,
    var fullName: String,
    var ownerId: Int
)