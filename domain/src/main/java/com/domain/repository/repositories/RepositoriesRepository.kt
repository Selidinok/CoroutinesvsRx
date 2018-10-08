package com.domain.repository.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.NetworkInfo
import com.domain.core.base.BaseDataRepository
import com.domain.core.result.State
import com.domain.entities.Repository
import com.domain.mappers.repositories.ReposiroriesMapper
import com.domain.usecases.repositories.GetRepositoriesUseCase
import com.example.android.cache.cache_source.RepositoriesCache
import com.example.android.remote.repositories.RepositoriesRemote
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import timber.log.Timber

class RepositoriesRepository(
    private val repositoriesDatabase: RepositoriesCache,
    private val repositoriesRemote: RepositoriesRemote,
    private val mapper: ReposiroriesMapper
) : BaseDataRepository() {


    suspend fun getRepositories(since: Int): LiveData<List<Repository>> {
        var data = getRepositoriesFromCache()

        val domainData = transformLiveData(data) {
            it.map { mapper.fromCache(it) }
        }

        if (repositoriesDatabase.isExpired()) {
            GlobalScope.launch {
                getNewRepositoriesWithSave(since)
            }
        }
        return domainData
    }

    /**
     * Get List<RepositoryResponse> and save it to database
     */
    suspend fun getNewRepositoriesWithSave(since: Int) {
        val remote = getRepositoriesFromRemote(since).await()
        if (!remote.isEmpty()) {
            repositoriesDatabase.saveRepositories(remote.map { mapper.fromRemoteToCache(it) })
        }
    }

    fun getRepositoriesFromCache() = repositoriesDatabase.getRepositories()

    suspend fun getRepositoriesFromRemote(since: Int) = repositoriesRemote.getRepositories(since)
}