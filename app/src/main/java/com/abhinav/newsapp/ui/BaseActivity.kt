package com.abhinav.newsapp.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.LifecycleFragment

/**
 * Created by abhinav.sharma on 01/11/17.
 */
open class BaseActivity : LifecycleActivity() {

    fun addFragment(fragment: LifecycleFragment, layoutResId: Int, tag : String) {
        supportFragmentManager.beginTransaction()
                .add(layoutResId, fragment, tag)
                .commit()
    }

    fun addFragmentWithBackStack(fragment: LifecycleFragment, layoutResId: Int, tag: String){
        supportFragmentManager.beginTransaction()
                .add(layoutResId, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }
}