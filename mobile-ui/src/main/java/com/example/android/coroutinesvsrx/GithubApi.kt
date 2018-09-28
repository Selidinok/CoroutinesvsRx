package com.example.android.coroutinesvsrx

import com.cashback.data.entitys.Repository
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface GithubApi {
    companion object {
        const val API_PATH = "https://api.github.com"
    }

    @GET("$API_PATH/repositories")
    fun getRepositories(
            @Field("since") since: String
    ): Call<List<Repository>>
}