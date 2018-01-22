package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.assist.AndroidBug5497Workaround;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.WithdrawHuiFuBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;
import com.tudoujf.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            WithdrawHuiFuActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/11
 * description：汇付提现activity
 * history：
 * ===================================================
 */

public class WithdrawHuiFuActivity extends BaseActivity {
    @BindView(R.id.mtb_act_huifuregister)
    MTopBarView mtbActHuiFuRegister;
    @BindView(R.id.wv_act_huifuregister)
    WebView wvActHuiFuRegister;

    private String url = "";
    private String params = "";

    private String amount;

    private long beforeTime = 0;
    private boolean flag = false;
    private int count = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.act_huifuregister;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            amount = intent.getStringExtra("amount");
        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActHuiFuRegister.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActHuiFuRegister.setLayoutParams(params);
        mtbActHuiFuRegister.setCenterTitle(getResources().getString(R.string.tixian));

        commitInfo();
        AndroidBug5497Workaround.assistActivity(this);

    }

    @Override
    public void initListener() {
        mtbActHuiFuRegister.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.huigucolor);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


    private class myWebClient extends WebViewClient {


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            flag = true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (flag && count != 0) {
                count++;
                flag = false;
                if (count < 5) {
                    showPDialog();
                    wvActHuiFuRegister.setVisibility(View.GONE);
                    wvActHuiFuRegister.postUrl(url, params.getBytes());//提交post请求
                } else {
                    super.onPageFinished(view, url);
                }
            } else {
                count = 1;
                super.onPageFinished(view, url);
                wvActHuiFuRegister.setVisibility(View.VISIBLE);
                dismissPDialog();
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            if (url.contains(Constants.STATUS_REGISTER_SUCCESS)) {
//                startActivity(new Intent(CreditorRightsHuiFuBuyActivity.this, HomeActivity.class));
//                finish();
//            } else if (url.contains(Constants.STATUS_REGISTER_FAIL)) {
//                ToastUtils.showToast(CreditorRightsHuiFuBuyActivity.this, "注册失败1");
//                finish();
//            } else
            Log.e("TAG", "onPageStarted: -----" + url);

            if (url.contains(Constants.STATUS_CLOSE)) {
//                UserConfig.getInstance().setCreditorFlush(true);
                openActivity(WithdrawRecordActivity.class);
                finish();
            }

            super.onPageStarted(view, url, favicon);
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    }


    private void commitInfo() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("amount", amount);
        map.put("paymentType", "chinapnrTrust");//paymentType:"chinapnrTrust"

        Log.e("TAG", "commitInfo: -----amount" + amount);
        Log.e("TAG", "commitInfo: -----login_token" + UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(this, Constants.HUIFU_WITHDRAW, map, "999", new StringCallback() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onSuccess(Response<String> response) {

                String temp = response.body();
                String temp2 = StringUtils.getDecodeString(temp);

                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------请求提现页面接口返回的json数据----------------" + result);



                if (temp2 != null) {
                    String code = "";
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = new JSONObject(temp2);
                        code = jsonobject.getString("code");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if ("200".equals(code)) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(temp2, CommonBean.class);

                        WithdrawHuiFuBean dataBean = gson.fromJson(bean.getData().toString(), WithdrawHuiFuBean.class);

                        url = dataBean.getSubmit_url();
                        JSONObject jo = null;
                        try {
                            jo = new JSONObject(gson.toJson(dataBean, new TypeToken<WithdrawHuiFuBean>() {
                            }.getType()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        params = Utils.handleJson(jo);//拼接请求参数

                        wvActHuiFuRegister.setWebViewClient(new myWebClient());
                        WebSettings ws = wvActHuiFuRegister.getSettings();
                        ws.setAllowFileAccess(true);
                        ws.setSupportZoom(true);
                        ws.setJavaScriptEnabled(true);
                        ws.setJavaScriptCanOpenWindowsAutomatically(true);
                        ws.setUseWideViewPort(true);
                        ws.setLoadWithOverviewMode(true);
                        ws.setBuiltInZoomControls(true);

                        wvActHuiFuRegister.postUrl(url, params.getBytes());//提交post请求

                    } else if ("100".equals(code)) {
                        try {
                            if (jsonobject != null) {
                                ToastUtils.showToast(WithdrawHuiFuActivity.this, jsonobject.getString("description"));
                                closeActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if ("".equals(code)) {
                        ToastUtils.showToast(WithdrawHuiFuActivity.this, R.string.denglushibai);
                    }
                    // 做对应返回错误码的处理
                } else {
                    ToastUtils.showToast(WithdrawHuiFuActivity.this, R.string.shujujiazaichucuo);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (wvActHuiFuRegister.canGoBack()) {
            wvActHuiFuRegister.goBack();
        } else {
            if (System.currentTimeMillis() - beforeTime < 2000) {
                closeActivity();
            } else {
                beforeTime = System.currentTimeMillis();
                ToastUtils.showToast(WithdrawHuiFuActivity.this, R.string.zaicidianjijinagtuichugaiyemian);
            }
        }
    }
}
