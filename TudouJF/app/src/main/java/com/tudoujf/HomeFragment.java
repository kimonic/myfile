package com.tudoujf;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.adapter.GuideVPAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.ui.DotView;

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
 * description：
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
    private ArrayList<ImageView> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_home;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle=getArguments();
        if (bundle!=null){
            tvFragHome.setText(bundle.getString("temp"));
        }

    }

    @Override
    public void initView() {
        initImagesViews();


        GuideVPAdapter adpter=new GuideVPAdapter(list);
        vpFragHome.setAdapter(adpter);

        dvFragHome.setViewPager(vpFragHome);
        dvFragHome.invalidate();

    }

    private void initImagesViews() {
        int images[]=new int[]{R.drawable.frag_home_vp1,
                R.drawable.frag_home_vp2,
                R.drawable.frag_home_vp3,
                R.drawable.frag_home_vp4,
        };
          list=new ArrayList<>();

        for (int resId:images) {
            ImageView imageview=new ImageView(getActivity());
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
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }
}
