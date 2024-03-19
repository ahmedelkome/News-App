package com.route.newsapp.ui.fragmnets.search

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


class SearchFragmentViewModel : ViewModel() {


    val newsSearchRepo: NewsRepository = NewsRepositoryImpl(
        RemoteDataSourceImpl(),
        LocalDataSourceImpl(MyDataBase.getInstance())
    )

    val sourceSearchListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleSearchListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilitySearchLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    val errorVisibilitySearchLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources() {
        progressVisibilitySearchLiveData.value = true
        viewModelScope.launch {
            try {
                val response = newsSearchRepo.loadSources(ApiManager.API_KEY)
                progressVisibilitySearchLiveData.value = false
                sourceSearchListLiveData.value = response
            } catch (e: Exception) {
                progressVisibilitySearchLiveData.value = false
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }

    fun loadSearchArticles(source:String="",search:String="") {
        viewModelScope.launch {
            try {
                val response = newsSearchRepo.loadArticles(ApiManager.API_KEY,source )
                articleSearchListLiveData.value = response
            } catch (e: Exception) {
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }
}
