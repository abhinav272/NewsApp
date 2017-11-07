package com.abhinav.newsapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.abhinav.newsapp.api.Resource
import com.abhinav.newsapp.db.SourceEntity
import com.abhinav.newsapp.ui.api.APIInterface
import com.abhinav.newsapp.ui.model.ArticlesResponse
import com.abhinav.newsapp.ui.model.SourceResponse
import com.abhinav.newsapp.ui.repo.NewsRepository

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepo : NewsRepository = NewsRepository(APIInterface.getNewsAPIService())
    val context: Context = application.applicationContext

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return newsRepo.fetchNewsSource(context,language, category, country)
    }

    fun getNewsArticles(source: String, sortBy: String?) : LiveData<ArticlesResponse> {
        return newsRepo.getNewsArticles(source, sortBy)
    }
}