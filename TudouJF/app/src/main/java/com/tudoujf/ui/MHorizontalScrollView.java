package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;


/**
 * * ==================================================
 * name:            MHorizontalScrollView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/26
 * description：   自定义HorizontalScrollView,可监听滚动位置
 * history：
 * * ==================================================
 */

public class MHorizontalScrollView extends HorizontalScrollView {

    private     ScrollListener listener;

    public void setListener(ScrollListener listener) {
        this.listener = listener;
    }

    public MHorizontalScrollView(Context context) {
        super(context);
    }

    public MHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(21)
    public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void setScrollX(@Px int value) {
        super.setScrollX(value);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (listener!=null){
            listener.currentScrollX(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);

    }

    public interface ScrollListener{
        void currentScrollX(int l, int t, int oldl, int oldt);
    }
}
