package com.route.newsapp.ui.fragmnets.search

import android.widget.SearchView
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
import com.route.newsapp.repo.data_sources.local_data_source.LocalDataSourceImpl
import com.route.newsapp.repo.data_sources.remote_data_source.RemoteDataSourceImpl
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragmentViewModel : ViewModel() {

    val newsSearchRepo : NewsRepository = NewsRepositoryImpl(
        RemoteDataSourceImpl(),
        LocalDataSourceImpl()
    )

    val sourceSearchListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleSearchListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilitySearchLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    val errorVisibilitySearchLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources() {
        progressVisibilitySearchLiveData.value = true
        viewModelScope.launch {
            try {
                val sourceList =
                    newsSearchRepo.loadSources(ApiManager.API_KEY)
                progressVisibilitySearchLiveData.value = false
                sourceSearchListLiveData.value = sourceList
            } catch (e: Exception) {
                progressVisibilitySearchLiveData.value = false
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }

    fun loadSearchArticles(sourceId: String, searchKey: String = "") {
        viewModelScope.launch {
            try {
                val articleList =
                    newsSearchRepo.loadArticles(ApiManager.API_KEY,sourceId,searchKey)
                articleSearchListLiveData.value = articleList
            } catch (e: Exception) {
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }
}
