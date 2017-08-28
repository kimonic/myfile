package com.tudoujf.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MLockView;
import com.tudoujf.utils.EncryptionLockUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.ToastUtils;

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
    /**需要绘制的次数*/
    private int inputCount = 2;
    /**手势密码加密后的字符串*/
    private String password;

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

    }

    @Override
    public void initView() {
        ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock_icon);
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
                    } else if (password!=null&&!password.equals( EncryptionLockUtils.convertList(positionSet))) {
                        password=null;
                        inputCount = inputCount+1;
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_error);
                    }
                    inputCount--;
                    if (inputCount == 0) {
                        tvPass.setClickable(false);
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_sucess);
                        savePassword(positionSet);//保存手势密码
                        mlvActDrawgesture.setOpenOrCloseDraw(false);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {//延迟2秒跳转至首页
                            @Override
                            public void run() {
                                openActivity(HomeActivity.class);
                                closeActivity();
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
        String temp=EncryptionLockUtils.convertEncryption(this,list);
        SharedPreferencesUtils.getInstance(this,"gesture").put("ciphertext",temp);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}

