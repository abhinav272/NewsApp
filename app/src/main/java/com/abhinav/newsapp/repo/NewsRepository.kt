package com.abhinav.newsapp.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.abhinav.newsapp.BuildConfig
import com.abhinav.newsapp.ui.api.APIInterface
import com.abhinav.newsapp.ui.model.ArticlesResponse
import com.abhinav.newsapp.ui.model.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by abhinav.sharma on 31/10/17.
 */
class NewsRepository(private val apiInterface: APIInterface) {

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<SourceResponse> {
        val liveDataSourceResponse: MutableLiveData<SourceResponse> = MutableLiveData()
        apiInterface.getSources(language, category, country).enqueue(object : Callback<SourceResponse> {
            override fun onResponse(call: Call<SourceResponse>, response: Response<SourceResponse>) {
                Log.e("Source Call Status : ", response.body()?.status)
                Log.e("Source Call List contains : ", "${response.body()?.sources?.size}")
                liveDataSourceResponse.value = response.body()
            }

            override fun onFailure(call: Call<SourceResponse>?, t: Throwable?) {
                Log.e("Oops", "Network error ${t?.message}")
            }

        })

        return liveDataSourceResponse
    }

    fun getNewsArticles(source: String, sortBy: String?): LiveData<ArticlesResponse> {
        val liveDataArticlesResponse: MutableLiveData<ArticlesResponse> = MutableLiveData()
        apiInterface.getArticles(source, sortBy, BuildConfig.API_KEY).enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ArticlesResponse>?, response: Response<ArticlesResponse>?) {
                liveDataArticlesResponse.value = response?.body()
            }
        })
        return liveDataArticlesResponse
    }
}