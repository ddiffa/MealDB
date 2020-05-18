package com.hellodiffa.themealdb.data.network

import com.hellodiffa.themealdb.base.BaseDataSource

class RemoteDataSource constructor(private val apiService: ApiService) : BaseDataSource() {

    suspend fun loadMealList() = getResult {
        apiService.getCategories()
    }
}