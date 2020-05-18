package com.hellodiffa.themealdb.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hellodiffa.themealdb.model.CategoriesItem

@Database(entities = [CategoriesItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealDao() : MealDao
}