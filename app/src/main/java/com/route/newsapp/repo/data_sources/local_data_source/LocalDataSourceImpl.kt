package com.route.newsapp.repo.data_sources.local_data_source

import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun loadSources(category: String): List<Source?>? {
        TODO("Not yet implemented")
    }

    override suspend fun loadArticles(sourceId: String, searchKey: String): List<Article?>? {
        TODO("Not yet implemented")
    }
}