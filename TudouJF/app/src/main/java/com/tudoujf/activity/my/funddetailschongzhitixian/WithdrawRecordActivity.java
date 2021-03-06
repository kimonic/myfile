package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.RechargeRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.WithdrawRecordBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            WithdrawRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  提现记录activity
 * history：
 * * ====================================================================
 */

public class WithdrawRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_withdrawrecord)
    MTopBarView mtbActWithdrawRecord;
    @BindView(R.id.lv_act_withdrawrecord)
    ListView lvActWithdrawRecord;
    @BindView(R.id.srl_act_withdrawrecord)
    SmartRefreshLayout srl;

    private List<WithdrawRecordBean.ItemsBean> list;
    private int page = 1;

    private WithdrawRecordBean bean;
    private RechargeRecordActLvAdapter adapter;


    @Override
    public int getLayoutResId() {
        return R.layout.act_withdrawrecord;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActWithdrawRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActWithdrawRecord.setLayoutParams(params);

        //设置全区背景色
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        srl.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));


    }

    @Override
    public void initListener() {
        mtbActWithdrawRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        srl.setOnLoadmoreListener(new OnLoadmoreListener() {//加载更多
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page++;
                    initDataFromInternet();
                } else {
                    srl.finishLoadmore();
                    ToastUtils.showToast(WithdrawRecordActivity.this, R.string.meiyougengduola);
                }

            }
        });
        srl.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initDataFromInternet();
            }
        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "" + page);


        HttpMethods.getInstance().POST(this, Constants.WITHDRAW_RECORD, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();


                if (page > 1) {
                    srl.finishLoadmore();
                } else {
                    srl.finishRefresh();
                }


                String result = StringUtils.getDecodeString(response.body());
                LUtils.e(WithdrawRecordActivity.class,"logflag-提现记录接口请求返回数据--"+result);

                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<WithdrawRecordBean>() {
                }.getType(), WithdrawRecordBean.class, WithdrawRecordActivity.this);
                if (bean1 != null) {
                    bean = (WithdrawRecordBean) bean1;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(WithdrawRecordActivity.this, R.string.shujujiazaichucuo);
                }


            }


        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            list.addAll(bean.getItems());

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new RechargeRecordActLvAdapter(list, this);
                lvActWithdrawRecord.setAdapter(adapter);
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
