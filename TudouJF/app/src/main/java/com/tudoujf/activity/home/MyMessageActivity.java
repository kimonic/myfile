package com.tudoujf.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.SystemMessageFragment;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyMessageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：   我的消息页activity
 * history：
 * ===================================================
 */
public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.tv_act_mymessage_systemmessage)
    TextView tvSystemMessage;
    @BindView(R.id.tv_act_mymessage_mymessage)
    TextView tvMyMessage;
    @BindView(R.id.tv_act_mymessage_bac)
    TextView tvBac;
    @BindView(R.id.vp_act_mymessage)
    ViewPager vpActMymessage;
    @BindView(R.id.fl_act_mymessage)
    FrameLayout flActMymessage;

    private List<Fragment> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_mymessage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_mymessage_bac:
                closeActivity();
                break;
            case R.id.tv_act_mymessage_systemmessage:
                tvSystemMessage.setTextColor(getResources().getColor(R.color.color_white));
                tvMyMessage.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                vpActMymessage.setCurrentItem(0);
                break;
            case R.id.tv_act_mymessage_mymessage:
                tvMyMessage.setTextColor(getResources().getColor(R.color.color_white));
                tvSystemMessage.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                vpActMymessage.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        initFragmentList();
        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getSupportFragmentManager(), list);
        vpActMymessage.setAdapter(adapter);
        vpActMymessage.setOffscreenPageLimit(4);

        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) flActMymessage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        flActMymessage.setLayoutParams(params);
    }

    private void initFragmentList() {
        list=new ArrayList<>();
        SystemMessageFragment   fragment1=new SystemMessageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",1);
        fragment1.setArguments(bundle);


        SystemMessageFragment   fragment2=new SystemMessageFragment();
        Bundle bundle1=new Bundle();
        bundle1.putInt("type",2);
        fragment2.setArguments(bundle1);

        list.add(fragment1);
        list.add(fragment2);
    }

    @Override
    public void initListener() {
        tvBac.setOnClickListener(this);
        tvSystemMessage.setOnClickListener(this);
        tvMyMessage.setOnClickListener(this);
        vpActMymessage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    tvSystemMessage.setTextColor(getResources().getColor(R.color.color_white));
                    tvMyMessage.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
                }else {
                    tvMyMessage.setTextColor(getResources().getColor(R.color.color_white));
                    tvSystemMessage.setTextColor(getResources().getColor(R.color.act_mymessage_unselcolor));
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

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
