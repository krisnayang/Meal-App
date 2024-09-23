package com.example.mealapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.repository.CategoryResository
import com.example.mealapp.ui.State.Loading
import com.example.mealapp.ui.State.Success
import com.example.mealapp.ui.State.Error
import com.example.mealapp.ui.State.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
  private val categoryResository: CategoryResository
): ViewModel() {

  private var _categoryList: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val categoryList = _categoryList

  fun getCategorylist() = viewModelScope.launch {
    try {
      categoryResository.getCategory().collect { categories ->
        _categoryList.value = Success(value = categories.categories.orEmpty())
      }
    } catch (e: Exception) {
      _categoryList.value = Error(errorMessage = e.toString())
    }
  }
}