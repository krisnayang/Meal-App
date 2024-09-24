package com.example.mealapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.mealapp.R
import com.example.mealapp.data.MealResponse
import com.example.mealapp.databinding.LayoutFragmentMealDetailBinding
import com.example.mealapp.ui.State.Success
import com.example.mealapp.ui.viewmodel.MealViewModel
import com.example.mealapp.ui.wrapper.GlideWrapper
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealDetailFragment: Fragment(R.layout.layout_fragment_meal_detail) {

  private val navigationArgs: MealDetailFragmentArgs by navArgs()

  private val viewBinding: LayoutFragmentMealDetailBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentMealDetailBinding? = null

  private val viewModel by viewModels<MealViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _viewBinding = LayoutFragmentMealDetailBinding.inflate(inflater, container, false)

    val id = navigationArgs.id
    viewModel.getMealById(id)
    setupMeal(viewModel)

    return viewBinding.root
  }

  private fun setupMeal(viewModel: MealViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.mealList.collect { state ->
            when (state) {
              is Success<*> -> {
                val meal = state.value as MealResponse
                val ingredientUrl = "https://themealdb.com/images/ingredients/"
                with(viewBinding) {
                  tvMealName.text = meal.strMeal
                  tvLocation.text = meal.strArea
                  tvCategoryName.text = meal.strCategory
                  tvTags.text = meal.strTags
                  tvInstruction.text = meal.strInstructions
                  tvIngredient1.text = meal.strIngredient1
                  tvIngredient2.text = meal.strIngredient2
                  tvIngredient3.text = meal.strIngredient3
                  context?.let {
                    GlideWrapper().addImage(it, ivMealImage, meal.strMealThumb.orEmpty())
                    GlideWrapper().addImage(it, ivIngredient1, ingredientUrl + meal.strIngredient1 + ".png")
                    GlideWrapper().addImage(it, ivIngredient2, ingredientUrl + meal.strIngredient2 + ".png")
                    GlideWrapper().addImage(it, ivIngredient3, ingredientUrl + meal.strIngredient3 + ".png")
                  }

                  collapseToolbar.title = meal.strMeal
                }
              }

              else -> {

              }
            }
          }
        }
      }
    }
  }
}