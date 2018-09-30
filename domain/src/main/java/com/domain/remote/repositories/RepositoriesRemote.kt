package com.domain.remote.repositories

import com.domain.core.base.BaseRemoteRepository
import com.domain.entities.domain.Repository
import com.domain.entities.remote.RepositoryResponse
import com.domain.mappers.repositories.RepositoriesMapper
import com.domain.remote.data.RepositoriesApi

class RepositoriesRemote(
        private val api: RepositoriesApi
) : BaseRemoteRepository() {

    fun getRepositories() = request(api.getRepositories())
}