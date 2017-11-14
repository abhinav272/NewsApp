package com.abhinav.newsapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.abhinav.newsapp.db.NewsDBHelper
import com.abhinav.newsapp.db.SourceEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by abhinav.sharma on 13/11/17.
 */
@RunWith(AndroidJUnit4::class)
class SourceDaoTest {

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

    private fun getNewsSourceDummyList() : List<SourceEntity> {
        var sourceEntity = SourceEntity()
        sourceEntity.name = "Google News"
        sourceEntity.category = "General"
        sourceEntity.description = "Some dummy description"
        sourceEntity.id = "googlenews"
        sourceEntity.language = "en"
        var newsSourceList = ArrayList<SourceEntity>()
        newsSourceList.add(sourceEntity)
        return newsSourceList
    }

    @Test
    fun testInsertAndRetrieve() {
        newsDBHelper.getSourceDao().insertSources(getNewsSourceDummyList())
        val allNewsSource = newsDBHelper.getSourceDao().getAllNewsSource()
        assert(getValue(allNewsSource).equals(getNewsSourceDummyList()))
    }

    @Test
    fun testDelete() {
        newsDBHelper.getSourceDao().deleteSource(getNewsSourceDummyList())
        val allNewsSource = newsDBHelper.getSourceDao().getAllNewsSource()
        assert(getValue(allNewsSource).isEmpty())
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