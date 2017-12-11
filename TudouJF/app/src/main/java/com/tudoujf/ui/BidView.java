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
import android.view.MotionEvent;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;

import java.text.DecimalFormat;

/**
 * * ==================================================
 * name:            BidView+
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/21
 * description：   managemoneymattersfragment理财页-投标信息展示view
 * history：  最后修改17.12.11
 * * ==================================================
 */

public class BidView extends View {

    /**
     * 画笔
     */
    private Paint blackPaint, cyanPaint, grayLinePaint, orangePaint, whitePaint, grayPaint, topLinePaint,orangePaintS,blackPaintS
            ,strokePaint;

    private Path path = new Path(), textPath = new Path();
    private RectF rectF = new RectF();

    /**
     * 是否包含  奖**.**%
     */
    private boolean award = true;
    /**
     * 奖励的百分比,以浮点型数值表示,实际数值为该值除以100
     */
    private float awardValue = 8.88f;
    /**
     * 标题
     */
    private String title = "房产抵押贷款20170327003";
    /**
     * 年化收益
     */
    private String nianHuaShouYi = "8.00%";
    /**
     * 投资期限
     */
    private String investTime = "";
    /**
     * 投资金额
     */
    private String investSum = "8,888.88";
    /**
     * 投资进度
     */
    private float investProgress = 0.5f;
    private DecimalFormat decimalFormat = new DecimalFormat(".00");
    private String shengYuKeTou = "999,999.00元";

    /**
     * 立即投资点击事件
     */
    private ClickEventListener listener;
    /**
     * 是否是立即投资
     */
    private boolean investNow = true;
    /**
     * 立即投资,已还完,还款中等状态
     */
    private String buttonText = "";
    /**
     * 是否是新手标
     */
    private String isNewer;


    public BidView(Context context) {
        this(context, null, 0);
    }

    public BidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public BidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.parseColor("#343434"));
        blackPaint.setStyle(Paint.Style.FILL);

        blackPaintS = new Paint();
        blackPaintS.setAntiAlias(true);
        blackPaintS.setColor(Color.parseColor("#343434"));
        blackPaintS.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(Color.parseColor("#0b657b"));
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(1);


        cyanPaint = new Paint();
        cyanPaint.setAntiAlias(true);
        cyanPaint.setColor(Color.parseColor("#149bbc"));
        cyanPaint.setStyle(Paint.Style.FILL);

        grayLinePaint = new Paint();
        grayLinePaint.setAntiAlias(true);
        grayLinePaint.setColor(Color.parseColor("#dddddd"));
        grayLinePaint.setStyle(Paint.Style.FILL);


        grayPaint = new Paint();
        grayPaint.setAntiAlias(true);
        grayPaint.setColor(Color.parseColor("#999999"));
        grayPaint.setStyle(Paint.Style.FILL);

        orangePaint = new Paint();
        orangePaint.setAntiAlias(true);
        orangePaint.setColor(Color.parseColor("#F48029"));
        orangePaint.setStyle(Paint.Style.FILL);

        orangePaintS = new Paint();
        orangePaintS.setAntiAlias(true);
        orangePaintS.setColor(Color.parseColor("#F48029"));
        orangePaintS.setStyle(Paint.Style.FILL);

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.parseColor("#FCFFFF"));
        whitePaint.setStyle(Paint.Style.FILL);

        topLinePaint = new Paint();
        topLinePaint.setAntiAlias(true);
        topLinePaint.setColor(Color.parseColor("#D6D6D6"));
        topLinePaint.setStyle(Paint.Style.FILL);
        topLinePaint.setStrokeWidth(3);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int heigt = getHeight();

////-------------------------------原代码-----------------------------------
//        float text1X = width * 0.03809f;
//        float text1Y = width * 0.06666f;
//        blackPaint.setTextSize(width * 0.03809f);
//        canvas.drawText(title, text1X, text1Y, blackPaint);
//
//
//        float text2X = width * 0.6666f;
//        float text2Y = width * 0.06666f;
//        cyanPaint.setTextSize(width * 0.03809f);
//        canvas.drawText(isNewer, text2X, text2Y, cyanPaint);
////-----------------------------原代码-------------------------------------

//---------------------------------上下边线---------------------------------------------------------
        canvas.drawLine(0, 0, width, 0, topLinePaint);
        canvas.drawLine(0, heigt, width, heigt, topLinePaint);
//---------------------------------上下边线---------------------------------------------------------


//---------------------------------标题-------------------------------------------------------------

        float text1X = width * 0.03148f;
        float text1Y = width * 0.071296f;
        blackPaint.setTextSize(width * 0.038888f);
        canvas.drawText(title, text1X, text1Y, blackPaint);
//---------------------------------标题-------------------------------------------------------------

