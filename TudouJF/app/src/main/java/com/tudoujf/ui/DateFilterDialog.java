package com.tudoujf.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.TimeUtils;

/**
 * * ==================================================
 * name:            DateFilterDialog
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/7
 * description：   选择日期view,确认时返回开始时间和结束时间
 * history：
 * * ==================================================
 */


public class DateFilterDialog implements View.OnClickListener {
    private Context context;
    private ClickEvent lisenter;

    /**
     * 自定义dialog的展示view
     */
    private View view;
    /**
     * 自定义dialog的展示view的控件
     */
    private TextView startTime, endTime, cancel, confirm;
    /**
     * 日历dialog
     */
    private CalendarDialogScroll calendarDialog;
    /**
     * 加载进度dialog,选择筛选时间dialog
     */
    private AlertDialog timeSelDialog;

    /**
     * 构造函数
     */
    public DateFilterDialog(Context context) {
        this.context = context;
        init();
    }

    public void setLisenter(ClickEvent lisenter) {
        this.lisenter = lisenter;
    }

    /**
     * 展示dialog
     */
    @SuppressLint("InflateParams")
    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_act_integralrecode_timesel, null);

        startTime = (TextView) view.findViewById(R.id.tv_dialog_starttime);
        endTime = (TextView) view.findViewById(R.id.tv_dialog_endtime);
        cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        confirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);

        startTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        endTime.setText(TimeUtils.getNowDateShort());

        calendarDialog = new CalendarDialogScroll(context);

        initLisenter();
    }

    public void show() {
        if (timeSelDialog == null) {
            timeSelDialog = DialogUtils.showTimeSel(context, "", view);
        } else {
            timeSelDialog.show();
        }
    }

    private void initLisenter() {
        calendarDialog.setListener(new CalendarDialogScroll.OnCalendarDialogDismissListener() {
            @Override
            public void onDismiss(String sTime, String eTime) {
                startTime.setText(sTime);
                endTime.setText(eTime);
            }
        });
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_starttime://dialog中选择开始时间
                if (calendarDialog == null) {
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog = new CalendarDialogScroll(context);
                }
                calendarDialog.showDialog();
                break;
            case R.id.tv_dialog_endtime://dialog中选择结束时间
                if (calendarDialog == null) {
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog = new CalendarDialogScroll(context);
                }
                calendarDialog.showDialog();
                break;
            case R.id.tv_dialog_cancel://dialog中取消选择
                timeSelDialog.dismiss();
                break;
            case R.id.tv_dialog_confirm://dialog中确认选择

                String date1 = startTime.getText().toString();
                String date2 = endTime.getText().toString();
                String temp;
                if (TimeUtils.compareDate(date1, date2)) {
                    temp = date1;
                    date1 = date2;
                    date2 = temp;
                }
                if (lisenter != null) {
                    lisenter.dismiss(date1, date2);
                }
                timeSelDialog.dismiss();
                break;
        }
    }


    public interface ClickEvent {
        void dismiss(String startTime, String endTime);
    }
}
