package com.tudoujf.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.fragment.myearnings.DueInInterestFragment;
import com.tudoujf.ui.CalendarDialogScroll;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyEarningsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/15
 * description：  我的收益页面activity
 * history：
 * ===================================================
 */
public class MyEarningsActivity extends BaseActivity {
    @BindView(R.id.tv_act_myearnings_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_myearnings_filtrate)
    LinearLayout llFiltrate;
    @BindView(R.id.fl_act_myearnings)
    FrameLayout flActMyEarnings;
    @BindView(R.id.utv_act_myearnings_daishoulixi)
    UnderlineTextView utvDaiShouLiXi;
    @BindView(R.id.utv_act_myearnings_yishoulixi)
    UnderlineTextView utvYiShouLiXi;
    @BindView(R.id.utv_act_myearnings_huodongshouyi)
    UnderlineTextView utvHuoDongShouYi;
    @BindView(R.id.vp_act_myearnings)
    ViewPager vpActMyEarnings;
    @BindView(R.id.tv_act_myearnings_total)
    TextView tvTotal;

    private List<UnderlineTextView>  list;
    private List<Fragment>  listFrag;
    private CalendarDialogScroll dialogScroll;

    /**
     * 自定义dialog的展示view
     */
    private View view;
    /**
     * 自定义dialog的展示view的控件
     */
    private TextView startTime, endTime, cancel, confirm;
    /**参数--start_time*/
    private String  paramsStartTime="";
    /**参数---end_time*/
    private String paramsEndTime="";



    /**加载进度dialog,选择筛选时间dialog*/
    private AlertDialog dialog,timeSelDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.act_myearnings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.utv_act_myearnings_daishoulixi:
                vpActMyEarnings.setCurrentItem(0);
                setButStyle(0);
                break;
            case R.id.utv_act_myearnings_yishoulixi:
                vpActMyEarnings.setCurrentItem(1);
                setButStyle(1);
                break;
            case R.id.utv_act_myearnings_huodongshouyi:
                vpActMyEarnings.setCurrentItem(2);
                setButStyle(2);
                break;
            case R.id.tv_act_myearnings_bac:
               closeActivity();
                break;
            case R.id.ll_act_myearnings_filtrate:
                if (timeSelDialog==null){
                    timeSelDialog= DialogUtils.showTimeSel(this, "", view);
                }else {
                    timeSelDialog.show();
                }
//                if (dialogScroll==null){
//                    dialogScroll=new CalendarDialogScroll(this);
//                }
//                dialogScroll.showDialog();
                break;
            case R.id.tv_dialog_starttime://dialog中选择开始时间
                if (dialogScroll==null){
                    dialogScroll=new CalendarDialogScroll(this);
                }
                dialogScroll.showDialog();
                break;
            case R.id.tv_dialog_endtime://dialog中选择结束时间
                if (dialogScroll==null){
                    dialogScroll=new CalendarDialogScroll(this);
                }
                dialogScroll.showDialog();
                break;
            case R.id.tv_dialog_cancel://dialog中取消选择
                timeSelDialog.dismiss();
                break;
            case R.id.tv_dialog_confirm://dialog中确认选择
//                dialog.show();//进度dialog
                String date1=startTime.getText().toString();
                String date2=endTime.getText().toString();
                if (TimeUtils.compareDate(date1,date2)){
                    paramsStartTime=date2;
                    paramsEndTime=date1;
                }else {
                    paramsStartTime=date1;
                    paramsEndTime=date2;
                }
//                page=1;
//                initDataFromInternet();
//                Log.e(TAG, "onClick:-----------true,第一个日期较大---------------- "+TimeUtils.compareDate(date1,date2) );
                timeSelDialog.dismiss();

                // TODO: 2017/8/21 根据选择的时间进行条件查询展示

                break;
        }

    }

    private void setButStyle(int position){
        for (int i = 0; i < list.size(); i++) {
            if (i==position){
                list.get(i).setUnderlinecolor(R.color.global_theme_background_color);
                list.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));
            }else {
                list.get(i).setUnderlinecolor(R.color.color_white);
                list.get(i).setTextColor(getResources().getColor(R.color.color_black));
            }
        }

    }

    @Override
    public void initDataFromIntent() {
        listFrag=new ArrayList<>();
        DueInInterestFragment  fragment1=new DueInInterestFragment();
        Bundle bundle1=new Bundle();
        bundle1.putInt("type",1);
        fragment1.setArguments(bundle1);

        DueInInterestFragment  fragment2=new DueInInterestFragment();
        Bundle bundle2=new Bundle();
        bundle2.putInt("type",2);
        fragment2.setArguments(bundle2);

        DueInInterestFragment  fragment3=new DueInInterestFragment();
        Bundle bundle3=new Bundle();
        bundle3.putInt("type",3);
        fragment3.setArguments(bundle3);

        listFrag.add(fragment1);
        listFrag.add(fragment2);
        listFrag.add(fragment3);

    }

    @Override
    public void initView() {

        list=new ArrayList<>();
        list.add(utvDaiShouLiXi);
        list.add(utvYiShouLiXi);
        list.add(utvHuoDongShouYi);

        HomeFragVPAdapter  adapter=new HomeFragVPAdapter(getSupportFragmentManager(),listFrag);
        vpActMyEarnings.setAdapter(adapter);
        vpActMyEarnings.setOffscreenPageLimit(3);


//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) flActMyEarnings.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        flActMyEarnings.setLayoutParams(params);


        view = LayoutInflater.from(this).inflate(R.layout.dialog_act_integralrecode_timesel, null);

        startTime = (TextView) view.findViewById(R.id.tv_dialog_starttime);
        endTime = (TextView) view.findViewById(R.id.tv_dialog_endtime);
        cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        confirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);

        startTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        endTime.setText(TimeUtils.getNowDateShort());

        dialogScroll=new CalendarDialogScroll(this);



    }

    @Override
    public void initListener() {

        utvDaiShouLiXi.setOnClickListener(this);
        utvHuoDongShouYi.setOnClickListener(this);
        utvYiShouLiXi.setOnClickListener(this);
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);

        vpActMyEarnings.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                setButStyle(position);
            }
        });

        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);

        dialogScroll.setListener(new CalendarDialogScroll.OnCalendarDialogDismissListener() {
            @Override
            public void onDismiss(String sTime, String eTime) {
                startTime.setText(sTime);
                endTime.setText(eTime);
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
