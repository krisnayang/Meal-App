package com.example.mealapp.repository

import com.example.mealapp.data.MealsResponse
import com.example.mealapp.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
  private val api: ApiService
): MealRepository {

  override suspend fun getMealsByCategory(category: String): Flow<MealsResponse> {
    return flow { emit(api.getMealsByCategory(category)) }
  }

  override suspend fun getTopMeals(): Flow<MealsResponse> {
    return flow { emit(api.getTopMeals()) }
  }

  override suspend fun getMealById(id: Int): Flow<MealsResponse> {
    return flow { emit(api.getMealById(id)) }
  }
}