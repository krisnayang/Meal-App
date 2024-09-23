package com.example.mealapp.data.remote

import com.example.mealapp.data.CategoriesResponse
import retrofit2.http.GET

interface ApiService {

  @GET("categories.php")
  suspend fun getCategoryList(): CategoriesResponse
}