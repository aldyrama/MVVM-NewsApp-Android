package com.example.mvvm_newsapp_android.activity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class PopularActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular)
        init()
    }

    private fun init(){
        toolbar.title = "Popular News"
        toolbar.background = ColorDrawable(resources.getColor(R.color.blueGrey700))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        setSupportActionBar(toolbar)
    }
}