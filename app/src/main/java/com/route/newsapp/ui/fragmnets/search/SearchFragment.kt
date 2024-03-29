package com.route.newsapp.ui.fragmnets.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.constants.Constants
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.SourceInArticles
import com.route.newsapp.databinding.FragmentSearchBinding
import com.route.newsapp.ui.adapter.articlesadapter.ArticlesAdapter
import com.route.newsapp.ui.screen.DetailsActivity


class SearchFragment : Fragment(), OnTabSelectedListener {
    lateinit var binding: FragmentSearchBinding
    val adapter = ArticlesAdapter(listOf())
    lateinit var searchViewModel: SearchFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayoutSearch.addOnTabSelectedListener(this)
        binding.rvSearch.adapter = adapter
        searchViewModel.loadSources()
        observeLiveData()
        initListenner()
    }

    private fun tabMargin(tab: TabLayout.Tab?) {
        val tabs = binding.tabLayoutSearch.getChildAt(0) as ViewGroup
        tabs.forEach { tab ->
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 20
            tab.layoutParams = layoutParams
            binding.tabLayoutSearch.requestLayout()
        }
    }

    private fun observeLiveData() {
        searchViewModel.sourceSearchListLiveData.observe(viewLifecycleOwner) {
            showSources(it!!)
        }

        searchViewModel.progressVisibilitySearchLiveData.observe(viewLifecycleOwner) {
            changeProgressVisibility(it)
        }

        searchViewModel.errorVisibilitySearchLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                return@observe
            }
            changeErrorVisibility(true, it)
        }

        searchViewModel.articleSearchListLiveData.observe(viewLifecycleOwner) {
            adapter.updateArticles(it)
        }
    }

    private fun initListenner() {
        adapter.onArticleClick = object : ArticlesAdapter.onClickArticle {
            override fun onitemclick(article: Article) {
                val intent = Intent(requireActivity(), DetailsActivity::class.java)
                intent.putExtra(Constants.ARTICLE_KEY, article)
                startActivity(intent)
            }
        }
        binding.includeErrorBady.retry.setOnClickListener {
            changeErrorVisibility(false)
            changeProgressVisibility(true)
            searchViewModel.loadSources()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.loadSearchArticles(SourceInArticles("",""), query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewModel.loadSearchArticles(SourceInArticles("",""), newText)
                return true
            }

        })
    }

    private fun showSources(sources: List<Source?>) {
        sources.forEach { source ->
            val tab = binding.tabLayoutSearch.newTab()
            tabMargin(tab)
            tab.text = source?.name
            binding.tabLayoutSearch.addTab(tab)
            tab.tag = source
        }
        binding.tabLayoutSearch.getTabAt(0)?.select()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            val sourceInArticle = SourceInArticles(source.id, source.name)
            searchViewModel.loadSearchArticles(sourceInArticle)
        }

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            val sourceInArticle = SourceInArticles(source.id, source.name)
            searchViewModel.loadSearchArticles(sourceInArticle)
        }

    }

    fun changeErrorVisibility(isVisible: Boolean, message: String = "") {
        binding.includeErrorBady.errorBady.isVisible = isVisible
        if (isVisible) {
            binding.includeErrorBady.errorMessage.text = message
        }
    }

    fun changeProgressVisibility(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

}