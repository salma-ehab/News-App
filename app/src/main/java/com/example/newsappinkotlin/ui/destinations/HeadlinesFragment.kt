package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel

import com.example.newsappinkotlin.network.NewsClient
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import com.example.newsappinkotlin.ui.adapter.HeadlinesAdapter
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment() {
    lateinit var vm: NewsViewModel
    lateinit var dbNews: NewsDatabase
    var currentPage = 1
    lateinit var Adapter: HeadlinesAdapter
    lateinit var llm: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fun onClickCard(new:NewsModel) {
            val extras = Bundle()
            extras.putString("title", new.title)
            extras.putString("releaseDate", new.publishedAt)
            extras.putString("name", new.source.name)
            extras.putString("description", new.desciption)
            extras.putString("image", new.urlToImage)
            extras.putString("link", new.url)
            findNavController().navigate(
                R.id.action_headlinesFragment_to_itemDetailsFragment,
                extras
            )
        }
        fun onSave(new:NewsModel)
        {
            dbNews.getNewsDao().insertNews(new)
            Toast.makeText(getActivity(), "News Saved", Toast.LENGTH_SHORT).show()
        }

        dbNews=NewsDatabase.getSavedItems(requireActivity().applicationContext)
        vm = ViewModelProvider(this).get(NewsViewModel::class.java)
        Adapter = HeadlinesAdapter(mutableListOf(),{e-> onClickCard(e)},{e-> onSave(e)})
        llm = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv_news.adapter = Adapter
        rv_news.layoutManager = llm
        getPopularNews()
    }
    fun getPopularNews(){
        vm.fetchHeadlines(currentPage)
        vm.mutableNewsList.observe(viewLifecycleOwner, object : Observer<ArrayList<NewsModel>> {
            override fun onChanged(list: ArrayList<NewsModel>?) {
                Adapter.appendNews(list as ArrayList<NewsModel>)
                attachOnClickListener()
            }
        })
    }

    // These functions are to be modified on adding the view model
    fun onError() {
        Toast.makeText(getActivity(), "Failed to get news", Toast.LENGTH_SHORT).show()
    }

    fun attachOnClickListener() {

        rv_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemsCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if (firstVisibleItem + visibleItemsCount >= totalItems / 2) {
                    rv_news.removeOnScrollListener(this)
                    currentPage++
                    getPopularNews()
                }
            }
        })

    }
}