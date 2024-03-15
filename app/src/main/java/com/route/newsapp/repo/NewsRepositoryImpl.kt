package com.route.newsapp.repo

import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source

class NewsRepositoryImpl : NewsRepository {
    override suspend fun loadSources(apiKey: String, category: String): List<Source> {
        TODO("Not yet implemented")
    }

    override suspend fun loadArticles(
        apiKey: String,
        sourceId: String,
        searchKey: String
    ): List<Article> {
        TODO("Not yet implemented")
    }
}