package com.domain.di

import com.domain.core.constants.AppConstants.API_URL
import com.domain.core.di.createOkHttpClient
import com.domain.core.di.createWebService
import com.domain.core.di.getDatabase
import com.domain.core.di.getNetworkInfo
import com.domain.mappers.repositories.OwnerMapper
import com.domain.mappers.repositories.ReposiroriesMapper
import com.domain.repository.repositories.RepositoriesRepository
import com.domain.usecases.repositories.GetRepositoriesUseCase
import com.example.android.cache.cache_source.RepositoriesCache
import com.example.android.cache.db.GithubDatabase
import com.example.android.remote.data.RepositoriesApi
import com.example.android.remote.repositories.RepositoriesRemote
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import org.koin.experimental.builder.single


val remoteModule = module {
    single { createOkHttpClient() }
    single { createWebService<RepositoriesApi>(get(), API_URL) }
    single { RepositoriesRemote(get()) }
    single { androidContext().getNetworkInfo() }
}

val cacheModule = module {
    single { getDatabase<GithubDatabase>(androidContext())}
    single { get<GithubDatabase>().repositoryDao() }
    single { get<GithubDatabase>().cashDao() }
    single<RepositoriesCache>()
}

val dataModule = module {
    single<ReposiroriesMapper>()
    single<OwnerMapper>()
    single { RepositoriesRepository(get(), get(), get(), get()) }
}

val domainModule = module {
    single<GetRepositoriesUseCase>()
}




