package com.tudoujf.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MLockView;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            LockActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：锁屏页activity
 * history：
 * ===================================================
 */
public class LockActivity extends BaseActivity {
    @BindView(R.id.iv_act_lock_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_act_lock_welcome)
    TextView tvWelcome;
    @BindView(R.id.mlv_act_lock)
    MLockView mlvActLock;
    @BindView(R.id.tv_act_lock_forget)
    TextView tvForget;
    @BindView(R.id.tv_act_lock_other)
    TextView tvOther;

    private Handler handler;
    private String password;

    @Override
    public int getLayoutResId() {
        return R.layout.act_lock;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_lock_forget://忘记密码操作
                // TODO: 2017/7/12 忘记密码操作
                break;
            case R.id.tv_act_lock_other://使用其他账号登陆
                // TODO: 2017/7/12   使用其他账号登陆
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        handler = new Handler();
        password = SharedPreferencesUtils.getInstance(this, "gesture").getString("ciphertext", "");
    }

    @Override
    public void initView() {
        ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock_icon);
        mlvActLock.setPassword(password);
    }

    @Override
    public void initListener() {
        tvForget.setOnClickListener(this);
        tvOther.setOnClickListener(this);
        mlvActLock.setLockInputListener(new MLockView.OnLockInputListener() {
            @Override
            public void errorMoreTime() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlvActLock.setOpenOrCloseDraw(true);
                    }
                }, 5000);
            }

            @Override
            public void drawFinish(List<Integer> positionSet) {

            }

            @Override
            public void sucess() {
            }
        });
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
