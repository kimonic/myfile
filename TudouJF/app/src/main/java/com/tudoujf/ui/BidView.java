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
 * history：
 * * ==================================================
 */

public class BidView extends View {

    /**
     * 画笔
     */
    private Paint blackPaint, cyanPaint, grayLinePaint, orangePaint, whitePaint, grayPaint;

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
    private String investTime = "12个月";
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


        cyanPaint = new Paint();
        cyanPaint.setAntiAlias(true);
        cyanPaint.setColor(Color.parseColor("#129CB8"));
        cyanPaint.setStyle(Paint.Style.FILL);

        grayLinePaint = new Paint();
        grayLinePaint.setAntiAlias(true);
        grayLinePaint.setColor(Color.parseColor("#939393"));
        grayLinePaint.setStyle(Paint.Style.FILL);


        grayPaint = new Paint();
        grayPaint.setAntiAlias(true);
        grayPaint.setColor(Color.parseColor("#999999"));
        grayPaint.setStyle(Paint.Style.FILL);

        orangePaint = new Paint();
        orangePaint.setAntiAlias(true);
        orangePaint.setColor(Color.parseColor("#F48029"));
        orangePaint.setStyle(Paint.Style.FILL);

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.parseColor("#FCFFFF"));
        whitePaint.setStyle(Paint.Style.FILL);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();

        float text1X = width * 0.03809f;
        float text1Y = width * 0.06666f;
        blackPaint.setTextSize(width * 0.03809f);
        canvas.drawText(title, text1X, text1Y, blackPaint);


        float text2X = width * 0.6666f;
        float text2Y = width * 0.06666f;
        cyanPaint.setTextSize(width * 0.03809f);
        canvas.drawText(getResources().getString(R.string.xinshouzhuanxiang), text2X, text2Y, cyanPaint);


        float line1X = width * 0.03809f;
        float line2X;
        if (award) {
            line2X = width * 0.9019f;
        } else {
            line2X = width * 0.9619f;
        }
        float lineY = width * 0.09523f;
        grayLinePaint.setStrokeWidth(2);
        canvas.drawLine(line1X, lineY, line2X, lineY, grayLinePaint);


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


        float text3X = width * 0.07714f;
        float text3Y = width * 0.1809f;
        orangePaint.setTextSize(width * 0.05714f);
        canvas.drawText(nianHuaShouYi, text3X, text3Y, orangePaint);


        grayLinePaint.setTextSize(width * 0.03809f);
        float text4X = text3X + orangePaint.measureText(nianHuaShouYi) / 2
                - grayLinePaint.measureText(getResources().getString(R.string.frag_home_yuqinianhuashouyi)) / 2;
        float text4Y = width * 0.2381f;
        canvas.drawText(getResources().getString(R.string.frag_home_yuqinianhuashouyi), text4X, text4Y, grayLinePaint);

        float text5X = width * 0.3904f;
        blackPaint.setTextSize(width * 0.05714f);
        canvas.drawText(investTime, text5X, text3Y, blackPaint);


        float text6X = text5X + blackPaint.measureText(investTime) / 2 - grayLinePaint.measureText(getResources().getString(R.string.qixian)) / 2;
        canvas.drawText(getResources().getString(R.string.qixian), text6X, text4Y, grayLinePaint);


        float text7X = width * 0.6666f;
        blackPaint.setTextSize(width * 0.03809f);
        canvas.drawText(investSum, text7X, text3Y, blackPaint);


        float text8X = text7X + blackPaint.measureText(investSum) / 2 -
                grayLinePaint.measureText(getResources().getString(R.string.jineyuan)) / 2;
        canvas.drawText(getResources().getString(R.string.jineyuan), text8X, text4Y, grayLinePaint);


        float line4X = (width * 0.6666f) * investProgress + line1X;
        float line2Y = width * 0.2857f;
        cyanPaint.setStrokeWidth(width * 0.0057f);
        canvas.drawLine(line1X, line2Y, line4X, line2Y, cyanPaint);

        grayLinePaint.setStrokeWidth(width * 0.0057f);
        float line6X = width * 0.7047f;
        canvas.drawLine(line4X, line2Y, line6X, line2Y, grayLinePaint);

        float text9Y = width * 0.35f;
        blackPaint.setTextSize(width * 0.03809f);

        if (investProgress * 100 != 0) {
            canvas.drawText(decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        } else {
            canvas.drawText(0+decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        }


        float text10X = width * 0.3219f;
        canvas.drawText(getResources().getString(R.string.shengyuketou), text10X, text9Y, grayLinePaint);

        float text12X = text10X + grayLinePaint.measureText(getResources().getString(R.string.shengyuketou)) + 3;
        canvas.drawText(shengYuKeTou, text12X, text9Y, blackPaint);


        rectF.left = width * 0.7428f;
        rectF.top = width * 0.2761f;
        rectF.right = width * 0.9619f;
        rectF.bottom = width * 0.3523f;


        whitePaint.setTextSize(width * 0.03809f);
        if (investNow) {
            canvas.drawRoundRect(rectF, 5, 5, cyanPaint);
            String invest1 = getResources().getString(R.string.frag_home_lijitouzi);
            float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(invest1) / 2;
            float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
            canvas.drawText(invest1, text11X, text11Y, whitePaint);
        } else {
            String invest1 = getResources().getString(R.string.huankuanzhong);
            canvas.drawRoundRect(rectF, 5, 5, grayPaint);
            float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(invest1) / 2;
            float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
            canvas.drawText(invest1, text11X, text11Y, whitePaint);
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
        }
        height = (int) (width * 0.4);
        setMeasuredDimension(width, height);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float currentX = event.getX();
            float currentY = event.getY();
            if (rectF.contains(currentX, currentY)) {
                if (listener != null && investNow) {
                    listener.clickEvent();
                }
            }
        }
        return true;
    }

    public interface ClickEventListener {
        void clickEvent();
    }
}
