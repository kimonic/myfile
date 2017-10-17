package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
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
    @BindView(R.id.tv_act_rechargerecord_loadmore)
    TextView tvLoadMore;


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
        switch (v.getId()) {
            case R.id.tv_act_rechargerecord_loadmore:
                if (bean != null && page < bean.getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else if (bean != null) {
                    ToastUtils.showToast(RechargeRecordActivity.this, R.string.meiyougengduola);
                } else {
                    page = 1;
                    initDataFromInternet();
                }

                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {

//        //临时数据源
//        list=new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            RechargeRecordActBean bean=new RechargeRecordActBean();
//            bean.setJinE("000,000,000.00");
//            bean.setDate("20XX-XX-XX");
//            bean.setState("充值成功");
//            bean.setContent("充值金额");
//
//            list.add(bean);
//        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRechargeRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRechargeRecord.setLayoutParams(params);


    }

    @Override
    public void initListener() {
        mtbActRechargeRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        tvLoadMore.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
//        RECHARG_ERECORD

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "" + page);
        Log.e("TAG", "initDataFromInternet: ---------loan_id------------" + page);

        HttpMethods.getInstance().POST(this, Constants.RECHARG_ERECORD, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
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
            if (page == 1) {
                list = bean.getItems();
            } else {
                list.addAll(bean.getItems());
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else{
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
