package com.hellodiffa.themealdb.di

import com.hellodiffa.themealdb.data.network.RemoteDataSource
import org.koin.dsl.module


val dataSourceModule = module {
    single { RemoteDataSource(get()) }
}