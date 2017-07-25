package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;

/**
 * * ==================================================
 * name:            RedView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   红包,加息券view
 * history：
 * * ==================================================
 */

public class RedView extends View {

    /**背景图片*/
    private Bitmap bitmap;
    /**控件宽度*/
    private int width;
    private String TAG="redview";
    /**初始背景图片类型*/
    private int type=1;

    private static final int RED_PACKAGE=1;
    private static final int JIA_XI_QUAN=2;



    public void setBitmap(int bitmapId) {
        this.bitmap = BitmapFactory.decodeResource(getResources(),bitmapId);
        invalidate();
    }


    public void setOneText(String oneText) {
        this.oneText = oneText;
    }

    public void setTwoText(String twoText) {
        this.twoText = twoText;
    }

    public void setFourText(String fourText) {
        this.fourText = fourText;
    }

    private Rect rect=new Rect();
    private Paint onePaint,twoPaint,threePaint;

    private String oneText,twoText=getResources().getString(R.string.yuan),threeText=getResources().getString(R.string.youxiaoqizhi),fourText;

    public RedView(Context context) {
        this(context, null, 0);
    }

    public RedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }
    @TargetApi(23)
    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
            onePaint=initPaint();
            twoPaint=initPaint();
            threePaint=initPaint();
        if (attrs!=null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RedView);
            type=a.getInt(R.styleable.RedView_type,1);
            a.recycle();
            switch (type){
                case RED_PACKAGE:
                    bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.act_redpackage2_unsel);
                    break;
                case JIA_XI_QUAN:
                    bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.act_redpackage2_quanunsel);
                    break;
            }
        }else {
            bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.act_redpackage2_unsel);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getWidth();

        rect.left=0;
        rect.top=0;
        rect.right=getWidth();
        rect.bottom=getHeight();
        if (bitmap!=null){
            canvas.drawBitmap(bitmap,null,rect,null);
        }

        float  oneScaleX=0.15f;
        float  oneScaleY=0.18f;
        float  oneSize=0.2f;

        float oneX=width*oneScaleX;
        float oneY=width*oneScaleY;
        float textSize1=width*oneSize;
        onePaint.setTextSize(textSize1);
        canvas.drawText(oneText,oneX,oneY+textSize1,onePaint);

        float distance=onePaint.measureText(oneText);
        float twoSize=0.15f;
        float textSize2=width*twoSize;

        twoPaint.setTextSize(textSize2);
        canvas.drawText(twoText,oneX+distance+5,oneY+textSize1,twoPaint);


        float threeSize=0.1f;
        float threeScaleY=0.5f;

        float threeY=width*threeScaleY;
        float textSize3=width*threeSize;

        threePaint.setTextSize(textSize3);


        canvas.drawText(threeText,oneX,threeY+textSize3,threePaint);
        canvas.drawText(fourText,oneX,threeY+textSize3*2+10,threePaint);






    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        width=MeasureSpec.getSize(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST){
            width=100* ScreenSizeUtils.getDensity(getContext());
        }
        if (heightMode==MeasureSpec.AT_MOST){
            height = (int) (100* ScreenSizeUtils.getDensity(getContext())/1.175f);
        }

        width= (int) (height *1.175f);
        setMeasuredDimension(width, height);
    }
    /**初始化画笔*/
    private Paint initPaint(){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FBFCFE"));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }


    /**该函数确定该view在父控件中的位置,并最终确定getwidth()和getheight()的值*/
    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {

        int  w=ScreenSizeUtils.getScreenWidth(getContext())-width-10*ScreenSizeUtils.getDensity(getContext());
        int  w2=w+width;
        super.layout(w, t, w2, b);
    }
}
