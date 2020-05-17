package com.hellodiffa.themealdb.common

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error<out T>(val errorMessage: String) : ResultState<T>()
    class Loading<out T> : ResultState<T>()
}