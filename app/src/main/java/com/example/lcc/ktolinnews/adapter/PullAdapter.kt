package com.example.lcc.ktolinnews.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lcc.ktolinnews.R
import com.example.lcc.ktolinnews.WebviewActivity
import com.example.lcc.ktolinnews.bean.NewsBean
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso

class PullAdapter : RecyclerView.Adapter<PullAdapter.MyViewHolder> {
    var mContext: Context? = null
    var mdata: List<NewsBean>? = null
    val transformation = RoundedTransformationBuilder()
            .borderColor(Color.TRANSPARENT)
            .borderWidthDp(0F)
            .oval(true)
            .build()

    constructor(context: Context, data: List<NewsBean>) {
        mContext = context
        mdata = data

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_item_news, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mdata?.size as Int
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        if (mdata?.get(position)?.userImg != null) {
            Picasso.with(mContext).load(mdata?.get(position)?.userImg).transform(transformation).placeholder(R.mipmap.ic_launcher).into(holder?.authorImg)
        }
        holder?.authorName?.setText(mdata?.get(position)?.userName)
        holder?.content?.setText(mdata?.get(position)?.content)
        holder?.commentNum?.setText(mdata?.get(position)?.commentNum)
        holder?.funNum?.setText(mdata?.get(position)?.funNum)
        holder?.content?.setOnClickListener {
          val intent:Intent= Intent(mContext, WebviewActivity::class.java)
            intent.putExtra("url",mdata?.get(position)?.contentUrl)
            mContext?.startActivity(intent)
        }
        holder?.authorImg?.setOnClickListener {
            val intent:Intent= Intent(mContext, WebviewActivity::class.java)
            intent.putExtra("url",mdata?.get(position)?.userLink)
            mContext?.startActivity(intent)
        }
    }

    class MyViewHolder : RecyclerView.ViewHolder {
        val authorImg: ImageView
        val authorName: TextView
        val content: TextView
        val commentNum: TextView
        val funNum: TextView

        constructor(itemView: View) : super(itemView) {
            authorImg = itemView.findViewById(R.id.news_fragment_user_img)
            authorName = itemView.findViewById(R.id.news_fragment_user_text)
            content = itemView.findViewById(R.id.news_fragment_content_text)
            commentNum = itemView.findViewById(R.id.news_fragment_commentNum_text)
            funNum = itemView.findViewById(R.id.news_fragment_funNum_text)
        }
    }

}