package com.example.nytimesapp

import android.app.Application
import androidx.room.Room
import com.example.nytimesapp.database.NewsDatabase

class RoomViewModelKotlinSampleApplication : Application() {

    companion object {
        var database: NewsDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, NewsDatabase::class.java, "news_db").fallbackToDestructiveMigration().build()
    }
}