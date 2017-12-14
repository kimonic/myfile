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
import com.tudoujf.utils.StringUtils;

import java.text.DecimalFormat;

/**
 * * ==================================================
 * name:            ClaimsBidView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：   managemoneymattersfragment理财页-债权投标信息展示view
 * history：
 * * ==================================================
 */

public class ClaimsBidView extends View {

    /**
     * 画笔
     */
    private Paint blackPaint, cyanPaint, grayLinePaint, orangePaint, whitePaint, grayPaint, strokePaint;

    private Path path = new Path(), textPath = new Path();
    private RectF rectF = new RectF();


    /**
     * 奖励的百分比,以浮点型数值表示,实际数值为该值除以100
     */
    private float awardValue = 8.88f;
    /**
     * 标题
     */
    private String title = "房产抵押贷款20170327003";
    /**
     * 债权价值
     */
    private String zhaiQuanJiaZhi = "1,800.00";
    /**
     * 原标年化收益
     */
    private String yuanBiaoNianHuaShouYi = "14%";
    /**
     * 转让价格元
     */
    private String transferPrice = "888,888.88";
    /**
     * 投资进度
     */
    private float investProgress = 0.5f;
    private DecimalFormat decimalFormat = new DecimalFormat(".00");
    private String shengYuKeTou = "999,999.00元";
    /**
     * 按钮文本显示判断
     */
    private String status = "";
    private Paint topLinePaint;

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 立即投资点击事件
     */
    private ClickEventListener listener;
    /**
     * 是否是立即投资
     */
    private boolean investNow = false;

    /**
     * 转让期数
     */
    private String transfer = "转让期数:7/12";


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

    public ClaimsBidView(Context context) {
        this(context, null, 0);
    }

