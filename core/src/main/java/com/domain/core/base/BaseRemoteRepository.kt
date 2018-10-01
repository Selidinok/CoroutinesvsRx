package com.domain.core.base

import com.domain.core.result.Failure
import com.domain.core.result.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by User on 22:49 30.06.2018.

 */
abstract class BaseRemoteRepository {

//    protected fun <R> request(call: Call<R>): Result<R> {
//        return try {
//            val response = call.execute()
//            when (response.isSuccessful) {
//                true -> {
//                    val body = response.body()
//                    if (body != null) {
//                        Result.Success(body)
//                    } else {
//                        Result.Error(Failure.EmptyError())
//                    }
//                }
//                false -> {
//                    Timber.e(response.errorBody().toString())
//                    Result.Error(Failure.ServerError(response.errorBody().toString()))
//                }
//            }
//        } catch (exception: Throwable) {
//            exception.printStackTrace()
//            Result.Error(Failure.ServerError(exception.message))
//        }
//    }

    protected suspend fun <R> Call<R>.await(): Result<R> = suspendCoroutine {
        this.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
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
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                Result.Error(Failure.ServerError(t.message))
            }
        })
    }
}