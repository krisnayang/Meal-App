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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.R
import com.example.mealapp.data.CategoryResponse
import com.example.mealapp.data.MealResponse
import com.example.mealapp.databinding.LayoutFragmentHomeBinding
import com.example.mealapp.ui.State.Success
import com.example.mealapp.ui.adapter.CategoryListAdapter
import com.example.mealapp.ui.adapter.MealListAdapter
import com.example.mealapp.ui.viewmodel.CategoryViewModel
import com.example.mealapp.ui.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.layout_fragment_home) {

  private val navigationArgs: HomeFragmentArgs by navArgs()

  private val viewBinding: LayoutFragmentHomeBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentHomeBinding? = null

  private val categoryViewModel by viewModels<CategoryViewModel>()

  private val mealViewModel by viewModels<MealViewModel>()

  private var categoryAdapter: CategoryListAdapter? = null

  private var mealAdapter: MealListAdapter? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _viewBinding = LayoutFragmentHomeBinding.inflate(inflater, container, false)

    val source = navigationArgs.item

    if (source.compareTo("Category") == 0) {
      viewBinding.tvRecommend.visibility = View.VISIBLE
      viewBinding.tvTitle.text = "Category"
      categoryAdapter = CategoryListAdapter { item ->
        val action = HomeFragmentDirections.actionHomeToMealByCategory(item.strCategory)
        findNavController().navigate(action)
      }
      categoryViewModel.getCategorylist()
      mealViewModel.getTopMeals()
      setupCategory(categoryViewModel)
      viewBinding.rvCategoryList.apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = categoryAdapter
      }
    } else {
      viewBinding.tvRecommend.visibility = View.GONE
      viewBinding.rvCategoryList.visibility = View.GONE
      viewBinding.tvTitle.text = source
      mealViewModel.getMealListByCategory(source)
    }
    mealAdapter = MealListAdapter { item ->
      val action = HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(item.idMeal)
      findNavController().navigate(action)
    }
    setupMeals(mealViewModel)
    viewBinding.rvMealList.apply {
      layoutManager = GridLayoutManager(context, 2)
      adapter = mealAdapter
    }

    return viewBinding.root
  }

  private fun setupCategory(viewModel: CategoryViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.categoryList.collect { state ->
            when (state) {
              is Success<*> -> {
                viewBinding.rvCategoryList.visibility = View.VISIBLE
                categoryAdapter?.submitList((state.value as List<CategoryResponse>))
              }

              else -> {
                viewBinding.rvCategoryList.visibility = View.GONE
              }
            }
          }
        }
      }
    }
  }

  private fun setupMeals(viewModel: MealViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.mealList.collect { state ->
            when (state) {
              is Success<*> -> {
                viewBinding.rvMealList.visibility = View.VISIBLE
                mealAdapter?.submitList((state.value as List<MealResponse>))
              }

              else -> {
                viewBinding.rvMealList.visibility = View.GONE
              }
            }
          }
        }
      }
    }
  }
}