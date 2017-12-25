package com.tudoujf.activity.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.tudoujf.jpush.ExampleUtil;
import com.tudoujf.jpush.LocalBroadcastManager;
import com.tudoujf.mapi.MApp;
import com.tudoujf.ui.NaviButtonView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

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

    private  String  signup="";

    /**是否已开启手势密码*/
    private  boolean  isLock=false;
    @Override
    public int getLayoutResId() {
        return R.layout.act_home;
    }

    public ViewPager getVpActHome() {
        return vpActHome;
    }

    @Override
    public void initDataFromIntent() {
//        registerMessageReceiver();  //jpush相关
//        init();//jpush相关
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


                if (position==3){
                    ((MyFragment)list.get(3)).showService();
                }


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

                if (position==3){
                    ((MyFragment)list.get(3)).showService();
                }

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
            if (!"".equals(UserConfig.getInstance().getLoginToken(this))&&"-1".equals(UserConfig.getInstance().getIsRealName(this))) {
                SharedPreferencesUtils.getInstance(this, "popshow").put("show", false);
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
                ((HomeFragment)(list.get(0))).initDataFromInternet();
            }

        }else if (requestCode==555){
            if (UserConfig.getInstance().isDraw()){
                vpActHome.setCurrentItem(3);
            }
        }
    }

    @Override
    protected void onResume() {
//        isForeground = true;//jpush相关
        super.onResume();
        //----------------------------可能会对页面跳转产生影响-------------------------------------
        checkLogin();
        if (!isLogin) {
            if (beforePosition != 3) {
                vpActHome.setCurrentItem(beforePosition);
            } else {
                vpActHome.setCurrentItem(0);
                try {
                    ((HomeFragment)(list.get(0))).initDataFromInternet();
                }catch (NullPointerException  e){

                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int flag = intent.getIntExtra("flag", 0);
        Log.e("TAG", "onNewIntent: flag-----"+flag);

        if (flag == 555) {
            vpActHome.setCurrentItem(1);
        }else if (flag==55){
            vpActHome.setCurrentItem(0);
        }else if (flag==666){
            try {
                ((HomeFragment)(list.get(0))).initDataFromInternet();
            }catch (NullPointerException  e){

            }
        }

    }

//    @Override
//    protected void onDestroy() {
////        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onPause() {
////        isForeground = false;//jpush相关
//        super.onPause();
//    }

//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------
//    private MessageReceiver mMessageReceiver;
//    public static boolean isForeground = false;
//    public static final String MESSAGE_RECEIVED_ACTION = "com.tudoujf.MESSAGE_RECEIVED_ACTION";
//    public static final String KEY_TITLE = "title";
//    public static final String KEY_MESSAGE = "message";
//    public static final String KEY_EXTRAS = "extras";
//    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
//    private void init(){
//        JPushInterface.init(getApplicationContext());
//    }
//
//
//    public void registerMessageReceiver() {
//        Log.e("TAG", "onReceive: --推送接收-注册通知--");
//        Log.e("TAG", "onReceive: --推送接收-注册通知--"+getPackageName());
//        mMessageReceiver = new MessageReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
//    }
//
//
//    public class MessageReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            Log.e("TAG", "onReceive: --推送接收---"+intent.getAction());
//
//            try {
//                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
//                    String messge = intent.getStringExtra(KEY_MESSAGE);
//                    String extras = intent.getStringExtra(KEY_EXTRAS);
//                    StringBuilder showMsg = new StringBuilder();
//                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (!ExampleUtil.isEmpty(extras)) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }
////                    setCostomMsg(showMsg.toString());
//                }
//            } catch (Exception e){
//            }
//        }
//    }
//
//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------
//    //-----------------------------------------jpush添加---------------------------------------------

}
