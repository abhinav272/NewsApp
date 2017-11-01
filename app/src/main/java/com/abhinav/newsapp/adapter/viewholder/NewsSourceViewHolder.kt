package com.abhinav.newsapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_news_source_single.view.*

/**
 * Created by abhinav.sharma on 01/11/17.
 */
class NewsSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val tvSourceName = itemView.tv_source_name
    val tvSourceCategory = itemView.tv_category
    val tvSourceDescription = itemView.tv_source_description
}