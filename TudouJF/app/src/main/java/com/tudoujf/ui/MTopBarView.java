package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;

/**
 * * ================================================
 * name:            MTopBarView
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/10
 * description：  顶部标题栏自定义控件
 * history：
 * ===================================================
 */

public class MTopBarView extends LinearLayout {


    public MTopBarView(Context context) {
        this(context, null, 0);
    }

    public MTopBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTopBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(23)
    public MTopBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.view_mtopbar,this);
    }
}
