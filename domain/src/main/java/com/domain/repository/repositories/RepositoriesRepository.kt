package com.domain.repository.repositories

import android.arch.lifecycle.LiveData
import android.net.NetworkInfo
import com.domain.core.base.BaseDataRepository
import com.domain.core.result.Result
import com.domain.entities.Repository
import com.domain.mappers.repositories.ReposiroriesMapper
import com.example.android.cache.RepositoriesCache
import com.example.android.remote.repositories.RepositoriesRemote
import timber.log.Timber

class RepositoriesRepository(
    private val repositoriesDatabase: RepositoriesCache,
    private val repositoriesRemote: RepositoriesRemote,
    private val mapper: ReposiroriesMapper,
    private val networkInfo: NetworkInfo
) : BaseDataRepository() {

    fun getReposirtories(refresh: Boolean): LiveData<List<Repository>> {
        if (refresh) {
            when(networkInfo.isConnected) {
                true -> asyncLaunch { getNewRepositoriesWithSave() }
                false ->
            }
        }

        return transformLiveData(getRepositoriesFromCache()) {
            it.map { mapper.fromCache(it) }
        }
    }

    /**
     * Get List<RepositoryResponse> and save it to database
     */
    suspend fun getNewRepositoriesWithSave() {
        val remoteResult = getRepositoriesFromRemote()
        when (remoteResult) {
            is Result.Success -> repositoriesDatabase.saveRepositories(
                remoteResult.result.map { mapper.fromRemoteToCache(it) }
            )
            is Result.Error -> Timber.e(remoteResult.failure.message)
        }
    }

    /**
     * return List<RepositoryResponse> without saving it to database
     */
    fun getNewRepositories() = asyncLaunchWithReturn { getRepositoriesFromRemote() }

    fun getRepositoriesFromCache() = repositoriesDatabase.getRepositories()

    suspend fun getRepositoriesFromRemote() = repositoriesRemote.getRepositories()

}