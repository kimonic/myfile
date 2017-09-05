package com.tudoujf.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            NowRecommendActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/7
 * description：  我的推广页面--->立即推荐activity
 * history：
 * ===================================================
 */

public class NowRecommendActivity extends BaseActivity {
    @BindView(R.id.mtb_act_nowrecommend)
    FrameLayout mtbActNowRecommend;
    @BindView(R.id.tv_act_nowrecommend_close)
    TextView tvClose;
    @BindView(R.id.tv_act_nowrecommend_share)
    TextView tvShare;
    @BindView(R.id.tv_act_nowrecommend_register)
    TextView tvRegister;
    @BindView(R.id.iv_act_nowrecommend_share)
    ImageView ivShare;

    @Override
    public int getLayoutResId() {
        return R.layout.act_nowrecommend;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_nowrecommend_close://关闭本页面
                closeActivity();
                break;
            case R.id.tv_act_nowrecommend_share://分享
                break;
            case R.id.tv_act_nowrecommend_register://立即注册
                break;
            case R.id.iv_act_nowrecommend_share://引导显示
                ivShare.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActNowRecommend.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActNowRecommend.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        tvClose.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivShare.setOnClickListener(this);

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
