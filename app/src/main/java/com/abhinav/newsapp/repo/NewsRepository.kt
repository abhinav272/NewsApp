package com.abhinav.newsapp.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.abhinav.newsapp.BuildConfig
import com.abhinav.newsapp.RateLimiter
import com.abhinav.newsapp.api.ApiResponse
import com.abhinav.newsapp.api.NetworkBoundResource
import com.abhinav.newsapp.api.Resource
import com.abhinav.newsapp.db.NewsDBHelper
import com.abhinav.newsapp.db.SourceEntity
import java.util.concurrent.TimeUnit
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

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchNewsSource(context: Context, language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return object : NetworkBoundResource<List<SourceEntity>, SourceResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: SourceResponse) {
//                To avoid this make API response pojo class as entity
                var sourceList = ArrayList<SourceEntity>()
                item.sources.forEach {
                    var sourceEntity = SourceEntity()
                    sourceEntity.id = it.id
                    sourceEntity.category = it.category
                    sourceEntity.country = it.country
                    sourceEntity.description = it.description
                    sourceEntity.language = it.language
                    sourceEntity.name = it.name
                    sourceEntity.url = it.url
                    sourceList.add(sourceEntity)
                }
                NewsDBHelper.getInstance(context).getSourceDao().insertSources(sourceList)
            }

            override fun shouldFetch(data: List<SourceEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<SourceEntity>> {
                return NewsDBHelper.getInstance(context).getSourceDao().getAllNewsSource()
            }

            override fun createCall(): LiveData<ApiResponse<SourceResponse>> {
                return apiInterface.getSources(language, category, country)
            }
        }.asLiveData()
    }

    fun getNewsArticles(source: String, sortBy: String?): LiveData<ArticlesResponse> {
        val liveDataArticlesResponse: MutableLiveData<ArticlesResponse> = MutableLiveData()
        apiInterface.getArticles(source, sortBy, BuildConfig.API_KEY).enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>?, t: Throwable?) {
                Log.e("Oops", "Network error ${t?.message}")
            }

            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                Log.e("Article Call Status", response.body()?.status)
                Log.e("Article Call List Contains", "${response.body()?.articles?.size}")
                liveDataArticlesResponse.value = response.body()
            }
        })
        return liveDataArticlesResponse
    }
}