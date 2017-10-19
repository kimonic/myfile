package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.tudoujf.activity.home.IntegralRecodeActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.IntegralRecodeBean;
import com.tudoujf.bean.databean.WithDrawBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

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

    private AlertDialog dialog;
    private WithDrawBean  bean;

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
                if (etJinE.getText().toString().equals("")) {
                    if (dialog == null) {
                        dialog = showCustomDialog(LayoutInflater.from(this).inflate(R.layout.dialog_input, null));
                    } else {
                        dialog.show();
                    }
                }
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

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

        etJinE.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                // TODO: 2017/9/7 当前提现金额大于余额时
//                tvActWithdrawHint.setText("账号余额不足");
                if (StringUtils.string2Integer(editable.toString()) < 100 || StringUtils.string2Integer(editable.toString()) > 500000) {
                    tvActWithdrawHint.setText(R.string.tixianjineyingdayu);
                    tvLiJiTiXian.setBackgroundResource(R.drawable.xshape_roundrect_mgray1);
                } else {
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
                }.getType(),WithDrawBean.class, WithdrawActivity.this);
                if (bean1!=null){
                    bean= (WithDrawBean) bean1;
                    LoadInternetDataToUi();
                }else {
                    ToastUtils.showToast(WithdrawActivity.this, R.string.shujujiazaichucuo);
                }


            }


        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean!=null){

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
}
