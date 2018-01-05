package com.tudoujf.utils.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * * ===============================================================
 * name:             QuXianView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/5
 * description：
 * history：
 * *==================================================================
 */

public class QuXianView extends View {

    private Paint paint;
    private float x=0;
    private float  y=1920;
    private Path path;
    private List<Integer>  list;

    public QuXianView(Context context) {
        super(context);
        initView();
    }

    public QuXianView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QuXianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(21)
    public QuXianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setColor(Color.parseColor("#1EA362"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);

        RadialGradient gradient=new RadialGradient(0,0,1080,
                Color.parseColor("#E9421B"), Color.parseColor("#311BE9"), Shader.TileMode.MIRROR
                );
        paint.setShader(gradient);

        path=new Path();
        list=new ArrayList<>();
//        while (x<1080){
//            if (x==0){
//                path.moveTo(0,500);
//            }
//            x++;
//            y=-x*x/500+500;
//            path.lineTo(x,y);
//        }

        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#C0C0C1"));

//        canvas.drawPath(path,paint);
//        while (x<1080){
//            if (x==0){
//                path.moveTo(0,500);
//            }
//            x=x+100f;
//            y= (float)Math.sin(x)*100+500;
//            Log.e("TAG", "onDraw: --dsdsdsdsds---"+x);
//
//            canvas.drawPoint(x,y,paint);
//        }
//        canvas.drawPoint(100,100,paint);
//        canvas.drawLine(0,500,1080,500,paint);

        canvas.drawCircle(x,y,30,paint);

    }

private boolean  flagX=true,flagY=true;
    private void start(){
        new Thread(){
            @Override
            public void run() {
                while (y>0){
                    if (y>1900){
                        flagY=false;
                    }else if (y<20){
                        flagY=true;
                    }
                    if (x>900){
                        flagX=false;
                    }else if (x<5){
                        flagX=true;
                    }
                    if (flagX){
                        x=x+5;
                    }else {
                        x=x-5;
                    }
                    if (flagY){
                        y=y-10;
                    }else {
                       y=y+10;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }


            }
        }.start();
    }
}
