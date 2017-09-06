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
 * name:            FundDetailsView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：   资金详情自定义view
 * history：
 * * ==================================================
 *
 */
public class FundDetailsView extends View {

    /**画笔*/
    private Paint redPaint,whitePaint,bluePaint,violetPaint,greenPaint,yellowPaint,blackPaint,
    grayPaint,orangePaint;


    public FundDetailsView(Context context) {
        this(context, null, 0);
    }

    public FundDetailsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FundDetailsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public FundDetailsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        redPaint=initPaint("#F96937");
        whitePaint=initPaint("#EFECF2");
        bluePaint=initPaint("#01AAEE");

        violetPaint=initPaint("#C686DA");
        greenPaint=initPaint("#64D0B6");
        yellowPaint=initPaint("#FFCA27");

        blackPaint=initPaint("#464545");
        grayPaint=initPaint("#B8B8B5");
        grayPaint.setStrokeWidth(3);
        orangePaint=initPaint("#F48029");
    }


    @Override
    protected void onDraw(Canvas canvas) {


        int  width=getWidth();
        float  radius=width*0.02666f;


        //--------背景色----------------------------------------------------------------------------

        canvas.drawColor(Color.parseColor("#F1F0E7"));

        //--------背景色----------------------------------------------------------------------------


        //--------线段----圆------------------------------------------------------------------------
        canvas.drawLine(width*0.1133f,0,width*0.1133f,width*0.6666f,grayPaint);

        canvas.drawCircle(width*0.1133f,width*0.1399f,radius,redPaint);
        canvas.drawCircle(width*0.1133f,width*0.2732f,radius,bluePaint);
        canvas.drawCircle(width*0.1133f,width*0.4065f,radius,violetPaint);
        canvas.drawCircle(width*0.1133f,width*0.5398f,radius,greenPaint);
        canvas.drawCircle(width*0.1133f,width*0.6731f,radius,yellowPaint);
        //--------线段----圆------------------------------------------------------------------------



        //--------圆内文字--------------------------------------------------------------------------
        whitePaint.setTextSize(width*0.03333f);
        canvas.drawText("可",width*0.0953f,width*0.1519f,whitePaint);
        canvas.drawText("冻",width*0.0953f,width*0.2852f,whitePaint);
        canvas.drawText("已",width*0.0953f,width*0.4185f,whitePaint);
        canvas.drawText("本",width*0.0953f,width*0.5518f,whitePaint);
        canvas.drawText("利",width*0.0953f,width*0.6851f,whitePaint);
        //--------圆内文字--------------------------------------------------------------------------


        //----------------文本----------------------------------------------------------------------

        blackPaint.setTextSize(width*0.03333f);
        orangePaint.setTextSize(width*0.03333f);

        canvas.drawText("可用余额",width*0.2f,width*0.1519f,blackPaint);
        canvas.drawText("冻结金额",width*0.2f,width*0.2852f,blackPaint);
        canvas.drawText("已收利息",width*0.2f,width*0.4185f,blackPaint);
        canvas.drawText("待收本金",width*0.2f,width*0.5518f,blackPaint);
        canvas.drawText("待收利息",width*0.2f,width*0.6851f,blackPaint);

        canvas.drawText("000,000,000.00",width*0.4f,width*0.1519f,orangePaint);
        canvas.drawText("000,000,000.00",width*0.4f,width*0.2852f,orangePaint);
        canvas.drawText("000,000,000.00",width*0.4f,width*0.4185f,orangePaint);
        canvas.drawText("000,000,000.00",width*0.4f,width*0.5518f,orangePaint);
        canvas.drawText("000,000,000.00",width*0.4f,width*0.6851f,orangePaint);


        canvas.drawText("元",width*0.8666f,width*0.1519f,blackPaint);
        canvas.drawText("元",width*0.8666f,width*0.2852f,blackPaint);
        canvas.drawText("元",width*0.8666f,width*0.4185f,blackPaint);
        canvas.drawText("元",width*0.8666f,width*0.5518f,blackPaint);
        canvas.drawText("元",width*0.8666f,width*0.6851f,blackPaint);



        //----------------文本----------------------------------------------------------------------



    }

    private Paint initPaint(String  color){
        Paint paint=new Paint();

        paint.setColor(Color.parseColor(color));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width= ScreenSizeUtils.getScreenWidth(getContext());
        int  height= (int) (width*0.8f);
        setMeasuredDimension(width,height);
    }
}
