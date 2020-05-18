package com.hellodiffa.themealdb.network

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiUtil {

    fun <T> getCall(data: T) = object : Call<T> {
        override fun enqueue(callback: Callback<T>) = Unit
        override fun isExecuted(): Boolean = false
        override fun clone(): Call<T> = this
        override fun isCanceled(): Boolean = false
        override fun cancel() = Unit
        override fun execute(): Response<T> = Response.success(data)
        override fun request(): Request = Request.Builder().build()
    }
}