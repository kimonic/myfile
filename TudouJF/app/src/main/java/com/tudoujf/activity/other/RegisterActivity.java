package com.tudoujf.activity.other;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.encryptionpackages.AESencrypt;
import com.example.encryptionpackages.CreateCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.CheckPhoneIsExistRegisterActBean;
import com.tudoujf.bean.databean.LoginBean;
import com.tudoujf.bean.databean.PhoneCodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.mapi.MApp;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;
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
    //    @BindView(R.id.et_act_register_code)
//    EditText etCode;
//    @BindView(R.id.iv_act_register_codeimage)
//    ImageView ivCodeimage;//自动加载图形验证码
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
    @BindView(R.id.view_placeholder)
    View view;
    @BindView(R.id.scrollview_act_register)
    ScrollView scrollView;
    private int count = 0, countAgree = 0;
    /**
     * 手机验证码
     */
    private String randomCode;
    private String TAG = "RegisterActivity";
    /**
     * 检测手机号是否存在的bean
     */
    private CheckPhoneIsExistRegisterActBean checkPhoneBean;
    /**
     * 获取手机验证码的返回bean
     */
    private PhoneCodeBean phoneCodeBean;
    /**
     * 用户名--手机号
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 登陆注册时展示的dialog
     */
    private AlertDialog dialog;
    /**
     * 记录第一次点击back键的时间
     */
    private long beforeTime;

    /**
     * 倒计时handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvGetcode.setText(msg.what + "秒后可重新获得验证码!");
            if (msg.what == 0) {
                countTime = 60;
                tvGetcode.setClickable(true);
                tvGetcode.setText("获取验证码");
            }
        }
    };
    /**
     * 倒计时时间
     */
    private int countTime = 60;
    /**
     * 是否同意协议
     */
    private boolean agreeRule = false;

    private boolean fosousOne, fosousTwo;

    @Override
    public int getLayoutResId() {
        return R.layout.act_register;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_act_register_clear://用户名清空
                etUsername.setText("");
                break;
            case R.id.iv_act_register_clear1://密码清空
                etPassword.setText("");
                break;
            case R.id.tv_act_register_getcode://发送网路请求,获取手机验证码

                if (etUsername.hasFocus()) {//有焦点时清除焦点
                    etUsername.clearFocus();
                }
                if (checkPhoneBean != null && !userName.equals("")) {
                    if (checkPhoneBean.getStatus().equals("0")) {
                        startCountDown();
                        getSms();
                    } else {
                        ToastUtils.showToast(RegisterActivity.this, "该号码已注册!!!");
                    }
                } else if (!etUsername.hasFocus()) {
                    ToastUtils.showToast(RegisterActivity.this, "请输入正确的手机号码!!!");
                }

                break;
//            case R.id.iv_act_register_codeimage://点击可刷新图形验证码
//                break;

            case R.id.iv_act_register_openclose://明文密文显示
                inputTypeConfig(count, ivOpenclose, etPassword);
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
                if (countAgree % 2 == 0) {
                    cbActRegister.setBackgroundResource(R.drawable.xvector_checkbox_sel);
                    agreeRule = true;
                } else {
                    cbActRegister.setBackgroundResource(R.drawable.xvector_checkbox_unsel);
                    agreeRule = false;
                }
                countAgree++;
                break;
            case R.id.et_act_register_username:
                etUsername.requestFocus();
                break;

        }
    }


    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = ScreenSizeUtils.getScreenHeight(this);
        view.setLayoutParams(params);

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
        etUsername.setOnClickListener(this);
//--------------------------------------------------------------------------------------------------------
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                fosousOne = hasFocus;
                visibilityplaceHolder();

            }
        });

        etReferrer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                fosousTwo = hasFocus;
                visibilityplaceHolder();
            }
        });
