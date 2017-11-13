package com.tudoujf.ui.mytest;

import android.annotation.TargetApi;
import android.content.Context;
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
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ===============================================================
 * name:             GameView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/10/30
 * description：  自定义游戏控件
 * history：
 * *==================================================================
 */

public class GameView extends View {

    private Rect rect = new Rect();
    /**
     * 各色画笔
     */
    private Paint paintWhite, paintRed, paintBlue, paintYellow, paintGreen;

    /**
     * 绘制的小矩形集合
     */
    private List<RectF> listRect;
    /**
     * 小矩形绘制的颜色标识集合
     */
    private List<Integer> listColorFlag;
    /**
     * 计数器
     */
    private int count = 0;
    /**
     * 线程关闭标识
     */
    private boolean threadFlag = false;
    /**
     * 控件的宽高
     */
    private float width, height;
    /**
     * 结果回调接口
     */
    private OnResult listener;
    /**
     * 线程生成随机数的时间间隔
     */
    private int sleepTime = 10;
    /**
     * 各种颜色在本期内出现的次数
     */
    private int redCount = 0, blueCount = 0, yellowCount = 0, greenCount = 0;
    /**
     * 倒计时
     */
    private int reciprocal;

    /**设置游戏周期*/
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * 设置结果回调接口
     */
    public void setListener(OnResult listener) {
        this.listener = listener;
    }

    /**
     * 控制线程的关闭与开启
     */
    public void setThreadFlag(boolean threadFlag) {
        this.threadFlag = threadFlag;
        if (threadFlag) {
            startRandom();
        }
    }

    public GameView(Context context) {
        this(context, null, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @TargetApi(21)
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        rect.left = 0;
        rect.top = 0;
        rect.right = 90;
        rect.bottom = 90;

        paintWhite = initPaint("#FFFFFF");
        paintWhite.setTextSize(39);

        paintRed = initPaint("#E23114");
        paintYellow = initPaint("#FFCA27");
        paintBlue = initPaint("#297B9F");
        paintGreen = initPaint("#85CE20");


        listRect = new ArrayList<>();
        listColorFlag = new ArrayList<>();

        width = ScreenSizeUtils.getScreenWidth(getContext());

        float gap = (width - 900) / 11;
        int startHeight = 30;
        float size = width * 0.08333f;
        height = startHeight * 2 + 10 * size + 9 * gap + 100;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                RectF rectF = new RectF(gap + (size + gap) * j,
                        startHeight + i * (size + gap),
                        (size + gap) * (j + 1),
                        startHeight + i * (size + gap) + size);
                listRect.add(rectF);
                listColorFlag.add(0);
            }

        }

        startRandom();

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.parseColor("#48AEFA"));

        for (int i = 0; i < listRect.size(); i++) {
            switch (listColorFlag.get(i)) {
                case 0://默认色
                    canvas.drawRect(listRect.get(i), paintWhite);
                    break;
                case 1://红色
                    canvas.drawRect(listRect.get(i), paintRed);
                    break;
                case 2://黄色
                    canvas.drawRect(listRect.get(i), paintYellow);
                    break;
                case 3://蓝色
                    canvas.drawRect(listRect.get(i), paintBlue);
                    break;
                case 4://绿色
                    canvas.drawRect(listRect.get(i), paintGreen);
                    break;
            }
        }

        //回调当前结果
        if (count - 1 > -1 && count < listColorFlag.size() + 1&&reciprocal==0) {
            returnResult(listColorFlag.get(count - 1));
//            ToastUtils.showToast(getContext(),"当前的结果是:     "+listColorFlag.get(count-1));
        }

        String temp = "剩余开奖时间" + reciprocal + "秒";
        canvas.drawText(temp, width / 2 - paintWhite.measureText(temp) / 2, height - 50, paintWhite);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = (int) width;
        int mHeight = (int) height;

        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 生成随机数的线程
     */
    private void startRandom() {
        new Thread() {
            @Override
            public void run() {
                while (threadFlag) {
                    for (int i = 0; i < sleepTime; i++) {
                        try {
                            Thread.sleep(1000);
                            reciprocal = sleepTime - i - 1;
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (count < 100) {
                        int random = (int) (Math.random() * 100);
                        if (random < 41) {
                            listColorFlag.set(count, 4);
                            greenCount++;
                        } else if (random > 40 && random < 76) {
                            listColorFlag.set(count, 3);
                            blueCount++;
                        } else if (random > 75 && random < 95) {
                            listColorFlag.set(count, 2);
                            yellowCount++;
                        } else {
                            listColorFlag.set(count, 1);
                            redCount++;
                        }
                    }

                    postInvalidate();
                    count++;
                    if (count == 101) {
                        if (listener!=null){
                            listener.oneFinish( redCount,  blueCount,  yellowCount,  greenCount);
                        }
                        count = 0;
                        redCount = 0;
                        blueCount = 0;
                        yellowCount = 0;
                        greenCount = 0;
                        listColorFlag.clear();
                        for (int i = 0; i < 100; i++) {
                            listColorFlag.add(0);
                        }
                    }
                }
            }
        }.start();
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
     * 返回结果
     */
    private void returnResult(int result) {
        if (listener != null) {
            listener.onResult(result);
            listener.resultCount(redCount, blueCount, yellowCount, greenCount);
        }
    }


    public interface OnResult {
        /**
         * 上次中奖的类型
         */
        void onResult(int flag);

        /**
         * 本期内各种类型出现的次数
         */
        void resultCount(int redCount, int blueCount, int yellowCount, int greenCount);

        /**一轮结束*/
        void oneFinish(int redCount, int blueCount, int yellowCount, int greenCount);
    }
}
