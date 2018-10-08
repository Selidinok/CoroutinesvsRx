package com.domain.core.base

import com.domain.core.result.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by User on 22:49 30.06.2018.

 */
abstract class BaseRemoteRepository {

//    protected fun <R> request(call: Call<R>): State<R> {
//        return try {
//            val response = call.execute()
//            when (response.isSuccessful) {
//                true -> {
//                    val body = response.body()
//                    if (body != null) {
//                        State.Success(body)
//                    } else {
//                        State.Error(Failure.EmptyError())
//                    }
//                }
//                false -> {
//                    Timber.e(response.errorBody().toString())
//                    State.Error(Failure.ServerError(response.errorBody().toString()))
//                }
//            }
//        } catch (exception: Throwable) {
//            exception.printStackTrace()
//            State.Error(Failure.ServerError(exception.message))
//        }
//    }

//    protected suspend fun <R> Call<R>.await(): State<R> = suspendCoroutine {
//        this.enqueue(object : Callback<R> {
//            override fun onResponse(call: Call<R>, response: Response<R>) {
//                when (response.isSuccessful) {
//                    true -> {
//                        val body = response.body()
//                        if (body != null) {
//                            State.Success(body)
//                        } else {
//                            State.Failure.EmptyError()
//                        }
//                    }
//                    false -> {
//                        Timber.e(response.errorBody().toString())
//                        State.Failure.ServerError(response.errorBody().toString())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<R>, t: Throwable) {
//                State.Failure.ServerError(t.message)
//            }
//        })
//    }
}