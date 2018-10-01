package com.domain.core.result

/**
 * Created by User on 18:49 30.06.2018.

 */
sealed class Result<out R> {
    data class Error(val failure: Failure) : Result<Nothing>()

    data class Success<out R>(val result: R) : Result<R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error

    fun <R> success(a: R) = Success(a)
    fun error(b: Exception, clazz: Class<Failure>) = Error(clazz.getConstructor(String::class.java)
            .newInstance(b.message))

    fun either(onError: (Failure) -> Any, onResult: (R) -> Any): Any =
            when (this) {
                is Error -> onError(failure)
                is Success -> onResult(result)
            }

    object Complete
}

