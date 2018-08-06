package com.tudoujf.activity.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.tudoujf.R;
import com.tudoujf.adapter.IntegralRecodeActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.IntegralRecodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.CalendarDialogScroll;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.TimeUtils;
import com.tudoujf.utils.ToastUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            IntegralRecodeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/17
 * description：     签到-->我的积分-->积分记录activity
 * history：
 * * ====================================================================
 */

public class IntegralRecodeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_integralrecode)
    MTopBarView mtbIntegralRecode;
    @BindView(R.id.tv_act_integralrecode_totalintegral)
    TextView tvTotalintegral;//总积分
    @BindView(R.id.tv_act_integralrecode_integralrecode)
    TextView intefralRecode;//积分记录
    @BindView(R.id.iv_act_integralrecode_filtrate)
    ImageView ivFiltrate;//筛选按钮
    @BindView(R.id.lv_act_integralrecode)
    ListView lvIntegralRecode;
    @BindView(R.id.srl_fact_integralrecode)
    SmartRefreshLayout srlIntegralRecode;
    private String TAG = "IntegralRecodeActivity";

    /**
     * 返回的请求数据databean
     */
    private IntegralRecodeBean bean;
    /**
     * 总积分
     */
    private String totalIntegral;
    /**
     * 积分记录数据集
     */
    private List<IntegralRecodeBean.ItemsBean> list;
    /**
     * 加载进度dialog,选择筛选时间dialog
     */
    private AlertDialog  timeSelDialog;
    /**
     * 加载显示积分页数
     */
    private int page = 1;
    /**
     * 参数--start_time
     */
    private String paramsStartTime = "";
    /**
     * 参数---end_time
     */
    private String paramsEndTime = "";
    /**
     * listview适配器
     */
    private IntegralRecodeActLvAdapter adapter;
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
//    private CalendarDialog calendarDialog;
    private CalendarDialogScroll calendarDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralrecode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_act_integralrecode_filtrate://时间筛选按钮
                if (timeSelDialog == null) {
                    timeSelDialog = DialogUtils.showTimeSel(this, "", view);
                } else {
                    timeSelDialog.show();
                }

                break;
            case R.id.tv_dialog_starttime://dialog中选择开始时间
                if (calendarDialog == null) {
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog = new CalendarDialogScroll(this, 1);
                }
                calendarDialog.showDialog(1);
                break;
            case R.id.tv_dialog_endtime://dialog中选择结束时间
                if (calendarDialog == null) {
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog = new CalendarDialogScroll(this, 2);
                }
                calendarDialog.showDialog(2);
                break;
            case R.id.tv_dialog_cancel://dialog中取消选择
                timeSelDialog.dismiss();
                break;
            case R.id.tv_dialog_confirm://dialog中确认选择
//                dialog.show();
                String date1 = startTime.getText().toString();
                String date2 = endTime.getText().toString();
                if (TimeUtils.compareDate(date1, date2)) {
                    paramsStartTime = date2;
                    paramsEndTime = date1;
                } else {
                    paramsStartTime = date1;
                    paramsEndTime = date2;
                }
                page = 1;
                initDataFromInternet();
                timeSelDialog.dismiss();
                break;
            case R.id.tv_act_integralrecode_integralrecode:
                paramsStartTime = "";
                paramsEndTime = "";
                page = 1;
//                dialog.show();
                initDataFromInternet();
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalIntegral = bundle.getString("totalIntegral");
        }
    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbIntegralRecode.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbIntegralRecode.setLayoutParams(params);
        tvTotalintegral.setText(totalIntegral);


        //设置全区背景色
        srlIntegralRecode.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
        srlIntegralRecode.setEnableRefresh(false);
        //设置 Footer 为 球脉冲
        srlIntegralRecode.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

//        dialog = DialogUtils.showProgreessDialog(this, "再次点击将退出该页面!");

        view = LayoutInflater.from(this).inflate(R.layout.dialog_act_integralrecode_timesel, null);

        startTime = (TextView) view.findViewById(R.id.tv_dialog_starttime);
        endTime = (TextView) view.findViewById(R.id.tv_dialog_endtime);
        cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        confirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);

        startTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        endTime.setText(TimeUtils.getNowDateShort());

//        calendarDialog=new CalendarDialog(this);
        calendarDialog = new CalendarDialogScroll(this, 1);
    }

    @Override
    public void initListener() {
        mtbIntegralRecode.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        srlIntegralRecode.setOnLoadmoreListener(new OnLoadmoreListener() {//加载更多
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages() + 1) {
                    page = page + 1;
                    initDataFromInternet();//再次请求数据
                } else {
                    srlIntegralRecode.finishLoadmore();
                    ToastUtils.showToast(IntegralRecodeActivity.this, R.string.meiyougengduojilula);
                }

            }
        });
        //非滑动日历
//        calendarDialog.setListener(new CalendarDialog.OnCalendarDialogDismissListener() {
//
//            @Override
//            public void onDismiss(String sTime, String eTime) {
//                startTime.setText(sTime);
//                endTime.setText(eTime);
//            }
//        });
        //滑动日历
        calendarDialog.setListener(new CalendarDialogScroll.OnCalendarDialogDismissListener() {
            @Override
            public void onDismiss(String sTime, String eTime) {
                startTime.setText(sTime);
                endTime.setText(eTime);
            }
        });

        ivFiltrate.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        intefralRecode.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        TreeMap<String, String> map = new TreeMap<>();

        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("login_token", "12267");
        map.put("start_time", paramsStartTime);
        map.put("end_time", paramsEndTime);
        map.put("page_count", "");//待删除字段
        map.put("page", "" + page);
        showPDialog();

        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_LIST, map, "IntegralRecodeActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                LUtils.e(IntegralRecodeActivity.class,"logflag--消息接口请求返回数据-"+result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IntegralRecodeBean>() {
                }.getType(), IntegralRecodeBean.class, IntegralRecodeActivity.this);

                if (page == 1 && bean1 != null) {
//                    dialog.dismiss();
                    bean = (IntegralRecodeBean) bean1;
                    LoadInternetDataToUi();
                } else if (page > 1 && bean1 != null) {
                    srlIntegralRecode.finishLoadmore();
                    bean = (IntegralRecodeBean) bean1;
                    LoadInternetDataToUi();
                }
            }

            @Override
            public void onError(Response<String> response) {
//                dialog.dismiss();
                dismissPDialog();
                super.onError(response);
            }
        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            if (page == 1 && bean.getItems() != null && bean.getItems().size() != 0) {
                list = bean.getItems();
                adapter = new IntegralRecodeActLvAdapter(list, this);
                lvIntegralRecode.setAdapter(adapter);
            } else if (page > 1 && bean.getItems() != null) {
                list.addAll(bean.getItems());
                adapter.notifyDataSetChanged();
            } else {
                ToastUtils.showToast(this, "当前没有要显示的积分记录");
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
