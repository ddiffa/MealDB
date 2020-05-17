package com.hellodiffa.themealdb.di

import androidx.room.Room
import com.hellodiffa.themealdb.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java,
            "mealdb"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().mealDao()
    }
}