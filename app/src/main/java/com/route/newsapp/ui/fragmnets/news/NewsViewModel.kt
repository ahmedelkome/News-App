package com.route.newsapp.ui.fragmnets.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.route.newsapp.data.api.ApiManager
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source
import com.route.newsapp.data.database.MyDataBase
import com.route.newsapp.data.repo.NewsRepository
import com.route.newsapp.data.repo.NewsRepositoryImpl
import com.route.newsapp.data.repo.data_sources.local_data_source.LocalDataSourceImpl
import com.route.newsapp.data.repo.data_sources.remote_data_source.RemoteDataSourceImpl

import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {


    val newsRepo: NewsRepository = NewsRepositoryImpl(
        RemoteDataSourceImpl(),
        LocalDataSourceImpl(MyDataBase.getInstance())
    )

    val sourceListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilityLiveData: MutableLiveData<Boolean> = MutableLiveData(true)

    val errorVisibilityLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources(categoryID: String) {
        viewModelScope.launch {
            progressVisibilityLiveData.value = true
            try {
                val response = newsRepo.loadSources(ApiManager.API_KEY, categoryID)
                progressVisibilityLiveData.value = false
                sourceListLiveData.value = response
            } catch (e: Exception) {
                progressVisibilityLiveData.value = false
                errorVisibilityLiveData.value = e.message ?: "There is something wrong try again"
            }
        }
    }

    fun loadArticles(source: String) {
        viewModelScope.launch {
            progressVisibilityLiveData.value = true
            try {
                val response = newsRepo.loadArticles(ApiManager.API_KEY, source)
                progressVisibilityLiveData.value = false
                articleListLiveData.value = response
            } catch (e: Exception) {
                progressVisibilityLiveData.value = false
                errorVisibilityLiveData.value = e.message ?: "There is something wrong try again"
            }
        }

    }
}