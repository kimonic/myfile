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
 * name:            ChangePasswordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改密码activity
 * history：
 * * ====================================================================
 */

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_changepassword)
    MTopBarView mtbActChangePassword;
    @BindView(R.id.et_act_changepassword_old)
    EditText etOld;
    @BindView(R.id.et_act_changepassword_new)
    EditText etNew;
    @BindView(R.id.et_act_changepassword_confirm)
    EditText etConfirm;
    @BindView(R.id.et_act_changepassword_commit)
    TextView etCommit;

    @Override
    public int getLayoutResId() {
        return R.layout.act_changepassword;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActChangePassword.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActChangePassword.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActChangePassword.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
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
