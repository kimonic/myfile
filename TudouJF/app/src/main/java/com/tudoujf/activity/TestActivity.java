package com.tudoujf.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.ProductDetailsActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.CalendarView;
import com.tudoujf.utils.AESUtils;
import com.tudoujf.utils.TimeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            TestActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：测试控件用的activity
 * history：
 * ===================================================
 */

public class TestActivity extends BaseActivity {
    @BindView(R.id.tv_dialog_canlendar_starttime)
    TextView tvStartTime;
    @BindView(R.id.tv_dialog_canlendar_endtime)
    TextView tvEndTime;
    @BindView(R.id.tv_dialog_canlendar_previous)
    TextView tvPrevious;
    @BindView(R.id.tv_dialog_canlendar_currenttime)
    TextView tvCurrentTime;
    @BindView(R.id.tv_dialog_canlendar_next)
    TextView tvNext;
    @BindView(R.id.ll_dialog_canlendar_current)
    LinearLayout llDialogCanlendarCurrent;
    @BindView(R.id.rv_dialog_canlendar)
    ViewPager viewPager;
    private List<CalendarView>  listData;
    private List<DayBean> monthList;



    /**
     * 当前点击按钮标识
     */
    private int buttonFlag = 1;
    private String TAG="TestActivity";

    @Override
    public int getLayoutResId() {
        return R.layout.act_test;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_canlendar_starttime://开始日期
                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_left);
                buttonFlag = 1;
                break;
            case R.id.tv_dialog_canlendar_endtime://结束日期
                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_right);
                buttonFlag = 2;
                break;
            case R.id.tv_dialog_canlendar_previous://上一个月
                if (viewPager.getCurrentItem()>0){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                }else {
                    ToastUtils.showToast(this,"没有更多了!!");
                }
                break;
            case R.id.tv_dialog_canlendar_next://下一个月
                if (viewPager.getCurrentItem()<listData.size()-1){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else{
                    ToastUtils.showToast(this,"没有更多了!!");
                }
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        initDataList();
        listData=new ArrayList<>();
        for (int i = 0; i < monthList.size(); i++) {
            CalendarView view=new CalendarView(this,monthList.get(i).getYear(),monthList.get(i).getMonth());
            view.setListener(new CalendarView.SelDayListener() {
                @Override
                public void onClick(String dayStr, String yearMonthStr) {
                    if (buttonFlag==1){
                        tvStartTime.setText(dayStr);
                    }else {
                        tvEndTime.setText(dayStr);
                    }
                }
            });
            listData.add(view);
        }

        //测试aes加密

        String  mingwen="12331";
        String  miwen= AESUtils.encrypt("123456",mingwen);
        String  jiemi=AESUtils.decrypt("123456",miwen);

        Log.e(TAG, "initDataFromIntent: ----------密文---------"+miwen );
        Log.e(TAG, "initDataFromIntent: ----------明文---------"+jiemi );


    }

    @Override
    public void initView() {
        CalendarVPAdapter adapter=new CalendarVPAdapter(listData);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(listData.size()-1);
        tvCurrentTime.setText(listData.get(listData.size()-1).getTitle());

        tvStartTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        tvEndTime.setText(TimeUtils.getNowDateShort());

    }

    @Override
    public void initListener() {



        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCurrentTime.setText(listData.get(position).getTitle());

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

    private void initDataList(){
         monthList=new ArrayList<>();
        int  currentYear=TimeUtils.getCurrentYear();
        int  currentMonth=TimeUtils.getCurrentMonthInt();
        int  monthCount=(currentYear-2016)*12+currentMonth;
        for (int i = 0; i < currentYear-2016; i++) {
            for (int j = 0; j < 12; j++) {
                DayBean bean=new DayBean();
                bean.setYear(2016+i);
                bean.setMonth(j+1);
                monthList.add(bean);
            }
        }
        for (int i = 0; i < currentMonth; i++) {
            DayBean bean=new DayBean();
            bean.setYear(currentYear);
            bean.setMonth(i+1);
            monthList.add(bean);
        }


    }

    private class DayBean{
        private int  year;
        private int  month;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    }


    private class CalendarVPAdapter extends PagerAdapter {

        private List<CalendarView> list;

        public CalendarVPAdapter(List<CalendarView> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /**
         * 实例化 一个 页卡
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 添加一个 页卡

            container.addView(list.get(position));

            return list.get(position);
        }

        /**
         * 销毁 一个 页卡
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 删除
            container.removeView(list.get(position));
        }



    }

}
