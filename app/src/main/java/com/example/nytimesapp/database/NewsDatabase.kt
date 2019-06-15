package com.example.nytimesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nytimesapp.database.NewsDao
import com.example.nytimesapp.model.NewsDBModel

@Database(entities = [(NewsDBModel.News::class)], version = 1,exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao
}


