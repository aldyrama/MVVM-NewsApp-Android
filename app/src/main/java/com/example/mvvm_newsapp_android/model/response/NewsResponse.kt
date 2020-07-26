package com.example.mvvm_newsapp_android.model.response

import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.google.gson.annotations.SerializedName

class NewsResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = null

    @SerializedName("articles")
    var articles: List<ArticlesModel>? = null
}