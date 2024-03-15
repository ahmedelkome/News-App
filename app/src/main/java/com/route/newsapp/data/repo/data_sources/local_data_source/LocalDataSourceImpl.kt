package com.route.newsapp.data.repo.data_sources.local_data_source

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.database.MyDataBase

class LocalDataSourceImpl(val database: MyDataBase) : LocalDataSource {
    override suspend fun loadSources(category: String): List<Source?>? {
        return database.getSourceDao().getSources(category)
    }

    override suspend fun loadArticles(sourceId: String, searchKey: String): List<Article?>? {
        return listOf()
    }

    override suspend fun saveSources(sourceList: List<Source?>) {
        val nonNullSourcesList = sourceList.filterNotNull()
        database.getSourceDao().addSources(nonNullSourcesList)
    }

    override suspend fun saveArticles(articleList: List<Article?>) {
        val nonNullArticlesList = articleList.filterNotNull()
        database.getArticleDao().addArticles(nonNullArticlesList)
    }
}