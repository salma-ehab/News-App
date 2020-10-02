package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
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
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import com.example.newsappinkotlin.ui.adapter.Adapter
import kotlinx.android.synthetic.main.fragment_headlines.*


class HeadlinesFragment : Fragment() {
    lateinit var vm: NewsViewModel
    var currentPage = 1
    lateinit var Adapter: Adapter
    lateinit var llm: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fun onClickCard() {
            findNavController().navigate(
                R.id.action_headlinesFragment_to_itemDetailsFragment)
        }

        vm = ViewModelProvider(this).get(NewsViewModel::class.java)
        Adapter = Adapter(mutableListOf(),{ onClickCard()},requireActivity().applicationContext )
        llm = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv_news.adapter = Adapter
        rv_news.layoutManager = llm
        getPopularNews()
    }
    fun getPopularNews(){
        vm.fetchHeadlines(currentPage,::onError)
        vm.mutableNewsList.observe(viewLifecycleOwner, object : Observer<ArrayList<NewsModel>> {
            override fun onChanged(list: ArrayList<NewsModel>?) {
                Adapter.appendNews(list as ArrayList<NewsModel>)
                attachOnClickListener()
            }
        })
    }

    fun onError() {
        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show() }

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