package com.route.newsapp.data.api.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.route.newsapp.data.SourceConverter
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class ArticlesResponse(
    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<Article?>? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)

@Entity
@Parcelize
data class Article(
    @ColumnInfo
    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,
    @ColumnInfo
    @field:SerializedName("author")
    val author: String? = null,

    @ColumnInfo
    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo
    @field:SerializedName("description")
    val description: String? = null,

    @PrimaryKey
    @ColumnInfo
    @TypeConverters(SourceConverter::class)
    @field:SerializedName("source")
    val source: @RawValue SourceInArticles,

    @ColumnInfo
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo
    @field:SerializedName("url")
    val url: String? = null,
    @ColumnInfo
    @field:SerializedName("content")
    val content: String? = null
) : Parcelable


