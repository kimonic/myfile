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
import android.util.Log;
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
    /**该控件的宽高比*/
    private static final float whScale=3.5f;


    /**投资进度*/
    private float underlineScale1=0.88f;//对应75%\
    /**预期年化收益率*/
    private String nianHuaShouYi="8.8%";
    /**借款期限*/
    private String jieKuanQiXian="8个月";
    /**是否是新手标*/
    private boolean  ifNew=true;

    public void setUnderlineScale1(float underlineScale1) {
        this.underlineScale1 = underlineScale1;
    }

    public void setNianHuaShouYi(String nianHuaShouYi) {
        this.nianHuaShouYi = nianHuaShouYi;
    }

    public void setJieKuanQiXian(String jieKuanQiXian) {
        this.jieKuanQiXian = jieKuanQiXian;
    }

    public void setIfNew(boolean ifNew) {
        this.ifNew = ifNew;
    }

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
        width=ScreenSizeUtils.getScreenWidth(getContext());
        paintWhite=initPaint(Color.parseColor("#FEFEFF"));


        paintGray=initPaint(Color.parseColor("#C1C2C7"));
        paintOrange=initPaint(Color.parseColor("#FD7833"));
        paintOrange.setTextSize(width*0.07333f);
        paintCyan=initPaint(Color.parseColor("#149BBB"));

        bitmapLeft= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua1);
        bitmapRight= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#E7FAFF"));//背景色
        float underlineScale=(width*0.9259f)*underlineScale1/width;

        float bitScale1=0.03333f;//位图1高与控件高的比例
        int bitWidth1= (int) (width*bitScale1);//控件的高度
        float initScale=13.5f;//位图1左上角位置与控件宽度的比例
        float initScaleh=6.6f;//位图1左上角位置与控件宽度的比例

        rectImag1.left= (int) (width/initScale);//位图1左上角x坐标
        rectImag1.top= (int) (width/initScaleh);//位图1左上角y坐标
        rectImag1.right=rectImag1.left+bitWidth1;
        rectImag1.bottom=rectImag1.top+bitWidth1;

        canvas.drawBitmap(bitmapLeft,null,rectImag1,null);
        paintGray.setTextSize(width*0.04f);//设置文字大小

        canvas.drawText("预期年化收益",rectImag1.left+bitWidth1+10,rectImag1.bottom-5,paintGray);

        float bitScale2=0.16f;//位图1高与控件高的比例
        int bitWidth2= (int) (width*bitScale2);//控件的高度
        float initScale2=1.29f;//位图1左上角位置与控件宽度的比例
        float initScaleh2=21f;//位图1左上角位置与控件宽度的比例

        rectImag2.left= (int) (width/initScale2);
        rectImag2.top= (int) (width/initScaleh2);
        rectImag2.right=rectImag2.left+bitWidth2;
        rectImag2.bottom=rectImag2.top+bitWidth2;


        canvas.drawBitmap(bitmapRight,null,rectImag2,null);
        canvas.drawText("借款期限",width/1.6f,rectImag1.bottom-5,paintGray);

        float textWidth1=paintGray.measureText("预期年化收益");

        //年化收益
        int textX1= (int) (rectImag1.left+(textWidth1+bitWidth1+10)/2-paintOrange.measureText(nianHuaShouYi)/2);
        canvas.drawText(nianHuaShouYi,textX1,width/9.1f,paintOrange);

        float textWidth2=paintGray.measureText("借款期限");

        //借款期限
        int textX2= (int) (width/1.6f+textWidth2/2-paintOrange.measureText(jieKuanQiXian)/2);
        canvas.drawText(jieKuanQiXian,textX2,width/9.1f,paintOrange);

        canvas.drawLine(width/1.86f,width/61.74f,width/1.86f,rectImag1.bottom-5,paintCyan);//中分线

        canvas.drawLine(0,width*0.2638f,width*underlineScale,width*0.2638f,paintCyan);//底线

        if (ifNew){//是否时新手标
            canvas.drawRect(width*0.4706f,width*0.03333f,width*0.5089f,width*0.07166f,paintCyan);
            paintWhite.setTextSize(width*.03333f);
            canvas.drawText("新",width*0.4736f,width*0.06666f,paintWhite);
        }


        rectF.left=width*underlineScale;
        rectF.top=width/4.07f;
        rectF.right=rectF.left+width*0.07407f;
        rectF.bottom=width*0.277f;
        canvas.drawRoundRect(rectF,width*0.01388f,width*0.01388f,paintCyan);

        paintWhite.setTextSize(width*0.02777f);
        canvas.drawText((int)(underlineScale1*100)+"%",width*(underlineScale+0.01f),width*0.2731f,paintWhite);

        canvas.drawLine(rectF.right,width*0.2638f,width,width*0.2638f,paintGray);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(width, (int) (width/whScale));
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
