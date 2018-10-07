package com.domain.repository.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.NetworkInfo
import com.domain.core.base.BaseDataRepository
import com.domain.core.result.Result
import com.domain.entities.Repository
import com.domain.mappers.repositories.ReposiroriesMapper
import com.example.android.cache.cache_source.RepositoriesCache
import com.example.android.remote.repositories.RepositoriesRemote

class RepositoriesRepository(
    private val repositoriesDatabase: RepositoriesCache,
    private val repositoriesRemote: RepositoriesRemote,
    private val mapper: ReposiroriesMapper,
    private val networkInfo: NetworkInfo
) : BaseDataRepository() {

    suspend fun getReposirtories(since: Int): Result<LiveData<List<Repository>>> {
        val returnData = MutableLiveData<List<Repository>>()

        val data = transformLiveData(getRepositoriesFromCache()) {
            it.map { mapper.fromCache(it) }
        }

        if (data == null || data.value == null) {
            val remote = getRepositoriesFromRemote(since).await()
            val result = remote.map { mapper.fromRemote(it) }
            returnData.postValue(result)
        } else {
            returnData.value = data.value
        }

//
//        val job = GlobalScope.launch {
//            returnData.addSource(data) {
//                if (it == null || it.isEmpty()) {
//                    runBlocking {
//                        val remote = async { getRepositoriesFromRemote(since) }.await()
//                        if (remote is Result.Success) {
//                            repositoriesDatabase.saveRepositories(
//                                remote.result.map { mapper.fromRemoteToCache(it) })
//                            returnData.value = remote.result.map { mapper.fromRemote(it) }
//                        }
//                    }
//                } else {
//                    returnData.value = it
//                }
//            }
//        }


//
//
//        if (repositoriesDatabase.isExpired()) {
//            when (networkInfo.isConnected) {
//                true -> asyncLaunch { getNewRepositoriesWithSave(since) }
//                false -> {
//                }
//            }
//        }
//        job.join()
//        return if (returnData.value == null) {
//            Result.Failure.EmptyError("Nothing Found")
//        } else {
//            Result.Success(data)
//        }
        return Result.Success(returnData)
    }

    /**
     * Get List<RepositoryResponse> and save it to database
     */
    suspend fun getNewRepositoriesWithSave(since: Int) {
        val remoteResult = getRepositoriesFromRemote(since)
        when (remoteResult) {
//            is Result.Success -> {
//                Timber.d("Get remote success")
//                repositoriesDatabase.saveRepositories(
//                    remoteResult.result.map { mapper.fromRemoteToCache(it) }
//                )
//            }
//            is Result.Failure -> Timber.e(remoteResult.message)
        }
    }

    /**
     * return List<RepositoryResponse> without saving it to database
     */
    fun getNewRepositories(since: Int) = asyncLaunchWithReturn { getRepositoriesFromRemote(since) }

    fun getRepositoriesFromCache() = repositoriesDatabase.getRepositories()

    suspend fun getRepositoriesFromRemote(since: Int) = repositoriesRemote.getRepositories(since)
}