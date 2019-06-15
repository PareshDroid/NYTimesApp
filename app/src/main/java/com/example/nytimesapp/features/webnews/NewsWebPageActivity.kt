package com.example.nytimesapp.features.webnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nytimesapp.R
import kotlinx.android.synthetic.main.activity_news_web_page.*

class NewsWebPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_web_page)

        webView.loadUrl(intent.getStringExtra("NewsUrl"))

    }
}