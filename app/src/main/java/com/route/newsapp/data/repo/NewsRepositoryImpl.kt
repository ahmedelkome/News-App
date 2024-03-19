package com.route.newsapp.data.repo

import com.route.newsapp.data.ConnectivityChecker
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.repo.data_sources.local_data_source.LocalDataSource
import com.route.newsapp.data.repo.data_sources.remote_data_source.RemoteDataSource


class NewsRepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun loadSources(apiKey: String, category: String): List<Source?>? {
        if (ConnectivityChecker.isNetworkAvailable()) {
            val response = remoteDataSource.loadSources(apiKey, category)
            val nonNullList = response?.filterNotNull()
            localDataSource.deleteSource(category)
            localDataSource.saveSources(nonNullList!!)
            return response
        } else {
            return localDataSource.loadSources(category)
        }
    }

    override suspend fun loadArticles(
        apiKey: String,
        source:String,
        search:String
    ): List<Article?>? {
        if (ConnectivityChecker.isNetworkAvailable()) {
            val response = remoteDataSource.loadArticles(apiKey, source)
            val nonNullArticle = response?.filterNotNull()
            localDataSource.deleteArticle(source)
            localDataSource.saveArticles(nonNullArticle!!)
            return response
        } else {
            return localDataSource.loadArticles(source)
        }
    }
}