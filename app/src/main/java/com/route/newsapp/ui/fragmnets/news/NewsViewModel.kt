package com.route.newsapp.ui.fragmnets.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.newsapp.api.ApiManager
import com.route.newsapp.api.models.Source
import com.route.newsapp.api.models.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val sourceListLiveData : MutableLiveData<List<Source?>?> = MutableLiveData(listOf())

    val progressVisibilityLiveData : MutableLiveData<Boolean> = MutableLiveData(false)

    val errorVisibilityLiveData : MutableLiveData<String> = MutableLiveData("")

    fun loadSources(categoryID:String) {
        //changeProgressVisibility(true)
        progressVisibilityLiveData.value = true
        ApiManager.getInstance().getSources(ApiManager.API_KEY,categoryID)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        //changeProgressVisibility(false)
                        progressVisibilityLiveData.value = false
                        response.body()?.sources.let {
                            sourceListLiveData.value = it
                           // showSources(it!!)
                        }
                    } else {
                       // changeProgressVisibility(false)
                        progressVisibilityLiveData.value = false
                       val error = Gson().fromJson(response.errorBody()?.string(),
                           SourcesResponse::class.java)
                        errorVisibilityLiveData.value = "There is something wrong try again"
//                        changeErrorVisibility(true,"There is something wrong try again")
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    changeProgressVisibility(false)
                    progressVisibilityLiveData.value = false
                    errorVisibilityLiveData.value = "Check your connection with wifi or mobile data"
//                    changeErrorVisibility(true,"Check your connection with wifi or mobile data")
                }

            })
    }

}