package com.example.lcc.ktolinnews

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.lcc.ktolinnews.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val mData = listOf("热门", "24小时", "热图", "文字", "穿越", "糗图","新鲜")
    val mFragments = ArrayList<Fragment>()
    lateinit var fm: FragmentManager
    var mAdapter: pagerAdapter? = null
    //    val Fragment= NewsFragments()
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_tx_more ->
                skipToOther(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tx_login.setOnClickListener {
            skipToOther(1)
        }
        main_tx_more.setOnClickListener(this)

        setPagerView()
        initMagicIndicator()
//        Fragment.setNotifyActivityListener(object : notifyActivity {
//            override fun method1() {
//            }
//
//        })
    }

    fun setPagerView() {
        mFragments.add(0, NewsFragments())
        mFragments.add(1, HoursFragment())
        mFragments.add(2, HotFragment())
        mFragments.add(3, WordFragment())
        mFragments.add(4, CrossFragment())
        mFragments.add(5, AwkwardFragment())
        mFragments.add(6, LatestFragment())

        fm = getSupportFragmentManager()
        fm.beginTransaction()
        mAdapter = pagerAdapter()
        main_viewpager.adapter = mAdapter
    }

    fun skipToOther(towhat: Int) {
        if (towhat == 0) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {

        }
    }

    fun initMagicIndicator() {
        main_magic_indicator.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_white))
        val mCommonNavigator = CommonNavigator(this)
        mCommonNavigator.scrollPivotX = 0.5f
        mCommonNavigator.adapter = commonNavAdapter()
        main_magic_indicator.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(main_magic_indicator, main_viewpager)
    }

    inner class commonNavAdapter() : CommonNavigatorAdapter() {

        override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
            val simplePagerTitleView: SimplePagerTitleView = ColorFlipPagerTitleView(this@MainActivity)
            simplePagerTitleView.text = mData!!.get(p1)
            simplePagerTitleView.textSize = 15F
            simplePagerTitleView.normalColor = Color.parseColor("#4a4a4a")
            simplePagerTitleView.selectedColor = ContextCompat.getColor(this@MainActivity, R.color.red)
            simplePagerTitleView.setOnClickListener {
                main_viewpager.setCurrentItem(p1)
            }
            return simplePagerTitleView
        }

        override fun getCount(): Int {
            if (mData == null) return 0 else return mData.size
        }

        override fun getIndicator(p0: Context?): IPagerIndicator {
            val indicator = LinePagerIndicator(this@MainActivity)
            indicator.mode = LinePagerIndicator.MODE_EXACTLY
            indicator.lineHeight = UIUtil.dip2px(this@MainActivity, 2.0).toFloat()
            indicator.lineWidth = UIUtil.dip2px(this@MainActivity, 20.0).toFloat()
            indicator.roundRadius = UIUtil.dip2px(this@MainActivity, 3.0).toFloat()
            indicator.startInterpolator = AccelerateInterpolator()
            indicator.endInterpolator = DecelerateInterpolator(5.0f)
            indicator.setColors(ContextCompat.getColor(this@MainActivity, R.color.red))
            return indicator
        }
    }

    inner class pagerAdapter() : FragmentStatePagerAdapter(fm) {

        override fun getItem(p0: Int): Fragment {
            return mFragments!!.get(p0)
        }

        override fun getCount(): Int = if (mFragments != null) mFragments.size else 0

    }
}
