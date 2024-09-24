package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealResponse(
  @SerializedName("idMeal") val idMeal: Int,
  @SerializedName("strMeal") val strMeal: String,
  @SerializedName("strMealThumb") val strMealThumb: String,
  @SerializedName("strCategory") val strCategory: String? = "",
)
