package com.tudoujf.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            FindPasswordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：找回密码页activity
 * history：
 * ===================================================
 */

public class FindPasswordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_find)
    MTopBarView mtbActFind;
    @BindView(R.id.et_act_find_username)
    EditText etUsername;
    @BindView(R.id.iv_act_find_clear)
    ImageView ivClear;
    @BindView(R.id.et_act_find_phonecode)
    EditText etPhonecode;
    @BindView(R.id.tv_act_find_getphonecode)
    TextView tvGetphonecode;
    @BindView(R.id.et_act_find_imagecode)
    EditText etImagecode;
    @BindView(R.id.iv_act_find_getimagecode)
    ImageView ivGetimagecode;
    @BindView(R.id.tv_act_find_next)
    TextView tvNext;

    @Override
    public int getLayoutResId() {
        return R.layout.act_findpassword;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_act_find_clear://清空用户名
                etUsername.setText("");
                break;
            case R.id.tv_act_find_getphonecode://提交请求获取手机验证码
                break;
            case R.id.iv_act_find_getimagecode://点击刷新图形验证码
                break;
            case R.id.tv_act_find_next://手机验证码与图形验证码正确后提交请求,否则重新输入

                break;

        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFind.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFind.setLayoutParams(params);

    }

    @Override
    public void initListener() {
        mtbActFind.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
        etUsername.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.GONE);
                }

            }
        });
        ivClear.setOnClickListener(this);
        tvGetphonecode.setOnClickListener(this);
        ivGetimagecode.setOnClickListener(this);
        tvNext.setOnClickListener(this);

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
