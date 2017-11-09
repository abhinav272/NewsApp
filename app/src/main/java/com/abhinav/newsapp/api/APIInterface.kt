package com.abhinav.newsapp.ui.api

import android.arch.lifecycle.LiveData
import com.abhinav.newsapp.api.ApiResponse
import com.abhinav.newsapp.api.LiveDataCallAdapterFactory
import com.abhinav.newsapp.ui.model.ArticlesResponse
import com.abhinav.newsapp.ui.model.SourceResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by abhinav.sharma on 31/10/17.
 */
interface APIInterface {

    companion object {
        val NEWSAPI_URL = "https://newsapi.org/v1/"

        fun getNewsAPIService(): APIInterface {
            return Retrofit.Builder()
                    .baseUrl(NEWSAPI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(APIInterface::class.java)
        }
    }

    @GET("sources")
    fun getSources(@Query("language") language: String?,
                   @Query("category") category: String?,
                   @Query("country") country: String?): LiveData<ApiResponse<SourceResponse>>

    @GET("articles")
    fun getArticles(@Query("source") source: String,
                    @Query("sortBy") sortBy: String?,
                    @Query("apiKey") apiKey: String): Call<ArticlesResponse>
}