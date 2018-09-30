package com.domain.di

import com.domain.core.constants.AppConstants.API_URL
import com.domain.core.constants.CASH_MODULE
import com.domain.core.constants.REMOTE_MODULE
import com.domain.core.constants.REPOSITORIES_REMOTE_MAPPER
import com.domain.core.di.createOkHttpClient
import com.domain.core.di.createWebService
import com.domain.core.di.getDatabase
import com.domain.core.di.getMapper
import com.domain.cache.db.GithubDatabase
import com.domain.remote.data.RepositoriesApi
import com.domain.mappers.repositories.RepositoriesMapper
import com.domain.remote.repositories.RepositoriesRemote
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
    single { get<GithubDatabase>().cashDao() }
}





