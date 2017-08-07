package com.tudoujf.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.HomeFragment;
import com.tudoujf.fragment.ManageMoneyMattersFragment;
import com.tudoujf.ui.NaviButtonView;
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
        HomeFragVPAdapter adapter=new HomeFragVPAdapter(getSupportFragmentManager(),list);
        vpActHome.setAdapter(adapter);
        nbvActHome.setViewPager(vpActHome);
        vpActHome.setOffscreenPageLimit(4);

        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) vpActHome.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        vpActHome.setLayoutParams(params);
    }

    private void initFragmentList() {
        list=new ArrayList<>();
        
        
        HomeFragment fragment1=new HomeFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("temp","第一页");
        fragment1.setArguments(bundle1);
        list.add(fragment1);


        ManageMoneyMattersFragment fragment2=new ManageMoneyMattersFragment();
//        Bundle bundle2=new Bundle();
//        bundle2.putString("temp","第二页");
//        fragment2.setArguments(bundle2);
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

    private void showAlertDialog(){
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_act_home,null);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        final PopupWindow pop = new PopupWindow(view, ScreenSizeUtils.getScreenWidth(this) - 180, 500);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点
        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        TextView   tvLiJiKaiTong= (TextView) view.findViewById(R.id.tv_dialog_lijikaitong);
        TextView   tvZanBuKaiTong= (TextView) view.findViewById(R.id.tv_dialog_zanbukaitong);
        tvZanBuKaiTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        tvLiJiKaiTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/8/1 开通资金托管逻辑
            }
        });


        SharedPreferencesUtils.getInstance(this,"popshow").put("show",false);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&SharedPreferencesUtils.getInstance(this,"popshow").getBoolean("show",true)){
            showAlertDialog();
        }
    }
}
