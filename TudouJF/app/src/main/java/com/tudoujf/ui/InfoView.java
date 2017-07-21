package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;

/**
 * * ==================================================
 * name:            InfoView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/21
 * description：   产品详情页-预期年化收益信息展示view
 * history：
 * * ==================================================
 *
 */

public class InfoView extends View {
    /**白色画笔,灰色画笔,橙色画笔,青色画笔*/
    private Paint paintWhite,paintGray,paintOrange,paintCyan;
    /**bitmap的绘制位置*/
    private Rect rectImag1=new Rect(),rectImag2=new Rect();
    /**需要绘制的bitmap*/
    private Bitmap bitmapLeft,bitmapRight;
    /**绘制的圆角矩形*/
    private RectF rectF=new RectF();
    /**屏幕的宽度*/
    private int width;
    /**此控件的宽度和高度*/
    private int viewWidth,viewHeight;
    /**该控件的宽高比*/
    private float whScale=3.5f;

    public InfoView(Context context) {
        this(context, null, 0);
    }

    public InfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    @TargetApi(23)
    public InfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paintWhite=initPaint(Color.parseColor("#FEFEFF"));
        paintWhite.setTextSize(45);
        paintGray=initPaint(Color.parseColor("#C1C2C7"));
        paintOrange=initPaint(Color.parseColor("#FD7833"));
        paintOrange.setTextSize(100);
        paintCyan=initPaint(Color.parseColor("#149BBB"));

        bitmapLeft= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua1);
        bitmapRight= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#E7FAFF"));

        int height=getHeight();//控件高度
        float bitScale1=4.4f;//位图1高与控件高的比例
        int bitWidth1= (int) (height/bitScale1);//控件的高度
        float initScale=13.5f;//位图1左上角位置与控件宽度的比例
        float initScaleh=1.9f;//位图1左上角位置与控件宽度的比例

        rectImag1.left= (int) (width/initScale);//位图1左上角x坐标
        rectImag1.top= (int) (height/initScaleh);//位图1左上角y坐标
        rectImag1.right=rectImag1.left+bitWidth1;
        rectImag1.bottom=rectImag1.top+bitWidth1;

        canvas.drawBitmap(bitmapLeft,null,rectImag1,null);
//        canvas.drawBitmap(bitmapLeft,100,200,null);
        paintGray.setTextSize(bitWidth1-10);//设置文字大小

        canvas.drawText("预期年化收益",rectImag1.left+bitWidth1+10,rectImag1.bottom-5,paintGray);

        float bitScale2=6.3f;//位图1高与控件高的比例
        int bitWidth2= (int) (width/bitScale2);//控件的高度
        float initScale2=1.29f;//位图1左上角位置与控件宽度的比例
        float initScaleh2=6.0f;//位图1左上角位置与控件宽度的比例

        rectImag2.left= (int) (width/initScale2);
        rectImag2.top= (int) (height/initScaleh2);
        rectImag2.right=rectImag2.left+bitWidth2;
        rectImag2.bottom=rectImag2.top+bitWidth2;

//        rectImag2.left=838;
//        rectImag2.top=51;
//        rectImag2.right=1009;
//        rectImag2.bottom=222;

        canvas.drawBitmap(bitmapRight,null,rectImag2,null);
//        canvas.drawBitmap(bitmapRight,600,50,null);
        canvas.drawText("借款期限",width/1.6f,rectImag1.bottom-5,paintGray);
//        canvas.drawText("借款期限",678,214,paintGray);

        float textWidth1=paintGray.measureText("预期年化收益");
        int textX1= (int) (rectImag1.left+(textWidth1+bitWidth1+10)/2-paintOrange.measureText("8%")/2);

        canvas.drawText("8%",textX1,height/2.6f,paintOrange);

//        canvas.drawText("8%",222,120,paintOrange);
//        canvas.drawText("1个月",658,120,paintOrange);
        float textWidth2=paintGray.measureText("借款期限");
        int textX2= (int) (width/1.6f+textWidth2/2-paintOrange.measureText("1个月")/2);
        canvas.drawText("1个月",textX2,height/2.6f,paintOrange);

        canvas.drawLine(width/1.86f,height/17.64f,width/1.86f,rectImag1.bottom-5,paintCyan);//中分线

        canvas.drawLine(0,height/1.08f,750,height/1.08f,paintCyan);//底线

        canvas.drawRect(482,26,530,75,paintCyan);
        canvas.drawText("新",486,70,paintWhite);

//        canvas.drawRoundRect(750,280,780,290,5,5,paintCyan);
        rectF.left=750;
        rectF.top=265;
        rectF.right=830;
        rectF.bottom=300;
        canvas.drawRoundRect(rectF,15,15,paintCyan);

        paintWhite.setTextSize(30);
        canvas.drawText("75%",760,295,paintWhite);

        canvas.drawLine(830,285,1080,285,paintGray);



//        canvas.drawLine(0,285,750,285,paintCyan);//底线
//
//        canvas.drawRect(482,26,530,75,paintCyan);
//        canvas.drawText("新",486,70,paintWhite);
//
////        canvas.drawRoundRect(750,280,780,290,5,5,paintCyan);
//        rectF.left=750;
//        rectF.top=265;
//        rectF.right=830;
//        rectF.bottom=300;
//        canvas.drawRoundRect(rectF,15,15,paintCyan);
//
//        paintWhite.setTextSize(30);
//        canvas.drawText("75%",760,295,paintWhite);
//
//        canvas.drawLine(830,285,1080,285,paintGray);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width= ScreenSizeUtils.getScreenWidth(getContext());
        int mWidth=MeasureSpec.getSize(widthMeasureSpec);
        int mHeight=MeasureSpec.getSize(heightMeasureSpec);

        int mWMode=MeasureSpec.getMode(widthMeasureSpec);
        int mHMode=MeasureSpec.getMode(heightMeasureSpec);

        if (mWMode==MeasureSpec.AT_MOST||mHMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(width, (int) (width/whScale));
        }else {
            setMeasuredDimension(mWidth,mHeight);
        }
    }

    private Paint initPaint(int color){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setColor(color);
        return paint;
    }
}
