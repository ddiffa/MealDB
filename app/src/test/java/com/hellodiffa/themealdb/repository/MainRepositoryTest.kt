package com.hellodiffa.themealdb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.hellodiffa.themealdb.MainCoroutinesRule
import com.hellodiffa.themealdb.MockTestUtil
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.data.network.RemoteDataSource
import com.hellodiffa.themealdb.data.persistence.MealDao
import com.hellodiffa.themealdb.data.repository.MainRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.misusing.NotAMockException


@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: MainRepository

    @Mock
    private lateinit var remote: RemoteDataSource

    private val dao: MealDao = mock()


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remote = RemoteDataSource(mock())
        repository = MainRepository(remote, dao)
    }

    @Throws(NotAMockException::class)
    @Test
    fun loadMealTest() = runBlocking {
        val mockData = MockTestUtil.mockMeal()

        whenever(dao.getCategoryList()).thenReturn(emptyList())
        whenever(remote.loadMealList()).thenReturn(ResultState.success(mockData))

        val loadData = MutableLiveData<ResultState<Any>>()

        repository.loadCategoriesFlow().asLiveData().map {
            loadData.postValue(ResultState.success(it))
        }
        val observer: Observer<ResultState<Any>> = mock()
        loadData.observeForever(observer)

        val updatedData = MockTestUtil.mockMeal().categories

        whenever(dao.getCategoryList()).thenReturn(updatedData)

        loadData.postValue(ResultState.success(updatedData))
        verify(observer).onChanged(ResultState.success(updatedData))
        loadData.removeObserver(observer)

    }
}