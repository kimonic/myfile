package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            CommitPhoneNumberActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改手机号码---绑定新手机号activity
 * history：
 * * ====================================================================
 */

public class CommitPhoneNumberActivity extends BaseActivity {


    @BindView(R.id.mtb_act_commitphonenumber)
    MTopBarView mtbActCommitPhonenumber;
    @BindView(R.id.et_act_commitphonenumber_number)
    EditText etNumber;
    @BindView(R.id.et_act_commitphonenumber_code)
    EditText etCode;
    @BindView(R.id.tv_act_commitphonenumber_getcode)
    TextView tvGetCode;
    @BindView(R.id.et_act_commitphonenumber_complete)
    TextView etComplete;


    @Override
    public int getLayoutResId() {
        return R.layout.act_commitphonenumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_commitphonenumber_getcode://获取验证码

                break;
            case R.id.et_act_commitphonenumber_complete://完成

                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActCommitPhonenumber.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActCommitPhonenumber.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActCommitPhonenumber.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvGetCode.setOnClickListener(this);
        etComplete.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

}
