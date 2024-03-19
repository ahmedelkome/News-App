package com.route.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.newsapp.data.SourceConverter
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.data.api.models.Source

@Database(entities = arrayOf(Source::class, Article::class), version = 2, exportSchema = false)
@TypeConverters(SourceConverter::class)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getSourceDao(): SourceDao

    abstract fun getArticleDao(): ArticleDao

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