package com.example.android.coroutinesvsrx.repository

import com.example.android.coroutinesvsrx.IppiesApi
import com.example.android.coroutinesvsrx.Result
import com.fernandocejas.sample.core.exception.Failure
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import ru.gildor.coroutines.retrofit.awaitResponse
import ru.gildor.coroutines.retrofit.awaitResult

/**
 * Created by User on 22:49 30.06.2018.

 */
abstract class BaseRepository {
    val retrofit = Retrofit.Builder()
            .baseUrl("")
            .build()
            .create(IppiesApi::class.java)

    suspend fun <T : Any> request(call: Call<T>) = call.awaitResult()

    suspend fun <T,R> request(call: Call<T>, transform: (T) -> R): Result<Throwable, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    if (response.body() != null) {
                        val trans = transform((response.body()));
                        Result.Success()
                    } else {
                        Result.Failure(Throwable())
                    }
                }
                false -> Result.Failure(response.errorBody())
            }
        } catch (exception: Throwable) {
            Result.Failure(Failure.ServerError())
        }
    }
}