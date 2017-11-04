package com.abhinav.newsapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.abhinav.newsapp.ui.NewsConstants

/**
 * Created by abhinav.sharma on 04/11/17.
 */

@Entity(tableName = NewsConstants.T_SOURCE)
class SourceEntity(
        @PrimaryKey()
        val id: String?,
        val name: String?,
        val description: String?,
        val url: String?,
        val category: String?,
        val language: String?,
        val country: String?,
        val sortBysAvailable: List<String?>?
)