package com.example.newsappinkotlin.network

import android.content.Context
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
    // to be added to the view model

    fun fetchNews(page:Int=1, onSuccess:(newsList:MutableList<NewsModel>)->Unit, onError:()->Unit)
    {
        service.getAllNews(pageNumber = page).enqueue(object : Callback<NewsResponse>
        {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                onError.invoke()
            }

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful)
                {
                    if (response.body()!=null)
                    {
                        onSuccess.invoke(response.body()!!.articles)
                    }
                    else
                    {
                        onError.invoke()
                    }
                }
            }
        })
    }
}