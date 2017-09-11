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
 * name:            CheckOldPhoneNumberActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改手机号码---验证原绑定手机号activity
 * history：
 * * ====================================================================
 */

public class CheckOldPhoneNumberActivity extends BaseActivity {


    @BindView(R.id.mtb_act_checkoldphonenumber)
    MTopBarView mtbActCheckOldPhonenumber;
    @BindView(R.id.tv_act_checkoldphonenumber_old)
    TextView tvOld;
    @BindView(R.id.et_act_checkoldphonenumber_code)
    EditText etCode;
    @BindView(R.id.tv_act_checkoldphonenumber_getcode)
    TextView tvGetCode;
    @BindView(R.id.tv_act_checkoldphonenumber_hint1)
    TextView tvHint1;
    @BindView(R.id.et_act_checkoldphonenumber_next)
    TextView etNext;
    @BindView(R.id.tv_act_checkoldphonenumber_hint2)
    TextView tvHint2;

    @Override
    public int getLayoutResId() {
        return R.layout.act_checkoldphonenumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_checkoldphonenumber_getcode://获取验证码

                break;
            case R.id.et_act_checkoldphonenumber_next://下一步
                openActivity(CommitPhoneNumberActivity.class);

                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActCheckOldPhonenumber.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActCheckOldPhonenumber.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActCheckOldPhonenumber.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvGetCode.setOnClickListener(this);
        etNext.setOnClickListener(this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
