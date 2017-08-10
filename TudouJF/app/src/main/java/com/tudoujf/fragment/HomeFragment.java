package com.tudoujf.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.home.InfoPublishActivity;
import com.tudoujf.activity.home.NewbieWelfareActivity;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.activity.home.SpecialOfferActivity;
import com.tudoujf.adapter.BallViewVPAdapter;
import com.tudoujf.adapter.GuideVPAdapter;
import com.tudoujf.assist.ViewPagerScroller;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.ui.BallView;
import com.tudoujf.ui.DotView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

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
    private List<ImageView> list;
    private List<BallView> listBall;
    private float currentY;
    private boolean flag = false;


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
                if (vpBall.getCurrentItem() > 0) {
                    vpBall.setCurrentItem(vpBall.getCurrentItem() - 1);

                }
                break;
            case R.id.tv_frag_home_rightarrow:
                if (vpBall.getCurrentItem() < vpBall.getAdapter().getCount() - 1) {
                    vpBall.setCurrentItem(vpBall.getCurrentItem() + 1);
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
        }

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
//            tvFragHome.setText(bundle.getString("temp"));
        }

    }

    @Override
    public void initView() {
        initImagesViews();
        initBallViews();


        GuideVPAdapter adpter = new GuideVPAdapter(list);
        vpFragHome.setAdapter(adpter);

        dvFragHome.setViewPager(vpFragHome);
        dvFragHome.invalidate();

        BallViewVPAdapter adapterBallView = new BallViewVPAdapter(listBall);
        vpBall.setAdapter(adapterBallView);
        ViewPagerScroller mPagerScroller = new ViewPagerScroller(getActivity());
        mPagerScroller.initViewPagerScroll(vpBall);

//         开启悬浮窗
//        Intent intent = new Intent(getActivity(), SignInService.class);
//        getActivity().startService(intent);


    }

    /**
     * 初始化ballview数据
     */
    private void initBallViews() {
        listBall = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            BallView view = new BallView(getActivity());
            view.setText(i + ".00%");
            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
            params.width = ViewPager.LayoutParams.MATCH_PARENT;
            params.height = ViewPager.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
            listBall.add(view);
        }
    }

    /**
     * 初始化导航图片数据
     */
    private void initImagesViews() {
        int images[] = new int[]{R.drawable.frag_home_vp1,
                R.drawable.frag_home_vp2,
                R.drawable.frag_home_vp3,
                R.drawable.frag_home_vp4,
        };
        list = new ArrayList<>();

        for (int resId : images) {
            ImageView imageview = new ImageView(getActivity());
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            imageview.setImageResource(resId);
            list.add(imageview);
        }


    }

    @Override
    public void initListener() {
        vpFragHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                dvFragHome.setPosition(position);
                dvFragHome.invalidate();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    }

    private void showInfo(final TextView tv) {
        tv.setText("理财有风险,投资需谨慎");
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

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public void onDestroy() {
//        关闭悬浮窗
//        Intent intent = new Intent(getActivity(), SignInService.class);
//        getActivity().stopService(intent);
        super.onDestroy();
    }


}
