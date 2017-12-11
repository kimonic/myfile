package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tudoujf.R;

/**
 * * ====================================================================
 * name:            BallView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/31
 * description：  预期年化收益信息展示view  首页中展示
 * history：
 * * ====================================================================
 */

public class BallView extends View {


    private Paint circlePaint, linePaint, textPaint, shadowpaint;


    private RectF rectF = new RectF();

    private int circleBorderWidth = 4;
    private int lineBorderWidth = 20;
    private String text="11.00%";
    private float duandianbuchang=10;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BallView(Context context) {
        this(context, null, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {


        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.parseColor("#169CBC"));
        circlePaint.setStrokeWidth(circleBorderWidth);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(Color.parseColor("#45C1E0"));
        linePaint.setStrokeWidth(lineBorderWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#FFFFFF"));
        textPaint.setStrokeWidth(10);


        shadowpaint = new Paint();
        shadowpaint.setAntiAlias(true);
        shadowpaint.setStyle(Paint.Style.FILL);
        shadowpaint.setColor(Color.parseColor("#149BBC"));


    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        int length = Math.min(width, height);




        float lineStartX = (float) (width / 2 - Math.sqrt(length * length / 4 - length * length * 0.01));
        float lineEndX = (float) (width / 2 + Math.sqrt(length * length / 4 - length * length * 0.01));
        float lineHeight = height / 2 + 0.1f * length / 2;
        canvas.drawLine(lineStartX+duandianbuchang, lineHeight, lineEndX-duandianbuchang, lineHeight, linePaint);

        float angle = (float) (360 * Math.asin((0.1 * length - lineBorderWidth) / (0.5 * length)) / (2 * Math.PI));


        rectF.left = width / 2 - length / 2 + 5;
        rectF.top = height / 2 - length / 2 + 5;
        rectF.right = width / 2 + length / 2 - 5;
        rectF.bottom = height / 2 + length / 2 - 5;
        canvas.drawArc(rectF, angle, 180 - 2 * angle, false, shadowpaint);


        float textY=height/2-length*0.11f;
//        shadowpaint.setTextSize(length*0.2f);
//        shadowpaint.setTextSize(length*0.1658f);
        shadowpaint.setTextSize(68);
        float  textLength=shadowpaint.measureText(text);
        float textX=width/2-textLength/2;

        canvas.drawText(text,textX,textY,shadowpaint);

//        textPaint.setTextSize(length*0.12f);
//        textPaint.setTextSize(length*0.09024f);
        textPaint.setTextSize(37);
        float bottomTextLength=textPaint.measureText(getResources().getString(R.string.frag_home_yuqinianhuashouyi));
        float textX1=width/2-bottomTextLength/2;
        float textY1=height/2+length*0.32f;
        canvas.drawText(getResources().getString(R.string.frag_home_yuqinianhuashouyi),textX1,textY1,textPaint);


        canvas.drawCircle(width / 2, height / 2, length / 2 - circleBorderWidth, circlePaint);



    }
}
