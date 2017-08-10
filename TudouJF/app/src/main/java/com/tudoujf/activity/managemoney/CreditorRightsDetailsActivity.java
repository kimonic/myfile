package com.tudoujf.activity.managemoney;

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
 * name:            CreditorRightsDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  债权详情activity
 * history：
 * * ====================================================================
 */

public class CreditorRightsDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_creditorrightsdetails)
    MTopBarView mtbCreditorRightsDetails;
    @BindView(R.id.tv_act_creditorsrightsdetails_buynow)
    TextView tvBuyNow;

    @Override
    public int getLayoutResId() {
        return R.layout.act_creditorrightsdetails;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbCreditorRightsDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbCreditorRightsDetails.setLayoutParams(params);

        mtbCreditorRightsDetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {
        tvBuyNow.setOnClickListener(this);
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
