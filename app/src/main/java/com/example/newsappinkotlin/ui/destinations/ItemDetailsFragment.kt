package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.ui.adapter.HeadlinesAdapter
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.news_card.view.*

class ItemDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleTitle2.text= arguments?.getString("title")
        date_time.text= arguments?.getString("releaseDate")
        des.text= arguments?.getString("description")
        website2.text= arguments?.getString("name")
        var image= arguments?.getString("image")
        Glide.with(this)
            .load(image)
            .transform(CenterCrop())
            .into(imageView3)

        readFull.setOnClickListener{
            val extras=Bundle()
            extras.putString("link",arguments?.getString("link"))
            findNavController().navigate(R.id.action_itemDetailsFragment_to_webFragment3,extras)
        }
    }
}