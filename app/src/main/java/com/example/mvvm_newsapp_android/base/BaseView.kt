package com.example.mvvm_newsapp_android.base

interface BaseView {
    fun refresh()
    fun reload()
    fun onFailure(t: Throwable)
    fun onLoading()
    fun onSuccess()
}