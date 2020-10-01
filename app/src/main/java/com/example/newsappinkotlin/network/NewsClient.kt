package com.example.newsappinkotlin.network

import android.util.Log
import com.example.newsappinkotlin.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsClient {
    val BASE_URL="https://newsapi.org/"
    val service: APIServices
    init {
        val retrofit=
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        service =retrofit.create(APIServices::class.java)
    }
}