package com.example.lcc.ktolinnews.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lcc.ktolinnews.R
import com.example.lcc.ktolinnews.adapter.PullAdapter
import com.example.lcc.ktolinnews.bean.NewsBean
import com.example.lcc.ktolinnews.myview.PullToRefreshLayout
import com.example.lcc.ktolinnews.myview.PullToRefreshRecycler
import com.example.lcc.ktolinnews.myview.RefreshSetting
import com.example.lcc.ktolinnews.network.NewsWebData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CrossFragment : Fragment() {
    val newsWeb = NewsWebData()
    var mData = ArrayList<NewsBean>()
    var newsData = ArrayList<NewsBean>()
    lateinit var recycler: PullToRefreshRecycler
    lateinit var layout: PullToRefreshLayout
    var p = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_new, container, false)
        layout = view.findViewById(R.id.news_fragment_layout)
        val listener = MyListener()
        layout.setOnPullListener(listener)
        recycler = RefreshSetting.setListViewRefresh(layout, this.context, listener) as PullToRefreshRecycler
        recycler.adapter = PullAdapter(this.context!!, mData)
        recycler.layoutManager = LinearLayoutManager(this.context)
        newsWeb.getContent(this.context!!)
        getdata(p.toString(), false)
        return view
    }

    //添加监听的事件
    inner class MyListener : PullToRefreshLayout.OnPullListener {
        override fun onRefresh(pullToRefreshLayout: PullToRefreshLayout?) {
            p=1
            getdata(p.toString(), true)
        }

        override fun onLoadMore(pullToRefreshLayout: PullToRefreshLayout?) {
            p++
            getdata(p.toString(), true)
        }
    }

    fun getdata(page: String, ispull: Boolean) {
        //请求数据
        val path = "https://www.qiushibaike.com/history/page/" + page + "/"
        doAsync {
            newsData = newsWeb.getData(path)

            uiThread {
                if (page.equals("1")) {
                    mData.clear()
                    mData.addAll(newsData)
                } else {
                    mData.addAll(newsData)
                }
                if (ispull) {
                    layout.refreshFinish(PullToRefreshLayout.SUCCEED)
                }
                recycler.adapter.notifyDataSetChanged()

            }
        }

    }
}