package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tudoujf.utils.ScreenSizeUtils;

/**
 * * ==================================================
 * name:            IndicatorView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/26
 * description：   指示器view
 * history：
 * * ==================================================
 */

public class IndicatorView extends View {

    private Paint selPaint, unselPaint;

    private  float scale=0;

    public void setScale(float scale) {
        this.scale = scale;
        invalidate();
    }

    public IndicatorView(Context context) {
        this(context, null, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        int density = ScreenSizeUtils.getDensity(getContext());

        selPaint = new Paint();
        selPaint.setAntiAlias(true);
        selPaint.setStyle(Paint.Style.FILL);
        selPaint.setColor(Color.parseColor("#F6CF62"));
        selPaint.setStrokeWidth(5 * density);


        unselPaint = new Paint();
        unselPaint.setAntiAlias(true);
        unselPaint.setStyle(Paint.Style.FILL);
        unselPaint.setColor(Color.parseColor("#a3a3a3"));
        unselPaint.setStrokeWidth(5 * density);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();


        canvas.drawLine(0,0,width*scale/3,0,unselPaint);
        canvas.drawLine(width*scale/3,0,width*2/3+width*scale/3,0,selPaint);
        canvas.drawLine(width*2/3+width*scale/3,0,width,0,unselPaint);

    }
}
