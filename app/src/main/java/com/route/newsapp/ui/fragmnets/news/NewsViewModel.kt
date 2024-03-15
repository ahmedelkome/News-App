package com.route.newsapp.ui.fragmnets.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
<<<<<<< Updated upstream
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.ArticlesResponse
import com.route.newsapp.api.models.Source
import com.route.newsapp.api.models.SourcesResponse
=======
import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.ArticlesResponse
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.api.models.SourcesResponse
import com.route.newsapp.data.database.MyDataBase
import com.route.newsapp.data.repo.NewsRepository
import com.route.newsapp.data.repo.NewsRepositoryImpl
import com.route.newsapp.data.repo.data_sources.local_data_source.LocalDataSource
import com.route.newsapp.data.repo.data_sources.local_data_source.LocalDataSourceImpl
import com.route.newsapp.data.repo.data_sources.remote_data_source.RemoteDataSourceImpl
>>>>>>> Stashed changes
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

<<<<<<< Updated upstream
=======
    val newsRepo : NewsRepository = NewsRepositoryImpl(
        RemoteDataSourceImpl(),
        LocalDataSourceImpl(MyDataBase.getInstance())
    )
>>>>>>> Stashed changes
    val sourceListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilityLiveData: MutableLiveData<Boolean> = MutableLiveData(true)

    val errorVisibilityLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources(categoryID: String) {
        viewModelScope.launch {
            progressVisibilityLiveData.value = true
            try {
                val response = ApiManager.getInstance()
                    .getSources(ApiManager.API_KEY, categoryID)
                progressVisibilityLiveData.value = false
                sourceListLiveData.value = response.sources
            } catch (e: Exception) {
                progressVisibilityLiveData.value = false
                errorVisibilityLiveData.value = e.message ?: "There is something wrong try again"
            }
        }
    }

    fun loadArticles(sourceId: String) {
        viewModelScope.launch {
            progressVisibilityLiveData.value = true
            try {
                val response = ApiManager.getInstance()
                    .getArticles(ApiManager.API_KEY, sourceId)
                progressVisibilityLiveData.value = false
                articleListLiveData.value = response.articles
            } catch (e: Exception) {
                progressVisibilityLiveData.value = false
                errorVisibilityLiveData.value = e.message ?: "There is something wrong try again"
            }
        }

    }
}