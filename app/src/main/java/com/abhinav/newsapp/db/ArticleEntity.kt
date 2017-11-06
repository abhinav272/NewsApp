package com.abhinav.newsapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.abhinav.newsapp.ui.NewsConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */

@Entity(tableName = NewsConstants.T_ARTICLE)
data class ArticleEntity(
        @PrimaryKey
        val title: String?,
        val source: String?,
        val author: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?
)