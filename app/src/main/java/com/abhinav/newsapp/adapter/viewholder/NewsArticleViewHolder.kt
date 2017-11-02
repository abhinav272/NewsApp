package com.abhinav.newsapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.abhinav.newsapp.load
import com.abhinav.newsapp.ui.model.Article
import kotlinx.android.synthetic.main.layout_news_article_single.view.*

/**
 * Created by abhinav.sharma on 02/11/17.
 */
class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) = with(itemView){
        tv_author.text = article.author
        tv_publish_date.text = article.publishedAt
        tv_title.text = article.title
        iv_article_image.load(article.urlToImage!!) { requestCreator -> requestCreator.fit().centerCrop() }
//        Picasso.with(itemView.context).load(article.urlToImage).fit().centerCrop().into(iv_article_image)
    }
}