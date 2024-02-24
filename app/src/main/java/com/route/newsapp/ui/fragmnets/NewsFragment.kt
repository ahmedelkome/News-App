package com.route.newsapp.ui.fragmnets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.ArticlesResponse
import com.route.newsapp.api.models.Source
import com.route.newsapp.api.models.SourcesResponse
import com.route.newsapp.databinding.FragmentNewsBinding
import com.route.newsapp.ui.adapter.ArticlesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() , OnTabSelectedListener {
    lateinit var binding: FragmentNewsBinding

    val adapter = ArticlesAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSources()
        binding.tabLayout.addOnTabSelectedListener(this)
        binding.rvArticles.adapter = adapter
    }

    private fun loadArticles(sourceId: String) {
        ApiManager.getInstance().getArticles(
            ApiManager.API_KEY,
            sourceId
        ).enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful) {
                    adapter.updateArticles(response.body()?.articles)

                } else {
                    Gson().fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {

            }

        })

    }

    private fun loadSources() {
        ApiManager.getInstance().getSources(ApiManager.API_KEY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.sources.let {
                            showSources(it!!)
                        }
                    } else {
                        Gson().fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }

            })
    }

    private fun showSources(sources: List<Source?>) {
        sources.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            binding.tabLayout.addTab(tab)
            tab.tag = source
        }
        binding.tabLayout.getTabAt(0)?.select()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            loadArticles(it)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val source = tab?.tag as Source?
        source?.id?.let {
            loadArticles(it)
        }
    }
}