package com.example.newsappinkotlin.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("v2/top-headlines")

    fun getAllHeadlines(@Query("apiKey") apiKey:String="7b68ee98ace74ebaa81eac2d8ee911ec",@Query("page") pageNumber:Int=1
                   ,@Query("country") country:String="us")
            :Call<NewsResponse>
}