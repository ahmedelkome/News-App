package com.route.newsapp.repo

import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source
import com.route.newsapp.repo.data_sources.local_data_source.LocalDataSource
import com.route.newsapp.repo.data_sources.remote_data_source.RemoteDataSource

class NewsRepositoryImpl (val remoteDataSource: RemoteDataSource,
                          val localDataSource: LocalDataSource)
    : NewsRepository {
    override suspend fun loadSources(apiKey: String, category: String): List<Source?>? {
        if (true){
            return remoteDataSource.loadSources(apiKey,category)
        }else{
            return localDataSource.loadSources(category)
        }
    }

    override suspend fun loadArticles(
        apiKey: String,
        sourceId: String,
        searchKey: String
    ): List<Article?>? {
        if (true){
            return remoteDataSource.loadArticles(apiKey,sourceId,searchKey)
        }else{
            return localDataSource.loadArticles(sourceId,searchKey)
        }
    }
}