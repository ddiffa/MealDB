package com.hellodiffa.themealdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hellodiffa.themealdb.MainCoroutinesRule
import com.hellodiffa.themealdb.MockTestUtil
import com.hellodiffa.themealdb.common.ResultState
import com.hellodiffa.themealdb.data.network.ApiService
import com.hellodiffa.themealdb.data.network.RemoteDataSource
import com.hellodiffa.themealdb.data.persistence.MealDao
import com.hellodiffa.themealdb.data.repository.MainRepository
import com.hellodiffa.themealdb.model.CategoriesItem
import com.hellodiffa.themealdb.ui.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainRepository: MainRepository

    private val dao: MealDao = mock()
    private val service: ApiService = mock()

    private lateinit var remote: RemoteDataSource

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        remote = RemoteDataSource(service)
        mainRepository = MainRepository(remote, dao)
        viewModel = MainViewModel(mainRepository)
    }

    @Test
    fun fetchCategoriesListTest() = runBlocking {
        val mockData = MockTestUtil.mockMeal()

        whenever(dao.getCategoryList()).thenReturn(mockData.categories)

        val fetchedData = mainRepository.loadCategoriesMeal()
        val observer: Observer<ResultState<List<CategoriesItem>>> = mock()

        fetchedData.observeForever(observer)

        viewModel.fetchingCategoryList()

        verify(dao, atLeastOnce()).getCategoryList()
        verify(observer).onChanged(ResultState.success(mockData.categories))
        fetchedData.removeObserver(observer)
    }
}