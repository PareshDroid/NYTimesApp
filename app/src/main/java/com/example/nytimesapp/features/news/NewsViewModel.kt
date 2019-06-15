package com.example.nytimesapp.features.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nytimesapp.repository.DataRepository
import com.example.nytimesapp.model.NewsDBModel

class NewsViewModel(application: Application,param:String): AndroidViewModel(application) {

    var type:String = ""
    var dataRepository: DataRepository

    init{
        type = param
        dataRepository = DataRepository()
    }

    fun getAllNews(): LiveData<List<NewsDBModel.News>>{
        return dataRepository.getNewsFromDB()
    }

    fun getNewsFromApiAndStore(): LiveData<MutableList<NewsDBModel.News>> {
        return dataRepository.getTopNews(type)
    }
}