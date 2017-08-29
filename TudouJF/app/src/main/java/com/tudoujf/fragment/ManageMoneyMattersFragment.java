package com.tudoujf.fragment;

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
 * * ====================================================================
 * name:            ManageMoneyMattersFragment
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  理财fragment
 * history：
 * * ====================================================================
 */

public class ManageMoneyMattersFragment extends BaseFragment {
//    @BindView(R.id.lv_frag_managemoneymatters)
//    ListView lvFragManageMoneyMatters;
    @BindView(R.id.tv_frag_managemoneymatters_touziliebiao)
    TextView tvTouZiLieBiao;
    @BindView(R.id.tv_frag_managemoneymatters_zhaiquanliebiao)
    TextView tvZhaiQuanLieBiao;
    @BindView(R.id.vp_frag_managemoneymatters)
    ViewPager vpFragManageMoneyMatters;

    private List<Fragment> listFrag;

    @Override
    public int layoutRes() {
        return R.layout.frag_managemoneymatters;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_managemoneymatters_touziliebiao:
                tvTouZiLieBiao.setTextColor(getResources().getColor(R.color.color_white));
                tvZhaiQuanLieBiao.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                vpFragManageMoneyMatters.setCurrentItem(0);

                break;
            case R.id.tv_frag_managemoneymatters_zhaiquanliebiao:
                tvZhaiQuanLieBiao.setTextColor(getResources().getColor(R.color.color_white));
                tvTouZiLieBiao.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                vpFragManageMoneyMatters.setCurrentItem(1);
                break;

        }

    }

    @Override
    public void initDataFromIntent() {
        listFrag = new ArrayList<>();


        InvestListFragment fragment1 = new InvestListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("type", 1);
        fragment1.setArguments(bundle1);


        InvestListFragment fragment2 = new InvestListFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 2);
        fragment2.setArguments(bundle2);


        listFrag.add(fragment1);
        listFrag.add(fragment2);


    }

    @Override
    public void initView() {


        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getChildFragmentManager(), listFrag);
        vpFragManageMoneyMatters.setAdapter(adapter);
        vpFragManageMoneyMatters.setOffscreenPageLimit(2);

    }

    @Override
    public void initListener() {
        tvTouZiLieBiao.setOnClickListener(this);
        tvZhaiQuanLieBiao.setOnClickListener(this);
        vpFragManageMoneyMatters.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    tvTouZiLieBiao.setTextColor(getResources().getColor(R.color.color_white));
                    tvZhaiQuanLieBiao.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                }else {
                    tvZhaiQuanLieBiao.setTextColor(getResources().getColor(R.color.color_white));
                    tvTouZiLieBiao.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                }

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


}
