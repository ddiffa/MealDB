package com.hellodiffa.themealdb

import com.hellodiffa.themealdb.model.CategoriesItem
import com.hellodiffa.themealdb.model.MealResponse


object MockTestUtil {

    fun mockMeal() = MealResponse(
      categoriesItem
    )

    private val categoriesItem = listOf(
        CategoriesItem(
            strCategory = "Beef",
            strCategoryDescription = "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]",
            idCategory = "1",
            strCategoryThumb = "https://www.themealdb.com/images/category/beef.png"
        )
    )
}
