package com.example.lcc.ktolinnews.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.*
import com.example.lcc.ktolinnews.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.big_pic_fragment.view.*

open class BigPicShowFragment : DialogFragment() {
    companion object {
        var imgUrl: String? = ""
        fun newInstance(url: String?): BigPicShowFragment {
            imgUrl = url
            return BigPicShowFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        val window = getDialog().getWindow()
        val params: WindowManager.LayoutParams = window.getAttributes();
        params.gravity = Gravity.CENTER
        window.setAttributes(params)
        window.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, R.color.black))
        //设置边距
        val dm = DisplayMetrics()
        getActivity()?.getWindowManager()?.getDefaultDisplay()?.getMetrics(dm)
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.big_pic_fragment, container, false)
        Picasso.with(this.context).load(imgUrl).placeholder(R.mipmap.ic_launcher).into(view.big_img)
        return view
    }

}