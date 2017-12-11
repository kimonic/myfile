package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tudoujf.utils.ScreenSizeUtils;

/**
 * * ====================================================================
 * name:            AwardInfoView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/31
 * description：  奖励 ****% view  首页中展示
 * history：
 * * ====================================================================
 */

public class AwardInfoView extends View {

    /**背景色画笔,文字画笔*/
    private Paint bacPaint,textPaint;
    /**背景路径,文本路径*/
    private Path bacPath=new Path(),textPath=new Path();

    private String text="奖0.50%";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AwardInfoView(Context context) {
        this(context, null, 0);
    }

    public AwardInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AwardInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public AwardInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

        bacPaint=new Paint();
        bacPaint.setAntiAlias(true);
        bacPaint.setColor(Color.parseColor("#F48029"));
        bacPaint.setStyle(Paint.Style.FILL);

        textPaint=new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#FFFBF6"));
        textPaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getWidth();
        int height=getHeight();


        bacPath.moveTo(0,0);
        bacPath.lineTo(width*0.45f,0);
        bacPath.lineTo(width,height*0.55f);
        bacPath.lineTo(width,height);
        bacPath.close();

        canvas.drawPath(bacPath,bacPaint);

        textPath.moveTo(width*0.37f,height*0.25f);
        textPath.lineTo(width*0.86f,height*0.77f);

        textPaint.setTextSize(width*0.2f);
        canvas.drawTextOnPath(text,textPath,0,0,textPaint);




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height=MeasureSpec.getSize(heightMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode==MeasureSpec.AT_MOST){
            height=40* ScreenSizeUtils.getDensity(getContext());
            width= (int) (height*1.25f);
            setMeasuredDimension(width,height);
        }else if (heightMode==MeasureSpec.EXACTLY){
            width= (int) (1.25f*height);
            setMeasuredDimension(width,height);
        }


    }
}