// ---------------------------------新手专享-------------------------------------------------------------
        float text2X = width * 0.6759f;
        float text2Y = width * 0.075925f;
        cyanPaint.setTextSize(width * 0.046296f);
        canvas.drawText(isNewer, text2X, text2Y, cyanPaint);
//---------------------------------新手专享-------------------------------------------------------------

//------------------------------------------标题下划线----------------------------------------------
        float line1X = width * 0.03809f;
        float line2X;
        if (award) {
            line2X = width * 0.9019f;
        } else {
            line2X = width * 0.9619f;
        }
        float lineY = width * 0.093518f;
        grayLinePaint.setStrokeWidth(3);
        canvas.drawLine(line1X, lineY, line2X, lineY, grayLinePaint);

//        float line1X = width * 0.03809f;
//        float line2X;
//        if (award) {
//            line2X = width * 0.9019f;
//        } else {
//            line2X = width * 0.9619f;
//        }
//        float lineY = width * 0.09523f;
//        grayLinePaint.setStrokeWidth(2);
//        canvas.drawLine(line1X, lineY, line2X, lineY, grayLinePaint);
//


//------------------------------------------标题下划线----------------------------------------------

//-------------------------------------------橙色块--奖0.05%----------------------------------------
        if (award) {
            path.moveTo(width * 0.8285f, 0);
            path.lineTo(width * 0.9142f, 0);
            path.lineTo(width, width * 0.08571f);
            path.lineTo(width, width * 0.1619f);
            path.close();
            canvas.drawPath(path, orangePaint);


            textPath.moveTo(width * 0.8761f, width * 0.02857f);
            textPath.lineTo(width * 0.9714f, width * 0.1238f);
            whitePaint.setTextSize(width * 0.03809f);
            canvas.drawTextOnPath("奖" + awardValue + "%", textPath, 0, 0, whitePaint);
        }

//-------------------------------------------橙色块--奖0.05%----------------------------------------

        //-----------------------------8.00%--------------------------------------------------------

        float text3X = width * 0.10714f;
        float text3Y = width * 0.1809f;
        orangePaint.setTextSize(width * 0.05555f);


        Log.e("TAG", "onDraw: --理财列表---" + nianHuaShouYi);

        canvas.drawText(nianHuaShouYi, text3X, text3Y, orangePaint);
        float eX = text3X + orangePaint.measureText(nianHuaShouYi);
        orangePaintS.setTextSize(width * 0.03333f);
        canvas.drawText("%", eX, text3Y, orangePaintS);


        //-----------------------------8.00%--------------------------------------------------------


        //-----------------------------文本预期年化收益--------------------------------------------------------
        grayPaint.setTextSize(width * 0.033333f);
        float text4X = text3X + orangePaint.measureText(nianHuaShouYi) / 2+orangePaintS.measureText("%")/2
                - grayPaint.measureText(getResources().getString(R.string.frag_home_yuqinianhuashouyi)) / 2;
        float text4Y = width * 0.24537f;
        canvas.drawText(getResources().getString(R.string.frag_home_yuqinianhuashouyi), text4X, text4Y, grayPaint);
        //-----------------------------文本预期年化收益--------------------------------------------------------

        //-----------------------------一个月--------------------------------------------------------
        float text5X = width * 0.4074f;
        blackPaint.setTextSize(width * 0.05555f);
        canvas.drawText(investTime, text5X, text3Y, blackPaint);
        blackPaintS.setTextSize(width * 0.03611f);
        canvas.drawText("个月", text5X+blackPaint.measureText(investTime), text3Y, blackPaintS);

        //-----------------------------一个月--------------------------------------------------------


        //-----------------------------文本期限--------------------------------------------------------
        float text6X = text5X + blackPaint.measureText(investTime) / 2+blackPaintS.measureText("个月") / 2 - grayPaint.measureText(getResources().getString(R.string.qixian)) / 2;
        canvas.drawText(getResources().getString(R.string.qixian), text6X, text4Y, grayPaint);
        //-----------------------------文本期限--------------------------------------------------------


        //-----------------------------30000元--------------------------------------------------------
        float text7X = width * 0.67129f;
        blackPaint.setTextSize(width * 0.033333f);
        canvas.drawText(investSum, text7X, text3Y, blackPaint);
        //-----------------------------30000元--------------------------------------------------------


        //-----------------------------文本金额(元)--------------------------------------------------------
        float text8X = text7X + blackPaint.measureText(investSum) / 2 -
                grayPaint.measureText(getResources().getString(R.string.jineyuan)) / 2;
        canvas.drawText(getResources().getString(R.string.jineyuan), text8X, text4Y, grayPaint);

        //-----------------------------文本金额(元)--------------------------------------------------------


        //--------------------------------------进度条蓝色线-------------------------------------------
        float line4X = (width * 0.6795f) * investProgress + line1X;
        float line2Y = width * 0.2857f;
