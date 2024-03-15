package com.route.newsapp.ui.adapter.articlesadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["url","placeholder"], requireAll = false)
fun imageUrl(imageView: ImageView, url: String,placeholder: Drawable?) {
    Glide.with(imageView)
        .load(url)
        .placeholder(placeholder)
        .into(imageView)
}