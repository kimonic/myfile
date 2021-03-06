package com.tudoujf.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.other.LockActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.NewVersionBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.fragment.DiscoverFragment;
import com.tudoujf.fragment.HomeFragment;
import com.tudoujf.fragment.ManageMoneyMattersFragment;
import com.tudoujf.fragment.MyFragment;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.mapi.MApp;
import com.tudoujf.ui.NaviButtonView;
import com.tudoujf.utils.AppInfoUtils;
import com.tudoujf.utils.DialogShowOrderUtils;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.DownloadAppUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
        //20180905用于控制dialog的显示顺序
        DialogShowOrderUtils.getInstance().setHomeActivity(this);
    }

    @Override
    public void initView() {
        initFragmentList();
        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getSupportFragmentManager(), list);
        vpActHome.setAdapter(adapter);
        nbvActHome.setViewPager(vpActHome);
        vpActHome.setOffscreenPageLimit(4);

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


                if (position == 3) {
                    ((MyFragment) list.get(3)).showService();
                }
                if (position == 3 && !isLogin) {
                    openLoginAct();
                    return false;
                } else if (position == 3 && UserConfig.getInstance().getLockPass(HomeActivity
                        .this) && !UserConfig.getInstance().isDraw()) {//已登录已设置手势密码本次未验证手势密码
//                    openActivity(LockActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "jiesuo");
                    openActivityForResult(LockActivity.class, bundle, 555);//打开手势密码界面
                    return false;
                } else {
                    beforePosition = position;
                    return true;
                }
            }
        });

        vpActHome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                if (position == 3) {
                    ((MyFragment) list.get(3)).showService();
                }

                if (position == 3 && !isLogin) {
                    openLoginAct();
                } else if (position == 3 && UserConfig.getInstance().getLockPass(HomeActivity
                        .this) && !UserConfig.getInstance().isDraw()) {//已登录已设置手势密码本次未验证手势密码
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "jiesuo");
                    openActivity(LockActivity.class, bundle);//打开手势密码界面
//                    openActivity(LockActivity.class);
                } else {
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

        checkNew();
    }

    /**
     * app版本更新
     */
    private void checkNew() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("phone_type", "1");
        HttpMethods.getInstance().POST(this, Constants.NEW_VERSION, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(HomeActivity.class, "logflag-检查版本更新接口返回数据--" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new
                                TypeToken<NewVersionBean>() {
                                }.getType(), NewVersionBean.class, HomeActivity.this);
                        if (bean1 != null) {
                            NewVersionBean bean = (NewVersionBean) bean1;
                            String versionName = AppInfoUtils.getVersionName(HomeActivity.this);
                            final String url;
                            if (bean.getUrl() != null) {
                                url = bean.getUrl();
                            } else {
                                url = "";
                            }

////                            捕获apk的下载地址
//                            Intent intent = new Intent(HomeActivity.this, WebActivity.class);
//                            intent.putExtra("url",
//                                    "http://sj.qq.com/myapp/detail.htm?apkName=com.tudoujf");
//                            intent.putExtra("title", "下载新版本");
//                            startActivity(intent);

                            //所有低版本都必须强制更新
                            if (bean.getForce_version() != null && (Integer.parseInt(versionName
                                    .replace(".", "").substring(0, 3)) <
                                    Integer.parseInt(bean.getForce_version().replace(".", "")))) {
                                // TODO: 2018/9/5 集中提示dialog
                                //201809051134
                                DialogShowOrderUtils.getInstance().setUpdateForceFlag(true,url);
//                                DialogUtils.showPromptDialogAll(HomeActivity.this, "提示",
//                                        "APP必须更新到最新版本,是否前往更新?",
//                                        new DialogUtils.DialogUtilsClickListener() {
//                                            @Override
//                                            public void onClick() {
//                                                DownloadAppUtils.downloadForWebView(HomeActivity
//                                                        .this, url);
//                                                closeActivity();
//                                            }
//                                        }, new DialogUtils.DialogUtilsClickListener() {
//                                            @Override
//                                            public void onClick() {
//                                                closeActivity();
//                                            }
//                                        });
                            } else if (!versionName.equals(bean.getNew_version())) {//普通更新
                                // TODO: 2018/9/5 集中提示dialog
                                //201809051134
                                DialogShowOrderUtils.getInstance().setUpdateFlag(true,url);

//                                DialogUtils.showPromptDialog(HomeActivity.this, "提示", "发现APP有新版本," +
//                                                "是否前往更新?",
//                                        new DialogUtils.DialogUtilsClickListener() {
//                                            @Override
//                                            public void onClick() {
//                                                DownloadAppUtils.downloadForWebView(HomeActivity
//                                                        .this, url);
//                                            }
//                                        });
                            }

                        } else {
                            ToastUtils.showToast(HomeActivity.this, R.string.shujujiazaichucuo);
                        }

                        //20180905   计数要显示的dialog,当所有dialog都确定是否显示后触发显示
                        DialogShowOrderUtils.getInstance().setCount();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(HomeActivity.this, R.string.jianchagengxinshiabai);
                        //20180905   计数要显示的dialog,当所有dialog都确定是否显示后触发显示
                        DialogShowOrderUtils.getInstance().setCount();
                    }
                });
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
        //是否需要显示开通汇付的对话框
        if (hasFocus && SharedPreferencesUtils.getInstance(this, "popshow").getBoolean("show",
                true)) {
            if (!"".equals(UserConfig.getInstance().getLoginToken(this)) && "-1".equals
                    (UserConfig.getInstance().getIsRealName(this))) {
                SharedPreferencesUtils.getInstance(this, "popshow").put("show", false);
                DialogUtils.showHuiFuDialog(this);
            }
        }
    }

    //----------------------------可能会对页面跳转产生影响-------------------------------------

    /**
     * 检测是否已登陆
     */
    private void checkLogin() {
        if (!"".equals(UserConfig.getInstance().getLoginToken(this))) {
            MApp.getInstance().setLogin(true);
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    /**
     * 打开登陆界面
     */
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
                ((HomeFragment) (list.get(0))).setFengXianFlag(true);
                ((HomeFragment) (list.get(0))).initDataFromInternet();
            }

        } else if (requestCode == 555) {
            if (UserConfig.getInstance().isDraw()) {
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
                    //重置风险提示显示
                    ((HomeFragment) (list.get(0))).initDataFromInternet();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int flag = intent.getIntExtra("flag", 0);
        if (flag == 555) {//理财界面
            vpActHome.setCurrentItem(1);
        } else if (flag == 55) {//首页界面
            vpActHome.setCurrentItem(0);
        } else if (flag == 666) {//刷新首页界面
            try {
                ((HomeFragment) (list.get(0))).initDataFromInternet();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else if (flag == 66) {//我的界面
            vpActHome.setCurrentItem(3);
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

//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------
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
//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------
//    //-----------------------------------------jpush
// 添加---------------------------------------------

}
