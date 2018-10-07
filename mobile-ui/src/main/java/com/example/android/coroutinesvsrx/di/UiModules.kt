package com.example.android.coroutinesvsrx.di

import com.example.android.coroutinesvsrx.viewmodels.repositories.RepositoriesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Artem Rumyantsev on 21:01 06.10.2018.

 */

val uiModule = module {
//    viewModel<RepositoriesViewModel>()
    viewModel { RepositoriesViewModel(get()) }
}