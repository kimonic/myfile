package com.tudoujf.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * * ===============================================================
 * name:             OrangeCircleBacTextView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/3
 * description：
 * history：
 * *==================================================================
 */

public class OrangeCircleBacTextView extends android.support.v7.widget.AppCompatTextView {


    private Paint paint;

    public OrangeCircleBacTextView(Context context) {
        super(context);
        initView();
    }

    public OrangeCircleBacTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OrangeCircleBacTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setColor(Color.parseColor("#FF6D00"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();
        float radius=width/2.00f;
        canvas.drawCircle(radius,radius,radius,paint);
        super.onDraw(canvas);
    }
}
