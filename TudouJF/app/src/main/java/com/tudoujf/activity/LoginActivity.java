package com.tudoujf.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.DataBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static String TAG = "loginactivity";
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
    private AlertDialog dialog;


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
                showProgreessDialog();
                login();

                break;
            case R.id.tv_act_login_register://跳转注册页
                openActivity(RegisterActivity.class);
                closeActivity();
                break;
        }
    }

    private void login() {


        HttpMethods.getInstance().POST(this, Constants.LOGIN, getArguments(), "123", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String temp = response.body();
                String temp1 = StringUtils.getDecodeString(temp);

                Log.e(TAG, "onSuccess:-- "+temp1 );

                if (temp1 != null) {
                    String code = "";
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = new JSONObject(temp1);
                        code = jsonobject.getString("code");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                    if (code.equals("200")) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(temp1, new TypeToken<CommonBean>() {
                        }.getType());
                        DataBean dataBean = gson.fromJson(bean.getData().toString(), new TypeToken<DataBean>() {
                        }.getType());
                        openActivity(HomeActivity.class);
                    } else if (code.equals("100")) {
                        try {
                            ToastUtils.showToast(LoginActivity.this, jsonobject.getString("description"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (code.equals("")){
                        ToastUtils.showToast(LoginActivity.this, R.string.denglushibai);
                    }
                    // TODO: 2017/8/8 做对应返回错误码的处理
                } else {
                    dialog.dismiss();
                    ToastUtils.showToast(LoginActivity.this, R.string.denglushibai);
                }

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
//        map.put("member_name", etUsername.getText().toString().trim());
//        map.put("password", etPassword.getText().toString().trim());//密码没加密????
//        map.put("type", "2");//登录类型 1验证码登录 2密码登录
//        map.put("phone_type", "1");//手机类型1=andriod 2=IOS


        map.put("member_name", "18022222222");
        map.put("password", "a123123");//密码没加密????
        map.put("type", "2");//登录类型 1验证码登录 2密码登录
        map.put("phone_type", "1");//手机类型1=andriod 2=IOS
        return map;
    }

    /**
     * 开始请求登陆时显示
     */
    private void showProgreessDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_act_login, null);
        dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            WindowManager.LayoutParams lp = window.getAttributes();
//            Log.e(TAG, "showProgreessDialog: --ScreenSizeUtils.getDensity(this)-"+ ScreenSizeUtils.getDensity(this));
            int wh = 90 * ScreenSizeUtils.getDensity(this);
            lp.width = wh;
            lp.height = wh;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }

    }


}
