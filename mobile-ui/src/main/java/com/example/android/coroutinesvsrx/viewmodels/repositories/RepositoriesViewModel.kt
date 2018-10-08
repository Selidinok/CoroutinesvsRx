package com.example.android.coroutinesvsrx.viewmodels.repositories

import android.arch.lifecycle.MediatorLiveData
import android.databinding.BindingConversion
import com.domain.core.base.BaseViewModel
import com.domain.core.result.State
import com.domain.entities.Repository
import com.domain.usecases.repositories.GetRepositoriesUseCase

/**
 * Created by Artem Rumyantsev on 20:15 06.10.2018.

 */
class RepositoriesViewModel(
    val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel() {

    private var since = 0
    var repositories: MediatorLiveData<List<Repository>> = MediatorLiveData()

    init {
        state.value = State.Loading
        getRepositories()
    }

    fun getRepositories() {
        launchWithHandler {
            getRepositoriesUseCase(since) {
                repositories.addSource(it) {
                    if (it == null || it.isEmpty()) {
                        state.postValue(State.Empty)
                    } else {
                        state.postValue(State.Success)
                        repositories.postValue(it)
                    }
                }
            }
        }
    }
}
