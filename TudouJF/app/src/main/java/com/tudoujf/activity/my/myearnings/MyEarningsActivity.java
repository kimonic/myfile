package com.tudoujf.activity.my.myearnings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.my.mypopularize.SucceedInvitationRecommendRecordActivity;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.myearnings.DueInInterestFragment;
import com.tudoujf.ui.DateFilterDialog;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyEarningsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/15
 * description：  我的收益页面activity
 * history：
 * ===================================================
 */
public class MyEarningsActivity extends BaseActivity {
    @BindView(R.id.tv_act_myearnings_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_myearnings_filtrate)
    LinearLayout llFiltrate;
    @BindView(R.id.fl_act_myearnings)
    FrameLayout flActMyEarnings;
    @BindView(R.id.utv_act_myearnings_daishoulixi)
    UnderlineTextView utvDaiShouLiXi;
    @BindView(R.id.utv_act_myearnings_yishoulixi)
    UnderlineTextView utvYiShouLiXi;
    @BindView(R.id.utv_act_myearnings_huodongshouyi)
    UnderlineTextView utvHuoDongShouYi;
    @BindView(R.id.vp_act_myearnings)
    ViewPager vpActMyEarnings;
    @BindView(R.id.tv_act_myearnings_total)
    TextView tvTotal;


    private List<UnderlineTextView> list;
    private List<Fragment> listFrag;


    private DateFilterDialog dateFilterDialog;


    private int position = 0;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myearnings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.utv_act_myearnings_daishoulixi:
                vpActMyEarnings.setCurrentItem(0);
                setButStyle(0);
                position = 0;
                tvTotal.setText(((DueInInterestFragment) (listFrag.get(0))).getTotal());
                break;
            case R.id.utv_act_myearnings_yishoulixi:
                vpActMyEarnings.setCurrentItem(1);
                setButStyle(1);
                position = 1;
                tvTotal.setText(((DueInInterestFragment) (listFrag.get(1))).getTotal());
                break;
            case R.id.utv_act_myearnings_huodongshouyi:
                vpActMyEarnings.setCurrentItem(2);
                setButStyle(2);
                position = 2;
                tvTotal.setText(((DueInInterestFragment) (listFrag.get(2))).getTotal());
                break;
            case R.id.tv_act_myearnings_bac:
                closeActivity();
                break;
            case R.id.ll_act_myearnings_filtrate:
                if (dateFilterDialog == null) {
                    dateFilterDialog = new DateFilterDialog(this);
                    dateFilterDialog.setLisenter(new DateFilterDialog.ClickEvent() {
                        @Override
                        public void dismiss(String startTime, String endTime) {

                            ((DueInInterestFragment) (listFrag.get(position))).setStart_time(startTime, endTime);
                            ToastUtils.showToast(MyEarningsActivity.this, startTime + "-----------" + endTime);
                        }
                    });
                }
                dateFilterDialog.show();
                break;
        }

    }

    private void setButStyle(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setUnderlinecolor(R.color.global_theme_background_color);
                list.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));
            } else {
                list.get(i).setUnderlinecolor(R.color.color_white);
                list.get(i).setTextColor(getResources().getColor(R.color.color_black));
            }
        }

    }

    @Override
    public void initDataFromIntent() {
        listFrag = new ArrayList<>();
        DueInInterestFragment fragment1 = new DueInInterestFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("type", 1);
        fragment1.setArguments(bundle1);

        DueInInterestFragment fragment2 = new DueInInterestFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 2);
        fragment2.setArguments(bundle2);

        DueInInterestFragment fragment3 = new DueInInterestFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 3);
        fragment3.setArguments(bundle3);

        listFrag.add(fragment1);
        listFrag.add(fragment2);
        listFrag.add(fragment3);

    }

    @Override
    public void initView() {


        list = new ArrayList<>();
        list.add(utvDaiShouLiXi);
        list.add(utvYiShouLiXi);
        list.add(utvHuoDongShouYi);

        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getSupportFragmentManager(), listFrag);
        vpActMyEarnings.setAdapter(adapter);
        vpActMyEarnings.setOffscreenPageLimit(3);


//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) flActMyEarnings.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        flActMyEarnings.setLayoutParams(params);

    }

    @Override
    public void initListener() {

        utvDaiShouLiXi.setOnClickListener(this);
        utvHuoDongShouYi.setOnClickListener(this);
        utvYiShouLiXi.setOnClickListener(this);
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);

        vpActMyEarnings.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setButStyle(position);
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


    public void setTotal(String total) {
        tvTotal.setText(total);
    }
}