//-----------------------------------------------------------------------------------------------------------
        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 验证手机号是否已存在
                if (!hasFocus) {
                    if (checkPhoneNumber()) {
                        TreeMap<String, String> map = new TreeMap<>();
                        map.put("username", userName);
                        HttpMethods.getInstance().POST(RegisterActivity.this, Constants.CHECK, map, "registeractivity", new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.e(TAG, "onSuccess:---------校验手机号是否存在返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                                BaseBean bean = ParseJson.getJsonResult(response.body(), new TypeToken<CheckPhoneIsExistRegisterActBean>() {
                                        }.getType(),
                                        CheckPhoneIsExistRegisterActBean.class, RegisterActivity.this);
                                if (bean != null) {
                                    checkPhoneBean = (CheckPhoneIsExistRegisterActBean) bean;
                                    if (checkPhoneBean.getStatus().equals("0")) {
                                        tvGetcode.setBackgroundResource(R.drawable.xshape_roundrect_myellow);
                                    }
                                }
                            }
                        });
                    } else {
                        ToastUtils.showToast(RegisterActivity.this, R.string.qingtianxiezhengquedeshoujihao);
                    }
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
        tvGetcode.setOnClickListener(this);
//        ivCodeimage.setOnClickListener(this);
        ivOpenclose.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        llAgree.setOnClickListener(this);
    }

    private void visibilityplaceHolder() {
        if (fosousOne || fosousTwo) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
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

    /**
     * 倒计时60秒线程
     */
    private void startCountDown() {
        tvGetcode.setClickable(false);
        tvGetcode.setText("60秒后可重新获取验证码!");
        new Thread() {
            @Override
            public void run() {
                while (countTime > 0) {
                    countTime--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.what = countTime;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }


    /**
     * 将object对象转化为json字符串
     */
    public static String object2JsonNoEscaping(Object obj) {
        GsonBuilder gson = new GsonBuilder();
        gson.disableHtmlEscaping();
        gson.serializeNulls();
        return gson.create().toJson(obj);
    }

    /**
     * 提交注册信息
     */
    private void commitInfo() {
        if (checkSubmit()) {
            dialog = DialogUtils.showProgreessDialog(this, "再点击一次将结束注册退出!");
            TreeMap<String, String> map = new TreeMap<>();
            map.put("phone", userName);
            map.put("password", password);
            map.put("referrer", etReferrer.getText().toString().trim());
            map.put("invite_username", "");
            HttpMethods.getInstance().POST(this, Constants.REGISTER, map, "registeractivity", new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Log.e(TAG, "onSuccess:---------注册账号返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                    // 调用登陆接口进行登陆,注册成功保存login_token,跳转设置手势密码页面
                    try {
                        JSONObject json = new JSONObject(StringUtils.getDecodeString(response.body()));
                        if (json.getString("code").equals("200")) {
                            login();//登陆
                        } else {
                            dialog.dismiss();
                            ToastUtils.showToast(RegisterActivity.this, json.getString("description"));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    /**
     * 调用登陆接口
     */
    private void login() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("member_name", userName);
        map.put("password", password);//密码
        map.put("type", "2");//登录类型 1验证码登录 2密码登录
        map.put("client_id", "");//预留字段
        HttpMethods.getInstance().POST(this, Constants.LOGIN, map, "registeractivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess:---------注册账号成功后登陆返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                // TODO: 2017/8/15 保存完数据之后,跳转手势密码页面

                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<LoginBean>() {
                        }.getType(),
                        LoginBean.class, RegisterActivity.this);
                if (bean1 != null) {
                    LoginBean bean = (LoginBean) bean1;
                    //加密存储logintoken,username到本地
                    SharedPreferencesUtils.getInstance(RegisterActivity.this, Constants.USER_CONFIG).put("userName", bean.getMember().getName());
                    SharedPreferencesUtils.getInstance(RegisterActivity.this, Constants.USER_CONFIG)
                            .put(Constants.SHARE_LOGINTOKEN, AESencrypt.encrypt2PHP(
                                    CreateCode.getSEND_AES_KEY(), bean.getLogin_token()));
                    UserConfig.getInstance().setLoginToken(bean.getLogin_token());//每次登陆重置logintoken
                    UserConfig.getInstance().setDraw(true);//密码登录时,本次前台不再需要手势密码
                    MApp.getInstance().setLogin(true);

                } else {
                    ToastUtils.showToast(RegisterActivity.this, R.string.loginerror);

                }

                openActivity(DrawGestureActivity.class);
                closeActivity();
            }
        });
    }

    /**
     * 获取手机验证码
     */
    public void getSms() {
        randomCode = StringUtils.getRandomCode();
        Log.e(TAG, "getSms: --------------------" + randomCode);
        //-----------------------------------------待删除-------------------------------------------------------
        //-----------------------------------------待删除-------------------------------------------------------
        ToastUtils.showToast(RegisterActivity.this, randomCode);
        //-----------------------------------------待删除-------------------------------------------------------
        //-----------------------------------------待删除-------------------------------------------------------
        TreeMap<String, String> map = new TreeMap<>();
        map.put("type", "reg");//类型注册
        map.put("phone", userName);//手机号码
        map.put("is_check", "1");//手机认证时默认为1
        map.put("phone_code", "" + randomCode);
        HttpMethods.getInstance().POST(RegisterActivity.this, Constants.REG_SMS, map, "registeractivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess:---------获取验证码返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                Gson gson = new Gson();
                phoneCodeBean = gson.fromJson(StringUtils.getDecodeString(response.body()), new TypeToken<PhoneCodeBean>() {
                }.getType());
                if (phoneCodeBean.getCode().equals("200")) {
                    ToastUtils.showToast(RegisterActivity.this, "验证码获取成功!!");
                }


            }
        });
    }

    /**
     * 检测输入的手机号码是否符合规则
     */
    private boolean checkPhoneNumber() {
        userName = etUsername.getText().toString().trim();
        return StringUtils.isCellphone(userName);
    }

    /**
     * 检测注册数据
     */
    private boolean checkSubmit() {
        if (!(phoneCodeBean != null && phoneCodeBean.getCode().equals("200")
                && phoneCodeBean.getData().equals(phoneCodeBean.getDescription())
                && etPhonecode.getText().toString().trim().equals(randomCode))) {
            ToastUtils.showToast(this, "验证码输入错误!!");
            return false;
        }
        if (!agreeRule) {
            ToastUtils.showToast(this, "未同意土豆金服服务协议!!");
            return false;
        }
        return checkPassword();
    }

    /**
     * 检验密码规则
     */
    private boolean checkPassword() {
        password = etPassword.getText().toString().trim();
        if (password.length() < 6 || password.length() > 16 || !StringUtils.conformPasswordRule(password)) {
            ToastUtils.showToast(this, "密码必须是6-16位数字和字母的组合!!");
            return false;
        }
        return true;
    }


    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.login_statusbar_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}


/**
 * [java] view plain copy
 * InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
 * // 接受软键盘输入的编辑文本或其它视图
 * imm.showSoftInput(submitBt,InputMethodManager.SHOW_FORCED);
 * <p>
 * 二、关闭输入法窗口
 * [java] view plain copy
 * InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
 * inputMethodManager.hideSoftInputFromWindow(OpeListActivity.this.getCurrentFocus().getWindowToken()
 * ,InputMethodManager.HIDE_NOT_ALWAYS);
 * <p>
 * //接受软键盘输入的编辑文本或其它视图
 * inputMethodManager.showSoftInput(submitBt,InputMethodManager.SHOW_FORCED);
 * <p>
 * 三、如果输入法打开则关闭，如果没打开则打开
 * [java] view plain copy  在CODE上查看代码片派生到我的代码片
 * InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
 * m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
 * <p>
 * 四、获取输入法打开的状态
 * [java] view plain copy  在CODE上查看代码片派生到我的代码片
 * InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
 * boolean isOpen=imm.isActive();
 */
