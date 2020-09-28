package com.example.newsappinkotlin.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.network.NewsClient
import com.example.newsappinkotlin.network.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var mutableNewsList : MutableLiveData<ArrayList<NewsModel>> = MutableLiveData()

    fun fetchHeadlines(page:Int=1)
    {
        NewsClient.service.getAllHeadlines(pageNumber = page).enqueue(object : Callback<NewsResponse>
        {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
              //  onError.invoke()
            }

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful)
                {
                    if (response.body()!=null)
                    {
                        mutableNewsList.value = response?.body()!!.articles as ArrayList<NewsModel>
                    }
                    else
                    {
                     //   onError.invoke()
                    }
                }
            }
        })
    }

}