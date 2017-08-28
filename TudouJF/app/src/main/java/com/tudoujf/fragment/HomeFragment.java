package com.tudoujf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.InfoPublishActivity;
import com.tudoujf.activity.home.MyMessageActivity;
import com.tudoujf.activity.home.NewbieWelfareActivity;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.activity.home.SpecialOfferActivity;
import com.tudoujf.adapter.BallViewVPAdapter;
import com.tudoujf.adapter.BannerVPAdapter;
import com.tudoujf.assist.ViewPagerScroller;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.HomeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.AwardInfoView;
import com.tudoujf.ui.BallView;
import com.tudoujf.ui.DotView;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * * ====================================================================
 * name:            HomeFragment
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/10
 * description：  首页activity中的首页fragment
 * history：
 * * ====================================================================
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_frag_home)
    TextView tvFragHome;
    @BindView(R.id.vp_frag_home)
    ViewPager vpFragHome;
    @BindView(R.id.dv_frag_home)
    DotView dvFragHome;
    @BindView(R.id.vp_frag_home_ball)
    ViewPager vpBall;
    @BindView(R.id.tv_frag_home_leftarrow)
    TextView tvLeftArrow;
    @BindView(R.id.tv_frag_home_rightarrow)
    TextView tvRightArrow;
    @BindView(R.id.tv_frag_home_fengxiantishi1)
    TextView tvFengXianTiShi1;
    @BindView(R.id.tv_frag_home_fengxiantishi2)
    TextView tvFengXianTiShi2;
    @BindView(R.id.ll_frag_home_xinshoufuli)
    LinearLayout llXinShouFuLi;
    @BindView(R.id.ll_frag_home_huodongzhuanqu)
    LinearLayout llHuoDongZhuanQu;
    @BindView(R.id.ll_frag_home_tuijianyouli)
    LinearLayout llTuiJianYouLi;
    @BindView(R.id.ll_frag_home_xinxipilu)
    LinearLayout llXinXiPiLu;
    @BindView(R.id.iv_frag_home_signin)
    ImageView ivSignIn;
    @BindView(R.id.tv_frag_home_xinshouzhuanxiang)
    TextView tvXinShouZhuanXinag;
    @BindView(R.id.tv_frag_home_bidtitle)
    TextView tvBidTitle;
    @BindView(R.id.aiv_frag_home)
    AwardInfoView aivFragHome;
    Unbinder unbinder;
    @BindView(R.id.tv_frag_home_touzijine)
    TextView tvTouZiJinE;
    @BindView(R.id.tv_frag_home_touziqixian)
    TextView tvTouZiQiXian;
    @BindView(R.id.fl_frag_msgcount)
    FrameLayout flMsgCount;
    @BindView(R.id.iv_frag_msgcount)
    ImageView ivMsgCount;
    @BindView(R.id.tv_frag_msgcount)
    TextView tvMsgCount;
    private List<ImageView> list;
    private List<BallView> listBall;
    private float currentY;
    private boolean flag = false;
    private String TAG = "HomeFragment";
    /**
     * 请求返回的json数据
     */
    private HomeBean bean;
    /**
     * banner的图片URL和跳转URL集合
     */
    private List<HomeBean.BannerBean> listUrl;
    /**
     * 标的展示数据的list集合
     */
    private List<HomeBean.LoanBean> loanBeanList;
    /**
     * 轮播handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (vpFragHome != null) {
                vpFragHome.setCurrentItem(msg.what);
            }
        }
    };

    /**
     * 控制轮播标志
     */
    private boolean autoFlag = true;
    /**
     * 当回到桌面时,暂停运行
     */
    private boolean autoFlagIn = true;
    /**
     * 轮播计数自增
     */
    private int autoCount = 0;
    /**
     * 递增递减控制
     */
    private boolean plummet = false;
    /**
     * 轮播线程
     */
    private Thread thread;
    private boolean notify = false;


    @Override
    public int layoutRes() {
        return R.layout.frag_home;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.tv_frag_home:
                break;
            case R.id.tv_frag_home_leftarrow:
                if (vpBall.getAdapter() != null && vpBall.getCurrentItem() > 0) {
                    vpBall.setCurrentItem(vpBall.getCurrentItem() - 1);
                } else {
                    ToastUtils.showToast(getActivity(), "已经没有更多啦!");
                }
                break;
            case R.id.tv_frag_home_rightarrow:
                if (vpBall.getAdapter() != null && vpBall.getCurrentItem() < vpBall.getAdapter().getCount() - 1) {
                    vpBall.setCurrentItem(vpBall.getCurrentItem() + 1);
                } else {
                    ToastUtils.showToast(getActivity(), "已经没有更多啦!");
                }
                break;
            case R.id.ll_frag_home_huodongzhuanqu:
                openActivity(SpecialOfferActivity.class);
                break;
            case R.id.ll_frag_home_xinshoufuli:
                openActivity(NewbieWelfareActivity.class);
                break;
            case R.id.ll_frag_home_tuijianyouli:
                break;
            case R.id.ll_frag_home_xinxipilu:
                openActivity(InfoPublishActivity.class);
                break;
            case R.id.iv_frag_home_signin:
                openActivity(SignInActivity.class);
                break;
            case R.id.fl_frag_msgcount://启动我的消息页面
                Intent intent = new Intent(getActivity(), MyMessageActivity.class);
                startActivityForResult(intent, 1);

//                openActivity(MyMessageActivity.class);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: ----未读消息数--------" + data.getStringExtra("msgcount"));
        if (requestCode == 1) {//我的消息返回数据
            int msgCount = data.getIntExtra("msgcount", 0);
            if (msgCount != 0) {
                Log.e(TAG, "onActivityResult: ----未读消息数--------" + data.getStringExtra("msgcount"));
                tvMsgCount.setText(("" + msgCount));
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
//            tvFragHome.setText(bundle.getString("temp"));
        }
//
//        TreeMap<String, String> map = new TreeMap<>();
//        map.put("login_token", "12267");
//        HttpMethods.getInstance().POST(getActivity(), Constants.HOME, map, "homeactivity", new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//
//            }
//        });

    }

    @Override
    public void initView() {
        initAutoCarousel();
        initDataFromInternet();

//         开启悬浮窗
//        Intent intent = new Intent(getActivity(), SignInService.class);
//        getActivity().startService(intent);


    }

    /**
     * 初始化ballview数据
     */
    private void initBallViews() {
        listBall = new ArrayList<>();
        loanBeanList = bean.getLoan();
        for (int i = 0; i < loanBeanList.size(); i++) {
            BallView view = new BallView(getActivity());
            view.setText(loanBeanList.get(i).getApr() + "%");
            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
            params.width = ViewPager.LayoutParams.MATCH_PARENT;
            params.height = ViewPager.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
            listBall.add(view);
        }
        BallViewVPAdapter adapterBallView = new BallViewVPAdapter(listBall);
        vpBall.setAdapter(adapterBallView);
        ViewPagerScroller mPagerScroller = new ViewPagerScroller(getActivity());
        mPagerScroller.initViewPagerScroll(vpBall);
    }

    /**
     * 初始化导航图片数据
     */
    private void initImagesViews() {
//        int images[] = new int[]{R.drawable.frag_home_vp1,
//                R.drawable.frag_home_vp2,
//                R.drawable.frag_home_vp3,
//                R.drawable.frag_home_vp4,
//        };
        list = new ArrayList<>();
        listUrl = bean.getBanner();

        for (int i = 0; i < listUrl.size(); i++) {
            ImageView imageview = new ImageView(getActivity());
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageGlideUtils.loadImageFromUrl(imageview, listUrl.get(i).getImage());
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getActivity(), listUrl.get(vpFragHome.getCurrentItem()).getJumpurl());
                    // TODO: 2017/8/15 传递跳转页面的URL
                }
            });
            list.add(imageview);
        }


        BannerVPAdapter adpter = new BannerVPAdapter(list, listUrl);
        vpFragHome.setAdapter(adpter);
        ViewPagerScroller mPagerScroller = new ViewPagerScroller(getActivity());
        mPagerScroller.initViewPagerScroll(vpFragHome);
        dvFragHome.setViewPager(vpFragHome);
        dvFragHome.invalidate();

    }

    @Override
    public void initListener() {
        vpFragHome.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                dvFragHome.setPosition(position);
                dvFragHome.invalidate();
            }
        });

        tvFragHome.setOnClickListener(this);
        tvLeftArrow.setOnClickListener(this);
        tvRightArrow.setOnClickListener(this);
        llHuoDongZhuanQu.setOnClickListener(this);
        llTuiJianYouLi.setOnClickListener(this);
        llXinShouFuLi.setOnClickListener(this);
        llXinXiPiLu.setOnClickListener(this);
        ivSignIn.setOnClickListener(this);
        flMsgCount.setOnClickListener(this);

        vpBall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (flag) {
                            if (event.getY() - currentY > 0) {
                                showInfo(tvFengXianTiShi1);
                            } else {
                                showInfo(tvFengXianTiShi2);
                            }
                            flag = false;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getY() - currentY) > 36) {
                            flag = true;
                        }
                        break;
                }
                return false;
            }
        });

        vpBall.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                initOtherView(position);
            }
        });

    }

    private void showInfo(final TextView tv) {
        tv.setText(R.string.licaiyoufengxian);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 20 * ScreenSizeUtils.getDensity(getActivity()));
        tv.setLayoutParams(params);
        Animation animation = new AlphaAnimation(0.5f, 1);
        animation.setDuration(2000);
        final Animation animation1 = new AlphaAnimation(1, 0);
        animation1.setDuration(2000);
        tv.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv.setAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv.setText("");
                ViewGroup.LayoutParams params = tvFengXianTiShi1.getLayoutParams();
                params.height = 10 * ScreenSizeUtils.getDensity(getActivity());
                tv.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void initDataFromInternet() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        //        map.put("login_token", "12267");
        HttpMethods.getInstance().POST(getActivity(), Constants.HOME, map, getActivity().getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String result = StringUtils.getDecodeString(response.body());
                Log.e(TAG, "onSuccess: ------------首页fragment返回的json数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<HomeBean>() {
                        }.getType(),
                        HomeBean.class, getActivity());
                if (bean1 != null) {
                    bean = (HomeBean) bean1;
                    LoadInternetDataToUi();
                }
            }

            @Override
            public void onError(Response<String> response) {
                Log.e(TAG, "onError: ------------首页fragment返回的json数据----------------" + response.message());
                super.onError(response);
            }
        });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            initBallViews();
            initImagesViews();
            initOtherView(0);
            if ("-1".equals(bean.getSign_status())) {
                ivSignIn.setVisibility(View.VISIBLE);
            } else {
                ivSignIn.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 对其他view加载网络数据
     */
    private void initOtherView(int position) {
        if (loanBeanList != null) {
            tvBidTitle.setText(loanBeanList.get(position).getName());
            if ("1".equals(loanBeanList.get(position).getAdditional_status())) {
                tvXinShouZhuanXinag.setVisibility(View.VISIBLE);
                tvXinShouZhuanXinag.setText(getResources().getString(R.string.frag_home_xinshouzhuanxiangbiao));
            } else if ("1".equals(loanBeanList.get(position).getExperience_status())) {
                tvXinShouZhuanXinag.setVisibility(View.VISIBLE);
                tvXinShouZhuanXinag.setText(getResources().getString(R.string.frag_home_xinshoutiyanbiao));
            } else {
                tvXinShouZhuanXinag.setVisibility(View.INVISIBLE);
            }

            if ("2".equals(loanBeanList.get(position).getAward_status())) {
                aivFragHome.setVisibility(View.VISIBLE);

                aivFragHome.setText(loanBeanList.get(position).getAward_proportion().toString());
                aivFragHome.invalidate();
            } else {
                aivFragHome.setVisibility(View.INVISIBLE);
            }
            tvTouZiJinE.setText((loanBeanList.get(position).getAmount() + "元"));
            if ("月".equals(loanBeanList.get(position).getPeriod_unit())) {
                tvTouZiQiXian.setText((loanBeanList.get(position).getPeriod() + "个" + loanBeanList.get(position).getPeriod_unit()));
            } else {
                tvTouZiQiXian.setText((loanBeanList.get(position).getPeriod() + loanBeanList.get(position).getPeriod_unit()));
            }

            if ("0".equals(bean.getMessage_count())) {
                ivMsgCount.setImageResource(R.drawable.frag_home_noinfo);
                tvMsgCount.setText("");
            } else {
                ivMsgCount.setImageResource(R.drawable.frag_home_info);
                tvMsgCount.setText(bean.getMessage_count());
            }

        }

    }


    @Override
    public void onStop() {
        autoFlagIn = false;
        super.onStop();
    }

    @Override
    public void onResume() {
        autoFlagIn = true;
        if (notify) {
            try {
                synchronized (thread) {
                    thread.notify();
                    notify = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
//        关闭悬浮窗
//        Intent intent = new Intent(getActivity(), SignInService.class);
//        getActivity().stopService(intent);
        autoFlag = false;
        super.onDestroy();
    }

    private void initAutoCarousel() {
        thread = new Thread() {
            @Override
            public void run() {
                while (autoFlag) {
                    if (autoFlagIn) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (plummet) {
                            autoCount++;
                            if (autoCount > 3) {
                                plummet = !plummet;
                                autoCount = autoCount - 2;
                            }
                        } else {
                            autoCount--;
                            if (autoCount < 0) {
                                plummet = !plummet;
                                autoCount = autoCount + 2;
                            }
                        }
                        Message msg = Message.obtain();
                        msg.what = autoCount;
                        handler.sendMessage(msg);
                    } else {
                        notify = true;
                        try {
                            synchronized (this) {
                                thread.wait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        thread.start();
    }
}
