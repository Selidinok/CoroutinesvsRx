package com.domain.core.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.domain.core.result.State
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Artem Rumyantsev on 22:39 08.10.2018.

 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {
    val state: MutableLiveData<State> = MutableLiveData()
    private val job = Job()
    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        state.postValue(State.Failure(throwable.message))
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun launchWithHandler(block: suspend CoroutineScope.() -> Unit) =
        launch(errorHandler) { block() }
}