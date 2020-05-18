package com.hellodiffa.themealdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MealResponse(

    @field:SerializedName("categories")
    val categories: List<CategoriesItem>
)

@Parcelize
@Entity
data class CategoriesItem(

    @field:SerializedName("strCategory")
    val strCategory: String? = null,

    @field:SerializedName("strCategoryDescription")
    val strCategoryDescription: String? = null,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("idCategory")
    val idCategory: String,

    @field:SerializedName("strCategoryThumb")
    val strCategoryThumb: String? = null
) : Parcelable
