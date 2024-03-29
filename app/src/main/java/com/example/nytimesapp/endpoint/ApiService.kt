package com.example.nytimesapp.endpoint

import com.example.nytimesapp.util.Constants
import com.example.nytimesapp.model.NewsModel
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/svc/topstories/v2/{type}")
    fun getTopNews(@Path("type") type: String, @Query("api-key") apiKey: String): Observable<NewsModel.Result>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}