package com.example.nytimesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nytimesapp.R
import com.example.nytimesapp.RoomViewModelKotlinSampleApplication
import com.example.nytimesapp.endpoint.ApiService
import com.example.nytimesapp.model.CategoryModel
import com.example.nytimesapp.model.NewsDBModel
import com.example.nytimesapp.model.NewsModel
import com.example.nytimesapp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DataRepository {

    private val api: ApiService = ApiService.create()
    private val subscriptions = CompositeDisposable()

    val wordList: LiveData<List<NewsModel>>? = null


    //static list for categories
    fun getCategoryData() : List<CategoryModel>{

        val categoryList = ArrayList<CategoryModel>()
        categoryList.add(
            CategoryModel(
                "Automobiles",
                R.mipmap.ic_automobiles,
                "automobiles.json"
            )
        )
        categoryList.add(CategoryModel("Business", R.mipmap.ic_business, "business.json"))
        categoryList.add(CategoryModel("Fashion", R.mipmap.ic_fashion, "fashion.json"))
        categoryList.add(CategoryModel("Food", R.mipmap.ic_food, "food.json"))
        categoryList.add(CategoryModel("Art", R.mipmap.ic_art, "arts.json"))
        categoryList.add(CategoryModel("Books", R.mipmap.ic_books, "books.json"))
        categoryList.add(CategoryModel("Movies", R.mipmap.ic_movies, "movies.json"))
        categoryList.add(CategoryModel("Sports", R.mipmap.ic_sports, "sports.json"))

        return categoryList
    }

    //get list of news from database
    fun getNewsFromDB() : LiveData<List<NewsDBModel.News>>
    {
        return RoomViewModelKotlinSampleApplication.database!!.newsDao().getAllNews()

    }

    //get top news from NY Times API
    public fun getTopNews(type:String) : MutableLiveData<MutableList<NewsDBModel.News>> {
        val data = MutableLiveData<MutableList<NewsDBModel.News>>()
        val list: MutableList<NewsDBModel.News> = ArrayList()

        subscriptions.add(
            api.getTopNews(
                type,
                Constants.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        newsData: NewsModel.Result -> Thread(Runnable {

                        val results = newsData.results

                        var i=0

                        //only few data types like imageurl, title, date, etc is taken from the News API and put into Database
                        for(item in results){
                            val multimediaSize=item.multimedia.size
                            if(multimediaSize>0) {
                                val model = NewsDBModel.News()
                                model.id = i
                                model.section = item.section
                                model.title = item.title
                                model.abstract = item.abstract
                                model.url = item.url
                                model.byline = item.byline
                                model.item_type = item.item_type
                                model.updated_date = item.updated_date
                                model.created_date = item.created_date
                                model.published_date = item.published_date


                                val multimedia = item.multimedia[multimediaSize - 1]

                                model.imageUrl = multimedia.url
                                model.format = multimedia.format
                                model.height = multimedia.height
                                model.width = multimedia.width
                                model.type = multimedia.type
                                model.subtype = multimedia.subtype
                                model.caption = multimedia.caption
                                model.copyright = multimedia.copyright

                                i++
                                list.add(model)
                            }
                        }

                        RoomViewModelKotlinSampleApplication.database!!.newsDao().deleteAllNews()
                        RoomViewModelKotlinSampleApplication.database!!.newsDao().insertAllNews(list)


                    }).start()
                        data.value = list
                    },
                    {
                            error:Throwable -> data.value = null
                    }
                )
        )
        return data
    }
}