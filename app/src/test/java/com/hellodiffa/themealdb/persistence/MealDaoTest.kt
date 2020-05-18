package com.hellodiffa.themealdb.persistence

import com.hellodiffa.themealdb.MockTestUtil
import com.hellodiffa.themealdb.data.persistence.MealDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MealDaoTest : LocalDatabase() {

    private lateinit var mealDao: MealDao

    @Before
    fun init() {
        mealDao = db.mealDao()
    }

    @Test
    fun insertAndLoadCategoriesListTest()  {
        val mockDataList = MockTestUtil.mockMeal()

        mealDao.insertCategoryList(mockDataList.categories)

        val loadFromDB = mealDao.getCategoryList()
        assertThat(loadFromDB.toString(), `is`(mockDataList.categories.toString()))

        val mockData = MockTestUtil.mockMeal()
        assertThat(loadFromDB?.get(0)?.toString(), `is`(mockData.categories[0].toString()))
    }
}