package com.hellodiffa.themealdb.common

data class ResultState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T): ResultState<T> =
            ResultState(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(message: String?, data: T? = null): ResultState<T> =
            ResultState(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): ResultState<T> =
            ResultState(status = Status.LOADING, data = data, message = null)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}