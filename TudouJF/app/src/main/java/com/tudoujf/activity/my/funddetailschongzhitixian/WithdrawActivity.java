package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.managemoney.ProductDetailsActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.bean.databean.WithDrawBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            WithdrawActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  提现activity
 * history：
 * * ====================================================================
 */

public class WithdrawActivity extends BaseActivity {
    @BindView(R.id.tv_act_withdraw_bac)
    TextView tvBac;
    @BindView(R.id.tv_act_withdraw_tixianjilu)
    TextView tvTiXianJiLu;
    @BindView(R.id.mtb_act_withdraw)
    FrameLayout mtbActWithdraw;
    @BindView(R.id.et_act_withdraw_jine)
    EditText etJinE;
    @BindView(R.id.tv_act_withdraw_hint)
    TextView tvActWithdrawHint;
    @BindView(R.id.tv_act_withdraw_lijitixian)
    TextView tvLiJiTiXian;
    @BindView(R.id.tv_act_withdraw_amount)
    TextView tvAmount;
    @BindView(R.id.tv_act_withdraw_all)
    TextView tvAll;

    private AlertDialog dialog;
    private WithDrawBean bean;

    private String amount = "1000";
    private boolean  jump=false;
    private String loginToken;
    private boolean isLogin;
    private IdentityCheckBean identityCheckBean;
    private boolean isLogin1;

    @Override
    public int getLayoutResId() {
        return R.layout.act_withdraw;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_act_withdraw_bac://结束本页面
                closeActivity();
                break;
            case R.id.tv_act_withdraw_tixianjilu://打开提现记录
                openActivity(WithdrawRecordActivity.class);
                break;
            case R.id.tv_act_withdraw_lijitixian://立即提现
                if ("1".equals(bean.getIs_bind())){
                    if (etJinE.getText().toString().equals("")) {
                        if (dialog == null) {
                            dialog = showCustomDialog(LayoutInflater.from(this).inflate(R.layout.dialog_input, null));
                        } else {
                            dialog.show();
                        }
                    }else if (jump){
                        checkLogin();//检测实名与登陆状态
                    }
                }else {
                    ToastUtils.showToast(WithdrawActivity.this, R.string.ninshangweibangding);

                }

                break;
            case R.id.tv_act_withdraw_all://全部提现
                etJinE.setText(StringUtils.getCommaDecimalsStr(amount));
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

        Intent intent = getIntent();
        if (intent != null) {
            amount = intent.getStringExtra("amount");
        } else {
            amount = "0";
        }
        //---------------------------临时数据-----------------------------------------------------------
//        amount = "1000";

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActWithdraw.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActWithdraw.setLayoutParams(params);

    }

    @Override
    public void initListener() {


        tvBac.setOnClickListener(this);
        tvTiXianJiLu.setOnClickListener(this);
        tvLiJiTiXian.setOnClickListener(this);
        tvAll.setOnClickListener(this);

        etJinE.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                // 当前提现金额大于余额时
//                tvActWithdrawHint.setText("账号余额不足");
                float input = StringUtils.string2Float(editable.toString().replace(",",""));
                float fAmount = StringUtils.string2Float(amount);
                if (input < 100 || input > 500000) {
                    jump=false;
                    tvActWithdrawHint.setText(R.string.tixianjineyingdayu);
                    tvLiJiTiXian.setBackgroundResource(R.drawable.xshape_roundrect_mgray1);
                } else if (input > fAmount) {
                    jump=false;
                    tvLiJiTiXian.setBackgroundResource(R.drawable.xshape_roundrect_mgray1);
                    ToastUtils.showToast(WithdrawActivity.this, R.string.yuebuzu);
                } else {
                    jump=true;
                    tvActWithdrawHint.setText("");
                    tvLiJiTiXian.setBackgroundResource(R.drawable.xshape_roundrect_themecolor);
                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        Log.e("TAG", "initDataFromInternet: ------获取的logintoken--??---------" + UserConfig.getInstance().getLoginToken(this));
        Log.e("TAG", "initDataFromInternet: ------获取的logintoken----??-------" + Constants.WITHDRAW);
//        map.put("login_token", "12267");

        HttpMethods.getInstance().POST(this, Constants.WITHDRAW, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------提现接口请求返回数据-----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<WithDrawBean>() {
                }.getType(), WithDrawBean.class, WithdrawActivity.this);
                if (bean1 != null) {
                    bean = (WithDrawBean) bean1;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(WithdrawActivity.this, R.string.shujujiazaichucuo);
                }


            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
            }
        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            tvAmount.setText(StringUtils.getCommaDecimalsStr(amount));
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

    /**
     * 显示提示dialog
     *
     * @return alertdialog(v7)
     */
    public AlertDialog showCustomDialog(View view) {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(150 * ScreenSizeUtils.getDensity(this), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            ColorDrawable drawable = new ColorDrawable(Color.WHITE);
            window.setBackgroundDrawable(drawable);
//            window.setBackgroundDrawable(new ColorDrawable());
            window.setContentView(view);
        }
        return dialog;
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
                Log.e("TAG", "onSuccess: -----------请求身份是否实名返回的json数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IdentityCheckBean>() {
                }.getType(), IdentityCheckBean.class, WithdrawActivity.this);
                if (bean1 != null) {
                    identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        openHuiFu();
                    } else {
                        DialogUtils.showHuiFuDialog(WithdrawActivity.this);
                    }
                } else {
                    ToastUtils.showToast(WithdrawActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }
        });

    }

    /**打开汇付提现页面*/
    private void openHuiFu(){
        Intent intent=new Intent(this,WithdrawHuiFuActivity.class);
        intent.putExtra("amount",etJinE.getText().toString().replace(",",""));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888) {
            isLogin1 = true;
            checkLogin();
        }
    }

    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------

}
