package com.route.newsapp.data.repo.data_sources.remote_data_source

import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source

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