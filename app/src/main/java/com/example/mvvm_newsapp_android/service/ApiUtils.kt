package com.example.mvvm_newsapp_android.service

import com.ashokvarma.gander.BuildConfig
import com.ashokvarma.gander.GanderInterceptor
import com.example.mvvm_newsapp_android.App
import com.example.mvvm_newsapp_android.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiUtils {

    private lateinit var retrofit: Retrofit

    fun getInterface(baseUrl: String? = null): ApiInterface {
        return getRetrofit(baseUrl).create(ApiInterface::class.java)
    }

    private fun getRetrofit(baseUrl: String? = null): Retrofit {
        if (!ApiUtils::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl ?: Constant.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }
        okHttpClientBuilder.addInterceptor(
            GanderInterceptor(App.context)
            .showNotification(true))

        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }
}