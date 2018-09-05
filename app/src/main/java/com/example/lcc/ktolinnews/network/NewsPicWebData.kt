package com.example.lcc.ktolinnews.network

import android.content.Context
import android.util.Log
import com.example.lcc.ktolinnews.bean.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class NewsPicWebData : WebData<NewsPicBean> {
    lateinit var mContext: Context
    lateinit var authorImg: String
    lateinit var authorName: String
    lateinit var authorLink: String
    val dataList = ArrayList<NewsPicBean>()
    override fun getData(url: String): ArrayList<NewsPicBean> {
        try {
            //从一个URL加载一个Document对象。
            val doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            //作者的信息
            val element: Elements = doc.select("div.author")
            for (index in element.indices) {
                authorLink = "https://www.qiushibaike.com" + element[index].select("a").attr("href")
                authorImg = "https:" + element[index].select("img").attr("src")
                authorName = element[index].select("img").attr("alt")
                var bean = NewsPicBean("", "", "", "", "", "", "","")
                dataList.add(bean)
                dataList.get(index).userImg = authorImg
                dataList.get(index).userLink = authorLink
                dataList.get(index).userName = authorName
            }
            //内容信息
            val element1: Elements = doc.select("div.content")
            for (index in element1.indices) {
                dataList[index].content = element1[index].text()
            }
            //内容图片信息
            val element3: Elements = doc.select("div.thumb")
            for (index in element1.indices) {
                //三种方法都可以
//                dataList[index].contentPic = "https:" + element3[index].select("img").get(0).attr("src")
//                dataList[index].contentPic = "https:" + element3[index].select("a").select("img").attr("src")
                dataList[index].contentPic = "https:" + element3[index].select("img").attr("src")
            }

            //评论和喜欢的数据
            val element2: Elements = doc.select("div.stats")
            for (index in element2.indices) {
                dataList[index].funNum = element2[index].select("span.stats-vote").text()
                dataList[index].commentNum = element2[index].select("a.qiushi_comments").text()
            }
            Log.i("mytag", dataList.toString())
        } catch (e: Exception) {
            Log.i("mytag", e.toString())
        }
        return dataList
    }

    fun getContent(context: Context) {
        mContext = context
    }
}