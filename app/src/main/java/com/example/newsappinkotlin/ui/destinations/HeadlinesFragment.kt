package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.ui.model.NewsModel
import com.example.newsappinkotlin.ui.network.NewsClient
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment() {
    var currentPage=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       getNews()
    }
    fun getNews()
    {
        Log.d("AllNews","here")
        NewsClient.fetchHeadlines(currentPage,::onSuccess,::onError)
    }
// These functions are to be modified on adding the view model
    private fun onError()
    {
        Toast.makeText(getActivity(),"Failed to get news", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(list: MutableList<NewsModel>)
    {
       //waiting  to append data from adapter
    }
}

