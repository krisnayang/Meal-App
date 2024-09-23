package com.example.mealapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.data.CategoryResponse
import com.example.mealapp.databinding.LayoutCategoryItemBinding
import com.example.mealapp.ui.wrapper.GlideWrapper

class CategoryListAdapter (private val clickListener: (CategoryResponse) -> Unit) :
    ListAdapter<CategoryResponse, CategoryListAdapter.NewsViewHolder>(DiffCallback) {
  private lateinit var context: Context
  private val viewBinding: LayoutCategoryItemBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutCategoryItemBinding? = null

  companion object DiffCallback : DiffUtil.ItemCallback<CategoryResponse>() {
    override fun areItemsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
      return oldItem.strCategory == newItem.strCategory
    }

    override fun areContentsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    _viewBinding = LayoutCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    context = parent.context
    return NewsViewHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val category = getItem(position)

    holder.itemView.setOnClickListener{
      clickListener(category)
    }
    GlideWrapper().addImage(context, holder.viewBinding.ivCategoryIcon, category.strCategoryThumb.orEmpty())

    holder.bind(category)
  }

  class NewsViewHolder(val viewBinding: LayoutCategoryItemBinding) :
      RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(categories: CategoryResponse) {
      viewBinding.tvCategoryName.text = categories.strCategory
    }
  }
}
