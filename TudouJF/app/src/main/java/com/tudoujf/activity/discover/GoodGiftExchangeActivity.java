package com.tudoujf.activity.discover;

import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             GoodGiftExchangeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/25
 * description：  豪礼兑换activity
 * history：
 * *==================================================================
 */

public class GoodGiftExchangeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_goodgiftexcahnge)
    MTopBarView mtb;

    @Override
    public int getLayoutResId() {
        return R.layout.act_goodgiftexchange;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
//Verification Code
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
    }

    @Override
    public void initListener() {

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
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
