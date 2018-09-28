package com.cashback.data.remote.repositories

import com.cashback.core.base.BaseRepository
import com.cashback.data.entities.domain.Repository
import com.cashback.data.entities.remote.RepositoryResponse
import com.cashback.data.mappers.repositories.RepositoriesMapper
import com.cashback.data.remote.data.RepositoriesApi

class RepositoriesRemote(
        private val api: RepositoriesApi,
        mapper: RepositoriesMapper
) : BaseRepository<Repository, RepositoryResponse>(mapper) {

    suspend fun getRepositories() = request(api.getRepositories())
}