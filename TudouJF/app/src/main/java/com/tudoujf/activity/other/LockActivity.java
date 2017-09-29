package com.tudoujf.activity.other;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
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
                openActivity(FindPasswordActivity.class);
                break;
            case R.id.tv_act_lock_other://使用其他账号登陆
                // TODO: 2017/7/12   使用其他账号登陆
                openActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        handler = new Handler();
        password = SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).getString("ciphertext","");
    }

    @Override
    public void initView() {
        ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock_icon);
        mlvActLock.setPassword(password);
        Log.e("TAG", "selselsel:--------------手势密码字符串--------------- "+password );
//        TreeMap<String,String>  map=new TreeMap<>();
//        map.put("login_token","12267");
//        Log.e("TAG", "initView:------------加密字符串---------- " + StringUtils.getRequestParams(map));
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
                }, 300000);
            }

            @Override
            public void drawFinish(List<Integer> positionSet) {
                for (int i = 0; i < positionSet.size(); i++) {
                    Log.e("TAG", "drawFinish:-------positionSet------------ " +positionSet.get(i));

                }

            }

            @Override
            public void sucess() {
                openActivity(HomeActivity.class);//手势密码验证成功后打开的页面
                closeActivity();
            }
        });
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public void onBackPressed() {
        //按下返回键回到主界面
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}
