package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
  @SerializedName("categories") val categories: List<CategoryResponse>?
)
