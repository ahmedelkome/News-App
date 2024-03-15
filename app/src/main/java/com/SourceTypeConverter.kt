package com

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.route.newsapp.data.api.models.Source

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return Gson().fromJson(sourceString, Source::class.java)
    }
}