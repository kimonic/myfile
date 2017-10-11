package com.tudoujf.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.UserConfig;
import com.tudoujf.fragment.DiscoverFragment;
import com.tudoujf.fragment.HomeFragment;
import com.tudoujf.fragment.ManageMoneyMattersFragment;
import com.tudoujf.fragment.MyFragment;
import com.tudoujf.mapi.MApp;
import com.tudoujf.ui.NaviButtonView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.vp_act_home)
    ViewPager vpActHome;
    @BindView(R.id.nbv_act_home)
    NaviButtonView nbvActHome;

    private List<Fragment> list;

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        initFragmentList();
        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getSupportFragmentManager(), list);
        vpActHome.setAdapter(adapter);
        nbvActHome.setViewPager(vpActHome);
        vpActHome.setOffscreenPageLimit(4);

//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vpActHome.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        vpActHome.setLayoutParams(params);
    }

    private void initFragmentList() {
        list = new ArrayList<>();


        HomeFragment fragment1 = new HomeFragment();
        list.add(fragment1);


        ManageMoneyMattersFragment fragment2 = new ManageMoneyMattersFragment();
        list.add(fragment2);

        DiscoverFragment fragment3 = new DiscoverFragment();
        list.add(fragment3);

        MyFragment fragment4 = new MyFragment();
        list.add(fragment4);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {
        //判断是否已登陆
        if (!"".equals(UserConfig.getInstance().getLoginToken(this))) {
            MApp.getInstance().setLogin(true);
        }

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_home;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && SharedPreferencesUtils.getInstance(this, "popshow").getBoolean("show", true)) {
            DialogUtils.showHuiFuDialog(this);
        }
    }
}
