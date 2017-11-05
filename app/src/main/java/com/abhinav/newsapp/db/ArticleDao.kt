package com.abhinav.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM t_article WHERE source = :source")
    fun getArticlesBySource(source: String): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(vararg articles: ArticleEntity)

    @Delete
    fun deleteArticle(vararg article: ArticleEntity)

}