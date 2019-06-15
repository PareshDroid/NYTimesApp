package com.example.nytimesapp.features.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesapp.R
import com.example.nytimesapp.model.NewsDBModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_news.view.*

class NewsListAdapter(private val newsList : List<NewsDBModel.News>, private val listener : Listener) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    interface Listener {
        fun onNewsClicked(newsModel : NewsDBModel.News)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(newsList[position], listener, position)
    }

    override fun getItemCount(): Int = newsList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_news, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(newsModel: NewsDBModel.News, listener: Listener, position: Int) {

            itemView.setOnClickListener{ listener.onNewsClicked(newsModel) }
            itemView.news_headline.text = newsModel.title
            if(null!= newsModel.imageUrl){
                Picasso.get().load(newsModel.imageUrl).fit().into(itemView.news_image)
            }else{
                Picasso.get().load(R.mipmap.ic_art).fit().into(itemView.news_image)
            }
        }
    }
}