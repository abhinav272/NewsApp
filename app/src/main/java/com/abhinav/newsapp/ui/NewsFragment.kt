package com.abhinav.newsapp.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhinav.newsapp.R
import com.abhinav.newsapp.adapter.NewsArticleAdapter
import com.abhinav.newsapp.adapter.NewsSourceAdapter
import com.abhinav.newsapp.api.Resource
import com.abhinav.newsapp.db.SourceEntity
import com.abhinav.newsapp.ui.model.ArticlesResponse
import com.abhinav.newsapp.ui.model.Source
import com.abhinav.newsapp.ui.model.SourceResponse
import com.abhinav.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsFragment : LifecycleFragment(), (SourceEntity) -> Unit {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var observerNewsSource: Observer<Resource<List<SourceEntity>>>
    private lateinit var observerNewsArticle: Observer<ArticlesResponse>
    private lateinit var newsSourceAdapter: NewsSourceAdapter
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private val sourceList = ArrayList<SourceEntity>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_news, container, false)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsSourceAdapter = NewsSourceAdapter(this, sourceList)
        recyclerView.adapter = newsSourceAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        observerNewsSource = Observer { newsSource ->
            sourceList.clear()
            if (newsSource?.data != null) {
                sourceList.addAll(newsSource.data)
                newsSourceAdapter.notifyDataSetChanged()
            }

        }

        observerNewsArticle = Observer { newsArticle ->
            if (newsArticle != null) {
                newsArticleAdapter = NewsArticleAdapter(newsArticle.articles!!)
                recyclerView.adapter = newsArticleAdapter
            }
        }


        newsViewModel.getNewsSource(null, null, null)
                .observe(this, observerNewsSource)
    }

    override fun invoke(source: SourceEntity) {
        newsViewModel.getNewsArticles(source.id!!, null)
                .observe(this, observerNewsArticle)
    }

    fun onBackPressed(): Boolean {
        return when {
            recyclerView.adapter is NewsArticleAdapter -> {
                recyclerView.adapter = newsSourceAdapter
                true
            }
            else -> false
        }
    }
}