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
 * name:            MyProjectBidView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/5
 * description：   我的项目中---->投标信息展示view
 * history：
 * * ==================================================
 */

public class MyProjectBidView extends View {

    /**
     * 画笔
     */
    private Paint blackPaint, cyanPaint, grayLinePaint, orangePaint, whitePaint, grayPaint, strokePaint;

    private Path path = new Path(), textPath = new Path();
    private RectF rectF = new RectF();


    /**
     * 左侧,中间/右侧说明文字---债权价值(元)/投资金额(元)等
     */
    private String explain1, explain2, explain3;

    /**
     * 按钮文本类型
     */
    private int type;

    /**
     * 状态文本
     */
    private String status_name;


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
    private Paint topLinePaint;


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

    /**
     * 第一项说明文字---债权价值(元)/投资金额(元)等
     */

    public String getExplain1() {
        return explain1;
    }

    /**
     * 第一项说明文字---债权价值(元)/投资金额(元)等
     */
    public void setExplain1(String explain1) {
        this.explain1 = explain1;
    }

    /**
     * 中间说明文本
     */
    public String getExplain2() {
        return explain2;
    }

    /**
     * 中间说明文本
     */

    public void setExplain2(String explain2) {
        this.explain2 = explain2;
    }

    /**
     * 右侧说明文本
     */
    public String getExplain3() {
        return explain3;
    }

    /**
     * 右侧说明文本
     */
    public void setExplain3(String explain3) {
        this.explain3 = explain3;
    }

    /**
     * 按钮文本类型
     */
    public int getType() {
        return type;
    }

