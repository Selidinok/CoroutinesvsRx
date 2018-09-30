package com.domain.remote.data

import com.domain.entities.remote.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface RepositoriesApi {

    @GET("/repositories")
    fun getRepositories(): Call<List<RepositoryResponse>>
}