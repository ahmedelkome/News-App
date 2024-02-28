package com.route.newsapp.ui.fragmnets.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.route.newsapp.R
import com.route.newsapp.databinding.FragmentCategoriesBinding
import com.route.newsapp.model.Category
import com.route.newsapp.ui.adapter.categoriesadapter.CategoryAdapter

class CategoriesFragment (val onCategoryClick:(category:Category) -> Unit): Fragment() {
    lateinit var binding: FragmentCategoriesBinding
    lateinit var adapter : CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        adapter = CategoryAdapter(Category.categories,onCategoryClick)
        binding.rvCategory.adapter = adapter
    }
}