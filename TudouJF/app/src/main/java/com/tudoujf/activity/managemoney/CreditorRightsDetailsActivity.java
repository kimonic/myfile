package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.CreditorRightsDetailsBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

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

    private String transfer_id = "", loan_id = "";
    private CreditorRightsDetailsBean bean;
    private IdentityCheckBean identityCheckBean;
    private String loginToken;
    private String status;
    /**
     * 是否已经登陆,已经登陆了则只检查是否实名,未登陆登陆后需要再次刷新本页面
     */
    private boolean isLogin = false;
    private boolean isLogin1 = false;

    /**
     * 验证请求只执行一次
     */
    private boolean request = true;
    private AlertDialog promptDialog;


    @Override
    public int getLayoutResId() {
        return R.layout.act_creditorrightsdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_creditorsrightsdetails_buynow:
                if (bean != null && "1".equals(bean.getTransferMap().getIs_self())) {
                    ToastUtils.showToast(CreditorRightsDetailsActivity.this, R.string.zijibunenggoumaizijidebiaodi);
                } else if (request) {
                    checkLogin();
                } else {
                    enterBuy();
                }
                break;
        }

    }

    /**
     * 进入购买页面
     */
    private void enterBuy() {
        request = false;
        if (bean != null) {
            if (bean.getTransferMap().getIs_self() == null) {
                Intent intent = new Intent(this, AffirmBuyCreditorsRightsActivity.class);
                intent.putExtra("id", bean.getTransferMap().getId());
                startActivity(intent);
            } else if ("2".equals(bean.getTransferMap().getIs_self())) {
                ToastUtils.showToast(CreditorRightsDetailsActivity.this, R.string.bunenggoumaizijidezhaiquan);
            } else if ("1".equals(bean.getTransferMap().getIs_self())) {
                ToastUtils.showToast(CreditorRightsDetailsActivity.this, R.string.bunenggoumaizijidezhaiwu);
            } else {
                ToastUtils.showToast(this, R.string.weizhicuowu);
            }
        } else {
            ToastUtils.showToast(this, R.string.shujujiazaichucuoqtchcxjr);
        }
    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            transfer_id = intent.getStringExtra("transfer_id");
            loan_id = intent.getStringExtra("loan_id");
            status = intent.getStringExtra("status");
        }


    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbCreditorRightsDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbCreditorRightsDetails.setLayoutParams(params);

        ivInfo.setIfNew(false);
        ivInfo.setCloseBottomLine(true);
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
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("transfer_id", transfer_id);
        map.put("loan_id", loan_id);
        HttpMethods.getInstance().POST(this, Constants.CREDITOR_DETAILS, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(CreditorRightsDetailsActivity.class, "logflag-请求债权详情返回的json数据--" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<CreditorRightsDetailsBean>() {
                        }.getType(), CreditorRightsDetailsBean.class, CreditorRightsDetailsActivity.this);
                        if (bean1 != null) {
                            bean = (CreditorRightsDetailsBean) bean1;
                            LoadInternetDataToUi();
                            if (isLogin) {
                                enterBuy();
                            }
                        } else {

                            ToastUtils.showToast(CreditorRightsDetailsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(CreditorRightsDetailsActivity.this, R.string.huoquzhaiquanxiangqignshujushibai);

                    }
                }
        );


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            tvTransferPrice.setText(bean.getTransferMap().getAmount());
            tvTransferEarnings.setText(bean.getTransferMap().getIncome());
            tvCapitalOnCall.setText(bean.getTransferMap().getWait_principal());

            tvInterrestOnCall.setText(bean.getTransferMap().getWait_interest());
            tvCreditorValue.setText(bean.getTransferMap().getAmount_money());
            tvFinancingAmount.setText(bean.getLoanMap().getLoan_info().getAmount());//融资金额

            tvRepaymentWay.setText(bean.getLoanMap().getRepay_type().getName());//还款方式
            tvRepaymentSchedule.setText(("已还" + bean.getTransferMap().getPeriod_yes() + "期/共"
                    + bean.getTransferMap().getTotal_period() + "期"));//??还款进度
            tvRepaymentDeadline.setText(StringUtils.getStrTime(bean.getTransferMap().getLast_repay_time()));//??还款期限
            tvInvestCount.setText(bean.getLoanMap().getLoan_info().getTender_count());//投资人数

            status = bean.getTransferMap().getStatus();

            ivInfo.setNianHuaShouYi(bean.getLoanMap().getLoan_info().getApr());
            ivInfo.setJieKuanQiXian(bean.getLoanMap().getLoan_info().getPeriod_name());
            ivInfo.invalidate();

            if (!"1".equals(status)) {
                tvBuyNow.setClickable(false);
                tvBuyNow.setBackgroundColor(getResources().getColor(R.color.color_gray));
                if ("2".equals(status)) {
                    tvBuyNow.setText(getResources().getString(R.string.shouqing));
                }

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

//-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------

    private void checkLogin() {
        loginToken = UserConfig.getInstance().getLoginToken(this);
        if ("".equals(loginToken)) {
            isLogin = false;
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("type", 888);
            startActivityForResult(intent, 888);
        } else {
            isLogin = true;
            checkIdentity();
        }

    }

    private void checkIdentity() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);
        HttpMethods.getInstance().POST(this, Constants.IDENTITY_CHECK, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                LUtils.e(CreditorRightsDetailsActivity.class, "logflag-请求身份是否实名返回的json数据--" +result);

                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IdentityCheckBean>() {
                }.getType(), IdentityCheckBean.class, CreditorRightsDetailsActivity.this);
                if (bean1 != null) {
                    identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        if (isLogin && !isLogin1) {//之前已登录
                            enterBuy();
                        } else {//之前未登录
                            isLogin = true;
                            initDataFromInternet();
                        }
                    } else {
                        if (promptDialog == null) {
                            promptDialog = DialogUtils.showPromptDialog(CreditorRightsDetailsActivity.this, "提示", "您还没有实名，请先实名!", new DialogUtils.DialogUtilsClickListener() {
                                @Override
                                public void onClick() {
                                    promptDialog.dismiss();
                                    openActivity(RealNameAuthenticationHuiFuActivity.class);
                                }
                            });
                        } else {
                            promptDialog.show();
                        }
//                        //  弹出汇付托管开通页面
//                        DialogUtils.showDialog(CreditorRightsDetailsActivity.this, R.string.weilenindezijinanquan,
//                                R.string.queding, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Intent intent=new Intent(CreditorRightsDetailsActivity.this, RealNameAuthenticationHuiFuActivity.class);
//                                        startActivity(intent);
//                                    }
//                                });
//                        DialogUtils.showHuiFuDialog(CreditorRightsDetailsActivity.this);
                    }
                } else {
                    ToastUtils.showToast(CreditorRightsDetailsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(CreditorRightsDetailsActivity.this, R.string.yanzhengshimingxinxishibai);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888 && resultCode == 888) {
            isLogin1 = true;
            checkLogin();
        }
    }

    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------


}
