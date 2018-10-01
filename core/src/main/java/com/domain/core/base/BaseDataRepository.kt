package com.domain.core.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import androidx.work.OneTimeWorkRequestBuilder
import kotlinx.coroutines.experimental.*
import timber.log.Timber

/**
 * Created by User on 12:00 30.09.2018.

 */
abstract class BaseDataRepository {
    val errorHandler = CoroutineExceptionHandler { context, throwable ->
        Timber.e(throwable)
    }

    fun asyncLaunch(block: suspend () -> Unit) = GlobalScope.launch(errorHandler) { block() }

    fun <T> asyncLaunchWithReturn(block: suspend () -> T): T =
        runBlocking { GlobalScope.async(errorHandler) { block() }.await() }


    fun <T, R> transformLiveData(obj: LiveData<T>, mapper: (T) -> R): LiveData<R> =
        Transformations.switchMap(obj) {
            val newData = MutableLiveData<R>()
            newData.value = mapper(it)
            return@switchMap newData
        }

    fun <T> waitForReconnect(block: () -> Unit) {
        val work = OneTimeWorkRequestBuilder<>()
    }

}