package com.tudoujf.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.TimeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * * ==================================================
 * name:            CalendarDialogScroll
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：   选择日期view(可以滑动)
 * history：
 * * ==================================================
 */

public class CalendarDialogScroll implements View.OnClickListener {
    private TextView tvStartTime, tvEndTime, tvPrevious, tvNext, tvCurrentTime;
    private ViewPager viewPager;
    /**
     * 自定义展示view
     */
    private View view;
    private Context context;

    /**
     * 当前点击按钮标识
     */
    private int buttonFlag = 1;
    /**
     * dialog dismiss监听
     */
    private OnCalendarDialogDismissListener listener;
    private AlertDialog dialog;
    private List<CalendarView>  listData;
    private List<DayBean> monthList;


    public OnCalendarDialogDismissListener getListener() {
        return listener;
    }

    public void setListener(OnCalendarDialogDismissListener listener) {
        this.listener = listener;
    }

    public CalendarDialogScroll(Context context) {
        this.context = context;
        initData();
        initView();
        initListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initDataList();
        listData=new ArrayList<>();
        for (int i = 0; i < monthList.size(); i++) {
            CalendarView view=new CalendarView(context,monthList.get(i).getYear(),monthList.get(i).getMonth());
            view.setListener(new CalendarView.SelDayListener() {
                @Override
                public void onClick(String dayStr, String yearMonthStr) {
                    if (buttonFlag==1){
                        tvStartTime.setText(dayStr);
                    }else {
                        tvEndTime.setText(dayStr);
                        dialog.dismiss();
                    }
                }
            });
            listData.add(view);
        }
    }

    private void initListener() {


        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tvCurrentTime.setText(listData.get(position).getTitle());

                // TODO: 2017/8/23 可在此处动态设置viewpager的高度,但设置后在切换时会有抖动
            }
        });
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
                    ToastUtils.showToast(context,"没有更多了!!");
                }
                break;
            case R.id.tv_dialog_canlendar_next://下一个月
                if (viewPager.getCurrentItem()<listData.size()-1){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else{
                    ToastUtils.showToast(context,"没有更多了!!");
                }
                break;
        }

    }
    /**初始化控件*/
    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.dialog_act_integralrecode_calendarscroll, null);
        tvStartTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_starttime);
        tvEndTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_endtime);
        tvPrevious = (TextView) view.findViewById(R.id.tv_dialog_canlendar_previous);
        tvNext = (TextView) view.findViewById(R.id.tv_dialog_canlendar_next);
        tvCurrentTime = (TextView) view.findViewById(R.id.tv_dialog_canlendar_currenttime);
        viewPager= (ViewPager) view.findViewById(R.id.vp_dialog_canlendar);

        CalendarVPAdapter adapter=new CalendarVPAdapter(listData);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(listData.size()-13);
        tvCurrentTime.setText(listData.get(listData.size()-13).getTitle());


        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) viewPager.getLayoutParams();
        params.height=6*(ScreenSizeUtils.getScreenWidth(context) - 40 * ScreenSizeUtils.getDensity(context))/7+30;
        viewPager.setLayoutParams(params);


        tvStartTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        tvEndTime.setText(TimeUtils.getNowDateShort());

    }



    /**
     * 显示当前控件
     */
    public void show() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (listener != null) {
                    listener.onDismiss(tvStartTime.getText().toString(), tvEndTime.getText().toString());
                }

            }
        });
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            ColorDrawable drawable = new ColorDrawable(Color.WHITE);
            window.setBackgroundDrawable(drawable);
            window.setContentView(view);
            window.setWindowAnimations(R.style.AnimBottom);
        }
    }

    public void showDialog(){
        if (dialog==null){
            show();
        }else {
            dialog.show();
        }
    }

    public interface OnCalendarDialogDismissListener {
        void onDismiss(String sTime, String eTime);
    }


    private void initDataList(){
        monthList=new ArrayList<>();
        int  currentYear=TimeUtils.getCurrentYear();
        int  currentMonth=TimeUtils.getCurrentMonthInt();
        for (int i = 0; i < currentYear-2016+1; i++) {
            for (int j = 0; j < 12; j++) {
                DayBean bean=new DayBean();
                bean.setYear(2016+i);
                bean.setMonth(j+1);
                monthList.add(bean);
            }
        }
        for (int i = 0; i < currentMonth; i++) {
            DayBean bean=new DayBean();
            bean.setYear(currentYear+1);
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
