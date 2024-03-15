package com.route.newsapp.ui.adapter.articlesadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun imageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}