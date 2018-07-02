package com.tudoujf.activity.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.SignInBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ===============================================================
 * name:             AllSignActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/5/29
 * method:
 * <p>
 * <p>
 * description：便捷签到
 * history：
 * *==================================================================
 */

public class AllSignActivity extends BaseActivity {
    @BindView(R.id.tv_act_allsign_11)
    TextView tvActAllsign11;
    @BindView(R.id.tv_act_allsign_12)
    TextView tvActAllsign12;
    @BindView(R.id.tv_act_allsign_13)
    TextView tvActAllsign13;
    @BindView(R.id.tv_act_allsign_21)
    TextView tvActAllsign21;
    @BindView(R.id.tv_act_allsign_22)
    TextView tvActAllsign22;
    @BindView(R.id.tv_act_allsign_23)
    TextView tvActAllsign23;
    @BindView(R.id.tv_act_allsign_31)
    TextView tvActAllsign31;
    @BindView(R.id.tv_act_allsign_32)
    TextView tvActAllsign32;
    @BindView(R.id.tv_act_allsign_33)
    TextView tvActAllsign33;
    @BindView(R.id.tv_act_allsign_41)
    TextView tvActAllsign41;
    @BindView(R.id.tv_act_allsign_42)
    TextView tvActAllsign42;
    @BindView(R.id.tv_act_allsign_43)
    TextView tvActAllsign43;
    @BindView(R.id.tv_act_allsign_51)
    TextView tvActAllsign51;
    @BindView(R.id.tv_act_allsign_52)
    TextView tvActAllsign52;
    @BindView(R.id.tv_act_allsign_53)
    TextView tvActAllsign53;
    @BindView(R.id.tv_act_allsign_61)
    TextView tvActAllsign61;
    @BindView(R.id.tv_act_allsign_62)
    TextView tvActAllsign62;
    @BindView(R.id.tv_act_allsign_63)
    TextView tvActAllsign63;
    @BindView(R.id.tv_act_allsign_71)
    TextView tvActAllsign71;
    @BindView(R.id.tv_act_allsign_72)
    TextView tvActAllsign72;
    @BindView(R.id.tv_act_allsign_73)
    TextView tvActAllsign73;


    private String loginToken = "";
    private int flag = 0;
    private SignInBean bean;
    private int[] list = {36544, 33196, 36844, 39070, 33231, 37386, 37406};

    @Override
    public int getLayoutResId() {
        return R.layout.act_allsign;
    }

    @Override
    public void onClick(View v) {
        //15064459947---36544,18706500033-36844,17852629273--39070
        //18765218309--33196
        //15014897985--asd6208--33231
        //15362088135---37386
        //15362088195--37406
        switch (v.getId()) {
            case R.id.tv_act_allsign_13:
                loginToken = "36544";
                flag = 1;
                sign();
                break;
            case R.id.tv_act_allsign_23:
                loginToken = "33196";
                flag = 2;
                sign();
                break;
            case R.id.tv_act_allsign_33:
                loginToken = "36844";
                flag = 3;
                sign();
                break;
            case R.id.tv_act_allsign_43:
                loginToken = "39070";
                flag = 4;
                sign();
                break;
                 case R.id.tv_act_allsign_53:
                     loginToken = "33231";
                     flag = 5;
                     sign();
                     break;
                 case R.id.tv_act_allsign_63:
                     loginToken = "37386";
                     flag = 6;
                     sign();
                     break;
                 case R.id.tv_act_allsign_73:
                     loginToken = "37406";
                     flag = 7;
                     sign();
                     break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        tvActAllsign13.setOnClickListener(this);
        tvActAllsign23.setOnClickListener(this);
        tvActAllsign33.setOnClickListener(this);
        tvActAllsign43.setOnClickListener(this);
        tvActAllsign53.setOnClickListener(this);
        tvActAllsign63.setOnClickListener(this);
        tvActAllsign73.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {
        check(0);
        check(1);
        check(2);
        check(3);
        check(4);
        check(5);
        check(6);
    }

    @Override
    public void LoadInternetDataToUi() {

    }

    private void sign() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);

        HttpMethods.getInstance().POST(AllSignActivity.this, Constants.SIGN_IN_SAVE, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                LUtils.e(SignInActivity.class, "logflag-请求签到返回的json数据--" + result);

                Gson gson = new Gson();
                CommonBean bean = gson.fromJson(result, CommonBean.class);
                if (bean.getCode().equals("200")) {
                    //签到成功
                    switch (flag) {
                        case 1:
                            tvActAllsign12.setText("签到成功");
                            break;
                        case 2:
                            tvActAllsign22.setText("签到成功");
                            break;
                        case 3:
                            tvActAllsign32.setText("签到成功");
                            break;
                        case 4:
                            tvActAllsign42.setText("签到成功");
                            break;
                        case 5:
                            tvActAllsign52.setText("签到成功");
                            break;
                        case 6:
                            tvActAllsign62.setText("签到成功");
                            break;
                        case 7:
                            tvActAllsign72.setText("签到成功");
                            break;
//                            case :break;
//                            case :break;
                    }
                } else {
                    ToastUtils.showToast(AllSignActivity.this, result);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
            }
        });


    }


    private void check(final int who) {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", "" + list[who]);


        HttpMethods.getInstance().POST(this, Constants.SIGN_IN, map, "SignInActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());

                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<SignInBean>() {
                }.getType(), SignInBean.class, AllSignActivity.this);

                if (bean1 != null) {
                    bean = (SignInBean) bean1;
                    if (bean.getSign_status().equals("-1")) {
                        switch (who) {
                            case 0:
                                tvActAllsign12.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 1:
                                tvActAllsign22.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 2:
                                tvActAllsign32.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 3:
                                tvActAllsign42.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 4:
                                tvActAllsign52.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 5:
                                tvActAllsign62.setText(("未签到+" + bean.getCredit_point()));
                                break;
                            case 6:
                                tvActAllsign72.setText(("未签到+" + bean.getCredit_point()));
                                break;
//                                 case R.id.:break;
//                                 case R.id.:break;
                        }
                    } else {
                        switch (who) {
                            case 0:
                                tvActAllsign12.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 1:
                                tvActAllsign22.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 2:
                                tvActAllsign32.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 3:
                                tvActAllsign42.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 4:
                                tvActAllsign52.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 5:
                                tvActAllsign62.setText(("已签到+" + bean.getCredit_point()));
                                break;
                            case 6:
                                tvActAllsign72.setText(("已签到+" + bean.getCredit_point()));
                                break;
//                                 case R.id.:break;
//                                 case R.id.:break;
                        }
                    }
                }
                LUtils.e(SignInActivity.class, "logflag-签到请求json数据--" + result);

            }

            @Override
            public void onError(Response<String> response) {
                dismissPDialog();
                super.onError(response);
            }
        });
    }


}
