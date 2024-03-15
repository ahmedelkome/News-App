package com.route.newsapp.data.repo.data_sources.remote_data_source

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source

interface RemoteDataSource {

    suspend fun loadSources(apiKey: String, category: String = ""): List<Source?>?

    suspend fun loadArticles(
        apiKey: String,
        sourceId: String = "",
        searchKey: String = ""
    ): List<Article?>?
}