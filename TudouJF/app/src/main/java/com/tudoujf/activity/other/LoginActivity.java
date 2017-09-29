package com.tudoujf.activity.other;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.encryptionpackages.AESencrypt;
import com.example.encryptionpackages.CreateCode;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.LoginBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.mapi.MApp;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.StringUtils;

import java.util.TreeMap;

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
    private static String TAG = "LoginActivity";
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
    /**密码明文或密文表示计数*/
    private int count = 0;
    /**加载中dialog*/
    private AlertDialog dialog;
    /**登陆后的返回数据*/
    private LoginBean bean;


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
                dialog= DialogUtils.showProgreessDialog(this, getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
                login();

                break;
            case R.id.tv_act_login_register://跳转注册页
                openActivity(RegisterActivity.class);
                closeActivity();
                break;
        }
    }

    /**
     * 登陆请求
     */
    private void login() {
        HttpMethods.getInstance().POST(this, Constants.LOGIN, getArguments(), getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dialog.dismiss();
                Log.e(TAG, "onSuccess:---------登陆成功后返回的json数据-------------"+StringUtils.getDecodeString(response.body()) );

                BaseBean bean1=  ParseJson.getJsonResult(response.body(),new TypeToken<LoginBean>() {}.getType(),
                        LoginBean.class,LoginActivity.this );
                if (bean1!=null){
                    bean= (LoginBean) bean1;
                    //加密存储logintoken到本地
                    SharedPreferencesUtils.getInstance(LoginActivity.this,Constants.USER_CONFIG)
                            .put(Constants.SHARE_LOGINTOKEN, AESencrypt.encrypt2PHP(
                                    CreateCode.getSEND_AES_KEY(),bean.getLogin_token()));
                    MApp.getInstance().setLogin(true);
                    openActivity(HomeActivity.class);
                }
//                     TODO: 2017/8/8 做对应返回错误码的处理
            }

            @Override
            public void onError(Response<String> response) {
                dialog.dismiss();
                DialogUtils.showDialogNoTitle(LoginActivity.this, R.string.wuwangluotishi, R.string.queding, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                super.onError(response);
            }
        });
    }

    private TreeMap<String, String> getArguments() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("member_name", etUsername.getText().toString().trim());
        map.put("password", etPassword.getText().toString().trim());//密码没加密????
        map.put("type", "2");//登录类型 1验证码登录 2密码登录
        map.put("client_id", "");//预留字段


//        map.put("member_name", "18022222222");
//        map.put("password", "a123123");//密码没加密????
//        map.put("type", "2");//登录类型 1验证码登录 2密码登录
//        map.put("phone_type", "1");//手机类型1=andriod 2=IOS
        return map;
    }


}
