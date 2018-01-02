package com.tudoujf.activity.my.myaccount;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            BindEmailConfirmActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  确认绑定邮箱activity
 * history：
 * * ====================================================================
 */

public class BindEmailConfirmActivity extends BaseActivity {


    @BindView(R.id.mtb_act_bindemailconfirm)
    MTopBarView mtbActBindEmailConfirm;
    @BindView(R.id.et_act_bindemailconfirm_name)
    TextView etName;
    @BindView(R.id.et_act_bindemailconfirm_code)
    EditText etCode;
    @BindView(R.id.tv_act_bindemailconfirm_getcode)
    TextView tvGetCode;
    @BindView(R.id.tv_act_bindemailconfirm_confirm)
    TextView tvConfirm;

    @Override
    public int getLayoutResId() {
        return R.layout.act_bindemailconfirm;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_bindemailconfirm_getcode://获取验证码

                break;
            case R.id.tv_act_bindemailconfirm_confirm://绑定

                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActBindEmailConfirm.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActBindEmailConfirm.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActBindEmailConfirm.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvGetCode.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
