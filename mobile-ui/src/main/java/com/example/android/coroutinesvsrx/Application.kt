package com.example.android.coroutinesvsrx

import android.app.Application
import com.domain.di.cacheModule
import com.domain.di.dataModule
import com.domain.di.domainModule
import com.domain.di.remoteModule
import com.example.android.coroutinesvsrx.di.uiModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Created by Artem Rumyantsev on 21:03 06.10.2018.

 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        startKoin(
            this, listOf(
                domainModule,
                dataModule,
                cacheModule,
                remoteModule,
                uiModule
            )
        )
    }
}