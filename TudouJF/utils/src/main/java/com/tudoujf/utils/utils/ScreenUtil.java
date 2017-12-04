package com.tudoujf.utils.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.reflect.Method;

/**
 * * ===============================================================
 * name:             ScreenUtil
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/4
 * description：   监听输入法发开关闭工具类
 * history：
 * remark:     该类如果在监听回调中动态设置布局则会触发死循环,示例解决代码如下:
 * <p>
 * ScreenUtil screenUtil = new ScreenUtil(this);
 * screenUtil.observeInputlayout(udeskRoot, new ScreenUtil.OnInputActionListener() {
 *
 * @Override public void onOpen(int inputHeight) {
 * if (count1==0){
 * count1++;
 * LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) udeskImContainer.getLayoutParams();
 * params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), inputHeight);
 * udeskImContainer.setLayoutParams(params);
 * count2=0;
 * }
 * }
 * @Override public void onClose() {
 * if (count2==0){
 * count2++;
 * LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) udeskImContainer.getLayoutParams();
 * params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), params.topMargin);
 * udeskImContainer.setLayoutParams(params);
 * count1=0;
 * }
 * }
 * });
 * *==================================================================
 */

public class ScreenUtil {
    private final Activity activity;
    /**
     * 毁掉监听器
     */
    private OnInputActionListener listener;

    public ScreenUtil(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return 底部的虚拟栏的高度
     */
    private int getBottomKeyboardHeight() {
        int screenHeight = getAccurateScreenDpi()[1];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);//去除底部虚拟栏之后的metric
        return screenHeight - dm.heightPixels;
    }

    /**
     * 获取实际的屏幕尺寸,所有的连同底部虚拟栏
     */
    public int[] getAccurateScreenDpi() {
        int[] screenWH = new int[2];//宽高数组
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenWH;
    }

    /**
     * 监听某个页面上输入法键盘打开动作
     *
     * @param view     要实现监听的页面上的任意一个view都可以
     * @param listener 监听接口
     */
    public void observeInputlayout(final View view, OnInputActionListener listener) {
        this.listener = listener;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                observe(view);
            }
        }, 500);
    }


    private void observe(View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    //当键盘弹出隐藏的时候会 调用此方法。
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        //获取当前界面可视部分
                        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                        //获取屏幕的高度
                        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
                        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                        int heightDifference = screenHeight - r.bottom;
                        if (heightDifference > getBottomKeyboardHeight()) {//有些手机用的是底部虚拟键,所以要大于虚拟键的高度
                            listener.onOpen(heightDifference);
                        } else {
                            listener.onClose();
                        }
                    }
                });
    }

    public interface OnInputActionListener {
        void onOpen(int inputHeight);
        void onClose();
    }
}


