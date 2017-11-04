package com.abhinav.newsapp.ui

import android.os.Bundle
import com.abhinav.newsapp.R

/**
 * Created by abhinav.sharma on 31/10/17.
 */
class HomeActivity : BaseActivity() {

    private var newsFragment : NewsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showSourceFragment()
    }

    private fun showSourceFragment() {
        newsFragment = NewsFragment()
        addFragment(newsFragment!!, R.id.container, "NewsFragment")
    }

    override fun onBackPressed() {
        if (!newsFragment?.onBackPressed()!!)
            super.onBackPressed()
    }
}