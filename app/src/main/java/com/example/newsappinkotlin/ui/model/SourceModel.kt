package com.example.newsappinkotlin.ui.model

import com.google.gson.annotations.SerializedName

data class SourceModel (
    @SerializedName("id") var id:String,
    @SerializedName("name") var name:String
)
