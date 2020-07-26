package com.example.mvvm_newsapp_android.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.news_one_item.view.*


class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        init()
    }

    fun init(){
        var bundle :Bundle ?=intent.extras
        var data = bundle!!.getString("data")
        val newDetail: ArticlesModel = Gson().fromJson(data, ArticlesModel::class.java)

        tv_date_detail.text = newDetail.publishedAt
        tv_title_detail.text = newDetail.title
        tv_detail_description.text = newDetail.content
        if (!newDetail.urlToImage.isNullOrBlank()) {
            Picasso.get().load(newDetail.urlToImage).into(iv_news_detail)
        }
    }
}