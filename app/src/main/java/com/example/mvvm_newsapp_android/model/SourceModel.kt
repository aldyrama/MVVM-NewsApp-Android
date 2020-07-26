package com.example.mvvm_newsapp_android.model

import com.google.gson.annotations.SerializedName

class SourceModel {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null
}