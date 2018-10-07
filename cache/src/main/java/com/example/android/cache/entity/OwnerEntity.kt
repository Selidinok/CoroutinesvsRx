package com.example.android.cache.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class OwnerEntity(
    @PrimaryKey
    var id: Int,
    var login: String,
    var nodeId: String,
    var avatarUrl: String,
    var gravatarId: String,
    var url: String
)