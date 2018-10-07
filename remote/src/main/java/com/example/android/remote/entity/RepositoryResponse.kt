package com.example.android.remote.entity

import com.google.gson.annotations.SerializedName


data class RepositoryResponse(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "node_id") var nodeId: String,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "full_name") var fullName: String,
    @SerializedName(value = "owner") val owner: Owner
) {


    data class Owner(
        @SerializedName(value = "login") val login: String,
        @SerializedName(value = "id") val id: Int,
        @SerializedName(value = "node_id") var nodeId: String,
        @SerializedName(value = "avatar_url") var avatarUrl: String,
        @SerializedName(value = "gravatar_id") var gravatarId: String,
        @SerializedName(value = "url") val url: String
    )
}