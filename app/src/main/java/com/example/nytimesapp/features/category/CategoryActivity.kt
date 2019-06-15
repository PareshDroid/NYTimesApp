package com.example.nytimesapp.features.category

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesapp.R
import com.example.nytimesapp.features.news.NewsActivity
import com.example.nytimesapp.model.CategoryModel

class CategoryActivity() : AppCompatActivity(), CategoryAdapter.Listener {



    lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        categoryRecyclerView = findViewById(R.id.category_recyclerView)

        categoryModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)

        setUpCategoryUI(categoryModel.getAllCategories())
    }


    fun setUpCategoryUI(categoryList:List<CategoryModel>) {
        val mListAdapter = CategoryAdapter(categoryList, this)

        val orientation = getResources().getConfiguration().orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            categoryRecyclerView.layoutManager = GridLayoutManager(this,3)
        }else{
            categoryRecyclerView.layoutManager = GridLayoutManager(this,2)
        }

        categoryRecyclerView.setHasFixedSize(true)
        categoryRecyclerView.setAdapter(mListAdapter)
    }

    override fun onCategoryClick(categoryModel: CategoryModel) {
        val intent = Intent(applicationContext, NewsActivity::class.java)
        intent.putExtra("CategoryType",categoryModel.categoryKey)
        startActivity(intent)
    }
}