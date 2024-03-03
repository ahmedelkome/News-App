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
//        ApiManager.getInstance().getSources(ApiManager.API_KEY, categoryID)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    if (response.isSuccessful) {
//
//                        progressVisibilityLiveData.value = false
//                        response.body()?.sources.let {
//                            sourceListLiveData.value = it
//
//                        }
//                    } else {
//                        progressVisibilityLiveData.value = false
//
//                        errorVisibilityLiveData.value = "There is something wrong try again"
//
//                    }
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//
//                    progressVisibilityLiveData.value = false
//
//                    errorVisibilityLiveData.value = "Check your connection with wifi or mobile data"
//                }
//
//            })

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

//        ApiManager.getInstance().getArticles(
//            ApiManager.API_KEY,
//            sourceId
//        ).enqueue(object : Callback<ArticlesResponse> {
//            override fun onResponse(
//                call: Call<ArticlesResponse>,
//                response: Response<ArticlesResponse>
//            ) {
//                if (response.isSuccessful) {
//
//                    articleListLiveData.value = response.body()?.articles
//
//                } else {
//
//                    errorVisibilityLiveData.value = "There is something wrong try again"
//                }
//            }
//
//            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
//                progressVisibilityLiveData.value = false
//
//                errorVisibilityLiveData.value = "Check your connection with wifi or mobile data"
//            }
//
//        })
//
//    }

    }
}