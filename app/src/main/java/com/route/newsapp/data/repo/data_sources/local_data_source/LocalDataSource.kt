package com.route.newsapp.data.repo.data_sources.local_data_source

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
interface LocalDataSource {
    suspend fun loadSources(category: String = ""): List<Source?>

    suspend fun loadArticles(
        source:String
    ): List<Article?>?

    suspend fun saveSources(sourceList : List<Source?>)

    suspend fun saveArticles(articleList : List<Article?>)

    suspend fun deleteArticle(source:String)

    suspend fun deleteSource(category: String)
}