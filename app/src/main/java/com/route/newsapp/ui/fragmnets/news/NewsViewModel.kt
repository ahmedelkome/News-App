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
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val sourceListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilityLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    val errorVisibilityLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources(categoryID: String) {
        progressVisibilityLiveData.value = true
        viewModelScope.launch {
            try {
                val response = ApiManager.getInstance()
                    .getSources(ApiManager.API_KEY, categoryID)
                progressVisibilityLiveData.value = false
                sourceListLiveData.value = response.sources
            } catch (e: Exception) {
                errorVisibilityLiveData.value = e.localizedMessage
            }
        }
    }

    fun loadArticles(sourceId: String) {

        viewModelScope.launch {
            try {
                val response = ApiManager.getInstance()
                    .getArticles(ApiManager.API_KEY, sourceId)
                articleListLiveData.value = response.articles
            } catch (e: Exception) {
                errorVisibilityLiveData.value = e.localizedMessage
            }
        }

    }
}