package com.example.android.remote.data

import com.example.android.remote.entity.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface RepositoriesApi {

    @GET("/repositories")
    fun getRepositories(): Call<List<RepositoryResponse>>
}