package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealResponse(
  @SerializedName("idMeal") val idMeal: Int,
  @SerializedName("strMeal") val strMeal: String,
  @SerializedName("strCategory") val strCategory: String? = "",
  @SerializedName("strArea") val strArea: String? = "",
  @SerializedName("strInstructions") val strInstructions: String? = "",
  @SerializedName("strMealThumb") val strMealThumb: String? = "",
  @SerializedName("strTags") val strTags: String? = "",
  @SerializedName("strIngredient1") val strIngredient1: String? = "",
  @SerializedName("strIngredient2") val strIngredient2: String? = "",
  @SerializedName("strIngredient3") val strIngredient3: String? = "",
)
