package com.domain.core.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import org.mapstruct.factory.Mappers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val module = module {
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified Api> createWebService(okHttpClient: OkHttpClient, url: String): Api {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(Api::class.java)
}

inline fun <reified Mapper> getMapper(): Mapper = Mappers.getMapper(Mapper::class.java)

inline fun <reified Database : RoomDatabase> getDatabase(context: Context) =
    Room.databaseBuilder(context, Database::class.java, Database::class.java.simpleName).build()

inline fun <reified Object : Any> getObjectName() = Object::class.simpleName ?: ""

fun Context.getNetworkInfo() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo