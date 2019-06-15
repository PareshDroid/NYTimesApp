package com.example.nytimesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nytimesapp.model.NewsDBModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsTable")
    fun getAllNews() : LiveData<List<NewsDBModel.News>>

    @Insert
    fun insertAllNews(countryList: MutableList<NewsDBModel.News>)

    @Query("DELETE FROM NewsTable")
    fun deleteAllNews()
}