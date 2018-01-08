package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * * ===============================================================
 * name:             BorderLinearLayout
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/7
 * description：上下边线为灰色的LinearLayout
 * history：
 * *==================================================================
 */

public class BorderLinearLayout extends LinearLayout {
    private Paint grayPaint,cyanPaint;
//    private Rect rect;

    public BorderLinearLayout(Context context) {
        super(context);
        initView();
    }

    public BorderLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BorderLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {


        setWillNotDraw(false);
        grayPaint = new Paint();
        grayPaint.setAntiAlias(true);
        grayPaint.setStyle(Paint.Style.FILL);
        grayPaint.setStrokeWidth(3);
//        grayPaint.setAlpha(1);
        grayPaint.setColor(Color.parseColor("#dcdcdc"));
//        grayPaint.setColor(Color.RED);

//        cyanPaint = new Paint();
//        cyanPaint.setAntiAlias(true);
//        cyanPaint.setStyle(Paint.Style.FILL);
//        cyanPaint.setColor(Color.parseColor("#E7FAFF"));
//
//        rect=new Rect();
    }

    @TargetApi(21)
    public BorderLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
//        rect.top=0;
//        rect.left=0;
//        rect.right=width;
//        rect.top=height-4;
//        canvas.drawRect(rect,cyanPaint);
        canvas.drawLine(0, height - 3, width, height - 3, grayPaint);
        canvas.drawLine(0, 0, width, 0, grayPaint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }
}
