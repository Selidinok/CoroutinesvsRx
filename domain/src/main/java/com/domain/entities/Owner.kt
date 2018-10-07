package com.domain.entities

data class Owner(
        val id: Int,
        val login: String,
        val nodeId: String,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String
)