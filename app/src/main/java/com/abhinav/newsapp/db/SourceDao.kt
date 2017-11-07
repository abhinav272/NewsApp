package com.abhinav.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.abhinav.newsapp.ui.NewsConstants
import com.abhinav.newsapp.ui.model.Source

/**
 * Created by abhinav.sharma on 04/11/17.
 */
@Dao
interface SourceDao {

    @Query("SELECT * FROM " + NewsConstants.T_SOURCE)
    fun getAllNewsSource(): LiveData<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(vararg source: SourceEntity)

    @Delete
    fun deleteSource(vararg source: SourceEntity)

//    fun insertSources(source: List<Source>) {
//
//        insertSources(*sourceEntityArray.toTypedArray())
//    }
}