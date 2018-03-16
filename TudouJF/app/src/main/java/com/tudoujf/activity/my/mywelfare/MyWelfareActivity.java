package com.tudoujf.activity.my.mywelfare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.mywelfare.MyWelfareFragment;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyWelfareActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/18
 * description： 我的福利activity
 * history：
 * ===================================================
 */

public class MyWelfareActivity extends BaseActivity {
    @BindView(R.id.tv_act_mywelfare_back)
    TextView tvBack;
    @BindView(R.id.tv_act_mywelfare_redpackage)
    TextView tvRedPackage;
    @BindView(R.id.tv_act_mywelfare_jiaxiquan)
    TextView tvJiaXiQuan;
    @BindView(R.id.tv_act_mywelfare_fanxianquan)
    TextView tvFanXianQuan;
    @BindView(R.id.ll_act_mywelfare_mtb)
    LinearLayout llMtb;
    @BindView(R.id.vp_act_mywelfare_one)
    ViewPager vpOne;

    private List<TextView> listTV;
    private List<Fragment> listFrag;

    @Override
    public int getLayoutResId() {
        return R.layout.act_mywelfare;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_mywelfare_back://结束本页面
                closeActivity();
                break;
            case R.id.tv_act_mywelfare_redpackage://红包
                changeButtonStyle(0);
                vpOne.setCurrentItem(0);
                break;
            case R.id.tv_act_mywelfare_jiaxiquan://加息券
                changeButtonStyle(1);
                vpOne.setCurrentItem(1);
                break;
            case R.id.tv_act_mywelfare_fanxianquan://返现券
                changeButtonStyle(2);
                vpOne.setCurrentItem(2);
               break;

        }

    }

    @Override
    public void initDataFromIntent() {
        listTV = new ArrayList<>(3);
        listTV.add(tvRedPackage);
        listTV.add(tvJiaXiQuan);
        listTV.add(tvFanXianQuan);


        listFrag=new ArrayList<>(3);
        
        MyWelfareFragment fragment1=new MyWelfareFragment();
        Bundle bundle1=new Bundle();
        bundle1.putInt("type",1);
        fragment1.setArguments(bundle1);
        listFrag.add(fragment1);


        MyWelfareFragment fragment2=new MyWelfareFragment();
        Bundle bundle2=new Bundle();
        bundle2.putInt("type",2);
        fragment2.setArguments(bundle2);
        listFrag.add(fragment2);


        MyWelfareFragment fragment3=new MyWelfareFragment();
        Bundle bundle3=new Bundle();
        bundle3.putInt("type",3);
        fragment3.setArguments(bundle3);
        listFrag.add(fragment3);
        

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llMtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        llMtb.setLayoutParams(params);

        HomeFragVPAdapter adapter=new HomeFragVPAdapter(getSupportFragmentManager(),listFrag);
        vpOne.setAdapter(adapter);
        vpOne.setOffscreenPageLimit(3);
    }

    @Override
    public void initListener() {

        vpOne.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                changeButtonStyle(position);
            }
        });

        tvBack.setOnClickListener(this);
        tvRedPackage.setOnClickListener(this);
        tvJiaXiQuan.setOnClickListener(this);
        tvFanXianQuan.setOnClickListener(this);

    }

    private void changeButtonStyle(int postion){
        for (int i = 0; i < 3; i++) {
            if (i==postion){
                listTV.get(i).setTextColor(getResources().getColor(R.color.color_white));
            }else {
                listTV.get(i).setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
            }
        }

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
