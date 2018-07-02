package com.example.android.coroutinesvsrx

/**
 * Created by User on 18:49 30.06.2018.

 */
sealed class Result<out F, out S> {
    data class Success<out S>(val a: S) : Result<Nothing ,S>()
    data class Failure<out F>(val b: F) : Result<F, Nothing>()


    val isFailure get() = this is Failure<F>
    val isSuccess get() = this is Success<S>

    fun either(fnSuccess: (S) -> Any, fnFailure: (S) -> Any): Any =
            when (this) {
                is Success -> fnSuccess(a)
                is Failure -> fnFailure
}
}

