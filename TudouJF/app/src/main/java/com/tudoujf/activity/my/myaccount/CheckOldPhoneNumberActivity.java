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
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            CheckOldPhoneNumberActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  修改手机号码---验证原绑定手机号activity
 * history：
 * * ====================================================================
 */

public class CheckOldPhoneNumberActivity extends BaseActivity {


    @BindView(R.id.mtb_act_checkoldphonenumber)
    MTopBarView mtbActCheckOldPhonenumber;
    @BindView(R.id.tv_act_checkoldphonenumber_old)
    TextView tvOld;
    @BindView(R.id.et_act_checkoldphonenumber_code)
    EditText etCode;
    @BindView(R.id.tv_act_checkoldphonenumber_getcode)
    TextView tvGetCode;
    @BindView(R.id.tv_act_checkoldphonenumber_hint1)
    TextView tvHint1;
    @BindView(R.id.et_act_checkoldphonenumber_next)
    TextView etNext;
    @BindView(R.id.tv_act_checkoldphonenumber_hint2)
    TextView tvHint2;

    private String hidePhone;
    private String randomCode;
    private PhoneCodeBean phoneCodeBean;
    private String phone;


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
            tvGetCode.setText((msg.what + "秒后重新发送"));
            if (msg.what == 0) {
                countTime = 60;
                tvGetCode.setClickable(true);
                tvGetCode.setText("获取验证码");
                tvHint2.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.act_checkoldphonenumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_checkoldphonenumber_getcode://获取验证码
                startCountDown();
                getSms();
                tvHint2.setVisibility(View.VISIBLE);
                tvHint2.setText(("已向手机" + hidePhone + "发送验证码,请输入短信验证码完成绑定."));
                break;
            case R.id.et_act_checkoldphonenumber_next://下一步

                checkCode();

                break;
        }

    }

    /**
     * 验证验证码输入是否正确
     */
    private void checkCode() {
        String str = etCode.getText().toString().trim();
        if (str.equals("")) {
            ToastUtils.showToast(CheckOldPhoneNumberActivity.this, R.string.qingshuruyanzhengma);
        } else if (str.equals(randomCode)) {
            openActivity(CommitPhoneNumberActivity.class);
            closeActivity();
        } else {
            ToastUtils.showToast(CheckOldPhoneNumberActivity.this, R.string.yanzhengmashurucuowu);
        }


    }

    @Override
    public void initDataFromIntent() {
        hidePhone = getIntent().getStringExtra("hidePhone");
        phone = getIntent().getStringExtra("phone");

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActCheckOldPhonenumber.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActCheckOldPhonenumber.setLayoutParams(params);
        if (hidePhone != null) {
            tvOld.setText(("原手机号:" + hidePhone));
        } else {
            tvOld.setText("原手机号:***********");
        }
    }

    @Override
    public void initListener() {
        mtbActCheckOldPhonenumber.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvGetCode.setOnClickListener(this);
        etNext.setOnClickListener(this);
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

    public void getSms() {
        showPDialog();
        randomCode = StringUtils.getRandomCode();
        Log.e("TAG", "getSms: --------------------" + randomCode);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("type", "reset");//类型注册
        map.put("phone", phone);//手机号码
        map.put("is_check", "1");//手机认证时默认为1
        map.put("phone_code", randomCode);
        HttpMethods.getInstance().POST(CheckOldPhoneNumberActivity.this, Constants.REG_SMS, map, "CheckOldPhoneNumberActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                dismissPDialog();
                Log.e("TAG", "onSuccess:---------修改手机号码,旧手机号验证,获取验证码返回的json数据-------------" + StringUtils.getDecodeString(response.body()));
                Gson gson = new Gson();
                phoneCodeBean = gson.fromJson(StringUtils.getDecodeString(response.body()), new TypeToken<PhoneCodeBean>() {
                }.getType());
                if (phoneCodeBean.getCode().equals("200")) {
                    ToastUtils.showToast(CheckOldPhoneNumberActivity.this, "验证码获取成功!!");
                }else {
                    ToastUtils.showToast(CheckOldPhoneNumberActivity.this, R.string.yanzhengmahuoqushibai);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(CheckOldPhoneNumberActivity.this, R.string.yanzhengmahuoqushibai);
            }
        });
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


}
