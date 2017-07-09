package com.tudoujf.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.http.HttpMethods;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            HttpTestActivity
 * guide:          WelcomeActivity-->GuideActivity--->HttpTestActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description： okhttp测试用activity
 * history：
 * ===================================================
 */

public class HttpTestActivity extends BaseActivity {
    @BindView(R.id.tv_act_httptest)
    TextView tvActHttptest;

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {
        Map<String,String> map=new HashMap<>();
        map.put("wd","你好");

        final StringConvert convert=new StringConvert();

        HttpMethods.getInstance().GET(this, "http://www.juzimi.com/aboutus", null, "123",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        tvActHttptest.setText(response.body());
                    }
                });

//        HttpMethods.getInstance().GET(this,"https://www.baidu.com/",null,"123", new Callback() {
//                    @Override
//                    public Object convertResponse(okhttp3.Response response) throws Throwable {
//                        final String s = convert.convertResponse(response);
//                        response.close();
//                        Log.e("TAG", "convertResponse: " +Thread.currentThread().getName());
//                        Log.e("TAG", "convertResponse: "+s );
//                        System.out.println("convertResponse"+s);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                tvActHttptest.setText(s);
//                            }
//                        });
//                        return s;
//                    }
//
//                    @Override
//                    public void onStart(Request request) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response response) {
//                        Log.e("TAG", "onSuccess: -----"+Thread.currentThread().getName() );
////                        Log.e("TAG", "onSuccess: -----"+response.body() );
//                        System.out.print("onSuccess"+response.body().toString());
//
//                    }
//
//
//                    @Override
//                    public void onCacheSuccess(Response response) {
//
//                    }
//
//                    @Override
//                    public void onError(Response response) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//
//                    @Override
//                    public void uploadProgress(Progress progress) {
//
//                    }
//
//                    @Override
//                    public void downloadProgress(Progress progress) {
//
//                    }
//                }
//        );
    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_httptest;
    }


}
