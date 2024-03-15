package com.route.newsapp.data.api

import com.route.newsapp.data.api.models.ArticlesResponse
import com.route.newsapp.data.api.models.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String = ""
    ): SourcesResponse

    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String = "",
        @Query("q") searchKey: String = ""
    ): ArticlesResponse
}