package com.domain.entities

data class Repository(
    val id: Int = 0,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val ownerId: Int
)