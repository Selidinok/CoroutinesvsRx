package com.domain.repository.repositories

import android.arch.lifecycle.LiveData
import com.domain.cache.RepositoriesCache
import com.domain.core.base.BaseDataRepository
import com.domain.core.result.Result
import com.domain.entities.cash.RepositoryEntity
import com.domain.entities.domain.Repository
import com.domain.entities.remote.RepositoryResponse
import com.domain.mappers.repositories.ReposiroriesMapper
import com.domain.remote.repositories.RepositoriesRemote

class RepositoriesRepository(
    private val repositoriesDatabase: RepositoriesCache,
    private val repositoriesRemote: RepositoriesRemote,
    private val mapper: ReposiroriesMapper
) : BaseDataRepository<Repository, RepositoryEntity, RepositoryResponse>(
    repositoriesDatabase,
    repositoriesRemote,
    mapper
) {

    fun getReposirtories(update: Boolean) : LiveData<List<Repository>> {
        if (update) {
            val remoteResult = getRepositoriesFromRemote()
            if (remoteResult.isSuccess) {
                val remote = (remoteResult as Result.Success).b
                repositoriesDatabase.saveRepositories(
                    remoteResult.b.map { mapper.fromRemoteToCache(it) }
                )
            }
        }
        return transformLiveData(getRepositoriesFromCache()) {
            it.map { mapper.fromCache(it) }
        }
    }

    fun getRepositoriesFromCache() = repositoriesDatabase.getRepositories()

    fun getRepositoriesFromRemote() = repositoriesRemote.getRepositories()
}