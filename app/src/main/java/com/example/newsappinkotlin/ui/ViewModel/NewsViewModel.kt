package com.example.newsappinkotlin.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.newsappinkotlin.model.NewsModel

class NewsViewModel {
    var NewsSourceMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsAuthorMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsTitleMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsDesciptionMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsUrlMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsUrltoimageMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsPublishedatMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    var NewsContentMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()

    private fun getNews(): NewsModel = NewsModel(
        source = "Al-Ahram",
        author = "Frouk",
        title = "EgyptAir to resume flights to five destinations starting 1 October",
        desciption = "Egypt will resume direct flights to.............",
        url = "http://english.ahram.org.eg",
        urlToImage = "http://english.ahram.org.eg",
        publishedAt = "27-9-2020",
        content = "Egypt will resume direct flights to Oman, Jordan, Rwanda,"
    )

    fun getNewsSource() {
        var data = getNews().source
        NewsSourceMutableLiveData.value = data
    }
    fun getNewsAuthor() {
        var data = getNews().source
        NewsAuthorMutableLiveData.value = data
    }
    fun getNewsTitle() {
        var data = getNews().title
        NewsTitleMutableLiveData.value = data
    }
    fun getNewsDesciption() {
        var data = getNews().source
        NewsDesciptionMutableLiveData.value = data
    }
    fun getNewsUrl() {
        var data = getNews().source
        NewsUrlMutableLiveData.value = data
    }
    fun getNewsUrltoimage() {
        var data = getNews().source
        NewsUrltoimageMutableLiveData.value = data
    }
    fun getNewspublishedat() {
        var data = getNews().source
        NewsPublishedatMutableLiveData.value = data
    }
    fun getNewsContent() {
        var data = getNews().source
        NewsContentMutableLiveData.value = data
    }
}