package com.tudoujf.activity.my.myproject;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.managemoney.AffirmBuyCreditorsRightsActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyInvestDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目-->我的投资详情activity
 * history：
 * * ====================================================================
 */

public class MyInvestDetailsActivity extends BaseActivity {


    @BindView(R.id.mtb_act_myinvestdetails)
    MTopBarView mtb;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myinvestdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_creditorsrightsdetails_buynow:
                openActivity(AffirmBuyCreditorsRightsActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
/**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
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
