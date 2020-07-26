package com.example.mvvm_newsapp_android.base

import androidx.fragment.app.Fragment

interface BaseFragmentView<PV> {
    fun refresh()
    fun reload()
    fun setParentView(view : PV)
    fun reattach()
}