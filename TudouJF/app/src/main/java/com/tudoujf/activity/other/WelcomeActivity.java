package com.tudoujf.activity.other;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            WelcomeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/7
 * description：  欢迎页activity
 * history：
 * ===================================================
 */

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.iv_act_welcome)
    ImageView ivActWelcome;
    /**
     * 是否有锁屏密码
     */
    private boolean isLock;
    /**
     * 是否是第一次打开app
     */
    private boolean isgudie;



    @Override
    public int getLayoutResId() {
        return R.layout.act_welcome;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initDataFromIntent() {
        isgudie = SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).getBoolean("isguide", false);
        isLock = UserConfig.getInstance().getLockPass(this);
    }

    @Override
    public void initView() {

        //        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivActWelcome.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        ivActWelcome.setLayoutParams(params);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isgudie) {//第一次打开
                    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                } else if (isLock) {//有手势密码
                    Intent intent = new Intent(WelcomeActivity.this, LockActivity.class);
                    startActivity(intent);
                } else {//无手势密码
                    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                WelcomeActivity.this.finish();
            }
        }, 2000);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
