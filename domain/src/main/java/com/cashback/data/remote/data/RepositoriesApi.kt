package com.cashback.data.remote.data

import com.cashback.data.entities.remote.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface RepositoriesApi {

    @GET("/repositories")
    fun getRepositories(): Call<List<RepositoryResponse>>
}