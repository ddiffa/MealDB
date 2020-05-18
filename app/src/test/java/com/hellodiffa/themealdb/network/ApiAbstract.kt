package com.hellodiffa.themealdb.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hellodiffa.themealdb.common.ResultState
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class ApiAbstract<T> {

    @Rule
    @JvmField
    val instantExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mockWebServer : MockWebServer

    @Throws(IOException::class)
    @Before
    fun mockServer(){
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Throws(IOException::class)
    @After
    fun stopServer(){
        mockWebServer.shutdown()
    }


    @Throws(IOException::class)
    fun enqueueResponse(fileName : String){
        enqueueResponse(fileName, emptyMap())
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName : String, headers : Map<String, String>){
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }


    fun createService(clazz: Class<T>) : T{
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(clazz)
    }

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultState<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultState.success(body)
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResultState<T> {
        Timber.e(message)
        return ResultState.error("Network call has failed for a following reason : $message")
    }
}