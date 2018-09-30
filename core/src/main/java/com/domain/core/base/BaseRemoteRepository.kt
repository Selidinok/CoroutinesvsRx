package com.domain.core.base

import android.util.Log
import com.domain.core.result.Failure
import com.domain.core.result.Result
import retrofit2.Call
import timber.log.Timber

/**
 * Created by User on 22:49 30.06.2018.

 */
abstract class BaseRemoteRepository {

    protected fun <R> request(call: Call<R>): Result<R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    val body = response.body()
                    if (body != null) {
                        Result.Success(body)
                    } else {
                        Result.Error(Failure.EmptyError())
                    }
                }
                false -> {
                    Timber.e(response.errorBody().toString())
                    Result.Error(Failure.ServerError(response.errorBody().toString()))
                }
            }
        } catch (exception: Throwable) {
            exception.printStackTrace()
            Result.Error(Failure.ServerError(exception.message))
        }
    }
}