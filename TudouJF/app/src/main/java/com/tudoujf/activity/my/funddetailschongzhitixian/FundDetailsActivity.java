package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            FundDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/11
 * description：  资金详情activity
 * history：
 * * ====================================================================
 */

public class FundDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_act_funddetails_bac)
    TextView tvActFundDetailsBac;
    @BindView(R.id.tv_act_funddetails_jiaoyijilu)
    TextView tvJiaoYiJiLu;
    @BindView(R.id.mtb_act_funddetails)
    FrameLayout mtbActFundDetails;

    @Override
    public int getLayoutResId() {
        return R.layout.act_funddetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_funddetails_bac:
                closeActivity();
                break;
            case R.id.tv_act_funddetails_jiaoyijilu://交易记录
                openActivity(TransactionRecordActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFundDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFundDetails.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        tvActFundDetailsBac.setOnClickListener(this);
        tvJiaoYiJiLu.setOnClickListener(this);
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
