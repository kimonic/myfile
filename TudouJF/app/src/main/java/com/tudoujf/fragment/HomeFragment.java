package com.tudoujf.fragment;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.home.InfoPublishActivity;
import com.tudoujf.activity.home.MyExperienceGoldActivity;
import com.tudoujf.activity.home.MyMessageActivity;
import com.tudoujf.activity.home.NewbieWelfareActivity;
import com.tudoujf.activity.home.NewcomerExperienceBidActivity;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.activity.home.SpecialOfferActivity;
import com.tudoujf.activity.managemoney.ProductDetailsActivity;
import com.tudoujf.activity.my.mypopularize.MyPopularizeActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.BallViewVPAdapter;
import com.tudoujf.adapter.BannerVPAdapter;
import com.tudoujf.assist.ViewPagerScroller;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.HomeBean;
import com.tudoujf.bean.databean.HomeBidIdBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.service.SignInService;
import com.tudoujf.ui.AwardInfoView;
import com.tudoujf.ui.BallView;
import com.tudoujf.ui.DotView;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import butterknife.BindView;

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
    @BindView(R.id.srl_frag_home)
    SmartRefreshLayout srl;
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
//    @BindView(R.id.drag_frame)
//    FrameLayout dragFrame;
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
    /**
     * 当前ballview的位置
     */
    private int ballviewPosition = 0;


    /**
     * 加载dialog
     */
    private AlertDialog dialog;
    /**
     * 将要打开的标的详情idbean
     */
    private HomeBidIdBean homeBidIdBean;
    /**
     * banner 的页面个数
     */
    private int bannerCount;
    private FrameLayout.LayoutParams params;
    /**
     * 屏幕像素密度
     */
    private int density;