//        cyanPaint.setStrokeWidth(width * 0.0057f);
        cyanPaint.setStrokeWidth(8);
        canvas.drawLine(line1X, line2Y, line4X, line2Y, cyanPaint);
        //--------------------------------------进度条蓝色线-------------------------------------------

        //--------------------------------------进度条灰色线-------------------------------------------
//        grayLinePaint.setStrokeWidth(width * 0.0057f);
        grayLinePaint.setStrokeWidth(8);
        float line6X = width * 0.71759f;
        canvas.drawLine(line4X, line2Y, line6X, line2Y, grayLinePaint);
        //--------------------------------------进度条灰色线-------------------------------------------

        //--------------------------------------进度文本:100.00%-------------------------------------------

        float text9Y = width * 0.35f;
        blackPaint.setTextSize(width * 0.02962f);

        if (investProgress * 100 != 0) {
            canvas.drawText(decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        } else {
            canvas.drawText(0 + decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        }
        //--------------------------------------进度文本:100.00%-------------------------------------------


        //--------------------------------------剩余可投金额:100000元-------------------------------------------
        float text10X = width * 0.3219f;
        canvas.drawText(getResources().getString(R.string.shengyuketou), text10X, text9Y, grayPaint);

        float text12X = text10X + grayPaint.measureText(getResources().getString(R.string.shengyuketou)) + 3;
        canvas.drawText(shengYuKeTou, text12X, text9Y, blackPaint);
        //--------------------------------------剩余可投金额:100000元-------------------------------------------


        rectF.left = width * 0.76388f;
        rectF.top = width * 0.2761f;
        rectF.right = width * 0.98148f;
        rectF.bottom = width * 0.3523f;


        whitePaint.setTextSize(width * 0.03809f);
        if (investNow) {
            canvas.drawRoundRect(rectF, 5, 5, cyanPaint);
//            String invest1 = getResources().getString(R.string.frag_home_lijitouzi);
            float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(buttonText) / 2;
            float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
            canvas.drawText(buttonText, text11X, text11Y, whitePaint);
            strokePaint.setColor(Color.parseColor("#0b657b"));
            canvas.drawRoundRect(rectF, 5, 5, strokePaint);
        } else {
//            String invest1 = getResources().getString(R.string.huankuanzhong);
            canvas.drawRoundRect(rectF, 5, 5, grayPaint);
            float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(buttonText) / 2;
            float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
            canvas.drawText(buttonText, text11X, text11Y, whitePaint);
            strokePaint.setColor(Color.parseColor("#666666"));
            canvas.drawRoundRect(rectF, 5, 5, strokePaint);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = ScreenSizeUtils.getScreenWidth(getContext());
            height = (int) (width * 0.4);
            setMeasuredDimension(width, height);
        } else {
            height = (int) (width * 0.4);
            setMeasuredDimension(width, height);
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float currentX = event.getX();
            float currentY = event.getY();
            if (rectF.contains(currentX, currentY)) {
//                if (listener != null && investNow) {
                if (listener != null) {
                    listener.clickEvent();
                }
            }
        }
        return true;
    }

    public interface ClickEventListener {
        void clickEvent();
    }

    /**
     * 是否是新手标
     */
    public String getIsNewer() {
        return isNewer;
    }

    /**
     * 是否是新手标
     */

    public void setIsNewer(String isNewer) {
        this.isNewer = isNewer;
    }


    /**
     * 按钮文本
     */
    public String getButtonText() {
        return buttonText;
    }

    /**
     * 按钮文本
     */

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    /**
     * 是否显示奖***%
     */
    public void setAward(boolean award) {
        this.award = award;
    }


    /**
     * 奖励的数值
     */
    public void setAwardValue(float awardValue) {
        this.awardValue = awardValue;
    }


    /**
     * 显示的标题
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * 年化收益率
     */
    public void setNianHuaShouYi(String nianHuaShouYi) {
        this.nianHuaShouYi = nianHuaShouYi;
    }


    /**
     * 投资期限
     */
    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }


    /**
     * 可投资金额
     */
    public void setInvestSum(String investSum) {
        this.investSum = investSum;
    }

    /**
     * 投资进度
     */
    public void setInvestProgress(float investProgress) {
        this.investProgress = investProgress;
    }


    /**
     * 剩余可投金额
     */
    public void setShengYuKeTou(String shengYuKeTou) {
        this.shengYuKeTou = shengYuKeTou;
    }


    /**
     * 立即投资按钮监听回调
     */
    public void setListener(ClickEventListener listener) {
        this.listener = listener;
    }

    /**
     * 是否是立即投资按钮
     */
    public void setInvestNow(boolean investNow) {
        this.investNow = investNow;
    }
}
