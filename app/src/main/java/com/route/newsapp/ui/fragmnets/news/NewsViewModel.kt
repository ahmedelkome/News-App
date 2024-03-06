package com.route.newsapp.ui.fragmnets.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.Article
import com.route.newsapp.api.models.ArticlesResponse
import com.route.newsapp.api.models.Source
import com.route.newsapp.api.models.SourcesResponse
import com.route.newsapp.repo.NewsRepository
import com.route.newsapp.repo.NewsRepositoryImpl
import com.route.newsapp.repo.data_sources.local_data_source.LocalDataSource
import com.route.newsapp.repo.data_sources.local_data_source.LocalDataSourceImpl
import com.route.newsapp.repo.data_sources.remote_data_source.RemoteDataSourceImpl
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val newsRepo : NewsRepository = NewsRepositoryImpl(
        RemoteDataSourceImpl(),
        LocalDataSourceImpl()
    )
    val sourceListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilityLiveData: MutableLiveData<Boolean> = MutableLiveData(true)

    val errorVisibilityLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources(categoryID: String) {
        viewModelScope.launch {
            progressVisibilityLiveData.value = true
            try {
                val sourceList =
                    newsRepo.loadSources(ApiManager.API_KEY,categoryID)
                progressVisibilityLiveData.value = false
                sourceListLiveData.value = sourceList
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
                val articleList =
                    newsRepo.loadArticles(ApiManager.API_KEY,sourceId)
                progressVisibilityLiveData.value = false
                articleListLiveData.value = articleList
            } catch (e: Exception) {
                progressVisibilityLiveData.value = false
                errorVisibilityLiveData.value = e.message ?: "There is something wrong try again"
            }
        }

    }
}