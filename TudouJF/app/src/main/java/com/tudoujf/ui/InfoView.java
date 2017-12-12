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
    private static final float whScale=0.3133f;
    /**最底部白色矩形框*/
    private Rect rect=new Rect();


    /**投资进度--0.88f*/
    private float underlineScale1=0;//对应75%\
    /**预期年化收益率*/
    private String nianHuaShouYi="8.8%";
    /**借款期限*/
    private String jieKuanQiXian="8个月";
    /**是否是新手标*/
    private boolean  ifNew=true;
    private boolean drawImage=true;

    private boolean  closeBottomLine=false;

    public boolean isCloseBottomLine() {
        return closeBottomLine;
    }

    public void setCloseBottomLine(boolean closeBottomLine) {
        this.closeBottomLine = closeBottomLine;
    }

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

    public boolean isDrawImage() {
        return drawImage;
    }

    public void setDrawImage(boolean drawImage) {
        this.drawImage = drawImage;
    }

    private void initView() {
        width=ScreenSizeUtils.getScreenWidth(getContext());
        paintWhite=initPaint(Color.parseColor("#FEFEFF"));


        paintGray=initPaint(Color.parseColor("#C1C2C7"));
        paintOrange=initPaint(Color.parseColor("#FD7833"));
        paintOrange.setTextSize(width*0.06933f);
        paintCyan=initPaint(Color.parseColor("#149BBB"));

        bitmapLeft= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua1);
        bitmapRight= BitmapFactory.decodeResource(getResources(), R.drawable.act_productdetails2_nianhua2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#E6FBFF"));//背景色
        float underlineScale=(width*0.9259f)*underlineScale1/100/width;


        float bitScale1=0.04f;//位图1高与控件高的比例
        int bitWidth1= (int) (width*bitScale1);//控件的高度

//        float initScale=13.5f;//位图1左上角位置与控件宽度的比例
//        float initScaleh=6.6f;//位图1左上角位置与控件宽度的比例

        rectImag1.left= (int) (width*0.13333f);//位图1左上角x坐标
        rectImag1.top= (int) (width*0.19066f);//位图1左上角y坐标
        rectImag1.right=rectImag1.left+bitWidth1;
        rectImag1.bottom=rectImag1.top+bitWidth1;

        canvas.drawBitmap(bitmapLeft,null,rectImag1,null);
        paintGray.setTextSize(width*0.044f);//设置文字大小

        canvas.drawText("预期年化收益",rectImag1.left+bitWidth1+10,rectImag1.bottom-5,paintGray);


        canvas.drawText("借款期限",width*0.63733f,rectImag1.bottom-5,paintGray);


        //-------------------------------年化收益---------------------------------------------------

        float textWidth1=paintGray.measureText("预期年化收益");
        int textX1= (int) (rectImag1.left+(textWidth1+bitWidth1+10)/2-paintOrange.measureText(nianHuaShouYi)/2);
        canvas.drawText(nianHuaShouYi,textX1,width*0.13733f,paintOrange);


        //-------------------------------年化收益---------------------------------------------------


        //--------------------------------借款期限--------------------------------------------------
        float textWidth2=paintGray.measureText("借款期限");
        int textX2= (int) (width*0.63733f+textWidth2/2-paintOrange.measureText(jieKuanQiXian)/2);
        canvas.drawText(jieKuanQiXian,textX2,width*0.13733f,paintOrange);
        //--------------------------------借款期限--------------------------------------------------


        //--------------------------------------------中分线----------------------------------------
        paintCyan.setStrokeWidth(3);
        canvas.drawLine(width*0.5533f,width*0.048f,width*0.5533f,width*0.24f,paintCyan);//中分线

//        paintCyan.setStrokeWidth(10);

        //--------------------------------------------中分线----------------------------------------



        //------------------------------------------新手标相关--------------------------------------
        if (ifNew){//是否时新手标
            canvas.drawRect(width*0.472f,width*0.048f,width*0.5066f,width*0.08f,paintCyan);
            paintWhite.setTextSize(width*0.028f);
            canvas.drawText("新",width*0.4756f,width*0.07366f,paintWhite);


            //-------------------------新手专享图片------------------------------------

            if (drawImage){
                float bitScale2=0.16f;//位图1高与控件高的比例
                int bitWidth2= (int) (width*bitScale2);//控件的高度
                float initScale2=1.29f;//位图1左上角位置与控件宽度的比例
                float initScaleh2=21f;//位图1左上角位置与控件宽度的比例

                rectImag2.left= (int) (width/initScale2);
                rectImag2.top= (int) (width/initScaleh2);
                rectImag2.right=rectImag2.left+bitWidth2;
                rectImag2.bottom=rectImag2.top+bitWidth2;


                canvas.drawBitmap(bitmapRight,null,rectImag2,null);
            }

        }
        //------------------------------------------新手标相关--------------------------------------


        if (!closeBottomLine){
            //------------------------------------------投资进度--------------------------------------


            rectF.left=width*underlineScale;
            rectF.top=width*0.27365f;

            rectF.right=rectF.left+width*0.10407f;

            if (rectF.right-rectF.left>width-rectF.left){
                rectF.left=width-width*0.10407f;
                rectF.right=width;
            }

            rectF.bottom=width*0.30494f;

            //-------------------------------------------------
            rect.left=0;
            rect.top= (int) (width*0.2893f);
            rect.right=width;
            rect.bottom=getHeight();
            canvas.drawRect(rect,paintWhite);
            //-------------------------------------------------


            canvas.drawRoundRect(rectF,width*0.01388f,width*0.01388f,paintCyan);



            Log.e("TAG", "onDraw: ---getStrokeWidth--"+paintGray.getStrokeWidth());
            paintGray.setStrokeWidth(6);
            canvas.drawLine(rectF.right,width*0.2893f,width,width*0.2893f,paintGray);

            paintCyan.setStrokeWidth(6);
            canvas.drawLine(0,width*0.2893f,width*underlineScale,width*0.2893f,paintCyan);//底线

            paintWhite.setTextSize(width*0.02777f);
            canvas.drawText(handleProgress(),rectF.left+((rectF.right-rectF.left)/2-paintWhite.measureText(handleProgress())/2),width*0.3f,paintWhite);

            //------------------------------------------投资进度--------------------------------------

        }



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(width, (int) (width*whScale));
    }

    private String handleProgress(){
        if (underlineScale1==0){
            return "0.00%";
        }else {
            return underlineScale1+"%";
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
