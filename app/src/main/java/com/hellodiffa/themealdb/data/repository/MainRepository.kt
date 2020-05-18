package com.hellodiffa.themealdb.data.repository

import androidx.lifecycle.MutableLiveData
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.data.network.RemoteDataSource
import com.hellodiffa.themealdb.data.persistence.MealDao
import com.hellodiffa.themealdb.model.CategoriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository constructor(
    private val remote: RemoteDataSource,
    private val dao: MealDao
) {

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadCategoriesMeal() = withContext(Dispatchers.IO) {

        val lvData = MutableLiveData<ResultState<List<CategoriesItem>>>()
        var meal: List<CategoriesItem>? = dao.getCategoryList()
        lvData.postValue(ResultState.loading())
        delay(1_500)
        if (dao.getCategoryList()
                .isEmpty() && (remote.loadMealList().status == ResultState.Status.SUCCESS)
        ) {
            meal = remote.loadMealList().data?.categories
            lvData.postValue(ResultState.success(remote.loadMealList().data?.categories))
            dao.insertCategoryList(remote.loadMealList().data?.categories)
        } else {
            lvData.postValue(ResultState.error(remote.loadMealList().message))
        }

        lvData.apply {
            postValue(ResultState.success(meal))
        }
    }
}