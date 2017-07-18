package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by Administrator on 2017/7/18.
 *
 */

public class TestNested extends ViewGroup implements NestedScrollingChild,NestedScrollingParent {
    private NestedScrollingChildHelper childHelper;
    private NestedScrollingParentHelper parentHelper;
    public TestNested(Context context) {
        super(context);
    }

    public TestNested(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestNested(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(23)
    public TestNested(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


}
