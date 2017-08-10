package com.tudoujf.activity.home;

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
 * name:            MyExperienceGoldActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/3
 * description：  我的体验金activity
 * history：
 * * ====================================================================
 */

public class MyExperienceGoldActivity extends BaseActivity {
    @BindView(R.id.mtb_act_myexperiencegold)
    MTopBarView mtbMyExperienceGold;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myexperiencegold;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbMyExperienceGold.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbMyExperienceGold.setLayoutParams(params);

        mtbMyExperienceGold.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
