package com.tudoujf.activity;

import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.DataBean;
import com.tudoujf.bean.HuiFuRegisterBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            RegisterActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：注册页activity
 * history：
 * ===================================================
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_act_register_username)
    EditText etUsername;
    @BindView(R.id.iv_act_register_clear)
    ImageView ivClear;
    @BindView(R.id.et_act_register_code)
    EditText etCode;
    @BindView(R.id.iv_act_register_codeimage)
    ImageView ivCodeimage;//自动加载图形验证码
    @BindView(R.id.et_act_register_phonecode)
    EditText etPhonecode;
    @BindView(R.id.tv_act_register_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_act_register_password)
    EditText etPassword;
    @BindView(R.id.iv_act_register_clear1)
    ImageView ivClear1;
    @BindView(R.id.iv_act_register_openclose)
    ImageView ivOpenclose;
    @BindView(R.id.et_act_register_referrer)
    EditText etReferrer;
    @BindView(R.id.tv_act_register_login)
    TextView tvLogin;
    @BindView(R.id.tv_act_register_register)
    TextView tvRegister;
    @BindView(R.id.ll_act_register_agree)
    LinearLayout llAgree;
    @BindView(R.id.cb_act_register)
    TextView cbActRegister;
    private int count=0,countAgree=0;
    private String TAG="RegisterActivity";

    @Override
    public int getLayoutResId() {
        return R.layout.act_register;
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_act_register_clear://用户名清空
                    etUsername.setText("");
                    break;
                case R.id.iv_act_register_clear1:
                    etPassword.setText("");
                    break;
                case R.id.tv_act_register_getcode://发送网路请求,获取手机验证码
                    break;
                case R.id.iv_act_register_codeimage://点击可刷新图形验证码
                    break;

                case R.id.iv_act_register_openclose://明文密文显示
                    inputTypeConfig(count,ivOpenclose,etPassword);
                    count++;
                    break;
                case R.id.tv_act_register_login://启动登陆界面
                    openActivity(LoginActivity.class);
                    closeActivity();
                    break;
                case R.id.tv_act_register_register://检测注册信息,无误后提交注册信息
                    commitInfo();
                    break;
                case R.id.ll_act_register_agree://同意土豆金服服务协议
                    if (countAgree%2==0){
                        cbActRegister.setBackgroundResource(R.drawable.xvector_checkbox_sel);
                    }else {
                        cbActRegister.setBackgroundResource(R.drawable.xvector_checkbox_unsel);
                    }
                    countAgree++;
                    break;

            }
    }

    private void commitInfo() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("login_token", "12290");
        HttpMethods.getInstance().POST(this, Constants.TRUST_REGISTER, map, "999", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String temp = response.body();
                String temp1 = StringUtils.getDecodeString(temp);
                String temp2 = temp1.replace("\\","");

                if (temp2 != null) {
                    String code = "";
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = new JSONObject(temp2);
                        code = jsonobject.getString("code");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (code.equals("200")) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(temp2, CommonBean.class);
                        HuiFuRegisterBean dataBean = gson.fromJson(bean.getData().toString(),HuiFuRegisterBean.class);
                        openActivity(HomeActivity.class);
                    } else if (code.equals("100")) {
                        try {
                            ToastUtils.showToast(RegisterActivity.this, jsonobject.getString("description"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (code.equals("")){
                        ToastUtils.showToast(RegisterActivity.this, R.string.denglushibai);
                    }
                    // TODO: 2017/8/8 做对应返回错误码的处理
                } else {
//                    ToastUtils.showToast(RegisterActivity.this, R.string.denglushibai);
                }
            }
        });
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
            etUsername.addTextChangedListener(new MTextWatchAdapter(){
                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length()>0){
                        ivClear.setVisibility(View.VISIBLE);
                    }else {
                        ivClear.setVisibility(View.GONE);
                    }
                }
            });

        etPassword.addTextChangedListener(new MTextWatchAdapter(){
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>0){
                    ivClear1.setVisibility(View.VISIBLE);
                }else {
                    ivClear1.setVisibility(View.GONE);
                }
            }
        });
        ivClear.setOnClickListener(this);
        ivClear1.setOnClickListener(this);
        tvGetcode.setOnClickListener(this);
        ivCodeimage.setOnClickListener(this);
        ivOpenclose.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        llAgree.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

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

    public static String object2JsonNoEscaping(Object obj){
        GsonBuilder gson = new GsonBuilder();
        gson.disableHtmlEscaping();
        gson.serializeNulls();
        return gson.create().toJson(obj);
    }


}
