package com.route.newsapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.route.newsapp.data.api.models.SourceInArticles

object SourceConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): SourceInArticles? {
        return Gson().fromJson(value, SourceInArticles::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toString(source: SourceInArticles?): String {
        return Gson().toJson(source)
    }
}