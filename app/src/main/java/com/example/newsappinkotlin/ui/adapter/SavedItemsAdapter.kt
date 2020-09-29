package com.example.newsappinkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.model.NewsModel
import kotlinx.android.synthetic.main.news_card.view.*

class SavedItemsAdapter (var News:MutableList<NewsModel>, var listener:(NewsModel)->Unit): RecyclerView.Adapter<SavedItemsAdapter.Viewholder>() {
    class Viewholder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onbind (NewsStory : NewsModel){
            itemView.headlineTitle.text = NewsStory.title
            itemView.website.text = NewsStory.source.name
            Glide.with(itemView)
                .load(NewsStory.urlToImage)
                .transform(CenterCrop())
                .into(itemView.newsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedItemsAdapter.Viewholder =
        SavedItemsAdapter.Viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var Newsitem = News!![position]
        holder.onbind(Newsitem)
        holder.itemView.setOnClickListener{listener(Newsitem)}
    }

    override fun getItemCount(): Int = News!!.size


}
