package com.tudoujf.activity;

import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;

import butterknife.BindView;

/**
 * * ================================================
 * name:            LoginActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：登陆页activity
 * history：
 * ===================================================
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_act_login_username)
    EditText etUsername;
    @BindView(R.id.et_act_login_password)
    EditText etPassword;
    @BindView(R.id.tv_act_login_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.tv_act_login_login)
    TextView tvLogin;
    @BindView(R.id.tv_act_login_register)
    TextView tvRegister;
    @BindView(R.id.iv_act_login_clear)
    ImageView ivClear;
    @BindView(R.id.iv_act_login_clear1)
    ImageView ivClear1;
    @BindView(R.id.iv_act_login_openclose)
    ImageView ivOpenclose;

    private int count = 0;


    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        etUsername.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.GONE);
                }

            }
        });
        etPassword.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    ivClear1.setVisibility(View.VISIBLE);
                } else {
                    ivClear1.setVisibility(View.GONE);
                }

            }
        });
        ivClear.setOnClickListener(this);
        ivClear1.setOnClickListener(this);
        ivOpenclose.setOnClickListener(this);
        tvForgetpassword.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_login;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_act_login_clear://用户名清除
                etUsername.setText("");
                break;
            case R.id.iv_act_login_clear1://密码清除
                etPassword.setText("");
                break;
            case R.id.iv_act_login_openclose://密码明文或密文显示
                if (count % 2 == 1) {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ivOpenclose.setImageResource(R.drawable.act_login2_eyeopen);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    ivOpenclose.setImageResource(R.drawable.act_login2_eyeclose);
                }
                count++;
                break;
            case R.id.tv_act_login_forgetpassword://跳转忘记密码activity
                openActivity(FindPasswordActivity.class);
                break;
            case R.id.tv_act_login_login://发送登陆请求,成功登陆后跳转主页

                break;
            case R.id.tv_act_login_register://跳转注册页
                openActivity(RegisterActivity.class);
                closeActivity();
                break;
        }
    }
}
