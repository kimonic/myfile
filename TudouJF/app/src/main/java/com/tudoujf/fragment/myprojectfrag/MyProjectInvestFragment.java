package com.tudoujf.fragment.myprojectfrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             MyProjectInvestFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/22
 * description：
 * history：
 * *==================================================================
 */

public class MyProjectInvestFragment extends BaseFragment {
    @BindView(R.id.tv_frag_myprojectinvest1)
    TextView tvBtn1;
    @BindView(R.id.tv_frag_myprojectinvest2)
    TextView tvBtn2;
    @BindView(R.id.tv_frag_myprojectinvest3)
    TextView tvBtn3;
    @BindView(R.id.vp_frag_myprojectinvest)
    ViewPager vp;

    private List<TextView> list;
    private List<Fragment> listFrag;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectinvest;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_myprojectinvest1:
                setBtnStyle(0);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_frag_myprojectinvest2:
                setBtnStyle(1);
                vp.setCurrentItem(1);
                break;
            case R.id.tv_frag_myprojectinvest3:
                setBtnStyle(2);
                vp.setCurrentItem(2);
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    public void getCurrentFragRefresh(String startTime, String endTime){
        ((MyProjectInvestChildFragment)(listFrag.get(vp.getCurrentItem()))).setStartEndTime(startTime,endTime);
    }


    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        listFrag = new ArrayList<>();

    }

    @Override
    public void initView() {
        list.add(tvBtn1);
        list.add(tvBtn2);
        list.add(tvBtn3);

        MyProjectInvestChildFragment fragment1 = new MyProjectInvestChildFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("type", "0");
        fragment1.setArguments(bundle1);


        MyProjectInvestChildFragment fragment2 = new MyProjectInvestChildFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", "1");
        fragment2.setArguments(bundle2);


        MyProjectInvestChildFragment fragment3 = new MyProjectInvestChildFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("type", "2");
        fragment3.setArguments(bundle3);

        listFrag.add(fragment1);
        listFrag.add(fragment2);
        listFrag.add(fragment3);

        vp.setAdapter(new HomeFragVPAdapter(getChildFragmentManager(), listFrag));
        vp.setOffscreenPageLimit(3);


    }

    @Override
    public void initListener() {
        tvBtn1.setOnClickListener(this);
        tvBtn2.setOnClickListener(this);
        tvBtn3.setOnClickListener(this);


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBtnStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    private void setBtnStyle(int position) {
        for (int j = 0; j < list.size(); j++) {
            if (position == j) {
                list.get(j).setTextColor(getResources().getColor(R.color.global_theme_background_color));
                list.get(j).setBackgroundColor(getResources().getColor(R.color.frag_managemoneymatterschild_bac));
            } else {
                list.get(j).setTextColor(getResources().getColor(R.color.color_black));
                list.get(j).setBackgroundColor(getResources().getColor(R.color.color_white));
            }
        }
    }
}
