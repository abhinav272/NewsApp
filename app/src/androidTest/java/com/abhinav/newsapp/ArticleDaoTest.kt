package com.abhinav.newsapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.abhinav.newsapp.db.ArticleEntity
import com.abhinav.newsapp.db.NewsDBHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.*

/**
 * Created by abhinav.sharma on 14/11/17.
 */
@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {


    private lateinit var newsDBHelper: NewsDBHelper

    @Before
    fun initDatabase() {
        newsDBHelper = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                NewsDBHelper::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDatabase() {
        newsDBHelper.close()
    }

    fun getGoogleArticleList(): List<ArticleEntity> {
        var article = ArticleEntity("Article1", "googlenews", "Abhinav",
                "This is the dummy article description", "no url string",
                "no url for thumbnail", "noDate")

        var articleDummyList = ArrayList<ArticleEntity>()
        articleDummyList.add(article)
        return articleDummyList
    }

    fun getArticleDummyList(): List<ArticleEntity> {
        var article2 = ArticleEntity("Article2", "foxnews", "Abhinav",
                "This is the dummy article description", "no url string",
                "no url for thumbnail", "noDate")

        var articleDummyList = ArrayList<ArticleEntity>()
        articleDummyList.addAll(getGoogleArticleList())
        articleDummyList.add(article2)
        return articleDummyList
    }

    @Test
    fun testInsertAndRetrieve(){
        newsDBHelper.getArticleDao().addArticle(getArticleDummyList())
        assertTrue(getArticleDummyList().containsAll(getValue(newsDBHelper.getArticleDao().getArticlesBySource("googlenews"))))
        assertFalse(getValue(newsDBHelper.getArticleDao().getArticlesBySource("bbcnews")).equals(getArticleDummyList()))
    }

    @Test
    fun testDelete() {
        newsDBHelper.getArticleDao().addArticle(getArticleDummyList())
        newsDBHelper.getArticleDao().deleteArticle(getGoogleArticleList())
        assertTrue(getValue(newsDBHelper.getArticleDao().getArticlesBySource("googlenews")).isEmpty())
        assertTrue(getValue(newsDBHelper.getArticleDao().getArticlesBySource("foxnews")).isNotEmpty())
    }

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}