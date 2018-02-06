package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;

/**
 * * ==================================================
 * name:            MRedView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   红包,加息券,返现券view-->完全自绘
 * history：
 * * ==================================================
 */

public class MRedView extends View {

    /**
     * 背景路径
     */
    private Path path;
    /**
     * 红包背景弧线
     */
    private Path pathL;
    /**
     * 红包背景圆角矩形
     */
    private RectF redRect;
    private Paint bacPaint, whitePaint;
    private Paint redPackagePaint;

    /**
     * 要绘制的四中文本,依次是,数字,元或%,有效期至:,日期
     */
    private String oneText = "50";
    private String threeText;
    private String fourText = "2017/07/08";
    private int widthFi = 0;


    /**
     * 是绘制券还是红包
     */
    private boolean redPackageOrQuan = true;
    /**
     * 是有效状态还是无效状态
     */
    private boolean valid = true;
    /**
     * 选中状态
     */
    private boolean sel = false;

    private String twoText = "元";

    public void setSel(boolean sel) {
        this.sel = sel;
    }

    public void setTwoText(String twoText) {
        this.twoText = twoText;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }


    public void setOneText(String oneText) {
        this.oneText = oneText;
    }


    public void setFourText(String fourText) {
        this.fourText = fourText;
    }


    public void setRedPackageOrQuan(boolean redPackageOrQuan) {
        this.redPackageOrQuan = redPackageOrQuan;
    }

    public MRedView(Context context) {
        this(context, null, 0);
    }

    public MRedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MRedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public MRedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        path = new Path();
        pathL = new Path();
        redRect = new RectF();
        threeText = getResources().getString(R.string.youxiaoqizhi);

        bacPaint = initPaint("#F67070");
        whitePaint = initPaint("#FFFFFF");
        redPackagePaint = initPaint("#F99696");
        redPackagePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();//控件宽
        int height = getHeight();//控件高




        if (sel) {
            bacPaint.setColor(Color.parseColor("#EA5A5A"));
        } else {
            bacPaint.setColor(Color.parseColor("#F67070"));
        }

        if (!valid) {
            bacPaint.setColor(Color.parseColor("#999999"));
            redPackagePaint.setColor(Color.parseColor("#8F8F8F"));
        }

        //------------------------绘制背景----------------------------------------------------------
        path.moveTo(0, 0);
        path.lineTo(width, 0);

        int count = 0;
        int radious = 10;//半径
        int radiousW = 18;//横向偏移量
        int offset = 5;//偏移量
        while (count < height + 10) {
            path.quadTo(width - radiousW, count - radious, width, count);
            path.lineTo(width, count + offset);
            count = count + radious * 2 + offset;
        }
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.close();
        canvas.drawPath(path, bacPaint);
        //------------------------绘制背景----------------------------------------------------------

        //----------------绘制第一行文字相关--------------------------------------------------------
        float oneTX;
        float textOneS = width * 0.2285f;
        whitePaint.setTextSize(textOneS);
        //----------------绘制第一行文字相关--------------------------------------------------------


        if (redPackageOrQuan) {
            //------------------------绘制红包背景------------------------------------------------------
            redPackagePaint.setStrokeWidth(3);
            redRect.left = width * 0.6383f;
            redRect.top = width * 0.02128f;
            redRect.right = width * 0.9362f;
            redRect.bottom = width * 0.3617f;
            float roundRadious = width * 0.04254f + 0.5f;
            canvas.drawRoundRect(redRect, roundRadious, roundRadious, redPackagePaint);
            float pathLH = width * 0.08511f;
            float pathLH1 = width * 0.1476f;
            float centerX = redRect.left + (redRect.right - redRect.left) / 2;
            pathL.moveTo(redRect.left, pathLH);
            pathL.quadTo(centerX, pathLH1, redRect.right, pathLH);
            canvas.drawPath(pathL, redPackagePaint);
            redPackagePaint.setStrokeWidth(width * 0.02f);
            float oneX = centerX - width * 0.06382f;
            float oneY = width * 0.1489f;
            float twoY = width * 0.2340f;
            float threeX = centerX + width * 0.06382f;
            float fourY = width * 0.3191f;
            float fiveX = width * 0.7234f;
            float sixX = width * 0.8511f;
            float sixY = width * 0.2553f;
            canvas.drawLine(oneX, oneY, centerX, twoY, redPackagePaint);
            canvas.drawLine(threeX, oneY, centerX, twoY, redPackagePaint);
            canvas.drawLine(centerX, fourY, centerX, twoY, redPackagePaint);
            canvas.drawLine(fiveX, sixY, sixX, sixY, redPackagePaint);
            //------------------------绘制红包背景--------------------------------------------------

            oneTX = width * 0.12f;

        } else {
            //------------------------绘制券背景----------------------------------------------------
            float quanRadious = width * 0.1356f;
            float circleX = width * 0.8723f;
            float circleY = width * 0.1064f;
            redPackagePaint.setStrokeWidth(width * 0.01111f);
            redPackagePaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(circleX, circleY, quanRadious, redPackagePaint);

            float textX = width * 0.7885f;
            float textY = width * 0.1674f;
            float textSize = width * 0.1656f;

            redPackagePaint.setTextSize(textSize);
            redPackagePaint.setStyle(Paint.Style.FILL);
            canvas.drawText(getResources().getString(R.string.quan), textX, textY, redPackagePaint);
            //------------------------绘制券背景----------------------------------------------------

            oneTX = width / 2 - whitePaint.measureText(oneText + twoText) / 2;
        }


        //---------------------------绘制第一行文字---------------------------------------------------------
        float oneTY = width * 0.4457f;

        canvas.drawText(oneText, oneTX, oneTY, whitePaint);
//---------------------------绘制第一行文字---------------------------------------------------------

//---------------------------绘制第二行文字---------------------------------------------------------
        float twoTX = oneTX + 10 + whitePaint.measureText(oneText);
        float textTwoS = width * 0.1657f;
        whitePaint.setTextSize(textTwoS);
        canvas.drawText(twoText, twoTX, oneTY, whitePaint);
//---------------------------绘制第二行文字---------------------------------------------------------


//---------------------------绘制第三行文字---------------------------------------------------------
        float threeTY = width * 0.60f;
        float textThreeS = width * 0.1f;
        whitePaint.setTextSize(textThreeS);
        canvas.drawText(threeText, oneTX, threeTY, whitePaint);
//---------------------------绘制第三行文字---------------------------------------------------------


//---------------------------绘制第四行文字---------------------------------------------------------
        float fourTY = width * 0.73f;
        float textFourS = width * 0.1f;
        whitePaint.setTextSize(textFourS);
        float fourTX=(width-whitePaint.measureText(fourText))/2;
        canvas.drawText(fourText, fourTX, fourTY, whitePaint);
//---------------------------绘制第四行文字---------------------------------------------------------

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            height = 100 * ScreenSizeUtils.getDensity(getContext());
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }

        int width = (int) (height * 1.175f + 0.5f);
        widthFi = width;
        setMeasuredDimension(width, height);
    }

    /**
     * 初始化画笔
     */
    private Paint initPaint(String color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    /**
     * 该函数确定该view在父控件中的位置,并最终确定getwidth()和getheight()的值
     */
    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        int w = ScreenSizeUtils.getScreenWidth(getContext()) - widthFi - 10 * ScreenSizeUtils.getDensity(getContext());
        int w2 = w + widthFi;
        super.layout(w, t, w2, b);
    }

}
