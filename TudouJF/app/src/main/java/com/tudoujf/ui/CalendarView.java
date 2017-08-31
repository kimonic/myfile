package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tudoujf.bean.databean.CalendarBean;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ==================================================
 * name:            CalendarDialog
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/23
 * description：   选择日期view
 * history：
 * * ==================================================
 */


public class CalendarView extends View {

    private int screenWidth;
    private int density;
    /**
     * 行数
     */
    private int row = 5;

    /**
     * 画笔
     */
    private Paint grayPaintBac, grayPaint, textPaint, cyanPaint, selPaint;
    /**
     * rectf集合
     */
    private List<RectF> list;
    /**
     * 文字的大小
     */
    private float textSize;

    /**
     * 数据源
     */
    private List<CalendarBean> listData;
    /**
     * 是否时滑动的标识
     */
    private boolean isScroll = false;
    /**
     * 上一次点击的item
     */
    private int beforeClickItem = -1;
    /**
     * 要显示的昂前年月
     */
    private int year, month;
    /**
     * 当前选中的日期
     */
    private String currentSelDayStr;
    /**
     * 监听接口
     */
    private SelDayListener listener;
    /**
     * 当前月的字符串---yyyy年mm月
     */
    private String title;

    public int getYear() {
        return year;
    }

    public void setYearMonth(int year, int month) {
        this.year = year;
        this.month = month;
        setTitle(year + "年" + month + "月");
    }

    public int getMonth() {
        return month;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SelDayListener getListener() {
        return listener;
    }

    public void setListener(SelDayListener listener) {
        this.listener = listener;
    }

    public String getCurrentSelDayStr() {
        return currentSelDayStr;
    }

    public void setCurrentSelDayStr(String currentSelDayStr) {
        this.currentSelDayStr = currentSelDayStr;
    }

    public CalendarView(Context context) {
        this(context, null, 0);
    }

    public CalendarView(Context context, int year, int month) {
        super(context);
        this.year=year;
        this.month=month;
        setTitle(year + "年" + month + "月");
        initView();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        screenWidth = ScreenSizeUtils.getScreenWidth(getContext());
        density = ScreenSizeUtils.getDensity(getContext());

        grayPaint = initPaint(Color.parseColor("#E1E0DC"));
        grayPaint.setStrokeWidth(2);


        grayPaintBac = initPaint(Color.parseColor("#F0F0E8"));
        cyanPaint = initPaint(Color.parseColor("#E6F9FF"));


        textPaint = initPaint(Color.parseColor("#796E74"));
        selPaint = initPaint(Color.parseColor("#159ABB"));


        //---------------临时数据源-----------------------------------------------------------------

        initList(year, month);
        row = listData.size() / 7;
        list = new ArrayList<>();
        for (int i = 0; i < 7 * row; i++) {
            RectF rectF = new RectF();
            list.add(rectF);
        }

        //------------------------------------------------------------------------------------------

    }

    private Paint initPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.WHITE);
        canvas.drawColor(Color.parseColor("#E1E0DC"));

        int width = getWidth();//控件宽度
        int height = getHeight();//控件高度

        float itemWidth = (width - 2 * 8) / 7.0f;//item宽高


