package com.cashback.data.di

import com.cashback.core.constants.API_URL
import com.cashback.core.constants.CASH_MODULE
import com.cashback.core.constants.REMOTE_MODULE
import com.cashback.core.constants.REPOSITORIES_REMOTE_MAPPER
import com.cashback.core.di.createOkHttpClient
import com.cashback.core.di.createWebService
import com.cashback.core.di.getDatabase
import com.cashback.core.di.getMapper
import com.cashback.data.cache.db.GithubDatabase
import com.cashback.data.remote.data.RepositoriesApi
import com.cashback.data.mappers.repositories.RepositoriesMapper
import com.cashback.data.remote.repositories.RepositoriesRemote
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val remoteModule = module(REMOTE_MODULE) {
    single { createOkHttpClient() }
    single { createWebService<RepositoriesApi>(get(), API_URL) }

    single(REPOSITORIES_REMOTE_MAPPER) { getMapper<RepositoriesMapper>() }

    single { RepositoriesRemote(get(), get(REPOSITORIES_REMOTE_MAPPER)) }
}

val cacheModule = module(CASH_MODULE) {
    single { getDatabase<GithubDatabase>(androidContext()) }
    single { get<GithubDatabase>().repositoryDao() }
}





