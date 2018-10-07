package com.example.android.coroutinesvsrx.viewmodels.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.domain.core.result.Result
import com.domain.entities.Repository
import com.domain.usecases.repositories.GetRepositoriesUseCase
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext
/**
 * Created by Artem Rumyantsev on 20:15 06.10.2018.

 */
class RepositoriesViewModel(
    val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private var since = 0
    private var repositories: MutableLiveData<List<Repository>> = MutableLiveData()

    init {
        getRepositories()
    }

    fun showError(error: Result.Failure) {
    }

    fun getRepositories() {
        GlobalScope.launch {
            getRepositoriesUseCase(since) {
                it.either(::showError, ::setRepositories)
            }
        }
    }

    fun setRepositories(data: LiveData<List<Repository>>) {
        repositories.postValue(data.value)
    }

    fun getRepositoriesList() = repositories

}