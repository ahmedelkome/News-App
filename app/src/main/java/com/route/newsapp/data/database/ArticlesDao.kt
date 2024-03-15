package com.route.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source

@Dao
interface ArticlesDao {

    @Insert
    suspend fun addArticles(articles: List<Article>)

    @Query("select * from Article where source = :source")
    suspend fun getArticles(source: String): List<Article>

    @Query("delete from Article where source =:source")
    suspend fun deleteArticle(source: String)
}