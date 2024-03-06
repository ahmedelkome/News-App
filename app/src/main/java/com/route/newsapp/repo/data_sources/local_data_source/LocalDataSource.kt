package com.route.newsapp.repo.data_sources.local_data_source

import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source

interface LocalDataSource {
    suspend fun loadSources(category: String = ""): List<Source?>?

    suspend fun loadArticles(
        sourceId: String = "",
        searchKey: String = ""
    ): List<Article?>?


}