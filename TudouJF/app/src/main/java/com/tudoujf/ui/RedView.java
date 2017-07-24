package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
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
import android.widget.LinearLayout;

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

    private int  imageId= R.drawable.act_redpackage2_unsel;
    private Bitmap bitmap;
    private int width;
    private int height;
    private String TAG="redview";

    public Bitmap getBitmap() {
        if (bitmap!=null){
            return bitmap;
        }
        return BitmapFactory.decodeResource(getResources(),imageId);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getOneText() {
        return oneText;
    }

    public void setOneText(String oneText) {
        this.oneText = oneText;
    }

    public String getTwoText() {
        return twoText;
    }

    public void setTwoText(String twoText) {
        this.twoText = twoText;
    }

    public String getThreeText() {
        return threeText;
    }

    public void setThreeText(String threeText) {
        this.threeText = threeText;
    }

    public String getFourText() {
        return fourText;
    }

    public void setFourText(String fourText) {
        this.fourText = fourText;
    }

    private Rect rect=new Rect();
    private Paint onePaint,twoPaint,threePaint;

    private String oneText="50",twoText="元",threeText="有效期至:",fourText="2016/XX/XX";

    public RedView(Context context) {
        this(context, null, 0);
    }

    public RedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(23)
    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
            bitmap= getBitmap();
            onePaint=initPaint();
            twoPaint=initPaint();
            threePaint=initPaint();


    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width=getWidth();
        Log.e("TAG", "onDraw:-*-------- " +getWidth());
        Log.e("TAG", "onDraw: --------" +getHeight());

        rect.left=0;
        rect.top=0;
        rect.right=getWidth();
        rect.bottom=getHeight();
        canvas.drawBitmap(bitmap,null,rect,null);

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


//        Log.e("TAG", "onMeasure: ------3---"+MeasureSpec.UNSPECIFIED);//0

        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST){
            width=100* ScreenSizeUtils.getDensity(getContext());
        }
        if (heightMode==MeasureSpec.AT_MOST){
            height= (int) (100* ScreenSizeUtils.getDensity(getContext())/1.175f);
        }

        width= (int) (height*1.175f);
        Log.e(TAG, "onMeasure: -----------" +width);
        Log.e(TAG, "onMeasure: -----------" +height);
        setMeasuredDimension(width,height);

    }

    private Paint initPaint(){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FBFCFE"));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {

        int  w=ScreenSizeUtils.getScreenWidth(getContext())-width-10*ScreenSizeUtils.getDensity(getContext());
        int  w2=w+width;

        super.layout(w, t, w2, b);
    }
}
