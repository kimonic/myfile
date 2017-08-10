package com.tudoujf.activity.home;

import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            NewbieWelfareActivity
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity-->HomeFragment
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  首页activity中的首页fragment中的新手福利activity
 * history：
 * * ====================================================================
 */

public class NewbieWelfareActivity extends BaseActivity {
    @BindView(R.id.mtb_act_newbiewelfare)
    MTopBarView mtbActNewbieWelfare;

    @Override
    public int getLayoutResId() {
        return R.layout.act_newbiewelfare;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActNewbieWelfare.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActNewbieWelfare.setLayoutParams(params);

        mtbActNewbieWelfare.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
