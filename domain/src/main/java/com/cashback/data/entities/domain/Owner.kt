package com.cashback.data.entities.domain

data class Owner(
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
) {
    constructor() : this(0, "","","","","","","","","","","","","","","","",false)
}