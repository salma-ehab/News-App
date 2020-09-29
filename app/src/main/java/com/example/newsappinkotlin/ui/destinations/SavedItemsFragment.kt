package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.adapter.SavedItemsAdapter
import kotlinx.android.synthetic.main.fragment_saved_items.*

class SavedItemsFragment : Fragment() {
    lateinit var dbNews: NewsDatabase
    lateinit var Adapter: SavedItemsAdapter
    lateinit var llm: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_saved_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fun onClickCard(new: NewsModel) {
            val extras = Bundle()
            extras.putString("title", new.title)
            extras.putString("releaseDate", new.publishedAt)
            extras.putString("name", new.source.name)
            extras.putString("description", new.desciption)
            extras.putString("image", new.urlToImage)
            extras.putString("link", new.url)
            findNavController().navigate(
                R.id.action_savedItemsFragment_to_itemDetailsFragment,
                extras
            )
        }

        dbNews = NewsDatabase.getSavedItems(requireActivity().applicationContext)
        Adapter = SavedItemsAdapter(
            dbNews.getNewsDao().getAllSavedNews() as MutableList<NewsModel>
        ) { e -> onClickCard(e) }
        llm = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv_saved.adapter = Adapter
        rv_saved.layoutManager = llm
    }

    fun onError() {
        Toast.makeText(getActivity(), "Failed to get news", Toast.LENGTH_SHORT).show()
    }
}

