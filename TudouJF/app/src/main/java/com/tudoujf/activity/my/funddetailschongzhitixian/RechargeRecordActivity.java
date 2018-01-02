package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.util.Log;
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
import com.tudoujf.adapter.RechargeRecordActLvAdapterN;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.RechargeRecodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            RechargeRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  充值记录activity
 * history：
 * * ====================================================================
 */

public class RechargeRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_rechargerecord)
    MTopBarView mtbActRechargeRecord;
    @BindView(R.id.lv_act_rechargerecord)
    ListView lvActRechargeRecord;
    //    @BindView(R.id.tv_act_rechargerecord_loadmore)
//    TextView tvLoadMore;
    @BindView(R.id.srl_act_rechargerecord)
    SmartRefreshLayout srl;


    private List<RechargeRecodeBean.ItemsBean> list;
    private int page = 1;
    private RechargeRecodeBean bean;
    private RechargeRecordActLvAdapterN adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_rechargerecord;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRechargeRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRechargeRecord.setLayoutParams(params);


        //设置全区背景色
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        srl.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        srl.setEnableLoadmore(true);

        list = new ArrayList<>();

    }

    @Override
    public void initListener() {
        mtbActRechargeRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
                    ToastUtils.showToast(RechargeRecordActivity.this, R.string.meiyougengduola);
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

        HttpMethods.getInstance().POST(this, Constants.RECHARG_ERECORD, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();

                        if (page > 1) {
                            srl.finishLoadmore();
                        } else {
                            srl.finishRefresh();
                        }

                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----充值记录接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<RechargeRecodeBean>() {
                        }.getType(), RechargeRecodeBean.class, RechargeRecordActivity.this);
                        if (bean1 != null) {
                            bean = (RechargeRecodeBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(RechargeRecordActivity.this, R.string.shujujiazaichucuo);
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
                adapter = new RechargeRecordActLvAdapterN(list, this);
                lvActRechargeRecord.setAdapter(adapter);
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
