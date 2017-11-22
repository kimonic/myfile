package com.tudoujf.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.activity.other.LockActivity;
import com.tudoujf.activity.other.LoginActivity;
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
    private boolean isLogin = false;

    private int beforePosition = 0;

    /**是否已开启手势密码*/
    private  boolean  isLock=false;

    public ViewPager getVpActHome() {
        return vpActHome;
    }

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
        //----------------------------可能会对页面跳转产生影响-------------------------------------
        nbvActHome.setListener(new NaviButtonView.CurrentPositionListener() {
            @Override
            public boolean currentPosition(int position) {


                if (position == 3 && !isLogin) {
                    openLoginAct();
                    return false;
                }
                else if (position == 3 &&UserConfig.getInstance().getLockPass(HomeActivity.this)&&!UserConfig.getInstance().isDraw()){//已登录已设置手势密码本次未验证手势密码
//                    openActivity(LockActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "jiesuo");
                    openActivityForResult(LockActivity.class, bundle,555);//打开手势密码界面
                    return false;
                }
                else {
                    beforePosition = position;
                    return true;
                }
            }
        });

        vpActHome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {


                if (position == 3 && !isLogin) {
                    openLoginAct();
                }
                else if (position == 3 &&UserConfig.getInstance().getLockPass(HomeActivity.this)&&!UserConfig.getInstance().isDraw()){//已登录已设置手势密码本次未验证手势密码
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "jiesuo");
                    openActivity(LockActivity.class, bundle);//打开手势密码界面
//                    openActivity(LockActivity.class);
                }
                else {
                    beforePosition = position;
                }

            }
        });
        //----------------------------可能会对页面跳转产生影响-------------------------------------

    }

    @Override
    public void initDataFromInternet() {
        //判断是否已登陆
//        if (!"".equals(UserConfig.getInstance().getLoginToken(this))) {
//            MApp.getInstance().setLogin(true);
//            isLogin = true;
//        }
        checkLogin();


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
            if (!"".equals(UserConfig.getInstance().getLoginToken(this))) {
                DialogUtils.showHuiFuDialog(this);
            }
        }
    }

    //----------------------------可能会对页面跳转产生影响-------------------------------------

    private void checkLogin() {
        if (!"".equals(UserConfig.getInstance().getLoginToken(this))) {
            MApp.getInstance().setLogin(true);
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    private void openLoginAct() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("type", 888);
        startActivityForResult(intent, 888);
    }
    //----------------------------可能会对页面跳转产生影响-------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            //----------------------------可能会对页面跳转产生影响-------------------------------------
            checkLogin();
            if (isLogin) {
                vpActHome.setCurrentItem(3);
            }

        }else if (requestCode==555){
            if (UserConfig.getInstance().isDraw()){
                vpActHome.setCurrentItem(3);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //----------------------------可能会对页面跳转产生影响-------------------------------------
        checkLogin();
        if (!isLogin) {
            if (beforePosition != 3) {
                vpActHome.setCurrentItem(beforePosition);
            } else {
                vpActHome.setCurrentItem(0);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int flag = intent.getIntExtra("flag", 0);
        if (flag == 555) {
            vpActHome.setCurrentItem(1);
        }else if (flag==55){
            vpActHome.setCurrentItem(0);
        }


    }
}
