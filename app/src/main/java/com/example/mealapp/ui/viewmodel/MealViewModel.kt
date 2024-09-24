package com.example.mealapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.repository.MealRepository
import com.example.mealapp.ui.State.Error
import com.example.mealapp.ui.State.Loading
import com.example.mealapp.ui.State.Success
import com.example.mealapp.ui.State.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
  private val mealRepository: MealRepository
): ViewModel() {

  private var _mealList: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val mealList = _mealList

  fun getMealListByCategory(category: String) = viewModelScope.launch {
    try {
      mealRepository.getMealsByCategory(category).collect { meals ->
        _mealList.value = Success (value = meals.meals.orEmpty())
      }
    } catch (e: Exception) {
      _mealList.value = Error(errorMessage = e.toString())
    }
  }

  fun getTopMeals() = viewModelScope.launch {
    try {
      mealRepository.getTopMeals().collect { meals ->
        _mealList.value = Success (value = meals.meals.orEmpty())
      }
    } catch (e: Exception) {
      _mealList.value = Error(errorMessage = e.toString())
    }
  }
}