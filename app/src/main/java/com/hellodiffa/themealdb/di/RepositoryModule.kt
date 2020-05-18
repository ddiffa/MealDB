package com.hellodiffa.themealdb.di

import com.hellodiffa.themealdb.data.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get(), get()) }
}