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
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragmentViewModel : ViewModel() {

    val sourceSearchListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val articleSearchListLiveData: MutableLiveData<List<Article?>?> = MutableLiveData(listOf())

    val progressVisibilitySearchLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    val errorVisibilitySearchLiveData: MutableLiveData<String> = MutableLiveData("")

    fun loadSources() {
        progressVisibilitySearchLiveData.value = true
        viewModelScope.launch {
            try {
                val response = ApiManager.getInstance().getSources(ApiManager.API_KEY)
                progressVisibilitySearchLiveData.value = false
                sourceSearchListLiveData.value = response.sources
            } catch (e: Exception) {
                progressVisibilitySearchLiveData.value = false
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }

    fun loadSearchArticles(sourceId: String, searchKey: String = "") {
        viewModelScope.launch {
            try {
                val response = ApiManager.getInstance()
                    .getArticles(ApiManager.API_KEY, sourceId, searchKey)
                articleSearchListLiveData.value = response.articles
            } catch (e: Exception) {
                errorVisibilitySearchLiveData.value = e.localizedMessage
            }
        }
    }
}
