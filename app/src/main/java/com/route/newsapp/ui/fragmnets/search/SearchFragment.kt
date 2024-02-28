package com.route.newsapp.ui.fragmnets.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.ArticlesResponse
import com.route.newsapp.api.models.Source
import com.route.newsapp.api.models.SourcesResponse
import com.route.newsapp.constants.Constants
import com.route.newsapp.databinding.FragmentSearchBinding
import com.route.newsapp.ui.adapter.articlesadapter.ArticlesAdapter
import com.route.newsapp.ui.screen.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() , OnTabSelectedListener {
    lateinit var binding: FragmentSearchBinding
    val adapter = ArticlesAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayoutSearch.addOnTabSelectedListener(this)
        binding.rvSearch.adapter = adapter
        loadSources()
        initListenner()

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
            loadSources()
        }
    }
//    private fun loadArticles(sourceId: String) {
//        ApiManager.getInstance().getArticles(
//            ApiManager.API_KEY,
//            sourceId
//        ).enqueue(object : Callback<ArticlesResponse> {
//            override fun onResponse(
//                call: Call<ArticlesResponse>,
//                response: Response<ArticlesResponse>
//            ) {
//                if (response.isSuccessful) {
//                    adapter.updateArticles(response.body()?.articles)
//
//                } else {
//                    val response= Gson()
//                        .fromJson(response.errorBody()?.string(),
//                            SourcesResponse::class.java)
//                }
//            }
//
//            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
//
//            }
//
//        })
//
//    }

    private fun loadSources() {
        changeProgressVisibility(true)
        ApiManager.getInstance().getSources(ApiManager.API_KEY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        changeProgressVisibility(false)
                        response.body()?.sources.let {
                            showSources(it!!)
                        }
                    } else {
                        changeProgressVisibility(false)
                        val error = Gson().fromJson(
                            response.errorBody()?.string(),
                            SourcesResponse::class.java
                        )
                        changeErrorVisibility(true, "There is something wrong try again")
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    changeProgressVisibility(false)
                    changeErrorVisibility(true, "Check your connection with wifi or mobile data")
                }

            })
    }

    private fun loadSearchArticles(sourceId: String) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                var textSearch = query
                ApiManager.getInstance().getArticles(ApiManager.API_KEY,sourceId,textSearch)
                    .enqueue(object : Callback<ArticlesResponse> {
                        override fun onResponse(
                            call: Call<ArticlesResponse>,
                            response: Response<ArticlesResponse>
                        ) {
                            adapter.updateArticles(response.body()?.articles)
                        }

                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        }

                    })
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var textSearch = newText
                ApiManager.getInstance().getArticles(ApiManager.API_KEY,sourceId,textSearch)
                    .enqueue(object : Callback<ArticlesResponse> {
                        override fun onResponse(
                            call: Call<ArticlesResponse>,
                            response: Response<ArticlesResponse>
                        ) {
                            adapter.updateArticles(response.body()?.articles)
                        }

                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        }

                    })
                return true
            }

        })

    }

    private fun showSources(sources: List<Source?>) {
        sources.forEach { source ->
            val tab = binding.tabLayoutSearch.newTab()
            tab.text = source?.name
            binding.tabLayoutSearch.addTab(tab)
            tab.tag = source
        }
        binding.tabLayoutSearch.getTabAt(0)?.select()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            loadSearchArticles(it)
        }

    }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            val source = tab?.tag as Source?
            source?.id?.let {
                loadSearchArticles(it)
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