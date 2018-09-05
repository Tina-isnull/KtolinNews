package com.example.lcc.ktolinnews.myview;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import com.example.lcc.ktolinnews.R;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by Chong on 2016/1/17.
 */
public class RefreshSetting {
    public static View setListViewRefresh(PullToRefreshLayout pullLayout, Context context, PullToRefreshLayout.OnPullListener listener) {
        int gifs[] = {R.drawable.wt, R.drawable.animation_top_2};
//        , R.drawable.animation_top_3
        // 设置带gif动画的上拉头与下拉头
        try {
            java.util.Random random = new java.util.Random();// 定义随机类
            int result = random.nextInt(2);// 返回[0,2)集合中的整数，注意不包括2
            pullLayout.setGifRefreshView(new GifDrawable(context.getResources(), gifs[result]));
        } catch (Resources.NotFoundException e) {
            //添加文件读取异常捕获
            Log.d(e.getMessage(), "gif文件读取异常");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(e.getMessage(), "IO异常");
            e.printStackTrace();
        }
        return pullLayout.getPullableView();
    }

}


