package com.tudoujf.mynotebook.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lzy.imagepicker.view.SystemBarTintManager;
import com.lzy.okgo.OkGo;
import com.tudoujf.mynotebook.R;
import com.tudoujf.mynotebook.utils.DialogUtils;
import com.tudoujf.mynotebook.utils.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            BaseActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/7
 * description：     activity基类
 * history：
 * ===================================================
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMethod, View.OnClickListener {

    /**
     * app进入前台后为false,返回后为true
     */
    private boolean isActive = true;

    private AlertDialog bDialog;

    public void showPDialog() {
        if (bDialog == null) {
            bDialog = DialogUtils.showProgreessDialog(this, getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
        } else {
            bDialog.show();
        }
    }

    public void dismissPDialog() {
        if (bDialog != null) {
            bDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBarTint();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏显示
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);//禁止截屏
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initDataFromIntent();
        initView();
        initListener();
        initDataFromInternet();

    }

    /**
     * 设置加载的布局
     */
    public abstract int getLayoutResId();


    /**
     * 设置状态栏颜色改变状态栏颜色
     */
    protected int setStatusBarColor() {
        return 0;
    }

    /**
     * 子类可以重写决定是否使用透明状态栏
     */
    protected boolean translucentStatusBar() {
        return false;
    }

    /**
     * 设置状态栏颜色
     */
    protected void initSystemBarTint() {
        if (setStatusBarColor() != 0) {
            Window window = getWindow();
            if (translucentStatusBar()) {
                // 设置状态栏全透明
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//21
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//19
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
            // 沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//21
                //5.0以上使用原生方法
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(setStatusBarColor());//直接设置状态栏的颜色
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//19
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintColor(setStatusBarColor());//直接设置状态栏的颜色
            }
        }


    }

    /**
     * 启动下一个activity
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    /**
     * 启动下一个activity
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    /**
     * 启动下一个activity
     */
    protected void openActivityForResult(Class<? extends BaseActivity> toActivity, Bundle parameter, int requestCode) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭activity
     */
    protected void closeActivity() {
        this.finish();
    }

    @Override
    protected void onDestroy() {

//        OkGo.cancelAll(OkGo.getInstance().getOkHttpClient()); //此处开启会造成不定时的数据无法加载
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), getLocalClassName());
        super.onDestroy();

    }


    /**
     * 程序是否在前台运行
     *
     * @return 返回程序是否在前台运行
     */
    public boolean isAppOnForeground() {
        //返回所有正在手机上运行的app的进程列表
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!isAppOnForeground()) {
            //app 进入后台
            ToastUtils.showToast(this, "应用已经进入后台运行!!");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isActive) {
//            app 从后台唤醒，进入前台
            ToastUtils.showToast(this, "应用已经回到前台!!");
            isActive = true;
        }

        }

    }
