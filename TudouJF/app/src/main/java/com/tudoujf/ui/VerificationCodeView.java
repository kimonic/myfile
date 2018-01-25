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

import java.sql.Ref;

/**
 * * ===============================================================
 * name:             VerificationCodeView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/25
 * description：  验证码生成view
 * history：
 * *==================================================================
 */

public class VerificationCodeView extends View {

    /**生成的并显示的验证码*/
    private  String  showCode="";
    /**验证码画笔*/
    private Paint codePaint;
    /**文本绘制的路径*/
    private Path path;
    /**圆角矩形画笔*/
    private Paint bacPaint;
    /**背景圆角矩形*/
    private RectF rectF;

    private StringBuilder builder;

    public String getShowCode() {
        return showCode;
    }

    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    public VerificationCodeView(Context context) {
        this(context, null, 0);
    }

    public VerificationCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerificationCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(21)
    public VerificationCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        rectF=new RectF();
        builder=new StringBuilder();

        showCode=generateCode();
        Log.e("TAG", "initView: VerificationCodeView-----"+showCode);


        path=new Path();

        codePaint=new Paint();
        codePaint.setAntiAlias(true);
        codePaint.setColor(Color.parseColor("#46B5E5"));
        codePaint.setStyle(Paint.Style.FILL);

        bacPaint=new Paint();
        bacPaint.setAntiAlias(true);
        bacPaint.setColor(Color.WHITE);
        bacPaint.setStyle(Paint.Style.FILL);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();
        int height=getHeight();
        rectF.top=0;
        rectF.left=0;
        rectF.right=width;
        rectF.bottom=height;
        canvas.drawRoundRect(rectF,6,6,bacPaint);
        codePaint.setTextSize(50);

        float  textLength=codePaint.measureText(showCode);
        float  spacing=(width*0.8f-textLength)/3;

        float  startX=width*0.2f;
        float startY=height*0.5f;

//        float endX=width*0.8f;
//        float endY=height*0.7f;
//
//        path.moveTo(startX,startY);
//        path.lineTo(endX,endY);



//        canvas.drawTextOnPath(showCode,path,0,0,codePaint);  240406564351
//        canvas.save();
//        canvas.rotate(45);
        canvas.drawText(showCode,startX,startY,codePaint);
//        canvas.restore();
//
//        canvas.drawText(String.valueOf(showCode.charAt(1)),startX+spacing,startY,codePaint);
//
//        canvas.save();
//        canvas.rotate(45);
//        canvas.drawText(String.valueOf(showCode.charAt(2)),startX+spacing*2,startY,codePaint);
////        canvas.rotate(-45);
//        canvas.restore();
//
//        canvas.drawText(String.valueOf(showCode.charAt(3)),startX+spacing*3,startY,codePaint);



    }

    private String generateCode(){
        return builder.append((int)(Math.random()*10))
                .append((int)(Math.random()*10))
                .append((int)(Math.random()*10))
                .append((int)(Math.random()*10)).toString();
    }
}
