package com.route.newsapp.data.repo

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import retrofit2.http.Query

interface NewsRepository {

    suspend fun loadSources(apiKey: String, category: String = ""): List<Source>

    suspend fun loadArticles(
        apiKey: String,
        sourceId: String = "",
        searchKey: String = ""
    ): List<Article>
}