package com.hellodiffa.themealdb

import android.app.Application
import com.hellodiffa.themealdb.di.networkModule
import com.hellodiffa.themealdb.di.persistenceModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MealApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MealApp)
            modules(networkModule)
            modules(persistenceModule)
        }


        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}