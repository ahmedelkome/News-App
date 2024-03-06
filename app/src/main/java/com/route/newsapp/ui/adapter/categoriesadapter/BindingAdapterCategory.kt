package com.route.newsapp.ui.adapter.categoriesadapter

import android.graphics.Color
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("imageresource")
fun setImage(imageView: ImageView, resource:Int){
    imageView.setImageResource(resource)
}

@BindingAdapter("cardbackground")
fun setCardColor(cardView: CardView, color:Int){
    cardView.setCardBackgroundColor(
        ContextCompat.getColor(
            cardView.context, color
        )
    )
}