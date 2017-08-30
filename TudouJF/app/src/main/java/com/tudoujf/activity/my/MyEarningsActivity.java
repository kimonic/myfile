package com.tudoujf.activity.my;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.ScreenSizeUtils;

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

    private List<UnderlineTextView>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_myearnings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.utv_act_myearnings_daishoulixi:
                setButStyle(0);
                break;
            case R.id.utv_act_myearnings_yishoulixi:
                setButStyle(1);
                break;
            case R.id.utv_act_myearnings_huodongshouyi:
                setButStyle(2);
                break;
            case R.id.tv_act_myearnings_bac:
               closeActivity();
                break;
        }

    }

    private void setButStyle(int position){
        for (int i = 0; i < list.size(); i++) {
            if (i==position){
                list.get(i).setUnderlinecolor(R.color.global_theme_background_color);
                list.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));
            }else {
                list.get(i).setUnderlinecolor(R.color.color_white);
                list.get(i).setTextColor(getResources().getColor(R.color.color_black));
            }
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

        list=new ArrayList<>();
        list.add(utvDaiShouLiXi);
        list.add(utvYiShouLiXi);
        list.add(utvHuoDongShouYi);


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
