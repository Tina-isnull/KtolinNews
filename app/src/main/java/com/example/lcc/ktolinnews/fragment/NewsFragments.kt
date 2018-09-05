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
import com.example.lcc.ktolinnews.myview.PullToRefreshLayout.SUCCEED
import com.example.lcc.ktolinnews.myview.PullToRefreshRecycler
import com.example.lcc.ktolinnews.myview.RefreshSetting
import com.example.lcc.ktolinnews.network.NewsWebData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NewsFragments : Fragment() {
    //    lateinit var listener: notifyActivity
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
//        //缓存数据,赋值数据(因为字符串不是json，所以没办法转。)
//        mData.clear()
//        val data = PrefUtils.getString(this.context!!, "newsdata", "")
//        if (!data.equals("")) {
//            try {
//                val array = JSONArray(data)
//                for (index in 0..(array.length() - 1)) {
//                    val jsonObject = array.getJSONObject(index)
//                    val userLink = jsonObject.getString("userLink")
//                    val userName = jsonObject.getString("userName")
//                    val userImg = jsonObject.getString("userImg")
//                    val content = jsonObject.getString("content")
//                    val commentNum = jsonObject.getString("commentNum")
//                    val funNum = jsonObject.getString("funNum")
//                    mData.add(NewsBean(userLink, userName, userImg, content, commentNum, funNum))
//                    recycler.adapter.notifyDataSetChanged()
//                }
//            } catch (e: Exception) {
//                Log.d("错误提示", e.toString())
//            }
//        }

        return view
    }

    //    fun setNotifyActivityListener(listener: notifyActivity) {
//        this.listener = listener
//    }
//
//    interface notifyActivity {
//        fun method1()
//    }
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
        val path = "http://www.qiushibaike.com/8hr/page/" + page + "/"
        doAsync {
            newsData = newsWeb.getData(path)

            uiThread {
                //    mData.clear()
//                    mData.addAll(newsData)
//                    PrefUtils.putString(this@NewsFragments.context!!, "newsdata", mData.toString())
                if (page.equals("1")) {
                    mData.clear()
                    mData.addAll(newsData)
                } else {
                    mData.addAll(newsData)
                }
                if (ispull) {
                    layout.refreshFinish(SUCCEED)
                }
                recycler.adapter.notifyDataSetChanged()

            }
        }

    }
}