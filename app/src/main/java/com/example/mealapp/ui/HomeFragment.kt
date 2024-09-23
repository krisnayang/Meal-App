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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapp.R
import com.example.mealapp.data.CategoryResponse
import com.example.mealapp.databinding.LayoutFragmentHomeBinding
import com.example.mealapp.ui.State.Success
import com.example.mealapp.ui.adapter.CategoryListAdapter
import com.example.mealapp.ui.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.layout_fragment_home) {

//  private val navigationArgs: NewsListFragmentArgs by navArgs()

  private val viewBinding: LayoutFragmentHomeBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentHomeBinding? = null

  private val viewModel by viewModels<CategoryViewModel>()

  private var categoryAdapter: CategoryListAdapter? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _viewBinding = LayoutFragmentHomeBinding.inflate(inflater, container, false)

    categoryAdapter = CategoryListAdapter { item ->

    }

    viewModel.getCategorylist()
    setupObserver(viewModel)
    viewBinding.rvCategoryList.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      adapter = categoryAdapter
    }

    return viewBinding.root
  }

  private fun setupObserver(viewModel: CategoryViewModel) {
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
}