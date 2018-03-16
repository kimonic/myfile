package com.tudoujf.activity.my.myaccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

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
    @BindView(R.id.tv_act_changepassword_commit)
    TextView tvCommit;
    private String password;

    @Override
    public int getLayoutResId() {
        return R.layout.act_changepassword;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_changepassword_commit:
                if (checkPassword()) {
                    commitPWD();
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
        tvCommit.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {
    }

    @Override
    public void LoadInternetDataToUi() {

    }

    /**
     * 检验密码规则
     */
    private boolean checkPassword() {

        password = etNew.getText().toString().trim();
        String temp = etConfirm.getText().toString().trim();
        if (password.equals(temp)) {
            if (password.length() < 6 || password.length() > 16 || !StringUtils.conformPasswordRule(password)) {
                ToastUtils.showToast(this, R.string.mimatishi);
                return false;
            }
        } else {
            ToastUtils.showToast(this, R.string.liangcishurudemimabuyizhi);
            return false;
        }

        return true;
    }

    private void commitPWD() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("password", etOld.getText().toString().trim());
        map.put("new_password", password);
        map.put("confirm_password", password);

        HttpMethods.getInstance().POST(this, Constants.EDITPWD, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ChangePasswordActivity.class,"logflag-修改密码接口返回数据--"+result);


                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        if (bean != null) {
                            if (!"200".equals(bean.getCode())) {
                                ToastUtils.showToast(ChangePasswordActivity.this, bean.getDescription().toString());
                            } else {
                                DialogUtils.showDialogNoTitle(ChangePasswordActivity.this, bean.getDescription().toString(), "确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SharedPreferencesUtils.getInstance(ChangePasswordActivity.this, Constants.USER_CONFIG)
                                                .put(Constants.SHARE_LOGINTOKEN, "");
                                        UserConfig.getInstance().setLoginToken("");//清空登录信息
                                        Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                        intent.putExtra("type", 55);
                                        startActivity(intent);
                                        closeActivity();
//                                        openActivity(LoginActivity.class);
                                    }
                                });
                            }
                        } else {
                            ToastUtils.showToast(ChangePasswordActivity.this, R.string.shujujiazaichucuo);
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(ChangePasswordActivity.this, R.string.xiugaimimashibai);

                    }
                });
    }

    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }
}
