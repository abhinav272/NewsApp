package com.abhinav.newsapp.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhinav.newsapp.R
import com.abhinav.newsapp.adapter.NewsSourceAdapter
import com.abhinav.newsapp.ui.model.Source
import com.abhinav.newsapp.ui.model.SourceResponse
import com.abhinav.newsapp.ui.viewmodel.NewsViewModel

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsFragment : LifecycleFragment() {

    private var recyclerView: RecyclerView? = null
    private var newsViewModel: NewsViewModel? = null
    private var observerNewsSource: Observer<SourceResponse>? = null
    private var newsSourceAdapter: NewsSourceAdapter? = null
    private var sourceList  = ArrayList<Source>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_news, container, false)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view?.findViewById(R.id.recyclerView)
        newsSourceAdapter = NewsSourceAdapter(sourceList)
        recyclerView?.adapter = newsSourceAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        observerNewsSource = Observer { newsSource ->
            sourceList.clear()
            if (newsSource?.sources != null) {
                sourceList.addAll(newsSource.sources)
                newsSourceAdapter!!.notifyDataSetChanged()
            }

        }
        newsViewModel?.getNewsSource(null, null, null)
                ?.observe(this, observerNewsSource)
    }
}