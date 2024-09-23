package com.example.mealapp.ui.wrapper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mealapp.R

class GlideWrapper(){
  fun addImage(context: Context, imageView: ImageView, url: String){
    Glide.with(context)
      .load(url)
      .placeholder(R.drawable.ic_image)
      .into(imageView)
  }
}