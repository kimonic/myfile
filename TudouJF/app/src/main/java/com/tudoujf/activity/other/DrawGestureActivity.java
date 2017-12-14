package com.tudoujf.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.ui.MLockView;
import com.tudoujf.utils.EncryptionLockUtils;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.MD5Utils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.ToastUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            DrawGestureActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/12
 * description：绘制手势密码activity
 * history：
 * ===================================================
 */

public class DrawGestureActivity extends BaseActivity {
    @BindView(R.id.iv_act_drawgesture_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_act_drawgesture_welcome)
    TextView tvWelcome;
    @BindView(R.id.mlv_act_drawgesture)
    MLockView mlvActDrawgesture;
    @BindView(R.id.tv_act_drawgesture_pass)
    TextView tvPass;
    @BindView(R.id.root_scrollview_draw)
    ScrollView scrollView;
    /**
     * 需要绘制的次数
     */
    private int inputCount = 2;
    /**
     * 手势密码加密后的字符串
     */
    private String password;
    /**
     * 从我的账户进入的绘制手势密码的标识
     */
    private String type;
    private String userName;

    @Override
    public int getLayoutResId() {
        return R.layout.act_drawgesture;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_drawgesture_pass://跳过设置手势密码
                openActivity(HomeActivity.class);
                closeActivity();
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");
            userName = bundle.getString("name");
        }

    }

    @Override
    public void initView() {
        new Thread(){
            @Override
            public void run() {
                Glide.get(DrawGestureActivity.this).clearDiskCache();
            }
        }.start();
        Glide.get(this).clearMemory();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivIcon.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this)+150, 0, 0);
        ivIcon.setLayoutParams(params);

        String  path= FileUtils.getIconPath(this);
        File file=new File(path);
        if (file.exists()){
            ImageGlideUtils.loadCircularImage(ivIcon, path);
        }else {
            ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock_icon);
        }

        if ("open".equals(type)) {
            tvPass.setVisibility(View.GONE);
        }
        tvWelcome.setText((getResources().getString(R.string.huanyingni) + userName));

        mlvActDrawgesture.setScrollView(scrollView);
    }

    @Override
    public void initListener() {
        tvPass.setOnClickListener(this);
        mlvActDrawgesture.setLockInputListener(new MLockView.OnLockInputListener() {
            @Override
            public void errorMoreTime() {

            }

            @Override
            public void drawFinish(List<Integer> positionSet) {
                if (positionSet.size() < 5 && password == null) {
                    ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_hint);
                } else {
                    if (inputCount == 2) {
                        password = EncryptionLockUtils.convertList(positionSet);
                    } else if (password != null && !password.equals(EncryptionLockUtils.convertList(positionSet))) {
                        password = null;
                        inputCount = inputCount + 1;
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_error);
                    }
                    inputCount--;
                    if (inputCount == 0) {
                        tvPass.setClickable(false);
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_sucess);

                        savePassword(positionSet);//保存手势密码
                        UserConfig.getInstance().setLockPass(true);//已开启手势密码
                        SharedPreferencesUtils.getInstance(DrawGestureActivity.this, Constants.USER_CONFIG).put(MD5Utils.md5(
                                UserConfig.getInstance().getLoginToken(DrawGestureActivity.this))
                                + "lockPass", true);

                        mlvActDrawgesture.setOpenOrCloseDraw(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {//延迟2秒跳转至首页
                            @Override
                            public void run() {
                                if ("open".equals(type)) {
                                    Intent intent = new Intent();
                                    intent.putExtra("result", true);
                                    setResult(111, intent);
                                    closeActivity();
                                } else {
                                    openActivity(HomeActivity.class);
                                    closeActivity();
                                }

                            }
                        }, 2000);
                    } else {
                        ToastUtils.showToast(DrawGestureActivity.this, "请再次输入,还需输入" + inputCount + "次!");
                    }
                }
                mlvActDrawgesture.afreshDraw();


            }

            @Override
            public void sucess() {

            }
        });
    }

    private void savePassword(List<Integer> list) {
        String temp = EncryptionLockUtils.convertEncryption(this, list);
        String  loginToken= UserConfig.getInstance().getLoginToken(this);
        String name= MD5Utils.md5(loginToken);

        SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).put(name+"ciphertext", temp);
        SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).put(name+"lockPass", true);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public void onBackPressed() {
        if ("open".equals(type)) {
            Intent intent = new Intent();
            intent.putExtra("result", false);
            setResult(111, intent);
            closeActivity();
        }

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }
}

