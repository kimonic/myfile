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
 * name:            MLockView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/7
 * description：   锁屏view
 * history：
 * * ==================================================
 */

public class MLockView extends View {

    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 圆之间的距离dp
     */
    private final static int distanceDP = 50;

    /**
     * 圆之间的距离px
     */
    private int distancePX;
    /**
     * 上下左右外边距px
     */
    private final static int PADDING = 50;
    /**
     * 圆的半径
     */
    private int radius;
    /**
     * 圆心的xy坐标
     */
    private int circleX[] = new int[3];
    private int circleY[] = new int[3];

    /**
     * 空心圆画笔
     */
    private Paint hollowPaint;


    public MLockView(Context context) {
        this(context, null, 0);
    }

    public MLockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public MLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        screenWidth = ScreenSizeUtils.getScreenWidth(getContext());
        distancePX = distanceDP * ScreenSizeUtils.getDensity(getContext());
        hollowPaint = new Paint();
        hollowPaint.setColor(Color.parseColor("#FE7B20"));
        hollowPaint.setAntiAlias(true);
        hollowPaint.setStyle(Paint.Style.STROKE);
        hollowPaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        radius = (getWidth() - PADDING * 2 - distancePX * 2) / 6;
        //中间圆的圆心坐标
        int circleX1 = getWidth() / 2 - 2 * radius - distancePX;
        int circleX2 = getWidth() / 2;
        int circleX3 = getWidth() / 2 + 2 * radius + distancePX;
        circleX[0] = circleX1;
        circleX[1] = circleX2;
        circleX[2] = circleX3;


        int circleY1 = PADDING + radius;
        int circleY2 = PADDING + radius * 3 + distancePX;
        int circleY3 = PADDING + radius * 5 + distancePX * 2;
        circleY[0] = circleY1;
        circleY[1] = circleY2;
        circleY[2] = circleY3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.drawCircle(circleX[i], circleY[j], radius,hollowPaint );
            }
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int newSize = Math.min(width, height);

        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            if (newSize > screenWidth) {
                setMeasuredDimension(screenWidth, screenWidth);
            } else {
                setMeasuredDimension(newSize, newSize);
            }
        }


    }
}
