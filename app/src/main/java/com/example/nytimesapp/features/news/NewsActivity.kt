package com.example.nytimesapp.features.news

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesapp.*
import com.example.nytimesapp.features.webnews.NewsWebPageActivity
import com.example.nytimesapp.model.NewsDBModel

class NewsActivity() : AppCompatActivity(), NewsListAdapter.Listener {

    private lateinit var model: NewsViewModel
    lateinit var newsRecyclerView: RecyclerView
    private lateinit var categoryType : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        newsRecyclerView = findViewById(R.id.news_recyclerView)

        categoryType = intent.getStringExtra("CategoryType")

        model = ViewModelProviders.of(this,
            MyViewModelFactory(this.application, categoryType)
        ).get(
            NewsViewModel::class.java)

        if(isNetworkConnected(this))
        {
            model.getNewsFromApiAndStore().observe(this, Observer<MutableList<NewsDBModel.News>>{ newsList ->
                setUpNewsRecyclerView(newsList)
            })

        }
        else
        {
            Toast.makeText(this,"No connected to internet. Displaying offline content", Toast.LENGTH_LONG).show()
            model.getAllNews().observe(this, Observer<List<NewsDBModel.News>> { newsList ->
                // Update the cached copy of the words in the adapter.
                Log.e("NewsList",newsList.toString())
                setUpNewsRecyclerView(newsList)
            })

        }

    }


    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun setUpNewsRecyclerView(newsList: List<NewsDBModel.News>){
        val mListAdapter = NewsListAdapter(newsList, this)
        val orientation = getResources().getConfiguration().orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //shows grid layout in landscape mode
            newsRecyclerView.layoutManager = GridLayoutManager(this,2)
            newsRecyclerView.setHasFixedSize(true)
            newsRecyclerView.setAdapter(mListAdapter)
        }else{
            //shows Linear layout in portrait mode
            val mLayoutManager = LinearLayoutManager(this)
            newsRecyclerView.setLayoutManager(mLayoutManager)
            newsRecyclerView.setItemAnimator(DefaultItemAnimator())
            newsRecyclerView.setAdapter(mListAdapter)
        }

    }

    override fun onNewsClicked(newsModel: NewsDBModel.News) {
        val intent = Intent(applicationContext, NewsWebPageActivity::class.java)
        intent.putExtra("NewsUrl",newsModel.url)
        startActivity(intent)
    }

}
