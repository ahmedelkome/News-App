package com.route.newsapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class SourceConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): com.route.newsapp.data.api.models.Source {
            return gson.fromJson(value, com.route.newsapp.data.api.models.Source::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun toString(source: com.route.newsapp.data.api.models.Source): String {
            return gson.toJson(source)
        }
    }
}