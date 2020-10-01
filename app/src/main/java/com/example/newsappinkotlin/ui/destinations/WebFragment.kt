package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var nModel: NewsModel = NewsViewModel.currentNews!!
        super.onViewCreated(view, savedInstanceState)
        wb.settings.javaScriptEnabled = true
        wb.webViewClient = WebViewClient()
        wb.loadUrl(nModel.url.toString())
    }
}