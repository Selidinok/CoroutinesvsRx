package com.cashback.data.entities.cash

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class OwnerEntity(
        @PrimaryKey
        val id: Int,
        val login: String,
        val nodeId: String,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String,
        val htmlUrl: String,
        val followersUrl: String,
        val followingUrl: String,
        val gistsUrl: String,
        val starredUrl: String,
        val subscriptionsUrl: String,
        val organizationsUrl: String,
        val reposUrl: String,
        val eventsUrl: String,
        val receivedEventsUrl: String,
        val type: String,
        val siteAdmin: Boolean
)
