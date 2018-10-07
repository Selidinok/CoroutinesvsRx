package com.example.android.remote.repositories

import com.domain.core.base.BaseRemoteRepository
import com.example.android.remote.data.RepositoriesApi

class RepositoriesRemote(
    private val api: RepositoriesApi
) : BaseRemoteRepository() {

    suspend fun getRepositories(since: Int) = api.getRepositories(since)
}