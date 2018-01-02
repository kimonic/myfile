package com.tudoujf.activity.other;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.CheckPhoneIsExistRegisterActBean;
import com.tudoujf.bean.databean.PhoneCodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            FindPasswordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：找回密码页activity
 * history：
 * ===================================================
 */

public class FindPasswordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_find)
    MTopBarView mtbActFind;
    @BindView(R.id.et_act_find_username)
    EditText etUsername;
    @BindView(R.id.iv_act_find_clear)
    ImageView ivClear;
    @BindView(R.id.et_act_find_phonecode)
    EditText etPhonecode;
    @BindView(R.id.tv_act_find_getphonecode)
    TextView tvGetphonecode;
    //    @BindView(R.id.et_act_find_imagecode)
//    EditText etImagecode;
//    @BindView(R.id.iv_act_find_getimagecode)
//    ImageView ivGetimagecode;
    @BindView(R.id.tv_act_find_next)
    TextView tvNext;
    private String randomCode;
    private String TAG = "FindPasswordActivity";
    private String userName;
    private PhoneCodeBean phoneCodeBean;
    private CheckPhoneIsExistRegisterActBean checkPhoneBean;
    private int countTime = 60;
    /**
     * 倒计时handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvGetphonecode.setText((msg.what + "秒后重新发送"));
            if (msg.what == 0) {
                countTime = 60;
                tvGetphonecode.setClickable(true);
                tvGetphonecode.setText("获取验证码");
            }
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.act_findpassword;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_act_find_clear://清空用户名
                etUsername.setText("");
                break;
            case R.id.tv_act_find_getphonecode://提交请求获取手机验证码
                if (etUsername.hasFocus()) {
                    etUsername.clearFocus();
                }

                if (checkPhoneBean != null && !userName.equals("")) {
                    if (checkPhoneBean.getStatus().equals("1")) {
                        startCountDown();
                        getSms();
                    } else {
                        ToastUtils.showToast(FindPasswordActivity.this, "该号码不存在!!!");
                    }
                } else if (!etUsername.hasFocus()) {
                    ToastUtils.showToast(FindPasswordActivity.this, "请输入正确的手机号码!!!");
                }

                break;
//            case R.id.iv_act_find_getimagecode://点击刷新图形验证码
//                break;
            case R.id.tv_act_find_next://手机验证码与图形验证码正确后提交请求,否则重新输入
                if (etPhonecode.getText().toString().trim().equals(randomCode)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", userName);
                    openActivity(AffirmPasswordActivity.class, bundle);
                    closeActivity();
                } else {
                    ToastUtils.showToast(this, "请输入正确的验证码!!");
                }
                break;

        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFind.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFind.setLayoutParams(params);


        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 验证手机号是否已存在
                if (!hasFocus) {
                    if (checkPhoneNumber()) {
                        TreeMap<String, String> map = new TreeMap<>();
                        map.put("username", userName);
                        HttpMethods.getInstance().POST(FindPasswordActivity.this, Constants.CHECK, map, "registeractivity", new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.e(TAG, "onSuccess:---------校验手机号是否存在返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                                BaseBean bean = ParseJson.getJsonResult(response.body(), new TypeToken<CheckPhoneIsExistRegisterActBean>() {
                                        }.getType(),
                                        CheckPhoneIsExistRegisterActBean.class, FindPasswordActivity.this);
                                if (bean != null) {
                                    checkPhoneBean = (CheckPhoneIsExistRegisterActBean) bean;
                                    if (checkPhoneBean.getStatus().equals("1")) {
                                        tvGetphonecode.setBackgroundResource(R.drawable.xshape_roundrect_myellow);
                                    }
                                }
                            }
                        });
                    } else {
                        ToastUtils.showToast(FindPasswordActivity.this, R.string.qingtianxiezhengquedeshoujihao);
                    }
                }
            }
        });

    }

    @Override
    public void initListener() {
        mtbActFind.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
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
        ivClear.setOnClickListener(this);
        tvGetphonecode.setOnClickListener(this);
//        ivGetimagecode.setOnClickListener(this);
        tvNext.setOnClickListener(this);

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

    /**
     * 获取手机验证码
     */
    public void getSms() {
        randomCode = StringUtils.getRandomCode();
        userName = etUsername.getText().toString().trim();
        Log.e(TAG, "getSms: --------------------" + randomCode);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("type", "pwd");//找回密码
        map.put("phone", userName);//手机号码
        map.put("is_check", "1");//手机认证时默认为1
        map.put("phone_code", "" + randomCode);
        HttpMethods.getInstance().POST(FindPasswordActivity.this, Constants.REG_SMS, map, "registeractivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess:---------获取验证码返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                Gson gson = new Gson();
                phoneCodeBean = gson.fromJson(StringUtils.getDecodeString(response.body()), new TypeToken<PhoneCodeBean>() {
                }.getType());
                if (phoneCodeBean.getCode().equals("200")) {
                    ToastUtils.showToast(FindPasswordActivity.this, R.string.yanzhengmahuoquchenggong);
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
     * 倒计时60秒线程
     */
    private void startCountDown() {
        tvGetphonecode.setClickable(false);
        tvGetphonecode.setText(R.string.liushimiaohou);
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

}
