package com.tudoujf.activity.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.VerificationCodeView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ===============================================================
 * name:             GoodGiftExchangeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/25
 * description：  豪礼兑换activity
 * history：
 * *==================================================================
 */

public class GoodGiftExchangeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_goodgiftexcahnge)
    MTopBarView mtb;
    @BindView(R.id.et_act_goodgiftexchange_duihuanma)
    EditText etDuiHuanMa;
    @BindView(R.id.et_act_goodgiftexchange_yanzhengma)
    EditText etYanZhengMa;
    @BindView(R.id.vcv_act_goodgiftexchange_yanzhengma)
    VerificationCodeView vcvYanZhengMa;
    @BindView(R.id.tv_act_goodgiftexchange_duihuan)
    TextView tvDuiHuan;

    @Override
    public int getLayoutResId() {
        return R.layout.act_goodgiftexchange;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_goodgiftexchange_duihuan:
                if (etYanZhengMa.getText().toString().equals(vcvYanZhengMa.getShowCode())) {
                    ToastUtils.showToast(GoodGiftExchangeActivity.this, "验证码输入正确!");
                } else {
                    ToastUtils.showToast(GoodGiftExchangeActivity.this, "验证码输入错误!");
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
//Verification Code
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        tvDuiHuan.setOnClickListener(this);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
