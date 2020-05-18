package com.hellodiffa.themealdb.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hellodiffa.themealdb.data.persistence.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDatabase {

    lateinit var db : AppDatabase

    @Before
    fun initDB(){
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB(){
        db.close()
        stopKoin()
    }
}