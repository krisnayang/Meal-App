package com.example.mealapp.data

import com.google.gson.annotations.SerializedName

data class MealsResponse(
  @SerializedName("meals") val meals: List<MealResponse>?
)
