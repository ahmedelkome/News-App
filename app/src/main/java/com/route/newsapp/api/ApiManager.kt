package com.route.newsapp.api

import android.util.Log
import com.route.newsapp.api.models.WebServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager {
    companion object{
        const val API_KEY = "82036a2aea05466abe15646e58ee6c0a"
        private var retrofit : Retrofit? = null
       private val logger = HttpLoggingInterceptor {
            Log.e("ApiManager", "Body -> $it") }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        fun getInstance():WebServices{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit!!.create(WebServices::class.java)
        }
    }

}