package com.tudoujf.activity;

import android.annotation.SuppressLint;
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

    private int inputCount = 4;
    private String password;

    @Override
    public int getLayoutResId() {
        return R.layout.act_drawgesture;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_act_drawgesture_pass://跳过设置手势密码

                break;
        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        ImageGlideUtils.loadCircularImage(ivIcon, R.drawable.act_lock2_icon);
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
                    if (inputCount == 4) {
                        password = EncryptionLockUtils.convertList(positionSet);
                    } else if (!password.equals( EncryptionLockUtils.convertList(positionSet))) {
                        password=null;
                        inputCount = 5;
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_error);
                    }
                    inputCount--;
                    if (inputCount == 0) {
                        ToastUtils.showToast(DrawGestureActivity.this, R.string.act_drawgesture_sucess);
                        savePassword(positionSet);
                        mlvActDrawgesture.setOpenOrCloseDraw(false);
                        //// TODO: 2017/7/12    保存password,结束本activity,跳转至其他界面
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

    @SuppressLint("HardwareIds")
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

