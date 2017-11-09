package com.tudoujf.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.ui.MLockView;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.MD5Utils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.ToastUtils;

import java.io.File;
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
     @BindView(R.id.tv_act_lock_hint)
    TextView tvHint;
    @BindView(R.id.ll_act_lock_btn)
    LinearLayout llBtn;

    private Handler handler;
    private String password;

    private String userName;
    private String type;
    private String name;

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
        //******************************************************************************************
//                            手势密码加用户识别后可能会出错
        //******************************************************************************************

        String  loginToken= UserConfig.getInstance().getLoginToken(this);
        name= MD5Utils.md5(loginToken);

        handler = new Handler();
        password = SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).getString(name+"ciphertext", "");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
//            userName = bundle.getString("name");//用户名
            type = bundle.getString("type");//关闭锁屏密码
        }
        userName= SharedPreferencesUtils.getInstance(this, Constants.USER_CONFIG).getString("userName", "");


    }

    @Override
    public void initView() {
        String  path= FileUtils.getIconPath(this);
        File file=new File(path);
        if (file.exists()){
            ImageGlideUtils.loadCircularImage(ivIcon, path);
        }else {
            ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock_icon);
        }
        mlvActLock.setPassword(password);
        Log.e("TAG", "selselsel:--------------手势密码字符串--------------- " + password);
//        TreeMap<String,String>  map=new TreeMap<>();
//        map.put("login_token","12267");
//        Log.e("TAG", "initView:------------加密字符串---------- " + StringUtils.getRequestParams(map));
        if ("close".equals(type)){
            llBtn.setVisibility(View.GONE);
            tvHint.setText(R.string.qinghuizhinindangqiandeshoushimima);
        }else {
            tvHint.setText(R.string.qingshurunindeshoushimima);
        }
        tvWelcome.setText((getResources().getString(R.string.huanyingni) + userName));

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
                    Log.e("TAG", "drawFinish:-------positionSet------------ " + positionSet.get(i));

                }

            }

            @Override
            public void sucess() {
                if ("close".equals(type)){

                    UserConfig.getInstance().setLockPass(false);

                    SharedPreferencesUtils.getInstance(LockActivity.this, Constants.USER_CONFIG).put(name+"lockPass", false);
                    SharedPreferencesUtils.getInstance(LockActivity.this, Constants.USER_CONFIG).put(name+"ciphertext", "");

                    ToastUtils.showToast(LockActivity.this, R.string.quxiaoshoushimimachenggong);
                    Intent intent=new Intent();
                    intent.putExtra("result",true);
                    setResult(222,intent);
                }else if ("jiesuo".equals(type)){
                    UserConfig.getInstance().setDraw(true);
                    setResult(555);
                }else {
                    openActivity(HomeActivity.class);//手势密码验证成功后打开的页面
                }
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
        if ("close".equals(type)){
            ToastUtils.showToast(LockActivity.this, R.string.quxiaoshoushimimashibai);
            Intent intent=new Intent();
            intent.putExtra("result",false);
            setResult(222,intent);
        }else {
            //按下返回键回到主界面
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
        closeActivity();

    }
}