    /**
     * 按钮文本类型
     */
    public void setType(int type) {
        this.type = type;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public MyProjectBidView(Context context) {
        this(context, null, 0);
    }

    public MyProjectBidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProjectBidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public MyProjectBidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        float text1X = width * 0.03809f;
        float text1Y = width * 0.06666f;
        blackPaint.setTextSize(width * 0.04166f);
        canvas.drawText(title, text1X, text1Y, blackPaint);
        //------------------------------------------------------------------------------------------

        //-----------------------转让期数:7/12------------------------------------------------------
        float text2X = width * 0.6666f;
        float text2Y = width * 0.06666f;
        cyanPaint.setTextSize(width * 0.03888f);
        canvas.drawText(transfer, text2X, text2Y, cyanPaint);
        //------------------------------------------------------------------------------------------

        //----------------------------标题下直线----------------------------------------------------
        float line1X = width * 0.03809f;
        float line2X = width * 0.9619f;
        float lineY = width * 0.09523f;
        grayLinePaint.setStrokeWidth(2);
        canvas.drawLine(line1X, lineY, line2X, lineY, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //-----------------------左侧介绍文本/待收本息(元)------------------------------------------
        grayLinePaint.setTextSize(width * 0.03148f);
//        grayLinePaint.setTextSize(width * 0.03777f);
        float text4X = width * 0.05555f;
//                text3X + orangePaint.measureText(zhaiQuanJiaZhi) / 2
//                - grayLinePaint.measureText(explain1) / 2;
        float text4Y = width * 0.2581f;
//        float text4Y = width * 0.2381f;
        canvas.drawText(explain1, text4X, text4Y, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //-----------------左侧介绍文本上的数字显示/债权价值(元)上方数字显示------------------------
        orangePaint.setTextSize(width * 0.03333f);
        float text3X = text4X + grayLinePaint.measureText(explain1) / 2 - orangePaint.measureText(zhaiQuanJiaZhi) / 2;
//        float text3X = width * 0.07277f;
        float text3Y = width * 0.1709f;
//        float text3Y = width * 0.1809f;
//        orangePaint.setTextSize(width * 0.05714f);
        canvas.drawText(zhaiQuanJiaZhi, text3X, text3Y, orangePaint);
        //------------------------------------------------------------------------------------------


        //---------------中间展示文本介绍/债权价值(元)----------------------------------------------
        float text6X = width * 0.35648f;
//                text5X + (orangePaint.measureText(yuanBiaoNianHuaShouYi) / 2 - grayLinePaint.measureText(explain2) / 2);
        canvas.drawText(explain2, text6X, text4Y, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //---------------中间展示文本/原标年化收益数字显示14%---------------------------------------
        blackPaint.setTextSize(width * 0.05714f);
        float text5X = text6X + (grayLinePaint.measureText(explain2) / 2 - orangePaint.measureText(yuanBiaoNianHuaShouYi) / 2);
//        float text5X = width * 0.37555f;
        canvas.drawText(yuanBiaoNianHuaShouYi, text5X, text3Y, orangePaint);
        //------------------------------------------------------------------------------------------

        //---------------------右侧展示文本介绍-----------------------------------------------------
        float text8X =width*0.675f;
//                text7X + blackPaint.measureText(transferPrice) / 2 - grayLinePaint.measureText(explain3) / 2;
        canvas.drawText(explain3, text8X, text4Y, grayLinePaint);
        //------------------------------------------------------------------------------------------



        //----------------右侧展示文本/到期时间2018-01-31-----------------------------------------
        blackPaint.setTextSize(width * 0.03333f);
        float text7X = text8X + grayLinePaint.measureText(explain3) / 2-blackPaint.measureText(transferPrice) / 2;
//        float text7X = width * 0.6231f;
        canvas.drawText(transferPrice, text7X, text3Y, blackPaint);
        //------------------------------------------------------------------------------------------





        //--------------------进度显示直线---已进行部分---------------------------------------------
        float line4X = (width * 0.6666f) * investProgress + line1X;
        float line2Y = width * 0.3157f;
//        float line2Y = width * 0.2857f;
        cyanPaint.setStrokeWidth(width * 0.0057f);
        canvas.drawLine(line1X, line2Y, line4X, line2Y, cyanPaint);
        //------------------------------------------------------------------------------------------

        //--------------------进度显示直线---未完成部分---------------------------------------------
        grayLinePaint.setStrokeWidth(width * 0.0057f);
        float line6X = width * 0.7047f;
        canvas.drawLine(line4X, line2Y, line6X, line2Y, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //--------------------进度显示文本--50.00%--------------------------------------------------
        float text9Y = width * 0.37f;
        blackPaint.setTextSize(width * 0.03009f);

        if (investProgress * 100 != 0) {
            canvas.drawText(decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        } else {
            canvas.drawText(0 + decimalFormat.format(investProgress * 100) + "%", text1X, text9Y, blackPaint);
        }
        //------------------------------------------------------------------------------------------


        //------------------------剩余可投:--固定文本-----------------------------------------------
        grayLinePaint.setTextSize(width * 0.03009f);
        float text10X = width * 0.3519f;
        canvas.drawText(getResources().getString(R.string.shengyuketou), text10X, text9Y, grayLinePaint);
        //------------------------------------------------------------------------------------------


        //------------------------剩余可投金额--999,999.00元----------------------------------------
        float text12X = text10X + grayLinePaint.measureText(getResources().getString(R.string.shengyuketou)) + 3;
        canvas.drawText(shengYuKeTou, text12X, text9Y, blackPaint);
        //------------------------------------------------------------------------------------------


        //-----------------按钮部分-----------------------------------------------------------------
        rectF.left = width * 0.7428f;
        rectF.top = width * 0.2961f;
//        rectF.top = width * 0.2761f;
        rectF.right = width * 0.9619f;
        rectF.bottom = width * 0.3723f;
//        rectF.bottom = width * 0.3523f;


        whitePaint.setTextSize(width * 0.03809f);
//        String invest1 = getButText();

//        if (investNow) {//可点击
        canvas.drawRoundRect(rectF, 5, 5, cyanPaint);
//        } else {//不可点击
//            canvas.drawRoundRect(rectF, 5, 5, grayPaint);
//        }
        //--------------------绘制按钮边框----------------------------------------------------------
        strokePaint.setColor(Color.parseColor("#0b657b"));
        canvas.drawRoundRect(rectF, 5, 5, strokePaint);

        float text11X = rectF.left + (rectF.right - rectF.left) / 2 - whitePaint.measureText(status_name) / 2;
        float text11Y = rectF.top + (rectF.bottom - rectF.top) / 2 + whitePaint.getTextSize() / 2.5f;
        canvas.drawText(status_name, text11X, text11Y, whitePaint);


        //------------------------------------------------------------------------------------------


    }

    private String getButText() {
        String text = "";
        investNow = false;
        switch (type) {
            case 1://立即购买
                text = getResources().getString(R.string.act_productdetails_lijigoumai);
                break;
            case 2://售磬
                text = getResources().getString(R.string.shouqing);
                break;
            case 3://回收中
                text = getResources().getString(R.string.huishouzhong);
                investNow = true;
                break;
            case 4://可转让
                text = getResources().getString(R.string.kezhuanrang);
                investNow = true;
                break;
            case 5://回款中
                text = getResources().getString(R.string.huikuanzhong);
                break;
            case 6://已结清
                text = getResources().getString(R.string.yijieqing);
                break;
        }
        return text;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = ScreenSizeUtils.getScreenWidth(getContext());
//            height = (int) (width * 0.3866);
            height = (int) (width * 0.4116);
            setMeasuredDimension(width, height);
        } else {
            height = (int) (width * 0.4116);
//            height = (int) (width * 0.3866);
            setMeasuredDimension(width, height);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
//            float currentX = event.getX();
//            float currentY = event.getY();
//            if (rectF.contains(currentX, currentY)) {
                if (listener != null) {
                    listener.clickEvent();
                }
//            }
        }
        return true;
    }

    public interface ClickEventListener {
        void clickEvent();
    }
}

