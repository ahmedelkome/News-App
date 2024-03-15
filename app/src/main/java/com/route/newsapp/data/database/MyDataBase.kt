package com.route.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source

@Database(entities = [Source::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getSourceDao(): SourceDao

    companion object {
        private var database: MyDataBase? = null

        fun verfiyContext(context: Context) {
            if (database == null) {
                database =
                    Room.databaseBuilder(
                        context, MyDataBase::class.java,
                        "My DataBase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }

        fun getInstance(): MyDataBase {
            return database!!
        }
    }
}