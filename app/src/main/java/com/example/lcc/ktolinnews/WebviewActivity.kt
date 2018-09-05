package com.example.lcc.ktolinnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {
    lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        url=intent.getStringExtra("url")
        webViewId.settings.javaScriptEnabled=true
        webViewId.settings.setAppCacheEnabled(true)
        webViewId.settings.databaseEnabled=true
        webViewId.settings.domStorageEnabled=true
        webViewId.settings.cacheMode=WebSettings.LOAD_NO_CACHE
        webViewId.loadUrl(url)

    }
}
