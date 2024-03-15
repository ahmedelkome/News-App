package com.route.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.newsapp.data.api.models.Source

@Dao
interface SourceDao {

    @Insert
   suspend fun addSources(sourcesList : List<Source>)

    @Query("delete from source where category =:category")
   suspend fun deleteSourceList(category: String)

    @Query("select * from Source where category = :category ")
   suspend fun getSources(category:String):List<Source>
}