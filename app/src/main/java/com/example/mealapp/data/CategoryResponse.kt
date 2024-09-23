package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
  @SerializedName("strCategory") val strCategory: String,
  @SerializedName("strCategoryThumb") val strCategoryThumb: String
)