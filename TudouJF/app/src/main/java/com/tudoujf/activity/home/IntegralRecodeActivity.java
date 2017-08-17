package com.tudoujf.activity.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            IntegralRecodeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/17
 * description：     签到-->我的积分-->积分记录activity
 * history：
 * * ====================================================================
 */

public class IntegralRecodeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_integralrecode)
    MTopBarView mtbIntegralRecode;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralrecode;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbIntegralRecode.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbIntegralRecode.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbIntegralRecode.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", "");
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_LIST, map, "info", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------消息接口请求返回数据-----------------" + result);
            }

            @Override
            public void onError(Response<String> response) {
                Log.e("TAG", "onSuccess: ----------消息接口请求返回错误信息-----------------" + response.message());
                super.onError(response);
            }
        });
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
