package com.abhinav.newsapp.ui

import android.os.Bundle
import com.abhinav.newsapp.R

/**
 * Created by abhinav.sharma on 31/10/17.
 */
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showSourceFragment()
    }

    fun showSourceFragment() {
        addFragment(NewsFragment(), R.id.container, "NewsFragment")
    }
}