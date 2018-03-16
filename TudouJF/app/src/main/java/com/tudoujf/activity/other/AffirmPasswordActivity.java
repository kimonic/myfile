package com.tudoujf.activity.other;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.databean.AffirmPasswordBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            AffirmPasswordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：确认密码页activity
 * history：
 * ==================================================
 */


public class AffirmPasswordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_affirm)
    MTopBarView mtbActAffirm;
    @BindView(R.id.et_act_affirm_password1)
    EditText etPassword1;
    @BindView(R.id.iv_act_affirm_clear1)
    ImageView ivClear1;
    @BindView(R.id.iv_act_affirm_openclose1)
    ImageView ivOpenclose1;
    @BindView(R.id.et_act_affirm_password2)
    EditText etPassword2;
    @BindView(R.id.iv_act_affirm_clear2)
    ImageView ivClear2;
    @BindView(R.id.iv_act_affirm_openclose2)
    ImageView ivOpenclose2;
    @BindView(R.id.tv_act_affirm_complete)
    TextView tvComplete;

    private int count1 = 0, count2 = 0;

    private String password1, password2;
    private String userName;
    private AlertDialog dialog;

    @Override
    public int getLayoutResId() {
        return R.layout.act_affirm;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_act_affirm_openclose1://一次确认edittext
                inputTypeConfig(count1, ivOpenclose1, etPassword1);
                count1++;
                break;
            case R.id.iv_act_affirm_openclose2://二次确认edittext
                inputTypeConfig(count2, ivOpenclose2, etPassword2);
                count2++;
                break;
            case R.id.iv_act_affirm_clear1://一次置空
                etPassword1.setText("");
                break;
            case R.id.iv_act_affirm_clear2://二次置空
                etPassword2.setText("");
                break;
            case R.id.tv_act_affirm_complete://两次密码输入一致则提交请求
                if (obtainETContent()) {
                    commitInfo();
                }
                break;
        }


    }

    private void commitInfo() {
        dialog = DialogUtils.showProgreessDialog(this, "正在提交信息,请稍候!");
        if (!userName.equals("")) {
            TreeMap<String, String> map = new TreeMap<>();
            map.put("type", "");
            map.put("username", userName);
            map.put("new_password", password1);
            map.put("confirm_password", password2);
            HttpMethods.getInstance().POST(this, Constants.EIDT_RECOBER_PWD, map, "affirmpasswordactivity", new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    dialog.dismiss();
                    String result = StringUtils.getDecodeString(response.body());
                    LUtils.e(AffirmPasswordActivity.class,"logflag-确认密码返回json字符串--"+result);

                    Gson gson = new Gson();
                    AffirmPasswordBean bean = gson.fromJson(result, AffirmPasswordBean.class);
                    if (bean != null) {
                        if (bean.getCode().equals("200")) {
                            ToastUtils.showToast(AffirmPasswordActivity.this, "密码修改成功,请重新登陆!");
                            openActivity(LoginActivity.class);
                            closeActivity();
                        } else {
                            ToastUtils.showToast(AffirmPasswordActivity.this, bean.getDescription());

                        }
                    }
                }
            });
        } else {
            ToastUtils.showToast(this, "出错啦,再来一遍吧!");
        }

    }

    /**
     * 获取两次输入的密码
     */
    private boolean obtainETContent() {
        password1 = etPassword1.getText().toString();
        password2 = etPassword2.getText().toString();
        if (password1.equals("") || null == password1 || !checkPassword(password1)) {
            return false;
        } else if (!password1.equals(password2)) {
            ToastUtils.showToast(this, "两次密码输入不一致,请重新输入!");
            return false;
        }
        return true;
    }

    /**
     * 检验密码规则
     */
    private boolean checkPassword(String password) {
        if (password.length() < 6 || password.length() > 16 || !StringUtils.conformPasswordRule(password)) {
            ToastUtils.showToast(this, "密码必须是6-16位数字和字母的组合!!");
            return false;
        }
        return true;
    }

    /**
     * 配置edittext的明文密文显示类型
     */
    private void inputTypeConfig(int count, ImageView ivOpenclose, EditText etPassword) {
        if (count % 2 == 0) {
            ivOpenclose.setImageResource(R.drawable.act_login2_eyeclose);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            ivOpenclose.setImageResource(R.drawable.act_login2_eyeopen);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName = bundle.getString("username", "");
        }

    }

    @Override
    public void initView() {
        mtbActAffirm.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
        etPassword1.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    ivClear1.setVisibility(View.VISIBLE);
                } else {
                    ivClear1.setVisibility(View.GONE);
                }
            }
        });
        etPassword2.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    ivClear2.setVisibility(View.VISIBLE);
                } else {
                    ivClear2.setVisibility(View.GONE);
                }
            }
        });

//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActAffirm.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActAffirm.setLayoutParams(params);


    }

    @Override
    public void initListener() {
        ivOpenclose1.setOnClickListener(this);
        ivOpenclose2.setOnClickListener(this);
        ivClear1.setOnClickListener(this);
        ivClear2.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
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
