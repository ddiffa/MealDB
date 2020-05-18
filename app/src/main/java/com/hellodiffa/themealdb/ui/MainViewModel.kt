package com.hellodiffa.themealdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.hellodiffa.themealdb.base.BaseViewModel
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.model.CategoriesItem
import com.hellodiffa.themealdb.data.repository.MainRepository
import timber.log.Timber

class MainViewModel constructor(private val repository: MainRepository) : BaseViewModel() {

    private var mealFetchingLiveData = MutableLiveData<Boolean>()
    val categoriesListLiveData: LiveData<ResultState<List<CategoriesItem>>>

    init {
        Timber.d("Injection MainViewModel")

        this.categoriesListLiveData = this.mealFetchingLiveData.switchMap {
            launchOnViewModelScope {
                this.repository.loadCategoriesMeal()
            }
        }
    }

    fun fetchingCategoryList() = this.mealFetchingLiveData.postValue(true)
}