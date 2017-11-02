package com.abhinav.newsapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.abhinav.newsapp.R
import com.abhinav.newsapp.adapter.viewholder.NewsSourceViewHolder
import com.abhinav.newsapp.inflate
import com.abhinav.newsapp.ui.model.Source

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsSourceAdapter(private val listener: (Source) -> Unit, private val sourceList: List<Source>) : RecyclerView.Adapter<NewsSourceViewHolder>() {


    override fun getItemCount(): Int {
        return sourceList.size
    }

    private fun getItem(position: Int): Source {
        return sourceList[position]
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) =
            holder.bind(getItem(position), listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder =
            NewsSourceViewHolder(parent.inflate(R.layout.layout_news_source_single))
}