package com.example.mvvm_newsapp_android.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_newsapp_android.App.Companion.context
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.activity.NewsDetailActivity
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_one_item.view.*

class HotNewsAdapter : RecyclerView.Adapter<HotNewsAdapter.ViewHolder>() {

    private val list = ArrayList<ArticlesModel>()

    fun setList(list: List<ArticlesModel>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int){
            val data = list[adapterPosition]
            itemView.apply {
                tv_title_hotnews.text = data.title
                tv_category.text = data.source!!.name
                tv_date.text = data.publishedAt
                if (!data.urlToImage.isNullOrBlank()) {
                    Picasso.get().load(data.urlToImage).into(img_hot_news)
                }

                cardView.setOnClickListener {
                    cardView.context.startActivity(
                        Intent(cardView.context, NewsDetailActivity::class.java)
                            .putExtra("data", Gson().toJson(data, ArticlesModel::class.java))
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_one_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }
}