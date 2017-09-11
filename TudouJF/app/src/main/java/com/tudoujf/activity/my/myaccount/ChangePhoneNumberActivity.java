package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
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
 * name:            ChangePhoneNumberActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改手机号码---展示已绑定手机号activity
 * history：
 * * ====================================================================
 */

public class ChangePhoneNumberActivity extends BaseActivity {
    @BindView(R.id.mtb_act_changephonenumber)
    MTopBarView mtbActChangePhonenumber;
    @BindView(R.id.tv_act_changephonenumber_change)
    TextView tvChange;

    @Override
    public int getLayoutResId() {
        return R.layout.act_changephonenumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_changephonenumber_change://验证已绑定号码
                openActivity(CheckOldPhoneNumberActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActChangePhonenumber.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActChangePhonenumber.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActChangePhonenumber.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvChange.setOnClickListener(this);
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
