package com.hellodiffa.themealdb.network

import com.hellodiffa.themealdb.data.network.ApiService
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ApiServiceTest : ApiAbstract<ApiService>() {

    private lateinit var service: ApiService

    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchMealDB() = runBlocking {
        enqueueResponse("/TheMealDB.json")

        val responseBody = requireNotNull(service.getCategories())

        val result = getResult {
            responseBody
        }
        mockWebServer.takeRequest()


        assertThat(result.data?.categories?.get(0)?.idCategory, `is`("1"))
        assertThat(result.data?.categories?.get(0)?.strCategory, `is`("Beef"))
        assertThat(
            result.data?.categories?.get(0)?.strCategoryDescription,
            `is`("Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]")
        )
        assertThat(
            result.data?.categories?.get(0)?.strCategoryThumb,
            `is`("https://www.themealdb.com/images/category/beef.png")
        )
    }
}