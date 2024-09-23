package com.example.mealapp.repository

import com.example.mealapp.data.CategoriesResponse
import kotlinx.coroutines.flow.Flow

interface CategoryResository {

  suspend fun getCategory(): Flow<CategoriesResponse>
}