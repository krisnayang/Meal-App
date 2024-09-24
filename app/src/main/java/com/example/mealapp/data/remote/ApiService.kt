package com.example.mealapp.data.remote

import com.example.mealapp.data.CategoriesResponse
import com.example.mealapp.data.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

  @GET("categories.php")
  suspend fun getCategoryList(): CategoriesResponse

  @GET("filter.php")
  suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

  @GET("search.php")
  suspend fun getTopMeals(@Query("s") s: String = "A"): MealsResponse

  @GET("lookup.php")
  suspend fun getMealById(@Query("i") id: Int): MealsResponse
}