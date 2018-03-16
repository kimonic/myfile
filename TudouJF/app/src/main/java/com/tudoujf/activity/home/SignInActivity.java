package com.tudoujf.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.SignInBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.SignInView;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            SignInActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  签到activity,我的积分界面
 * history：
 *
 * * ====================================================================
 */

public class SignInActivity extends BaseActivity {

    private static final String TAG = "SignInActivity";
    @BindView(R.id.mtb_act_signin)
    MTopBarView mtbActSignIn;
    @BindView(R.id.ll_act_signin_shop)
    LinearLayout llShop;
    @BindView(R.id.ll_act_signin_jilu)
    LinearLayout llJiLu;
    @BindView(R.id.ll_act_signin_shuoming)
    LinearLayout llShuoMing;
    @BindView(R.id.siv_act_signin)
    SignInView sivSignIn;

    private SignInBean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_signin;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_signin_shop:

                openActivity(IntegralShopActivity.class);

                break;
            case R.id.ll_act_signin_jilu:
                Bundle bundle = new Bundle();
                if (bean != null) {
                    bundle.putString("totalIntegral", bean.getCredit_point());
                }
                openActivity(IntegralRecodeActivity.class, bundle);
                break;
            case R.id.ll_act_signin_shuoming:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", Constants.JI_FEN_SHUO_MING);
                intent.putExtra("title", getResources().getString(R.string.act_signin_jifenshuoming));
                startActivity(intent);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

        // TODO: 2017/12/22 暂时去掉积分商城
        llShop.setVisibility(View.GONE);



        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActSignIn.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActSignIn.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActSignIn.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(666);
                closeActivity();
            }
        });

        llJiLu.setOnClickListener(this);
        llShop.setOnClickListener(this);
        llShuoMing.setOnClickListener(this);
        sivSignIn.setListener(new SignInView.ClickEventListener() {
            @Override
            public void clickEvent() {
//                dialog.show();

                showPDialog();
                TreeMap<String, String> map = new TreeMap<>();
                map.put("login_token", UserConfig.getInstance().getLoginToken(SignInActivity.this));
                Log.e(TAG, "clickEvent: ------logintoken--------" + UserConfig.getInstance().getLoginToken(SignInActivity.this));


                HttpMethods.getInstance().POST(SignInActivity.this, Constants.SIGN_IN_SAVE, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        dialog.dismiss();

                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(SignInActivity.class,"logflag-请求签到返回的json数据--"+result);

                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        if (bean.getCode().equals("200")) {
                            sivSignIn.setFlagIsSignIn(true);
                            initDataFromInternet();//再次请求接口刷新界面积分
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                    }
                });

            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(SignInActivity.this));


        HttpMethods.getInstance().POST(this, Constants.SIGN_IN, map, "SignInActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());

                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<SignInBean>() {
                }.getType(), SignInBean.class, SignInActivity.this);

                if (bean1 != null) {
                    bean = (SignInBean) bean1;
                    LoadInternetDataToUi();
                }
                LUtils.e(SignInActivity.class,"logflag-签到请求json数据--"+result);

            }

            @Override
            public void onError(Response<String> response) {

                dismissPDialog();
                Log.e(TAG, "onSuccess:------------签到请求json数据失败----------------- " + response.code());
                super.onError(response);
            }
        });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {

            if (bean.getSign_status().equals("-1")) {
                sivSignIn.setTotalIntegrel(bean.getCredit_point());
                sivSignIn.setFlagIsSignIn(true);
            } else {
                sivSignIn.setTotalIntegrel(bean.getCredit_point());
                sivSignIn.setFlagIsSignIn(false);
                sivSignIn.setFlagSignInSuccess(true);
            }
            sivSignIn.invalidate();
        }

    }

    @Override
    public void onBackPressed() {
        setResult(666);
        closeActivity();
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
