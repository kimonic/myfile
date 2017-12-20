package com.tudoujf.activity.my.myproject;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.myprojectfrag.MyProjectCreditorFragment;
import com.tudoujf.fragment.myprojectfrag.MyProjectTotalFragment;
import com.tudoujf.ui.DateFilterDialog;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyPopularizeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/7
 * description：  我的项目页面activity
 * history：
 * ===================================================
 */
public class MyProjectActivity extends BaseActivity {
    @BindView(R.id.tv_act_myproject_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_myproject_filtrate)
    LinearLayout llFiltrate;
    @BindView(R.id.mtb_act_myproject)
    FrameLayout mtbActMyproject;
    @BindView(R.id.vp_act_myproject)
    ViewPager vpActMyproject;
//    @BindView(R.id.utv_act_myproject_total)
//    UnderlineTextView utvTotal;
    @BindView(R.id.utv_act_myproject_touzixiangmu)
    UnderlineTextView utvTouZiXiangMu;
    @BindView(R.id.utv_act_myproject_zhaiquanxiangmu)
    UnderlineTextView utvZhaiQuanXiangMu;


    private List<UnderlineTextView> list;
    private List<Fragment> listFrag;
    private DateFilterDialog dateFilterDialog;
    private int currentItem = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.act_myproject;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.utv_act_myproject_total:
//                vpActMyproject.setCurrentItem(0);
//                setButStyle(0);
//                break;
            case R.id.utv_act_myproject_touzixiangmu:
                vpActMyproject.setCurrentItem(0);
                setButStyle(0);
                break;
            case R.id.utv_act_myproject_zhaiquanxiangmu:
                vpActMyproject.setCurrentItem(1);
                setButStyle(1);
                break;
            case R.id.tv_act_myproject_bac:
                closeActivity();
                break;
            case R.id.ll_act_myproject_filtrate:
                if (dateFilterDialog == null) {
                    dateFilterDialog = new DateFilterDialog(this,getResources().getString(R.string.qignxuanzeninyaochaxundehuikuanshijian));
                    dateFilterDialog.setLisenter(new DateFilterDialog.ClickEvent() {
                        @Override
                        public void dismiss(String startTime, String endTime) {
                            refreshFragment(startTime, endTime);

                            // TODO: 2017/9/4  请求网络筛选展示数据
//                            ToastUtils.showToast(MyProjectActivity.this, startTime + "-----------" + endTime);
                        }
                    });
                }
                dateFilterDialog.show();
                break;
        }
    }


    private void refreshFragment(String startTime, String endTime) {
        switch (vpActMyproject.getCurrentItem()) {
//            case 0:
//                ((MyProjectTotalFragment) listFrag.get(0)).setStart_time(startTime);
//                ((MyProjectTotalFragment) listFrag.get(0)).setEnd_time(endTime);
//                ((MyProjectTotalFragment) listFrag.get(0)).initDataFromInternet();
//                break;
            case 0:
                ((MyProjectTotalFragment) listFrag.get(0)).setStart_time(startTime);
                ((MyProjectTotalFragment) listFrag.get(0)).setEnd_time(endTime);
                ((MyProjectTotalFragment) listFrag.get(0)).initDataFromInternet();
                break;
            case 1:
                ((MyProjectCreditorFragment) listFrag.get(1)).setStart_time(startTime);
                ((MyProjectCreditorFragment) listFrag.get(1)).setEnd_time(endTime);
                ((MyProjectCreditorFragment) listFrag.get(1)).initDataFromInternet();
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        listFrag = new ArrayList<>();
//        MyProjectTotalFragment fragment1 = new MyProjectTotalFragment();


        MyProjectTotalFragment fragment2 = new MyProjectTotalFragment();


        MyProjectCreditorFragment fragment3 = new MyProjectCreditorFragment();


//        listFrag.add(fragment1);
        listFrag.add(fragment2);
        listFrag.add(fragment3);


    }

    @Override
    public void initView() {
        list = new ArrayList<>();
//        list.add(new UnderlineTextView(this));
        list.add(utvTouZiXiangMu);
        list.add(utvZhaiQuanXiangMu);

        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getSupportFragmentManager(), listFrag);
        vpActMyproject.setAdapter(adapter);
        vpActMyproject.setOffscreenPageLimit(2);


//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActMyproject.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActMyproject.setLayoutParams(params);
    }

    @Override
    public void initListener() {
//        utvTotal.setOnClickListener(this);
        utvTouZiXiangMu.setOnClickListener(this);
        utvZhaiQuanXiangMu.setOnClickListener(this);
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);

        vpActMyproject.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }
}
