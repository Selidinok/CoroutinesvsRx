package com.cashback.core.base

import android.util.Log
import com.cashback.core.result.Failure
import com.cashback.core.result.Result
import retrofit2.Call

/**
 * Created by User on 22:49 30.06.2018.

 */
abstract class BaseRepository <Domain, Response> (private val mapper: BaseRemoteMapper<Domain, Response>) {

    protected suspend fun request(call: Call<Response>): Result<Domain> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    val body = response.body()
                    if (body != null) {
                        val result = mapper.fromRemote(body)
                        Result.Success(result)
                    } else {
                        Result.Error(Failure.EmptyError())
                    }
                }
                false -> {
                    Log.e("BaseRepository", response.errorBody().toString())
                    Result.Error(Failure.ServerError(response.errorBody().toString()))
                }
            }
        } catch (exception: Throwable) {
            exception.printStackTrace()
            Result.Error(Failure.ServerError(exception.message))
        }
    }

    protected suspend fun requestList(call: Call<List<Response>>): Result<List<Domain>> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    val body = response.body()
                    if (body != null) {
                        val result = body.map { mapper.fromRemote(it) }
                        Result.Success(result)
                    } else {
                        Result.Error(Failure.EmptyError())
                    }
                }
                false -> {
                    Log.e("BaseRepository", response.errorBody().toString())
                    Result.Error(Failure.ServerError(response.errorBody().toString()))

                }
            }
        } catch (exception: Throwable) {
            exception.printStackTrace()
            Result.Error(Failure.ServerError(exception.message))
        }
    }
}