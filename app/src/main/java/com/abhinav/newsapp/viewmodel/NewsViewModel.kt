package com.abhinav.newsapp.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.abhinav.newsapp.ui.api.APIInterface
import com.abhinav.newsapp.ui.model.ArticlesResponse
import com.abhinav.newsapp.ui.model.SourceResponse
import com.abhinav.newsapp.ui.repo.NewsRepository

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsViewModel : ViewModel() {

    private val newsRepo : NewsRepository = NewsRepository(APIInterface.getNewsAPIService())

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<SourceResponse>{
        return newsRepo.getNewsSource(language, category, country)
    }

    fun getNewsArticles(source: String, sortBy: String?) : LiveData<ArticlesResponse> {
        return newsRepo.getNewsArticles(source, sortBy)
    }
}