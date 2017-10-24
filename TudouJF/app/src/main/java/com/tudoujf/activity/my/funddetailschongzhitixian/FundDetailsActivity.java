package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.FundDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.FundDetailsView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            FundDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/11
 * description：  资金详情activity
 * history：
 * * ====================================================================
 */

public class FundDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_act_funddetails_bac)
    TextView tvActFundDetailsBac;
    @BindView(R.id.tv_act_funddetails_jiaoyijilu)
    TextView tvJiaoYiJiLu;
    @BindView(R.id.tv_act_funddetails_withdraw)
    TextView tvWithdraw;
    @BindView(R.id.tv_act_funddetails_recharge)
    TextView tvRecharge;
    @BindView(R.id.mtb_act_funddetails)
    FrameLayout mtbActFundDetails;
    @BindView(R.id.tv_act_funddetails_total)
    TextView tvTotal;
    @BindView(R.id.fdv_act_funddetails_details)
    FundDetailsView fdvDetails;
    private FundDetailsBean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_funddetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_funddetails_bac:
                closeActivity();
                break;
            case R.id.tv_act_funddetails_jiaoyijilu://交易记录
                openActivity(TransactionRecordActivity.class);
                break;
            case R.id.tv_act_funddetails_withdraw:
                Intent intent = new Intent(this, WithdrawActivity.class);
                if (bean != null) {
                    intent.putExtra("amount", bean.getBalance_amount());
                } else {
                    intent.putExtra("amount", getResources().getString(R.string.zanwu));
                }
                startActivity(intent);
//                openActivity(WithdrawActivity.class);
                break;
            case R.id.tv_act_funddetails_recharge:
                Bundle bundle = new Bundle();
                if (bean != null) {
                    bundle.putString("balance", bean.getBalance_amount());
                } else {
                    bundle.putString("balance", getResources().getString(R.string.zanwu));
                }
                openActivity(RechargeActivity.class, bundle);

//                openActivity(RechargeActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFundDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFundDetails.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        tvActFundDetailsBac.setOnClickListener(this);
        tvJiaoYiJiLu.setOnClickListener(this);
        tvWithdraw.setOnClickListener(this);
        tvRecharge.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {


        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        Log.e("TAG", "initDataFromInternet: ---------loan_id------------" + UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.FUND_DETAILS, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();

                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----资金详情接口返回数据--------" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<FundDetailsBean>() {
                        }.getType(), FundDetailsBean.class, FundDetailsActivity.this);
                        if (bean1 != null) {
                            bean = (FundDetailsBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(FundDetailsActivity.this, R.string.shujujiazaichucuo);
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            tvTotal.setText(StringUtils.getCommaDecimalsStr(bean.getTotal_amount()));
            fdvDetails.setBalance_amount(StringUtils.getCommaDecimalsStr(bean.getBalance_amount()));
            fdvDetails.setFreeze_amount(StringUtils.getCommaDecimalsStr(bean.getFreeze_amount()));
            fdvDetails.setInterest_wait_total(StringUtils.getCommaDecimalsStr(bean.getInterest_wait_total()));
            fdvDetails.setInterest_yes_total(StringUtils.getCommaDecimalsStr(bean.getInterest_yes_total()));
            fdvDetails.setPrincipal_wait_total(StringUtils.getCommaDecimalsStr(bean.getPrincipal_wait_total()));
            fdvDetails.invalidate();
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
