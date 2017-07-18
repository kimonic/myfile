package com.tudoujf.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.adapter.SimplVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * * ==================================================
 * name:            PullPushLayout
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/17
 * description：   上拉加载下拉刷新布局
 * history：
 * * ==================================================
 */

public class PullPushLayout extends LinearLayout {


    /**
     * 头部view
     */
    private View headerView;
    /**
     * 底部view
     */
    private View footerView;
    /**
     * 中间view
     */
    private ListView listView;
    /**
     * 按下时的xy坐标
     */
    private float downX, downY;
    /**
     * 滑动时的坐标差
     */
    private float moveX, moveY;
    /**
     * 抬起时的坐标点
     */
    private float endX, endY;
    /**
     * 回弹标志
     */
    private boolean backFlag = false;
    /**
     * 刷新加载监听器
     */
    private RefreshLoadListener listener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LayoutParams params = (LayoutParams) headerView.getLayoutParams();
            params.height = msg.what;
            if (headerView.getVisibility() == VISIBLE) {
                headerView.setLayoutParams(params);
            }
            if (footerView.getVisibility() == VISIBLE) {
                footerView.setLayoutParams(params);
            }
            if (msg.what == -1) {
                headerView.setVisibility(GONE);
                footerView.setVisibility(GONE);
            }
        }
    };

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    private String TAG = "nestedscroll";


    public PullPushLayout(Context context) {
        this(context, null, 0);
    }

    public PullPushLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullPushLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(23)
    public PullPushLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent: ----------ACTION_DOWN-");
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent: ----------ACTION_MOVE-");
                moveX = Math.abs(ev.getX() - downX);
                moveY = ev.getY() - downY;
                if (moveX < 30 && moveY > 50 && listView.getFirstVisiblePosition() == 0
                        && listView.getChildAt(0).getTop() == 0) {
                    headerView.setVisibility(VISIBLE);
                    LinearLayout.LayoutParams params = (LayoutParams) headerView.getLayoutParams();
                    params.height = (int) moveY / 2;
                    headerView.setLayoutParams(params);
                    backFlag = true;
                    return true;
                } else if (moveX < 30 && -moveY > 50 && listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1
                        ) {
                    footerView.setVisibility(VISIBLE);
                    LinearLayout.LayoutParams params = (LayoutParams) footerView.getLayoutParams();
                    params.height = (int) -moveY / 2;
                    footerView.setLayoutParams(params);
                    backFlag = true;
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:

                Log.e(TAG, "dispatchTouchEvent: ----------" + listView.getLastVisiblePosition());

                endX = ev.getX() - downX;
                endY = Math.abs((ev.getY() - downY) / 2);
                if (endY - 150 > 0 && backFlag) {
                    new Thread() {
                        @Override
                        public void run() {
                            while (endY - 150 > 0) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                endY = endY - 3;
                                Message msg = Message.obtain();
                                msg.what = (int) endY;
                                handler.sendMessage(msg);
                            }
                            Message msg = Message.obtain();
                            msg.what = -1;
                            handler.sendMessage(msg);
                            backFlag = false;
//
                            //-----------启动刷新展示动画
                            if (listener != null) {//开始刷新
                                listener.refresh();
                            }


                        }
                    }.start();
                }
                Log.e(TAG, "dispatchTouchEvent: ----------ACTION_UP-");

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG", "onTouchEvent: ---------3333-ACTION_DOWN--------------");

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "onTouchEvent: ---------3333-ACTION_MOVE--------------");


                break;
            case MotionEvent.ACTION_UP:

                Log.e("TAG", "onTouchEvent: ---------3333-ACTION_UP--------------");

                break;
        }


        return super.onTouchEvent(event);
    }

    private void initView() {


//        在被判定为滚动之前用户手指可以移动的最大值--即超过该值后即判定为滚动状态
        int maxMove = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.e("TAG", "initView22: " + maxMove);
        ViewCompat.setNestedScrollingEnabled(this, true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        this.setOrientation(VERTICAL);


        headerView = new View(getContext());
        LinearLayout.LayoutParams paramsHeader = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                150);
        headerView.setLayoutParams(paramsHeader);
        headerView.setBackgroundColor(Color.BLUE);
        headerView.setVisibility(GONE);

        footerView = new View(getContext());
        LinearLayout.LayoutParams paramsFooter = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                150);
        footerView.setLayoutParams(paramsFooter);
        footerView.setBackgroundColor(Color.RED);
        footerView.setVisibility(GONE);

        listView = new ListView(getContext());
        LinearLayout.LayoutParams paramsListview = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                0);
        paramsListview.weight = 1;
        listView.setLayoutParams(paramsListview);
        listView.setBackgroundColor(Color.BLACK);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("这个item的序号是---------" + i);
        }
        listView.setAdapter(new SimplVAdapter(list, getContext()));


        this.addView(headerView);
        this.addView(listView);
        this.addView(footerView);
    }

    public interface RefreshLoadListener {
        void refresh();

        void loading();
    }


}
