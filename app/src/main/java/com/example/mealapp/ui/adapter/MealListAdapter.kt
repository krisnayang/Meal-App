package com.example.mealapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.data.MealResponse
import com.example.mealapp.databinding.LayoutMealItemBinding
import com.example.mealapp.ui.wrapper.GlideWrapper

class MealListAdapter (private val clickListener: (MealResponse) -> Unit) :
    ListAdapter<MealResponse, MealListAdapter.NewsViewHolder>(DiffCallback) {
  private lateinit var context: Context
  private val viewBinding: LayoutMealItemBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutMealItemBinding? = null

  companion object DiffCallback : DiffUtil.ItemCallback<MealResponse>() {
    override fun areItemsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
      return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    _viewBinding = LayoutMealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    context = parent.context
    return NewsViewHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val meal = getItem(position)

    holder.itemView.setOnClickListener{
      clickListener(meal)
    }
    GlideWrapper().addImage(context, holder.viewBinding.ivMeal, meal.strMealThumb.orEmpty())

    holder.bind(meal)
  }

  class NewsViewHolder(val viewBinding: LayoutMealItemBinding) :
      RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(meal: MealResponse) {
      viewBinding.tvMealName.text = meal.strMeal
      viewBinding.tvMealCategory.text = meal.strCategory
    }
  }
}