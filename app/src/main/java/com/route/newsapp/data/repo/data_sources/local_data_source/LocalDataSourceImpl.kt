package com.route.newsapp.data.repo.data_sources.local_data_source

import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.database.MyDataBase

class LocalDataSourceImpl(val database: MyDataBase) : LocalDataSource {
    override suspend fun loadSources(category: String): List<Source?> {
        return database.getSourceDao().getSources(category)
    }

    override suspend fun loadArticles(source: String): List<Article?> {
        return database.getArticleDao().getArticle(source)
    }

    override suspend fun saveSources(sourceList: List<Source?>) {
        val nonNullSourcesList = sourceList.filterNotNull()
        database.getSourceDao().addSources(nonNullSourcesList)
    }

    override suspend fun saveArticles(articleList: List<Article?>) {
        val nonNullArticleList = articleList.filterNotNull()
        database.getArticleDao().addArticles(nonNullArticleList)
    }

    override suspend fun deleteArticle(source: String) {
        database.getArticleDao().deleteArticle(source)
    }

    override suspend fun deleteSource(category: String) {
        database.getSourceDao().deleteSourceList(category)
    }
}