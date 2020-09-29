package com.example.newsappinkotlin.model

import com.google.gson.annotations.SerializedName


data class NewsModel (
    @SerializedName("source") val source: SourceModel,
    @SerializedName("author") var author:String,
    @SerializedName("title") var title:String,
    @SerializedName("description") var desciption:String,
    @SerializedName("url") var url:String,
    @SerializedName("urlToImage") var urlToImage:String,
    @SerializedName("publishedAt") var publishedAt:String,
    @SerializedName("content") var content:String
)
