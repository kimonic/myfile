package com.tudoujf.activity;

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
 * * ================================================
 * name:            FanXianQuanSelActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description： 选择返现券activity
 * history：
 * ===================================================
 */

public class FanXianQuanSelActivity extends BaseActivity {
    @BindView(R.id.mtb_act_fanxianquansel)
    MTopBarView mtbFanxianquansel;

    @Override
    public int getLayoutResId() {
        return R.layout.fanxianquansel;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbFanxianquansel.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbFanxianquansel.setLayoutParams(params);
    }

    @Override
    public void initListener() {

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
