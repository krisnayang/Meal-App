package com.example.mealapp.repository

import com.example.mealapp.data.CategoriesResponse
import com.example.mealapp.data.CategoryResponse
import com.example.mealapp.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
  private val api: ApiService
): CategoryResository {

  override suspend fun getCategory(): Flow<CategoriesResponse> {
    return flow { emit(api.getCategoryList()) }
  }
}