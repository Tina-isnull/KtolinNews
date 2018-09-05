package com.example.lcc.ktolinnews.adapter

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.FragmentContainer
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lcc.ktolinnews.MainActivity
import com.example.lcc.ktolinnews.R
import com.example.lcc.ktolinnews.WebviewActivity
import com.example.lcc.ktolinnews.bean.NewsBean
import com.example.lcc.ktolinnews.bean.NewsPicBean
import com.example.lcc.ktolinnews.fragment.BigPicShowFragment
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hour.*

class PullPicAdapter : RecyclerView.Adapter<PullPicAdapter.MyViewHolder> {
    var mContext: Context? = null
    var mdata: List<NewsPicBean>? = null
    var mfragment:FragmentManager?=null
    val transformation = RoundedTransformationBuilder()
            .borderColor(Color.TRANSPARENT)
            .borderWidthDp(0F)
            .oval(true)
            .build()

    constructor(context: Context, data: List<NewsPicBean>) {
        mContext = context
        mdata = data

    }
    fun setFragmentManger(fragment:FragmentManager?){
        mfragment=fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_item_pic, parent, false)
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
        Picasso.with(mContext).load(mdata?.get(position)?.contentPic).placeholder(R.mipmap.ic_launcher).into(holder?.contextImg)
        holder?.content?.setOnClickListener {
            val intent: Intent = Intent(mContext, WebviewActivity::class.java)
            intent.putExtra("url", mdata?.get(position)?.contentUrl)
            mContext?.startActivity(intent)
        }
        holder?.authorImg?.setOnClickListener {
            val intent: Intent = Intent(mContext, WebviewActivity::class.java)
            intent.putExtra("url", mdata?.get(position)?.userLink)
            mContext?.startActivity(intent)
        }
        holder?.contextImg?.setOnClickListener {
            val fragment = BigPicShowFragment.newInstance(mdata?.get(position)?.contentPic)
            //我真的是醉了！！
            fragment.show((mContext as MainActivity).supportFragmentManager,"")
        }


    }

    class MyViewHolder : RecyclerView.ViewHolder {
        val contextImg: ImageView
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
            contextImg = itemView.findViewById(R.id.news_fragment_content_img)
        }
    }

}

