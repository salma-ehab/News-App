package com.example.newsappinkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.destinations.HeadlinesFragment
import kotlinx.android.synthetic.main.news_card.view.*

class HeadlinesAdapter(var News:MutableList<NewsModel>, context: HeadlinesFragment): RecyclerView.Adapter<HeadlinesAdapter.Viewholder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesAdapter.Viewholder =
        HeadlinesAdapter.Viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var Newsitem = News!![position]
        holder.onbind(Newsitem)
    }

    override fun getItemCount(): Int = News!!.size

    fun appendMovies(updateNews: List<NewsModel>){
        this.News!!.addAll(updateNews)
        notifyItemRangeInserted(
            this.News!!.size,
            News!!.size - 1
        )
    }
}