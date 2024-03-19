package com.route.newsapp.ui.fragmnets.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.databinding.FragmentNewsBinding
import com.route.newsapp.ui.adapter.articlesadapter.ArticlesAdapter

class NewsFragment(val categoryId: String) : Fragment(), OnTabSelectedListener {
    lateinit var viewModelNews: NewsViewModel
    lateinit var binding: FragmentNewsBinding
    val adapter = ArticlesAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelNews = ViewModelProvider(this)[NewsViewModel::class.java]
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.tabLayout.addOnTabSelectedListener(this)
        binding.rvArticles.adapter = adapter
        binding.viewModel = viewModelNews
        viewModelNews.loadSources(categoryId)
//        initListenner()
        observeLiveData()
    }

    private fun tabMargin(tab: TabLayout.Tab?) {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {tab->
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 20
            tab.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    private fun observeLiveData() {
        viewModelNews.sourceListLiveData.observe(
            viewLifecycleOwner
        ) {
            showSources(it!!)
        }

        viewModelNews.articleListLiveData.observe(viewLifecycleOwner) {
            adapter.updateArticles(it)
        }

        viewModelNews.errorVisibilityLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                return@observe
            } else
                changeErrorVisibility(true, it)
        }

    }

//    private fun initListenner() {
//        adapter.onArticleClick = object : ArticlesAdapter.onClickArticle {
//            override fun onitemclick(article: Article) {
//                val intent = Intent(requireActivity(), DetailsActivity::class.java)
//                intent.putExtra(Constants.ARTICLE_KEY, article)
//                startActivity(intent)
//            }
//        }
//        binding.includeErrorBady.retry.setOnClickListener {
//            changeErrorVisibility(false)
//            changeProgressVisibility(true)
//            viewModelNews.loadSources(categoryId)
//        }
//    }

    private fun showSources(sources: List<Source?>) {
        sources.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tabMargin(tab)
            tab.text = source?.name
            binding.tabLayout.addTab(tab)
            tab.tag = source
        }
        binding.tabLayout.getTabAt(0)?.select()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            viewModelNews.loadArticles(it)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {

            viewModelNews.loadArticles(it)
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