//    private int screenWidth, screenHeight;
//    private int right, bottom;
//    private float downX, downY;

    @Override
    public int layoutRes() {
        return R.layout.frag_home;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.tv_frag_home://立即投资
                requestLoanId();
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
                Intent intent1 = new Intent(getActivity(), WebActivity.class);
                intent1.putExtra("url", Constants.XIN_SHOU_FU_LI);
                intent1.putExtra("title", getResources().getString(R.string.frag_home_xinshoufuli));
                startActivity(intent1);
//                openActivity(NewbieWelfareActivity.class);
                break;
            case R.id.ll_frag_home_tuijianyouli:
                openActivity(MyPopularizeActivity.class);
                break;
            case R.id.ll_frag_home_xinxipilu:
                openActivity(InfoPublishActivity.class);
                break;
            case R.id.iv_frag_home_signin:


                retractAnim(1);


                if (params.getMarginEnd() == 0 && "".equals(UserConfig.getInstance().getLoginToken(getActivity()))) {
                    openActivity(LoginActivity.class);
                } else if (params.getMarginEnd() < 0) {
                    params.setMarginEnd(0);
                    ivSignIn.setLayoutParams(params);
                } else {
                    openActivityForResult(SignInActivity.class, 666);
                }
                break;
            case R.id.fl_frag_msgcount://启动我的消息页面
                Intent intent = new Intent(getActivity(), MyMessageActivity.class);
                startActivityForResult(intent, 1);

//                openActivity(MyMessageActivity.class);
                break;
        }

    }

    /**
     * 签到缩进动画
     */
    private void retractAnim(int flag) {
        params = (FrameLayout.LayoutParams) ivSignIn.getLayoutParams();
        int marginEnd = params.getMarginEnd();
        ValueAnimator animator;
        if (flag == 1) {
            animator = ValueAnimator.ofInt(marginEnd, 0);
        } else {
            animator = ValueAnimator.ofInt(0, -50 * density);
        }
        animator.setDuration(1000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.setMarginEnd((Integer) animation.getAnimatedValue());
                ivSignIn.setLayoutParams(params);
            }
        });
    }

    /**
     * 请求对应标的id
     */
    private void requestLoanId() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", "");
        map.put("time_limit", loanBeanList.get(ballviewPosition).getPeriod());
        map.put("is_experience", loanBeanList.get(ballviewPosition).getExperience_status());
        map.put("is_new", loanBeanList.get(ballviewPosition).getAdditional_status());

        HttpMethods.getInstance().POST(getActivity(), Constants.HOME_DETAILS_ID, map, getActivity().getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e(TAG, "onSuccess: ------------首页fragment返回的标的详情id数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<HomeBidIdBean>() {
                        }.getType(),
                        HomeBidIdBean.class, getActivity());
                if (bean1 != null) {
                    homeBidIdBean = (HomeBidIdBean) bean1;
                    Log.e(TAG, "onSuccess: ------------首页fragment返回的标的详情id数据loan_id----------------" + homeBidIdBean.getLoan_id());

                    if ("1".equals(loanBeanList.get(ballviewPosition).getExperience_status())) {
                        // TODO: 2017/12/4 新手体验标要跳转不同的页面

                        Log.e("TAG", "onSuccess: bean.getExperience_amount()-----" + bean.getExperience_amount());

                        //体验金大于0,未登录,已登录未实名,跳转体验金详情
                        if (StringUtils.string2Integer(bean.getExperience_amount()) > 0 || "".equals(UserConfig.getInstance().getLoginToken(getActivity()))) {
                            Intent intent1 = new Intent(getActivity(), NewcomerExperienceBidActivity.class);
                            intent1.putExtra("loan_id", homeBidIdBean.getLoan_id());
                            startActivity(intent1);
                        } else {
                            // TODO: 2017/12/6 检测是否已实名
                            checkIdentity();
//                            openActivity(MyExperienceGoldActivity.class);//打开我体验金页面
                        }

                    } else {
                        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                        intent.putExtra("loan_id", homeBidIdBean.getLoan_id());
                        startActivity(intent);
                    }

                } else {
                    ToastUtils.showToast(getActivity(), R.string.qingqiushujuchucuo);
                }
            }
        });
    }

    /**
     * 检测是否已实名
     */
    private void checkIdentity() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        HttpMethods.getInstance().POST(getActivity(), Constants.IDENTITY_CHECK, map, getActivity().getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------请求身份是否实名返回的json数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IdentityCheckBean>() {
                }.getType(), IdentityCheckBean.class, getActivity());
                if (bean1 != null) {
                    IdentityCheckBean identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        openActivity(MyExperienceGoldActivity.class);//打开我体验金页面
                    } else {//未实名
                        Intent intent1 = new Intent(getActivity(), NewcomerExperienceBidActivity.class);
                        intent1.putExtra("loan_id", homeBidIdBean.getLoan_id());
                        startActivity(intent1);
                    }
                } else {
                    ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(getActivity(), R.string.yanzhengshimingxinxishibai);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {//我的消息返回数据
            Log.e(TAG, "onActivityResult: ----未读消息数--------" + data.getStringExtra("msgcount"));
            int msgCount = data.getIntExtra("msgcount", 0);
            if (msgCount != 0) {
                Log.e(TAG, "onActivityResult: ----未读消息数--------" + data.getStringExtra("msgcount"));
                if (msgCount < 100) {
                    tvMsgCount.setText(("" + msgCount));
                } else {
                    tvMsgCount.setText(getResources().getString(R.string.ninenine));
                }
            }

        } else if (requestCode == 666) {//签到界面返回
            initDataFromInternet();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initDataFromIntent() {
//        Bundle bundle = getArguments();
//        if (bundle != null) {
////            tvFragHome.setText(bundle.getString("temp"));
//        }
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
        density = ScreenSizeUtils.getDensity(getActivity());
        initAutoCarousel();
        initDataFromInternet();

//         开启悬浮窗-----------------------------------------------------------------------------------------------------------------------------------
//        Intent intent = new Intent(getActivity(), SignInService.class);
//        getActivity().startService(intent);

//        dialog = DialogUtils.showProgreessDialog(getActivity(), getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));


        //设置全区背景色
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srl.setEnableLoadmore(false);

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

        bannerCount = listUrl.size() - 1;
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

        //显示隐藏控件
        srl.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                if (offset > 36) {
                    showInfo(tvFengXianTiShi1);
                }
            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                if (offset > 36) {
                    showInfo(tvFengXianTiShi2);
                }
            }
        });


