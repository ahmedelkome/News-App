package com.route.newsapp

import android.app.Application
import com.route.newsapp.data.ConnectivityChecker
import com.route.newsapp.data.database.MyDataBase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ConnectivityChecker.context = this
        MyDataBase.verfiyContext(this)
    }
}