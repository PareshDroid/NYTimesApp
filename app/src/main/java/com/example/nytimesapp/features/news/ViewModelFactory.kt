package com.example.nytimesapp.features.news

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider


class MyViewModelFactory(private val mApplication: Application, private val mParam: String) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(mApplication, mParam) as T
    }
}