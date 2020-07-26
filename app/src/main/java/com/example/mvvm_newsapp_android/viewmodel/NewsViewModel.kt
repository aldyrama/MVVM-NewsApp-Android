package com.example.mvvm_newsapp_android.viewmodel

import Resource
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_newsapp_android.base.BaseView
import com.example.mvvm_newsapp_android.model.ArticlesModel
import com.example.mvvm_newsapp_android.model.response.NewsResponse
import com.example.mvvm_newsapp_android.service.ApiUtils
import com.example.mvvm_newsapp_android.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

class NewsViewModel : ViewModel(), LifecycleObserver {

    val resource = MutableLiveData<Resource<Any>>()
    val dataList = MutableLiveData<ArrayList<ArticlesModel>>()

    init {
        dataList.value = ArrayList()
    }

    fun onRefreshTopHeadlines(country: String?){
        dataList.value?.clear()
        getTopHeadlines(country = country)
    }

    fun getTopHeadlines(country: String?){
        resource.postValue(Resource.loading())
        val call = ApiUtils.getInterface(Constant.baseUrl).getTopHeadlines(
            country = country,
            apiKey = Constant.apiKey,
            pageSize = 8
        )

        call.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                resource.postValue(Resource.error(t))
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val list = ArrayList<ArticlesModel>()
                    dataList.value?.clear()
                    dataList.value?.let { list.addAll(it)}
                    val newList = response.body()?.articles
                    if (!newList.isNullOrEmpty()) list.addAll(newList)

                    dataList.postValue(list)
                    resource.postValue(Resource.success(response.body()))
                }else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    resource.postValue(Resource.error(Exception(errorBody)))
                }
            }

        })
    }
}