package com.route.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.api.models.SourceInArticles

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(artilces: List<Article>)

    @Query("select * from Article where source = :source")
    suspend fun getArticle(source: SourceInArticles): List<Article>

    @Query("delete from Article where source = :source")
    suspend fun deleteArticle(source: SourceInArticles)
}