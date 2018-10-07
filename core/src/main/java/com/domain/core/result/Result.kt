package com.domain.core.result

/**
 * Created by User on 18:49 30.06.2018.

 */
sealed class Result<out R> {
    sealed class Failure(val message: String?) : Result<Nothing>() {

        class NetworkConnection(val errorMessage: String? = null) : Failure(errorMessage)
        class ServerError(val errorMessage: String? = null) : Failure(errorMessage)
        class EmptyError(val errorMessage: String? = null) : Failure(errorMessage)
        class DatabaseError(val errorMessage: String? = null) : Failure(errorMessage)

        /** * Extend this class for feature specific failures.*/
        abstract class FeatureFailure : Failure(null)
    }

    data class Success<out R>(val result: R) : Result<R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Failure

    fun <R> success(a: R) = Success(a)
    fun error(b: Exception, clazz: Class<Failure>) =
        clazz.getConstructor(String::class.java)
            .newInstance(b.message)


    fun either(onError: (Failure) -> Unit, onResult: (R) -> Any): Any =
        when (this) {
            is Failure -> onError(this)
            is Success -> onResult(result)
        }

    object Complete
}

