package com.tudoujf.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.tudoujf.R;
import com.tudoujf.utils.ToastUtils;

/**
 * * ====================================================================
 * name:            SignInService
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/31
 * description：  启动签到悬浮窗的service
 * history：用法
 *           Intent intent = new Intent(getActivity(), SignInService.class);
 *          getActivity().startService(intent);
 * * ====================================================================
 */

public class SignInService extends Service {


    private WindowManager mWindowManager;
    private View view;
    private WindowManager.LayoutParams wmParams;
    private float downX, downY;


    public SignInService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showPop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (view != null) {
            mWindowManager.removeView(view);
        }
        super.onDestroy();
    }

    public void showPop() {
        //获取LayoutParams对象
        wmParams = new WindowManager.LayoutParams();

        //获取的是LocalWindowManager对象
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        wmParams.format = PixelFormat.RGBA_8888;

        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.END | Gravity.BOTTOM;
        wmParams.x = 0;
        wmParams.y = 160;//相对边缘的偏移量
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.view_popitem_fraghome, null);


        mWindowManager.addView(view, wmParams);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SignInService.this.stopSelf();
                // 此处控制悬浮窗接下来的行为

                ToastUtils.showToast(SignInService.this, "点击事件");

            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getRawX();
                        downY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        float distanceX = event.getRawX()-downX;//负值左移,正值右移
//                        float distanceY = event.getRawY()-downY;

                        wmParams.x -= event.getRawX() - downX;
                        wmParams.y -= event.getRawY() - downY;
                        if (wmParams.x < 0) {
                            wmParams.x = 0;
                        }
                        if (wmParams.y < 0) {
                            wmParams.y = 0;
                        }
                        mWindowManager.updateViewLayout(view, wmParams);
                        downX = event.getRawX();
                        downY = event.getRawY();


                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });


    }
}
