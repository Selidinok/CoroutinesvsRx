package com.example.android.remote.data

import com.example.android.remote.entity.RepositoryResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface RepositoriesApi {

    @GET("/repositories")
    fun getRepositories(@Query("since") since: Int): Deferred<List<RepositoryResponse>>
}