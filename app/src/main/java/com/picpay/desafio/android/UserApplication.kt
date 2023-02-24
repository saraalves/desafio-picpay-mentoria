package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataBaseModule
import com.picpay.desafio.android.di.dataSourceModule
import com.picpay.desafio.android.di.mapperModule
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.useCaseModule
import com.picpay.desafio.android.di.userViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val allModules = listOf(
            dataBaseModule,
            useCaseModule,
            mapperModule,
            dataSourceModule,
            repositoryModule,
            userViewModelModule,
            networkModule
        )

        startKoin {
            androidContext(this@UserApplication)
            modules(allModules)
        }
    }
}