        for (int i = 0; i < list.size() / 7; i++) {//行号
            for (int j = 0; j < 7; j++) {//列号
                list.get(i * 7 + j).left = 2 + j * (itemWidth + 2);
                list.get(i * 7 + j).right = (j + 1) * (itemWidth + 2);
                list.get(i * 7 + j).top = 2 + i * (itemWidth + 2);
                list.get(i * 7 + j).bottom = (i + 1) * (2 + itemWidth);
            }
        }
        for (int i = 0; i < list.size(); i++) {//绘制方格背景色
            switch (listData.get(i).getFlag()) {
                case 1:
                    canvas.drawRect(list.get(i), grayPaintBac);
                    break;
                case 2:
                    canvas.drawRect(list.get(i), cyanPaint);
                    break;
                case 3:
                    canvas.drawRect(list.get(i), selPaint);
                    break;
            }
        }
        textSize = itemWidth * 0.3f;
        textPaint.setTextSize(textSize);
        for (int i = 0; i < list.size(); i++) {
            String s = ("" + listData.get(i).getDay());
            float sWidth = textPaint.measureText(s);
            float textX = list.get(i).left + itemWidth / 2 - sWidth / 2;
            float textY = list.get(i).bottom - itemWidth / 2 + textSize / 2;
            canvas.drawText(s, textX, textY, textPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isScroll = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                isScroll = true;
                break;
            case MotionEvent.ACTION_UP:
                if (!isScroll) {
                    float x = event.getX();
                    float y = event.getY();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).contains(x, y)) {
                            if (beforeClickItem != -1) {
                                listData.get(beforeClickItem).setFlag(listData.get(beforeClickItem).getDefaultFlag());
                            }
                            listData.get(i).setFlag(3);
                            setCurrentSelDayStr(listData.get(i).getDateStr());
                            beforeClickItem = i;
                            if (listener != null) {
                                listener.onClick(getCurrentSelDayStr(), getTitle());
                            }
                            invalidate();
                            break;
                        }
                    }
                }

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {



        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = screenWidth - 40 * density;
            height = row * width / 7;
            setMeasuredDimension(width, height);
        } else {
            height = row * width / 7;
            setMeasuredDimension(width, height);
        }
    }


    /**
     * 初始化日历数据源
     */
    private void initList(int currentYear, int currentMonth) {
        if (listData == null) {
            listData = new ArrayList<>();
        } else {
            listData.clear();
        }
        //获取当前月有多少天
        int currentDayOfMonth = TimeUtils.getDayOfMonth(currentYear, currentMonth);
        //当前月的上一个月是几月
        int lastMonth = TimeUtils.getLastMonth(currentMonth);
        //上一个月所在年份
        int lastYear = TimeUtils.getYearOfLastMonth(currentYear, currentMonth);
        //当前月的下一个月是几月
        int nextMonth = TimeUtils.getNextMonth(currentMonth);
        //下一个月所在的年份
        int nextYear = TimeUtils.getYearOfNextMonth(currentYear, currentMonth);


        //获取上个月有多少天
        int lastDayOfMonth = TimeUtils.getDayOfMonth(lastYear, lastMonth);
        //获取1号是星期几
        int oneOfWeekCurrentMonth = TimeUtils.getCurrentDayOfWeekTheFirstOfMonteh(currentYear, currentMonth);
        if (oneOfWeekCurrentMonth==1){
            //上一个月的日期--1号是星期天时
            for (int i = 0; i < 7; i++) {
                CalendarBean bean = new CalendarBean();
                bean.setDay(lastDayOfMonth + i - 6);
                bean.setFlag(1);
                bean.setDefaultFlag(1);
                bean.setDateStr(lastYear, lastMonth, lastDayOfMonth + i - 6);
                listData.add(bean);
            }
        }else {
            //上一个月的日期---1号不是星期天时
            for (int i = 0; i < oneOfWeekCurrentMonth - 1; i++) {
                CalendarBean bean = new CalendarBean();
                bean.setDay(lastDayOfMonth + i - (oneOfWeekCurrentMonth - 2));
                bean.setFlag(1);
                bean.setDefaultFlag(1);
                bean.setDateStr(lastYear, lastMonth, lastDayOfMonth + i - (oneOfWeekCurrentMonth - 2));
                listData.add(bean);
            }
        }


        //当前月的日期
        for (int i = 0; i < currentDayOfMonth; i++) {
            CalendarBean bean = new CalendarBean();
            bean.setDay(i + 1);
            bean.setFlag(2);
            bean.setDefaultFlag(2);
            bean.setDateStr(currentYear, currentMonth, i + 1);
            listData.add(bean);
        }

        //下一个月的日期---固定六行
        if (listData.size()<42) {
            int itemCount = 42 - listData.size();
            for (int i = 0; i < itemCount; i++) {
                CalendarBean bean = new CalendarBean();
                bean.setDay(i + 1);
                bean.setFlag(1);
                bean.setDefaultFlag(1);
                bean.setDateStr(nextYear, nextMonth, i + 1);
                listData.add(bean);
            }
        }
//        //下一个月的日期--可能多一行
//        if (listData.size() % 7 != 0) {
//            int itemCount = 7 - listData.size() % 7;
//            for (int i = 0; i < itemCount; i++) {
//                CalendarBean bean = new CalendarBean();
//                bean.setDay(i + 1);
//                bean.setFlag(1);
//                bean.setDefaultFlag(1);
//                bean.setDateStr(nextYear, nextMonth, i + 1);
//                listData.add(bean);
//            }
//        }
    }

    /**
     * 监听接口
     */
    public interface SelDayListener {
        void onClick(String dayStr, String yearMonthStr);
    }


}
