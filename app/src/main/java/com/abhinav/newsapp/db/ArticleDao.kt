package com.abhinav.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface ArticleDao {

//    Using arg0 is way around, some issue with kotlin gradle plugin
//    expecting fix in further releases
    @Query("SELECT * FROM t_article WHERE source = :arg0")
    fun getArticlesBySource(source: String): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(vararg articles: ArticleEntity)

    @Delete
    fun deleteArticle(vararg article: ArticleEntity)

}