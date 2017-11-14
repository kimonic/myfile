package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ===============================================================
 * name:             LuckyLotteryLayout
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/14
 * description：
 * history：
 * *==================================================================
 */

public class LuckyLotteryLayout extends FrameLayout {
    /**
     * 屏幕宽度
     */
    private int width;
    /**
     * 左右上下边距
     */
    private float edge;
    /**
     * 三个圆角矩形
     */
    private RectF rectFWhite, rectFRed, rectFPink;
    /**
     * 圆角矩形画笔
     */
    private Paint paintWhite, paintRed, paintPink;
    /**
     * 圆角半径
     */
    private float radiousW, radiousP, radiousR;
    /**
     * 小圆点半径
     */
    private float dotRadious;
    /**
     * 小圆点的四角位置坐标
     */
    private float dotRadiousL, dotRadiousT, dotRadiousR, dotRadiousB;
    /**
     * 小圆点中心点位置集合
     */
    private List<DotCoordinate> list;
    /**
     * 小圆点颜色切换画笔
     */
    private Paint paintYellow, paintLightPink;
    /**
     * 小圆点闪烁标识
     */
    private int flag = 0;
    /**
     * 小圆点闪烁控制开关
     */
    private boolean dotGlint = true;

    /**
     * 小圆点闪烁控制开关
     */
    public void setDotGlint(boolean dotGlint) {
        this.dotGlint = dotGlint;
    }

    public LuckyLotteryLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public LuckyLotteryLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyLotteryLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }

    @TargetApi(21)
    public LuckyLotteryLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initview();
    }

    private void initview() {
        setWillNotDraw(false);
        width = ScreenSizeUtils.getScreenWidth(getContext());
        edge = width * 0.03636f;
        radiousW = width * 0.01818f;
        radiousR = width * 0.01454f;
        radiousP = width * 0.01454f;

        dotRadious = width * 0.01090f;

        float pLT = edge + width * 0.02727f;
        float pRB = width - pLT;


        float rLT = pLT + width * 0.05454f;
        float rRB = width - rLT;


        dotRadiousL = pLT + (rLT - pLT) / 2;
        dotRadiousT = dotRadiousL;
        dotRadiousR = width - dotRadiousL;
        dotRadiousB = dotRadiousR;

        float interval = (dotRadiousR - dotRadiousL) / 6;

        list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
//            for (int j = 0; j < 7; j++) {
//                if (j==0||j==6||i==0||i==6){
//                    DotCoordinate dotCoordinate = new DotCoordinate();
//                    dotCoordinate.x = dotRadiousL + j * interval;
//                    dotCoordinate.y = dotRadiousL + i * interval;
//                    list.add(dotCoordinate);
//                }
//
//            }

            DotCoordinate dotCoordinate = new DotCoordinate();
            if (i < 7) {
                dotCoordinate.x = dotRadiousL + i * interval;
                dotCoordinate.y = dotRadiousL;
            } else if (6 < i && i < 13) {
                dotCoordinate.x = dotRadiousR;
                dotCoordinate.y = dotRadiousL + (i - 6) * interval;
            } else if (i > 12 && i < 19) {
                dotCoordinate.x = dotRadiousR - (i - 12) * interval;
                dotCoordinate.y = dotRadiousB;
            } else {
                dotCoordinate.x = dotRadiousL;
                dotCoordinate.y = dotRadiousB - (i - 18) * interval;
            }
            list.add(dotCoordinate);


        }


        rectFWhite = new RectF(edge, edge, width - edge, width - edge);
        rectFRed = new RectF(rLT, rLT, rRB, rRB);
        rectFPink = new RectF(pLT, pLT, pRB, pRB);

        paintWhite = initPaint("#FFFCF2");
        paintPink = initPaint("#FE4236");
        paintRed = initPaint("#D80015");
        paintYellow = initPaint("#FFE965");
        paintLightPink = initPaint("#F98E8D");

        glintThread();


    }

    private Paint initPaint(String colorString) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(colorString));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#EB4046"));

        canvas.drawRoundRect(rectFWhite, radiousW, radiousW, paintWhite);
        canvas.drawRoundRect(rectFPink, radiousP, radiousP, paintPink);
        canvas.drawRoundRect(rectFRed, radiousR, radiousR, paintRed);

        for (int i = 0; i < list.size(); i++) {
            if ((i + flag) % 2 == 0) {
                canvas.drawCircle(list.get(i).x, list.get(i).y, dotRadious, paintYellow);
            } else {
                canvas.drawCircle(list.get(i).x, list.get(i).y, dotRadious, paintLightPink);
            }
        }


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);

    }

    private void glintThread() {
        new Thread() {
            @Override
            public void run() {
                while (dotGlint) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (flag==1){
                        flag=0;
                    }else {
                        flag=1;
                    }
                    postInvalidate();
                }
            }
        }.start();
    }


    private class DotCoordinate {
        private float x;
        private float y;
    }
}
