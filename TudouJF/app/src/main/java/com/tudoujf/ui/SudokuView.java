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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ===============================================================
 * name:             SudokuView
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/14
 * description：
 * history：
 * *==================================================================
 */

public class SudokuView extends View {

    /**
     * 屏幕宽度
     */
    private int width;
    /**
     * 矩形的宽高
     */
    private float rectWH;
    /**
     * 矩形之间的间距
     */
    private float interval;
    /**
     * 矩形的圆角大小
     */
    private float radious;
    /**
     * 矩形的list集合
     */
    private List<Rect> list;

    /**
     * 阴影画笔
     */
    private Paint paintShade;
    /**
     * 是否初始化图片资源
     */
    private boolean initBitmap = false;

    /**
     * 选择计数
     */
    private int count = 0;
    /**
     * 矩形的list集合,阴影遍历使用
     */
    private List<Rect> listShade;

    /**
     * 开启阴影覆盖
     */
    private boolean isShade = false;
    /**
     * 点击事件监听以及结束回传
     */
    private ClickEvent listener;
    /**
     * 阴影选择转动速度
     */
    private long sleepTime = 100;

    public void setShade(boolean shade) {
        isShade = shade;
        shadeThread();
    }

    /**
     * 图片资源集合
     */
    private int[] resId = {
            R.drawable.lucky01,
            R.drawable.lucky02,
            R.drawable.lucky03,
            R.drawable.lucky04,
            R.drawable.lucky05,
            R.drawable.lucky06,
            R.drawable.lucky07,
            R.drawable.lucky08,
            R.drawable.lucky09
    };

    private List<Bitmap> bitmapList;

    /**
     * 点击事件监听以及结束回传
     */
    public void setListener(ClickEvent listener) {
        this.listener = listener;
    }

    /**
     * 是否初始化图片资源
     */
    public void setInitBitmap(boolean initBitmap) {
        this.initBitmap = initBitmap;
        initBitmap();
        invalidate();
    }

    /**
     * 可以点击按钮
     */
    private boolean canClick = true;

    /**
     * 可以点击按钮
     */
    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public SudokuView(Context context) {
        this(context, null, 0);
    }

    public SudokuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SudokuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(21)
    public SudokuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        width = ScreenSizeUtils.getScreenWidth(getContext());
        rectWH = width * 0.18f;
        radious = width * 0.01086f;
        interval = (width * 0.5652f - 3 * rectWH) / 2;


        list = new ArrayList<>();
        bitmapList = new ArrayList<>();
        listShade = new ArrayList<>();

        if (initBitmap) {
            initBitmap();
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rect rectF = new Rect();
                rectF.left = (int) (j * rectWH + j * interval);
                rectF.top = (int) (i * rectWH + i * interval);
                rectF.right = (int) (rectF.left + rectWH);
                rectF.bottom = (int) (rectF.top + rectWH);
                list.add(rectF);
            }
        }

        listShade.add(list.get(0));
        listShade.add(list.get(1));
        listShade.add(list.get(2));
        listShade.add(list.get(5));
        listShade.add(list.get(8));
        listShade.add(list.get(7));
        listShade.add(list.get(6));
        listShade.add(list.get(3));


        paintShade = new Paint();
        paintShade.setColor(Color.parseColor("#C0C0C1"));
        paintShade.setAntiAlias(true);
        paintShade.setStyle(Paint.Style.FILL);
        paintShade.setAlpha(170);


    }

    /**
     * 初始化图片资源
     */
    private void initBitmap() {
        for (int aResId : resId) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), aResId);
            bitmapList.add(bitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (initBitmap) {
            for (int i = 0; i < list.size(); i++) {
                canvas.drawBitmap(bitmapList.get(i), null, list.get(i), null);
            }
        } else if (isShade) {
            canvas.drawRect(listShade.get(count % listShade.size()), paintShade);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (initBitmap && canClick) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    if (list.get(4).contains((int) event.getX(), (int) event.getY())) {
                        ToastUtils.showToast(getContext(), "开始抽奖");
                        canClick = false;
                        if (listener != null) {
                            listener.startSel();
                        }
                    }
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int tWidth = (int) (width * 0.5652f);
        int tHeight = (int) (width * 0.5652f);
        setMeasuredDimension(tWidth, tHeight);
    }

    private void shadeThread() {
        new Thread() {
            @Override
            public void run() {
                while (isShade) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    if (count == 100) {
                        count = 0;
                        isShade = false;
                        if (listener != null) {
                            listener.finishSel();
                        }
                        postInvalidate();
                        break;
                    }
                    postInvalidate();
                }
            }
        }.start();
    }


    public interface ClickEvent {
        /**结束阴影选择*/
        void finishSel();
        /**开启阴影选择*/
        void startSel();
    }
}

