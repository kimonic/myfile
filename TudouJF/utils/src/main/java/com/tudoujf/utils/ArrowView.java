package com.tudoujf.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tudoujf.utils.utils.ScreenSizeUtils;
import com.tudoujf.utils.utils.ScreenUtil;

/**
 * * ===============================================================
 * name:             ArrowView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/13
 * description：
 * history：
 * *==================================================================
 */

public class ArrowView extends View {

    private int  width;
    private Paint whitePaint,bkuePaint;

    public ArrowView(Context context) {
        this(context, null, 0);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(21)
    public ArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

        width= ScreenSizeUtils.getScreenWidth(getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int  widthM= (int) (width*0.056f);
//        int  heightM= widthM;
        setMeasuredDimension(widthM,widthM);
    }
}
