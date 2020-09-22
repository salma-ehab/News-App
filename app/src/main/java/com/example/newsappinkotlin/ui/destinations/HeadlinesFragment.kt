package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.network.APIServices
import com.example.newsappinkotlin.ui.destinations.HeadlinesFragment

import com.example.newsappinkotlin.network.NewsClient
import com.example.newsappinkotlin.ui.adapter.HeadlinesAdapter
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment() {
    var currentPage = 1
    lateinit var Adapter: HeadlinesAdapter
    lateinit var llm: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Adapter = HeadlinesAdapter(mutableListOf(), this)
        llm = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv_news.adapter = Adapter
        rv_news.layoutManager = llm
        getNews()
    }

    fun getNews() {
        Log.d("AllNews", "here")
        NewsClient.fetchHeadlines(currentPage, ::onSuccess, ::onError)
    }

    // These functions are to be modified on adding the view model
    fun onError() {
        Toast.makeText(getActivity(), "Failed to get news", Toast.LENGTH_SHORT).show()
    }

    fun onSuccess(list: MutableList<NewsModel>) {
        Adapter.appendMovies(list)

        rv_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemsCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if (firstVisibleItem + visibleItemsCount >= totalItems / 2) {
                    rv_news.removeOnScrollListener(this)
                    currentPage++
                    getNews()
                }
            }
        })

    }
}