    public ClaimsBidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClaimsBidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public ClaimsBidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.parseColor("#FCFFFF"));
        whitePaint.setStyle(Paint.Style.FILL);


        topLinePaint = new Paint();
        topLinePaint.setAntiAlias(true);
        topLinePaint.setColor(Color.parseColor("#D6D6D6"));
        topLinePaint.setStyle(Paint.Style.FILL);
        topLinePaint.setStrokeWidth(3);


        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(Color.parseColor("#0b657b"));
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(1);


    }

    /**
     * 转让期数
     */
    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    /**
     * 债权价值
     */
    public void setZhaiQuanJiaZhi(String zhaiQuanJiaZhi) {
        this.zhaiQuanJiaZhi = zhaiQuanJiaZhi;
    }

    /**
     * 原标年化收益
     */
    public void setYuanBiaoNianHuaShouYi(String yuanBiaoNianHuaShouYi) {
        this.yuanBiaoNianHuaShouYi = yuanBiaoNianHuaShouYi;
    }

    /**
     * 转让价格
     */
    public void setTransferPrice(String transferPrice) {
        this.transferPrice = transferPrice;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int heigt = getHeight();

        //---------------------------------上下边线---------------------------------------------------------
        canvas.drawLine(0, 0, width, 0, topLinePaint);
        canvas.drawLine(0, heigt, width, heigt, topLinePaint);
        //---------------------------------上下边线---------------------------------------------------------


        //-----------------------标题--房产抵押贷款20170327003--------------------------------------
//        float text1X = width * 0.03809f;
//        float text1Y = width * 0.06666f;
//        blackPaint.setTextSize(width * 0.03809f);
//        canvas.drawText(title, text1X, text1Y, blackPaint);

        float text1X = width * 0.03148f;
        float text1Y = width * 0.07066f;
        blackPaint.setTextSize(width * 0.04133f);
        canvas.drawText(title, text1X, text1Y, blackPaint);
        //------------------------------------------------------------------------------------------

        //-----------------------转让期数:7/12------------------------------------------------------
//        float text2X = width * 0.6666f;
//        float text2Y = width * 0.06666f;
//        cyanPaint.setTextSize(width * 0.03809f);
//        canvas.drawText(transfer, text2X, text2Y, cyanPaint);

        float text2X = width * 0.6759f;
        float text2Y = width * 0.07066f;
        cyanPaint.setTextSize(width * 0.04133f);
        canvas.drawText(transfer, text2X, text2Y, cyanPaint);
        //------------------------------------------------------------------------------------------

        //----------------------------标题下直线----------------------------------------------------
        float line1X = width * 0.03809f;
        float line2X = width * 0.9619f;
//        float lineY = width * 0.09523f;
        float lineY = width * 0.093518f;
        grayLinePaint.setStrokeWidth(3);
        canvas.drawLine(line1X, lineY, line2X, lineY, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //-----------------------债权价值(元)-------------------------------------------------------
        grayPaint.setTextSize(width * 0.03333f);
        float text4X = width * 0.06f;
//                text3X + orangePaint.measureText(zhaiQuanJiaZhi) / 2
//                - grayPaint.measureText(getResources().getString(R.string.frag_home_zhaiquanjiazhiyuan)) / 2;
        float text4Y = width * 0.2481f;
        canvas.drawText(getResources().getString(R.string.frag_home_zhaiquanjiazhiyuan), text4X, text4Y, grayPaint);
        //------------------------------------------------------------------------------------------

        //-----------------债权价值(元)上方数字显示---------------------------------------------------
        orangePaint.setTextSize(width * 0.05266f);
        float text3X = text4X + grayPaint.measureText(getResources().getString(R.string.frag_home_zhaiquanjiazhiyuan)) / 2
                - orangePaint.measureText(zhaiQuanJiaZhi) / 2;
//        float text3X = width * 0.06133f;
        float text3Y = width * 0.1673f;
        canvas.drawText(zhaiQuanJiaZhi, text3X, text3Y, orangePaint);

        //------------------------------------------------------------------------------------------


        //---------------原标年化收益---------------------------------------------------------------
        float text6X = width * 0.3466f;
//                text5X + blackPaint.measureText(yuanBiaoNianHuaShouYi) / 2 - grayPaint.measureText(getResources().getString(R.string.yuanbiaonianhuashouyi)) / 2;
        canvas.drawText(getResources().getString(R.string.yuanbiaonianhuashouyi), text6X, text4Y, grayPaint);
        //------------------------------------------------------------------------------------------


        //---------------原标年化收益数字显示14%----------------------------------------------------
        blackPaint.setTextSize(width * 0.05266f);
        float text5X = text6X + grayPaint.measureText(getResources().getString(R.string.yuanbiaonianhuashouyi)) / 2 -
                blackPaint.measureText(yuanBiaoNianHuaShouYi) / 2;
//        float text5X = width * 0.4013f;
        canvas.drawText(yuanBiaoNianHuaShouYi, text5X, text3Y, blackPaint);

        float baiX = text5X + blackPaint.measureText(yuanBiaoNianHuaShouYi)+5;
        blackPaint.setTextSize(width * 0.03566f);
        canvas.drawText("%", baiX, text3Y, blackPaint);

        //------------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------------------
        float text8X = width * 0.6466f;
//                text7X + blackPaint.measureText(transferPrice) / 2 - grayPaint.measureText(getResources().getString(R.string.zhuanrangjiageyuan)) / 2;
        canvas.drawText(getResources().getString(R.string.zhuanrangjiageyuan), text8X, text4Y, grayPaint);
        //------------------------------------------------------------------------------------------


        //----------------转让价格(元)显示数字------------------------------------------------------
        blackPaint.setTextSize(width * 0.03566f);
        float text7X = text8X + grayPaint.measureText(getResources().getString(R.string.zhuanrangjiageyuan)) / 2 -
                blackPaint.measureText(transferPrice) / 2;
//        float text7X = width * 0.6493f;
        canvas.drawText(transferPrice, text7X, text3Y, blackPaint);
        //------------------------------------------------------------------------------------------


        //--------------------进度显示直线---已进行部分---------------------------------------------
        float line4X = (width * 0.6666f) * investProgress + line1X;
        float line2Y = width * 0.306f;
//        cyanPaint.setStrokeWidth(width * 0.0057f);
        cyanPaint.setStrokeWidth(5);
        canvas.drawLine(line1X, line2Y, line4X, line2Y, cyanPaint);
        //------------------------------------------------------------------------------------------

        //--------------------进度显示直线---未完成部分---------------------------------------------
//        grayLinePaint.setStrokeWidth(width * 0.0057f);
        float line6X = width * 0.7047f;
        grayLinePaint.setStrokeWidth(5);
        canvas.drawLine(line4X, line2Y, line6X, line2Y, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //--------------------进度显示文本--50.00%--------------------------------------------------
        float text9Y = width * 0.36f;
        float text9X = width * 0.04666f;
        blackPaint.setTextSize(width * 0.03333f);

        if (investProgress * 100 != 0) {
            canvas.drawText(decimalFormat.format(investProgress * 100) + "%", text9X, text9Y, blackPaint);
        } else {
            canvas.drawText(0 + decimalFormat.format(investProgress * 100) + "%", text9X, text9Y, blackPaint);
        }
        //------------------------------------------------------------------------------------------


        //------------------------剩余可投:--固定文本-----------------------------------------------
        grayPaint.setTextSize(width * 0.03333f);
        float text10X = width * 0.3219f;
        canvas.drawText(getResources().getString(R.string.shengyuketou), text10X, text9Y, grayPaint);
        //------------------------------------------------------------------------------------------


        //------------------------剩余可投金额--999,999.00元----------------------------------------
        float text12X = text10X + grayPaint.measureText(getResources().getString(R.string.shengyuketou)) + 3;
        canvas.drawText(shengYuKeTou, text12X, text9Y, blackPaint);
        //------------------------------------------------------------------------------------------


        //-----------------按钮部分-----------------------------------------------------------------
        rectF.left = width * 0.7428f;
        rectF.top = width * 0.286f;//0.296
        rectF.right = width * 0.9619f;
        rectF.bottom = width * 0.3622f;


        whitePaint.setTextSize(width * 0.03809f);
        String invest1;
        if (status.equals("1")) {
            invest1 = getResources().getString(R.string.act_productdetails_lijigoumai);
            canvas.drawRoundRect(rectF, 5, 5, cyanPaint);

            strokePaint.setColor(Color.parseColor("#0b657b"));
            canvas.drawRoundRect(rectF, 5, 5, strokePaint);
//            float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(invest1) / 2;
//            float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
//            canvas.drawText(invest1, text11X, text11Y, whitePaint);
        } else if (status.equals("2")) {
            canvas.drawRoundRect(rectF, 5, 5, grayPaint);
            strokePaint.setColor(Color.parseColor("#666666"));
            canvas.drawRoundRect(rectF, 5, 5, strokePaint);
            invest1 = getResources().getString(R.string.shouqing);
        } else {
            canvas.drawRoundRect(rectF, 5, 5, grayPaint);
            strokePaint.setColor(Color.parseColor("#666666"));
            canvas.drawRoundRect(rectF, 5, 5, strokePaint);
            invest1 = getResources().getString(R.string.chexiao);
        }
        float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(invest1) / 2;
        float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
        canvas.drawText(invest1, text11X, text11Y, whitePaint);
        //------------------------------------------------------------------------------------------


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = ScreenSizeUtils.getScreenWidth(getContext());
            height = (int) (width * 0.4f);
            setMeasuredDimension(width, height);
        } else {
            height = (int) (width * 0.4f);
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
}

