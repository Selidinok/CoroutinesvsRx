package com.domain.core.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import org.mapstruct.factory.Mappers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val module = module {
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified Api> createWebService(okHttpClient: OkHttpClient, url: String): Api {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    return retrofit.create(Api::class.java)
}

inline fun <reified Mapper> getMapper() = Mappers.getMapper(Mapper::class.java)
        ?: Timber.e("Can't find mapper class: ${Mapper::class.java.simpleName}")

inline fun <reified Database : RoomDatabase> getDatabase(context: Context) =
        Room.databaseBuilder(context, Database::class.java, Database::class.java.simpleName).build()

inline fun <reified Object: Any> getObjectName() = Object::class.simpleName ?: ""