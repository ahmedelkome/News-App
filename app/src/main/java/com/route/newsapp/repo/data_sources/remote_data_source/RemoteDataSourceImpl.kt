package com.route.newsapp.repo.data_sources.remote_data_source

import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun loadSources(apiKey: String, category: String): List<Source?>? {

        return ApiManager.getInstance().getSources(apiKey,category).sources
    }

    override suspend fun loadArticles(
        apiKey: String,
        sourceId: String,
        searchKey: String
    ): List<Article?>? {
        return ApiManager.getInstance().getArticles(apiKey,sourceId,searchKey).articles
    }
}