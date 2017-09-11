package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            BindEmailActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  绑定邮箱activity
 * history：
 * * ====================================================================
 */

public class BindEmailActivity extends BaseActivity {


    @BindView(R.id.mtb_act_bindemail)
    MTopBarView mtbActBindEmail;
    @BindView(R.id.et_act_bindemail_emailname)
    EditText etEmailname;
    @BindView(R.id.tv_act_bindemail_next)
    TextView tvNext;

    @Override
    public int getLayoutResId() {
        return R.layout.act_bindemail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_bindemail_next://下一步
                openActivity(BindEmailConfirmActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActBindEmail.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActBindEmail.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActBindEmail.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvNext.setOnClickListener(this);
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
