package com.cashback.data.repository.repositories

import com.cashback.data.cache.RepositoriesDatabase
import com.cashback.data.remote.data.RepositoriesApi

class RepositoriesRepository(
        private val repositoriesDatabase: RepositoriesDatabase,
        private val repostoriesRemote: RepositoriesApi

) {

    open fun retriveDataStore() {
//        if (repositoriesDatabase.isCashed()) repositoriesDatabase.getRepositories().map {  }
    }
}