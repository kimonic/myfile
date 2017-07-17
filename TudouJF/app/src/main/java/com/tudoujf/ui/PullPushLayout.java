package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * * ==================================================
 * name:            PullPushLayout
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/17
 * description：   上拉加载下拉刷新布局
 * history：
 * * ==================================================
 *
 */

public class PullPushLayout extends LinearLayout {
    private View headerView;
    private View footerView;
    private ListView listView;




    public PullPushLayout(Context context) {
        this(context, null, 0);
    }

    public PullPushLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullPushLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(23)
    public PullPushLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void initView() {

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        this.setOrientation(VERTICAL);


        headerView=new View(getContext());
        LinearLayout.LayoutParams paramsHeader=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                150);
        headerView.setLayoutParams(paramsHeader);
        headerView.setBackgroundColor(Color.BLUE);

        footerView=new View(getContext());
        LinearLayout.LayoutParams paramsFooter=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                150);
        footerView.setLayoutParams(paramsFooter);
        footerView.setBackgroundColor(Color.RED);

        listView=new ListView(getContext());
        LinearLayout.LayoutParams paramsListview=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
               0);
        params.weight=1;
        listView.setLayoutParams(paramsListview);
        listView.setBackgroundColor(Color.BLACK);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
