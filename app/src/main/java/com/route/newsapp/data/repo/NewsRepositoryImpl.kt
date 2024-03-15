package com.route.newsapp.data.repo

<<<<<<< Updated upstream:app/src/main/java/com/route/newsapp/repo/NewsRepositoryImpl.kt
import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.Source

class NewsRepositoryImpl : NewsRepository {
    override suspend fun loadSources(apiKey: String, category: String): List<Source> {
        TODO("Not yet implemented")
=======
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.ConnectivityChecker
import com.route.newsapp.data.repo.data_sources.local_data_source.LocalDataSource
import com.route.newsapp.data.repo.data_sources.remote_data_source.RemoteDataSource

class NewsRepositoryImpl (val remoteDataSource: RemoteDataSource,
                          val localDataSource: LocalDataSource
)
    : NewsRepository {
    override suspend fun loadSources(apiKey: String, category: String): List<Source?>? {
        if (ConnectivityChecker.isNetworkAvailable()){
            val response = remoteDataSource.loadSources(apiKey,category)
            val nonNullList =response?.filterNotNull()
            localDataSource.saveSources(nonNullList!!)
            return response
        }else{
            return localDataSource.loadSources(category)
        }
>>>>>>> Stashed changes:app/src/main/java/com/route/newsapp/data/repo/NewsRepositoryImpl.kt
    }

    override suspend fun loadArticles(
        apiKey: String,
        sourceId: String,
        searchKey: String
<<<<<<< Updated upstream:app/src/main/java/com/route/newsapp/repo/NewsRepositoryImpl.kt
    ): List<Article> {
        TODO("Not yet implemented")
=======
    ): List<Article?>? {
        if (ConnectivityChecker.isNetworkAvailable()){
            val response = remoteDataSource.loadArticles(apiKey,sourceId,searchKey)
            localDataSource.saveArticles(response!!)
            return response
        }else{
            return localDataSource.loadArticles(sourceId,searchKey)
        }
>>>>>>> Stashed changes:app/src/main/java/com/route/newsapp/data/repo/NewsRepositoryImpl.kt
    }
}