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
 * name:            ProductDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/21
 * description：产品详情界面activity
 * history：
 * ===================================================
 */

public class ProductDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_productdetails)
    MTopBarView mtbProductdetails;

    @Override
    public int getLayoutResId() {
        return R.layout.act_productdetails;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbProductdetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbProductdetails.setLayoutParams(params);
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
