package com.example.newsappinkotlin.ui.destinations

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var dbNews: NewsDatabase = NewsDatabase.getSavedItems(requireActivity().applicationContext)
        var nModel:NewsModel=NewsViewModel.currentNews!!
        super.onViewCreated(view, savedInstanceState)
        articleTitle2.text= nModel.title
        date_time.text= nModel.publishedAt
        des.text= nModel.desciption
        website2.text= nModel.source.name
        var image= nModel.urlToImage
        Glide.with(this)
            .load(image)
            .transform(CenterCrop())
            .into(imageView3)

        readFull.setOnClickListener{
            findNavController().navigate(R.id.action_itemDetailsFragment_to_webFragment3) }

        if (dbNews.getNewsDao().getCount(nModel.title )==1 )
            bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))

        bookmark_icon.setOnClickListener {
            if (dbNews.getNewsDao().getCount(nModel.title ) ==0 ) {
                dbNews.getNewsDao().insertNews(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))
                Toast.makeText(context, "News Saved", Toast.LENGTH_SHORT).show() }

            else {
                dbNews.getNewsDao().delete(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#484a49"))
                Toast.makeText(context, "News Unsaved", Toast.LENGTH_SHORT).show() }
        }
    }
}