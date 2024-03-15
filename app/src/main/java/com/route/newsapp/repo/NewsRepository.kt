package com.route.newsapp.repo

import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source
import retrofit2.http.Query

interface NewsRepository {

    suspend fun loadSources(apiKey: String, category: String = ""): List<Source>

    suspend fun loadArticles(
        apiKey: String,
        sourceId: String = "",
        searchKey: String = ""
    ): List<Article>
}