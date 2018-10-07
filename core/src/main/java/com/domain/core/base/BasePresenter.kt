package com.domain.core.base

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.Job

abstract class BasePresenter {
    private val job = Job()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable -> {
        handleError(throwable)
    } }

//    protected val context = UI + job + exceptionHandler

    abstract fun handleError(error: Throwable)
}