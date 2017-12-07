package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * * ===============================================================
 * name:             WhiteLinearLayout
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/7
 * description：
 * history：
 * *==================================================================
 */

public class WhiteLinearLayout extends LinearLayout {
    private Paint whitePaint;

    public WhiteLinearLayout(Context context) {
        super(context);
        initView();
    }

    public WhiteLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WhiteLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeWidth(3);
        whitePaint.setAlpha(1);
        whitePaint.setColor(Color.parseColor("#ffffff"));
    }

    @TargetApi(21)
    public WhiteLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height - 3, width, height - 3, whitePaint);
    }
}
