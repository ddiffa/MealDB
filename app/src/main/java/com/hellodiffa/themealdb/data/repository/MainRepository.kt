package com.hellodiffa.themealdb.data.repository

import com.hellodiffa.themealdb.data.network.RemoteDataSource
import com.hellodiffa.themealdb.data.persistence.MealDao
import com.hellodiffa.themealdb.utils.resultFlow
import timber.log.Timber

class MainRepository constructor(
    private val remote: RemoteDataSource,
    private val dao: MealDao
) {

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadCategoriesFlow() = resultFlow(
        databaseQuery = { dao.getCategoryList() },
        networkCall = { remote.loadMealList() },
        saveCallResult = { dao.insertCategoryList(it.categories) }
    )
}