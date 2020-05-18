package com.hellodiffa.themealdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.hellodiffa.themealdb.base.BaseViewModel
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.data.repository.MainRepository
import com.hellodiffa.themealdb.model.CategoriesItem
import timber.log.Timber

class MainViewModel constructor(private val repository: MainRepository) : BaseViewModel() {

    val categoriesListLiveData: LiveData<ResultState<List<CategoriesItem>>>

    init {
        Timber.d("Injection MainViewModel")

        this.categoriesListLiveData =
            launchOnViewModelScope {
                this.repository.loadCategoriesFlow()
                    .asLiveData()
            }
    }

}