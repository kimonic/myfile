package com.tudoujf.activity.my.myaccount;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.databean.PhoneCodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            CommitPhoneNumberActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改手机号码---绑定新手机号activity
 * history：
 * * ====================================================================
 */

public class CommitPhoneNumberActivity extends BaseActivity {


    @BindView(R.id.mtb_act_commitphonenumber)
    MTopBarView mtbActCommitPhonenumber;
    @BindView(R.id.et_act_commitphonenumber_number)
    EditText etNumber;
    @BindView(R.id.et_act_commitphonenumber_code)
    EditText etCode;
    @BindView(R.id.tv_act_commitphonenumber_getcode)
    TextView tvGetCode;
    @BindView(R.id.et_act_commitphonenumber_complete)
    TextView etComplete;
    @BindView(R.id.tv_act_commitphonenumber_hint)
    TextView tvHint;
    private String randomCode;
    private PhoneCodeBean phoneCodeBean;

    /**
     * 倒计时时间
     */
    private int countTime = 60;
    /**
     * 倒计时handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvGetCode.setText((msg.what + "秒后可重新获得验证码!"));
            if (msg.what == 0) {
                countTime = 60;
                tvGetCode.setClickable(true);
                tvGetCode.setText("获取验证码");
                tvHint.setVisibility(View.GONE);
            }
        }
    };
    private String newPhone;

    @Override
    public int getLayoutResId() {
        return R.layout.act_commitphonenumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_commitphonenumber_getcode://获取验证码
                if (checkPhoneNumber()) {
                    startCountDown();
                    tvHint.setVisibility(View.VISIBLE);
                    tvHint.setText(("已向手机" + newPhone + "发送验证码,请输入短信验证码完成绑定."));
                    getSms();

                } else {
                    ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.qingtianxiezhengquedeshoujihao);
                }

                break;
            case R.id.et_act_commitphonenumber_complete://完成
                checkCode();
                break;
        }

    }


    /**
     * 验证验证码输入是否正确
     */
    private void checkCode() {
        String str = etCode.getText().toString().trim();
        String str1 = etNumber.getText().toString().trim();
        if (str1.equals("")) {
            ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.qingshurushoujihao);
        } else if (str.equals("")) {
            ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.qingshuruyanzhengma);
        } else if (str.equals(randomCode)) {
            commitNewPhone();
        } else {
            ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.yanzhengmashurucuowu);
        }


    }

    private void commitNewPhone() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("type", "reset");//类型注册
        map.put("new_phone", newPhone);//手机号码
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(CommitPhoneNumberActivity.this, Constants.CHANGE_PHONE, map, "CommitPhoneNumberActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                Log.e("TAG", "onSuccess:-getSms--------修改手机号码,新手机号完成绑定返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                Gson gson = new Gson();
                phoneCodeBean = gson.fromJson(StringUtils.getDecodeString(response.body()), new TypeToken<PhoneCodeBean>() {
                }.getType());
                if (phoneCodeBean.getCode().equals("200")) {
                    UserConfig.getInstance().setBindPhone(newPhone);
                    ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.bangdingshoujihaochenggong);
                    closeActivity();
                } else {
                    ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.shoujihaobangdingshibai);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.shoujihaobangdingshibai);
            }
        });
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActCommitPhonenumber.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActCommitPhonenumber.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActCommitPhonenumber.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvGetCode.setOnClickListener(this);
        etComplete.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    public void getSms() {
        showPDialog();
        randomCode = StringUtils.getRandomCode();
        Log.e("TAG", "getSms: --------------------" + randomCode);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("type", "reset");//类型注册
        map.put("phone", newPhone);//手机号码
        map.put("is_check", "1");//手机认证时默认为1
        map.put("phone_code", randomCode);
        HttpMethods.getInstance().POST(CommitPhoneNumberActivity.this, Constants.REG_SMS, map, "CommitPhoneNumberActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                Log.e("TAG", "onSuccess:---------修改手机号码,新手机号验证,获取验证码返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                Gson gson = new Gson();
                phoneCodeBean = gson.fromJson(StringUtils.getDecodeString(response.body()), new TypeToken<PhoneCodeBean>() {
                }.getType());
                if (phoneCodeBean.getCode().equals("200")) {
                    ToastUtils.showToast(CommitPhoneNumberActivity.this, "验证码获取成功!!");
                } else {
                    ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.yanzhengmahuoqushibai);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(CommitPhoneNumberActivity.this, R.string.yanzhengmahuoqushibai);
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

    /**
     * 倒计时60秒线程
     */
    private void startCountDown() {
        tvGetCode.setClickable(false);
        tvGetCode.setText(R.string.liushimiaohou);
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
     * 检测输入的手机号码是否符合规则
     */
    private boolean checkPhoneNumber() {
        newPhone = etNumber.getText().toString().trim();
        return StringUtils.isCellphone(newPhone);
    }
}
