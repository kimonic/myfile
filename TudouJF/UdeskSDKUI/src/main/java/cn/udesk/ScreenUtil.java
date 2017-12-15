package cn.udesk;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
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
 * *==================================================================
 */

public class ScreenUtil {
    private final Activity activity;
    private OnInputActionListener listener;

    public ScreenUtil(Activity activity) {
        this.activity = activity;
    }

    public int getBottomKeyboardHeight() {
        int screenHeight = getAccurateScreenDpi()[1];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return screenHeight - dm.heightPixels;
    }

    private int[] getAccurateScreenDpi() {
        int[] screenWH = new int[2];
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

    public void observeInputlayout(final View view, OnInputActionListener listener) {
        this.listener = listener;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                observe(view);
            }
        }, 1000);
    }


    private void observe(View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
                        int heightDifference = screenHeight - r.bottom;
                        if (heightDifference > getBottomKeyboardHeight()) {
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
