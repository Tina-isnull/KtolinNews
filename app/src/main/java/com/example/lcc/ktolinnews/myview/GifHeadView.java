package com.example.lcc.ktolinnews.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.lcc.ktolinnews.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by lcc on 2017/11/13.
 */

public class GifHeadView extends RelativeLayout {
    private GifImageView gifImageView;
    private TextView textView;
    public GifHeadView(Context context) {
        super(context);
    }

    public GifHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.gif_headview,this,true);
        gifImageView= (GifImageView) view.findViewById(R.id.giv_anim);
        textView= (TextView) view.findViewById(R.id.giv_text);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    /**
     * 设置gif动画资源
     */
    public void setGifAnim(GifDrawable gifdrawable){
        gifImageView.setImageDrawable(gifdrawable);
        //停止自动播放
        gifdrawable.stop();
    }

    /**
     * 获取gif
     */
    public GifDrawable getDrawable() {
        return (GifDrawable) gifImageView.getDrawable();
    }
}
