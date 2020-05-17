package com.hellodiffa.themealdb.network

import com.hellodiffa.themealdb.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://www.themealdb.com/"
    }

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<MealResponse>
}