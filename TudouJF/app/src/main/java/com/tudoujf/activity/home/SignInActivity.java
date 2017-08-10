package com.tudoujf.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            SignInActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  签到activity
 * history：
 * * ====================================================================
 */

public class SignInActivity extends BaseActivity {

    @BindView(R.id.mtb_act_signin)
    MTopBarView mtbActSignIn;
    @BindView(R.id.ll_act_signin_shop)
    LinearLayout llShop;
    @BindView(R.id.ll_act_signin_jilu)
    LinearLayout llJilu;
    @BindView(R.id.ll_act_signin_shuoming)
    LinearLayout llShuoming;

    @Override
    public int getLayoutResId() {
        return R.layout.act_signin;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_signin_shop:
                openActivity(IntegralShopActivity.class);
                break;
            case R.id.ll_act_signin_jilu:
                break;
            case R.id.ll_act_signin_shuoming:
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActSignIn.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActSignIn.setLayoutParams(params);


    }

    @Override
    public void initListener() {
        mtbActSignIn.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        llJilu.setOnClickListener(this);
        llShop.setOnClickListener(this);
        llShuoming.setOnClickListener(this);
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
