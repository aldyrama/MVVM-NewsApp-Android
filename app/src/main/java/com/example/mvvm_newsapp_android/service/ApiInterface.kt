package com.example.mvvm_newsapp_android.service

import com.example.mvvm_newsapp_android.model.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageSize: Int?
    ): Call<NewsResponse>

}