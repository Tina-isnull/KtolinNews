package com.example.lcc.ktolinnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.lcc.ktolinnews.adapter.PullAdapter
import com.example.lcc.ktolinnews.bean.NewsBean
import com.example.lcc.ktolinnews.myview.PullToRefreshLayout
import com.example.lcc.ktolinnews.myview.PullToRefreshRecycler
import com.example.lcc.ktolinnews.myview.RefreshSetting
import com.example.lcc.ktolinnews.network.NewsWebData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginActivity : AppCompatActivity() {
    val newsWeb = NewsWebData()
    var mData = ArrayList<NewsBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}
