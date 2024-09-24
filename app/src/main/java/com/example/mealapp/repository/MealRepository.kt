package com.example.mealapp.repository

import com.example.mealapp.data.MealsResponse
import kotlinx.coroutines.flow.Flow

interface MealRepository {

  suspend fun getMealsByCategory(category: String): Flow<MealsResponse>

  suspend fun getTopMeals(): Flow<MealsResponse>
}