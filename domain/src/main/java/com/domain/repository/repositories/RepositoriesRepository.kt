package com.domain.repository.repositories

import android.arch.lifecycle.LiveData
import android.content.Context
import android.net.NetworkInfo
import androidx.work.*
import com.domain.core.base.BaseDataRepository
import com.domain.core.result.Result
import com.domain.entities.Repository
import com.domain.mappers.repositories.ReposiroriesMapper
import com.example.android.cache.RepositoriesCache
import com.example.android.remote.repositories.RepositoriesRemote
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class RepositoriesRepository(
        private val repositoriesDatabase: RepositoriesCache,
        private val repositoriesRemote: RepositoriesRemote,
        private val mapper: ReposiroriesMapper,
        private val networkInfo: NetworkInfo
) : BaseDataRepository() {

    fun getReposirtories(): LiveData<List<Repository>> {
        if (repositoriesDatabase.isExpired()) {
            when (networkInfo.isConnected) {
                true -> asyncLaunch { getNewRepositoriesWithSave() }
                false -> createWork()
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

    private fun createWork() {
        val request = OneTimeWorkRequestBuilder<RemoteRequestWorker>()
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
        WorkManager.getInstance().enqueue(request)

    }
}

class RemoteRequestWorker(val context: Context, val workerParameters: WorkerParameters) :
        Worker(context, workerParameters), KoinComponent {

    private val repositoriesRepository: RepositoriesRepository by inject()

    override fun doWork(): Result {
        repositoriesRepository.asyncLaunch { repositoriesRepository.getNewRepositoriesWithSave() }
        return Result.SUCCESS
    }
}
