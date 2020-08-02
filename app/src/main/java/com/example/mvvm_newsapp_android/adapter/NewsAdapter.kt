package com.example.mvvm_newsapp_android.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_newsapp_android.R
import com.example.mvvm_newsapp_android.activity.NewsDetailActivity
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_one_item.view.*
import kotlinx.android.synthetic.main.news_one_item.view.cardView
import kotlinx.android.synthetic.main.news_one_item.view.img_hot_news
import kotlinx.android.synthetic.main.news_one_item.view.tv_date
import kotlinx.android.synthetic.main.news_two_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = ArrayList<ArticlesModel>()

    fun setCategoryList(list: List<ArticlesModel>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int){
            val data = list[adapterPosition]
            itemView.apply {
                tv_title_category_news.text = data.title
                tv_sort_description.text = data.description
                tv_date_category.text = data.publishedAt
                tv_category_news.text = data.source!!.name
                if (!data.urlToImage.isNullOrBlank()) {
                    Picasso.get().load(data.urlToImage).into(img_category_news)
                }

                cardView_category.setOnClickListener {
                    cardView_category.context.startActivity(
                        Intent(cardView_category.context, NewsDetailActivity::class.java)
                            .putExtra("data", Gson().toJson(data, ArticlesModel::class.java))
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_two_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }
}