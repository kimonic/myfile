package com.tudoujf.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.tudoujf.HomeFragment;
import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.NaviButtonView;

import java.util.ArrayList;
import java.util.List;

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
    
    private List<Fragment> list;

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        nbvActHome.setmTopBarView(tbvActHome);
        initFragmentList();
        HomeFragVPAdapter adapter=new HomeFragVPAdapter(getSupportFragmentManager(),list);
        vpActHome.setAdapter(adapter);
        nbvActHome.setViewPager(vpActHome);
        vpActHome.setOffscreenPageLimit(4);
        



    }

    private void initFragmentList() {
        list=new ArrayList<>();
        
        
        HomeFragment fragment1=new HomeFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("temp","第一页");
        fragment1.setArguments(bundle1);
        list.add(fragment1);


        HomeFragment fragment2=new HomeFragment();
        Bundle bundle2=new Bundle();
        bundle2.putString("temp","第二页");
        fragment2.setArguments(bundle2);
        list.add(fragment2);

        HomeFragment fragment3=new HomeFragment();
        Bundle bundle3=new Bundle();
        bundle3.putString("temp","第三页");
        fragment3.setArguments(bundle3);
        list.add(fragment3);

        HomeFragment fragment4=new HomeFragment();
        Bundle bundle4=new Bundle();
        bundle4.putString("temp","第四页");
        fragment4.setArguments(bundle4);
        list.add(fragment4);
        
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
