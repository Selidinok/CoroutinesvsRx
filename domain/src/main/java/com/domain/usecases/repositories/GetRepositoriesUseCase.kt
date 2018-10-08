package com.domain.usecases.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.domain.core.base.SuspendUseCase
import com.domain.core.result.State
import com.domain.entities.Repository
import com.domain.repository.repositories.RepositoriesRepository

/**
 * Created by Artem Rumyantsev on 20:27 06.10.2018.

 */
class GetRepositoriesUseCase(
    val repositoriesRepository: RepositoriesRepository
) : SuspendUseCase<LiveData<List<Repository>>, Int>() {
    override suspend fun run(since: Int): LiveData<List<Repository>> =
        repositoriesRepository.getRepositories(since)

}
