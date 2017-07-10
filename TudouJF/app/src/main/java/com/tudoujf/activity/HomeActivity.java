package com.tudoujf.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.NaviButtonView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            HomeActivity
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/10
 * description：主页activity
 * history：
 * ===================================================
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.tbv_act_home)
    MTopBarView tbvActHome;
    @BindView(R.id.vp_act_home)
    ViewPager vpActHome;
    @BindView(R.id.nbv_act_home)
    NaviButtonView nbvActHome;

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        nbvActHome.setmTopBarView(tbvActHome);



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
    public int getLayoutResId() {
        return R.layout.act_home;
    }


}
