package com.hellodiffa.themealdb.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hellodiffa.themealdb.model.CategoriesItem


@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryList(category: List<CategoriesItem>)

    @Query("SELECT * FROM CategoriesItem WHERE idCategory=:id")
    fun getCategory(id : String) : CategoriesItem

    @Query("SELECT * FROM CategoriesItem")
    fun getCategoryList() : List<CategoriesItem>

}