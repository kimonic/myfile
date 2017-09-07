package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            RechargeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  充值activity
 * history：
 * * ====================================================================
 */

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.tv_act_recharge_bac)
    TextView tvActRechargeBac;
    @BindView(R.id.tv_act_recharge_chognzhijilu)
    TextView tvChognZhiJiLu;
    @BindView(R.id.mtb_act_recharge)
    FrameLayout mtbActRecharge;

    @Override
    public int getLayoutResId() {
        return R.layout.act_recharge;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_recharge_bac:
                closeActivity();
                break;
            case R.id.tv_act_recharge_chognzhijilu://充值记录
                openActivity(RechargeRecordActivity.class);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRecharge.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRecharge.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        tvActRechargeBac.setOnClickListener(this);
        tvChognZhiJiLu.setOnClickListener(this);
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
