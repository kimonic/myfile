package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.managemoney.RechargeHuiFuActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            RechargeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  充值activity
 * history：
 * * ====================================================================
 */

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.tv_act_recharge_bac)
    TextView tvActRechargeBac;
    @BindView(R.id.tv_act_recharge_chognzhijilu)
    TextView tvChognZhiJiLu;
    @BindView(R.id.mtb_act_recharge)
    FrameLayout mtbActRecharge;
    @BindView(R.id.tv_act_recharge_balance)
    TextView tvBalance;
    @BindView(R.id.et_act_recharge_amount)
    EditText etAmount;
    @BindView(R.id.tv_act_recharge_rechargenow)
    TextView tvRechargenow;

    private String balance = "";

    @Override
    public int getLayoutResId() {
        return R.layout.act_recharge;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_recharge_bac:
                closeActivity();
                break;
            case R.id.tv_act_recharge_chognzhijilu://充值记录
                openActivity(RechargeRecordActivity.class);
                break;
            case R.id.tv_act_recharge_rechargenow://立即充值
                String  temp=etAmount.getText().toString();
                if (!temp.equals("")&&StringUtils.string2Float(temp)>0){
                    Intent intent=new Intent(this, RechargeHuiFuActivity.class);
                    intent.putExtra("amount",temp);
                    startActivity(intent);
                    closeActivity();
                }else {
                    ToastUtils.showToast(RechargeActivity.this, R.string.qingxinashuruchongzhijine);
                }

                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            balance = bundle.getString("balance");
        }

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRecharge.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRecharge.setLayoutParams(params);


        tvBalance.setText(StringUtils.getCommaDecimalsStr(balance));
    }

    @Override
    public void initListener() {
        tvActRechargeBac.setOnClickListener(this);
        tvChognZhiJiLu.setOnClickListener(this);
        tvRechargenow.setOnClickListener(this);

        etAmount.addTextChangedListener(new MTextWatchAdapter(){
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().contains(".")&&editable.toString().substring(editable.toString().indexOf(".")).length()>3){
                    etAmount.setText(StringUtils.getTwoDecimalsStr(editable.toString()));
                    etAmount.setSelection(etAmount.getText().length());
                    ToastUtils.showToast(RechargeActivity.this, R.string.zuiduozhinengshuruliangweixiaoshu);
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


}
