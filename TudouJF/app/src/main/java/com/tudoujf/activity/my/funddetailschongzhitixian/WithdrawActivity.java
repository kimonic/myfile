package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;

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

    private AlertDialog dialog;

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
                if (etJinE.getText().toString().equals("")){
                    if (dialog==null){
                        dialog=showCustomDialog(LayoutInflater.from(this).inflate(R.layout.dialog_input,null));
                    }else {
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
                    tvActWithdrawHint.setText("提现金额应大于100元小于500000元");
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

    }

    @Override
    public void LoadInternetDataToUi() {

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
     *
     * 显示提示dialog
     * @return   alertdialog(v7)
     */
    public  AlertDialog showCustomDialog(View view) {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(150*ScreenSizeUtils.getDensity(this), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            ColorDrawable drawable=new ColorDrawable(Color.WHITE);
            window.setBackgroundDrawable(drawable);
//            window.setBackgroundDrawable(new ColorDrawable());
            window.setContentView(view);
        }

        return dialog;
    }
}
