package com.route.newsapp.data.repo

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.api.models.SourceInArticles

interface NewsRepository {

    suspend fun loadSources(apiKey: String, category: String = ""): List<Source?>?

    suspend fun loadArticles(
        apiKey: String,
        source: SourceInArticles,
        search:String=""
    ): List<Article?>?
}