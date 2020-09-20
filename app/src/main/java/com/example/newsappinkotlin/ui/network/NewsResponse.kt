package com.example.newsappinkotlin.ui.network

import com.example.newsappinkotlin.ui.model.NewsModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("totalResults") var totalResults:Int,
    @SerializedName("articles") var articles:MutableList<NewsModel>
)
