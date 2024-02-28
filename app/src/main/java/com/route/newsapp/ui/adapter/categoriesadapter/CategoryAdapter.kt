package com.route.newsapp.ui.adapter.categoriesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.newsapp.databinding.CategoryItemBinding
import com.route.newsapp.model.Category

class CategoryAdapter(val categories : List<Category>,val onCategoryClick : (category:Category)-> Unit) : Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) : ViewHolder(binding.root){
        fun bind(category:Category){
            binding.categoryName.text = category.title
            binding.categoryImage.setImageResource(category.image)
            binding.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(
                binding.root.context,category.color
            ))
            binding.root.setOnClickListener {
                onCategoryClick.invoke(category)
            }
        }
    }
}