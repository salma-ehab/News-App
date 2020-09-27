package com.example.newsappinkotlin.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var vm : NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        vm = ViewModelProvider (this).get(NewsViewModel :: class.java )

        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment_container))

        button.setOnClickListener{
            vm.getNewsSource()
            vm.NewsSourceMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsSourse(t)
                    }
                }
            } )
            vm.getNewsAuthor()
            vm.NewsAuthorMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsAuthor(t)
                    }
                }
            } )
            vm.getNewsTitle()
            vm.NewsTitleMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsTitle(t)
                    }
                }
            } )
            vm.getNewsDesciption()
            vm.NewsDesciptionMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsDesciption(t)
                    }
                }
            } )
            vm.getNewsUrl()
            vm.NewsUrlMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsUrl(t)
                    }
                }
            } )
            vm.getNewsUrltoimage()
            vm.NewsUrltoimageMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsUrltoimage(t)
                    }
                }
            } )
            vm.getNewspublishedat()
            vm.NewsPublishedatMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsPuplishedat(t)
                    }
                }
            } )
            vm.getNewsContent()
            vm.NewsContentMutableLiveData.observe(this , object : Observer<String> {
                override fun onChanged(t: String?) {
                    if (t != null){
                        displayNewsContent(t)
                    }
                }
            } )
        }
    }
    private fun displayNewsSourse(newsSource:String){
        textView.text = newsSource
    }
    private fun displayNewsAuthor(newsAuthor:String){
        textView.text = newsAuthor
    }
    private fun displayNewsTitle(newstitle:String){
        textView.text = newstitle
    }
    private fun displayNewsDesciption(newsDesciption:String){
        textView.text = newsDesciption
    }
    private fun displayNewsUrl(newsUrl:String){
        textView.text = newsUrl
    }
    private fun displayNewsUrltoimage(newsUrltoimage:String){
        textView.text = newsUrltoimage
    }
    private fun displayNewsPuplishedat(newsPuplishedat:String){
        textView.text = newsPuplishedat
    }
    private fun displayNewsContent(newsContent:String){
        textView.text = newsContent
    }
}