//        //-------------------------签到可滑动-------------------------------------------------------
//        screenWidth = ScreenSizeUtils.getScreenWidth(getActivity());
//        screenHeight = ScreenSizeUtils.getScreenHeight(getActivity());
//        params = (FrameLayout.LayoutParams) ivSignIn.getLayoutParams();
//        right = params.rightMargin;
//        bottom = params.bottomMargin;
//
//        dragFrame.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downX = event.getRawX();
//                        downY = event.getRawY();
//                        return  true;
//                    case MotionEvent.ACTION_MOVE:
//                        float distanceX = event.getRawX()-downX;//负值左移,正值右移
//                        float distanceY = event.getRawY()-downY;
//
//                        Log.e("TAG", "onTouch: ---distanceX--"+distanceX);
//                        Log.e("TAG", "onTouch: ---distanceY--"+distanceY);
//
//
//                        params.rightMargin= (int) (right-distanceX);
//                        params.bottomMargin= (int) (bottom-distanceY);
//                        ivSignIn.setLayoutParams(params);
//
//                        return  true;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return false;
//            }
//        });
//        //-------------------------签到可滑动-------------------------------------------------------


//
//        vpBall.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                srl.setEnableRefresh(false);
//                srl.setEnableHeaderTranslationContent(false);
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.e("TAG", "onTouch: ---555--");
//                        currentY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.e("TAG", "onTouch: ---333--"+flag);
//                        if (flag) {
//                            if (event.getY() - currentY > 0) {
//                                showInfo(tvFengXianTiShi1);
//                                Log.e("TAG", "onTouch: ---11111--");
//                            } else {
//                                showInfo(tvFengXianTiShi2);
//                                Log.e("TAG", "onTouch: -2222----");
//                            }
//                            flag = false;
//                        }
//                        Log.e("TAG", "onTouch: ---444--");
//
//
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (Math.abs(event.getY() - currentY) > 36) {
//                            flag = true;
//                        }
//                        Log.e("TAG", "onTouch: ---666--"+(Math.abs(event.getY() - currentY)) );
//
//                        break;
//                }
//                srl.setEnableRefresh(true);
//                srl.setEnableHeaderTranslationContent(true);
//                return false;
//            }
//
//        });

        vpBall.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ballviewPosition = position;
                initOtherView(position);
            }
        });

        srl.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDataFromInternet();

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
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();

        //首页的logintoken为null时,会出现系统错误
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));

        HttpMethods.getInstance().POST(getActivity(), Constants.HOME, map, getActivity().getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
//                dialog.dismiss();
                dismissPDialog();

                finishRL();

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
                super.onError(response);
                dismissPDialog();
                finishRL();
                ToastUtils.showToast(getActivity(), R.string.huoqushouyeshujushibai);
            }
        });

    }

    private void finishRL() {
        if (srl.isRefreshing()) {
            srl.finishRefresh();
        } else if (srl.isLoading()) {
            srl.finishLoadmore();
        }

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            initBallViews();
            initImagesViews();
            initOtherView(0);

            params = (FrameLayout.LayoutParams) ivSignIn.getLayoutParams();
            if ("-1".equals(bean.getSign_status())) {
                params.setMarginEnd(0);
                ivSignIn.setLayoutParams(params);
            } else {
                retractAnim(2);
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

                aivFragHome.setText("奖"+loanBeanList.get(position).getAward_proportion().toString()+"%");
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
                int count = StringUtils.string2Integer(bean.getMessage_count());
                if (count < 100) {
                    tvMsgCount.setText(bean.getMessage_count());
                } else {
                    tvMsgCount.setText(getResources().getString(R.string.ninenine));
                }
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
                        //------------------------------钟摆循环----------------------------------------------------------
//                        if (plummet) {
//                            autoCount++;
//                            if (autoCount > bannerCount) {
//                                plummet = !plummet;
//                                autoCount = autoCount - 2;
//                            }
//                        } else {
//                            autoCount--;
//                            if (autoCount < 0) {
//                                plummet = !plummet;
//                                autoCount = autoCount + 2;
//                            }
//                        }
//------------------------------钟摆循环----------------------------------------------------------


                        if (autoCount >= bannerCount) {
                            autoCount = 0;
                        } else {
                            autoCount++;
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
