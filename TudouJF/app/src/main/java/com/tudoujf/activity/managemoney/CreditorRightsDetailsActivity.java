package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.CreditorRightsDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            CreditorRightsDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  债权详情activity
 * history：
 * * ====================================================================
 */

public class CreditorRightsDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_creditorrightsdetails)
    MTopBarView mtbCreditorRightsDetails;
    @BindView(R.id.tv_act_creditorsrightsdetails_buynow)
    TextView tvBuyNow;
    @BindView(R.id.iv_act_creditorsrightsdetails_info)
    InfoView ivInfo;

    @BindView(R.id.tv_act_creditorsrightsdetails_transfer_price)
    TextView tvTransferPrice;
    @BindView(R.id.tv_act_creditorsrightsdetails_transfer_earnings)
    TextView tvTransferEarnings;
    @BindView(R.id.tv_act_creditorsrightsdetails_capital_on_call)
    TextView tvCapitalOnCall;
    @BindView(R.id.tv_act_creditorsrightsdetails_interrest_on_call)
    TextView tvInterrestOnCall;
    @BindView(R.id.tv_act_creditorsrightsdetails_creditor_value)
    TextView tvCreditorValue;
    @BindView(R.id.tv_act_creditorsrightsdetails_financing_amount)
    TextView tvFinancingAmount;
    @BindView(R.id.tv_act_creditorsrightsdetails_repayment_way)
    TextView tvRepaymentWay;
    @BindView(R.id.tv_act_creditorsrightsdetails_repayment_schedule)
    TextView tvRepaymentSchedule;
    @BindView(R.id.tv_act_creditorsrightsdetails_repayment_deadline)
    TextView tvRepaymentDeadline;
    @BindView(R.id.tv_act_creditorsrightsdetails_invest_count)
    TextView tvInvestCount;

    private String transfer_id = "",loan_id="";
    private AlertDialog dialog;

    private CreditorRightsDetailsBean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_creditorrightsdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_creditorsrightsdetails_buynow:
                if (bean!=null){
                    Intent intent=new Intent(this,AffirmBuyCreditorsRightsActivity.class);
                    intent.putExtra("id",bean.getTransferMap().getId());
                    startActivity(intent);
                }
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            transfer_id = intent.getStringExtra("transfer_id");
            loan_id = intent.getStringExtra("loan_id");
        }

    }

    @Override
    public void initView() {
///**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbCreditorRightsDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbCreditorRightsDetails.setLayoutParams(params);

        ivInfo.setIfNew(false);
        ivInfo.invalidate();

        mtbCreditorRightsDetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {
        tvBuyNow.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {
        showDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("transfer_id", transfer_id);
        map.put("loan_id", loan_id);
        Log.e("TAG", "initDataFromInternet: transfer_id-----" + transfer_id);
        Log.e("TAG", "initDataFromInternet: loan_id-----" + loan_id);
        Log.e("TAG", "initDataFromInternet: logintoken-----" + UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.CREDITOR_DETAILS, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dialog.dismiss();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------请求债权详情返回的json数据----------------" + result);
                Gson gson = new Gson();
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<CreditorRightsDetailsBean>() {
                }.getType(), CreditorRightsDetailsBean.class, CreditorRightsDetailsActivity.this);
                if (bean1 != null) {
                    bean = (CreditorRightsDetailsBean) bean1;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(CreditorRightsDetailsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }
        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean!=null){
            tvTransferPrice.setText(bean.getTransferMap().getAmount());
            tvTransferEarnings.setText(bean.getTransferMap().getIncome());
            tvCapitalOnCall.setText(bean.getTransferMap().getWait_principal());

            tvInterrestOnCall.setText(bean.getTransferMap().getWait_interest());
            tvCreditorValue.setText(bean.getTransferMap().getAmount_money());
            tvFinancingAmount.setText(bean.getLoanMap().getLoan_info().getAmount());//融资金额

            tvRepaymentWay.setText(bean.getLoanMap().getRepay_type().getName());//还款方式
            tvRepaymentSchedule.setText("已还"+bean.getTransferMap().getPeriod_yes()+"期/共"+bean.getTransferMap().getTotal_period()+"期");//??还款进度
            tvRepaymentDeadline.setText(bean.getTransferMap().getLast_repay_time());//??还款期限
            tvInvestCount.setText(bean.getLoanMap().getLoan_info().getTender_count());//投资人数
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

    private void showDialog() {
        if (dialog == null) {
            dialog = DialogUtils.showProgreessDialog(this, getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
        } else {
            dialog.show();
        }

    }



}
