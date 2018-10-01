package com.domain.di

import com.domain.core.constants.AppConstants.API_URL
import com.domain.core.constants.CASH_MODULE
import com.domain.core.constants.DOMAIN_MODULE
import com.domain.core.constants.REMOTE_MODULE
import com.domain.core.constants.REPOSITORIES_MAPPER
import com.domain.core.di.*
import com.domain.mappers.repositories.ReposiroriesMapper
import com.domain.repository.repositories.RepositoriesRepository
import com.example.android.cache.db.GithubDatabase
import com.example.android.remote.data.RepositoriesApi
import com.example.android.remote.repositories.RepositoriesRemote
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val remoteModule = module(REMOTE_MODULE) {
    single { createOkHttpClient() }
    single { createWebService<RepositoriesApi>(get(), API_URL) }
    single { RepositoriesRemote(get()) }
    single { androidContext().getNetworkInfo() }
}

val cacheModule = module(CASH_MODULE) {
    single { getDatabase<GithubDatabase>(androidContext()) }
    single { get<GithubDatabase>().repositoryDao() }
    single { get<GithubDatabase>().cashDao() }
}

val domainModule = module(DOMAIN_MODULE) {
    single(REPOSITORIES_MAPPER) { getMapper<ReposiroriesMapper>() }
    single { RepositoriesRepository(get(), get(), get(REPOSITORIES_MAPPER), get()) }
}





