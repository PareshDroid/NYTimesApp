package com.example.nytimesapp.features.category

import androidx.lifecycle.ViewModel
import com.example.nytimesapp.repository.DataRepository
import com.example.nytimesapp.model.CategoryModel

class CategoryViewModel: ViewModel() {

    var dataRepository: DataRepository

    init{
        dataRepository = DataRepository()
    }

    fun getAllCategories(): List<CategoryModel> {
        return dataRepository.getCategoryData()
    }
}