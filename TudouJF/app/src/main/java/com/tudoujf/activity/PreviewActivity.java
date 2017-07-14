package com.tudoujf.activity;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.AESUtils;
import com.tudoujf.utils.AppInfoUtils;
import com.tudoujf.utils.L;
import com.tudoujf.utils.NetworkUtils;
import com.tudoujf.utils.ToastUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            PreviewActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：预览界面activity
 * history：
 * ===================================================
 */

public class PreviewActivity extends BaseActivity {
    @BindView(R.id.bt_act_preview)
    Button btActPreview;
    @BindView(R.id.bt_act_preview1)
    Button btActPreview1;
    @BindView(R.id.bt_act_preview2)
    Button btActPreview2;
    @BindView(R.id.bt_act_preview3)
    Button btActPreview3;
    @BindView(R.id.bt_act_preview4)
    Button btActPreview4;
    @BindView(R.id.bt_act_preview5)
    Button btActPreview5;
    @BindView(R.id.bt_act_preview6)
    Button btActPreview6;
    @BindView(R.id.bt_act_preview7)
    Button btActPreview7;
    @BindView(R.id.bt_act_preview8)
    Button btActPreview8;
    @BindView(R.id.bt_act_preview9)
    Button btActPreview9;

    @Override
    public int getLayoutResId() {
        return R.layout.act_preview;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_act_preview:
                openActivity(LoginActivity.class);
                break;
            case R.id.bt_act_preview1:
                openActivity(AffirmPasswordActivity.class);
                break;
            case R.id.bt_act_preview2:
                openActivity(FindPasswordActivity.class);
                break;
            case R.id.bt_act_preview3:
                openActivity(RegisterActivity.class);
                break;
            case R.id.bt_act_preview4:
                openActivity(LockActivity.class);
                break;
            case R.id.bt_act_preview5:
                openActivity(DrawGestureActivity.class);
                break;
            case R.id.bt_act_preview6:
                break;
            case R.id.bt_act_preview7:
                break;
            case R.id.bt_act_preview8:
                break;
            case R.id.bt_act_preview9:
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
//        Log.e("initDataFromIntent","1231213"+ NetworkUtils.connectionNetwork());
        Log.e("initDataFromIntent", NetworkUtils.getLocalIpAddress());
        Log.e("initDataFromIntent", ""+NetworkUtils.getNetState(this));
        Log.e("initDataFromIntent", ""+NetworkUtils.is2G(this));
        Log.e("initDataFromIntent", ""+NetworkUtils.is3G(this));
        Log.e("initDataFromIntent", ""+NetworkUtils.isNetworkAvailable(this));
        Log.e("initDataFromIntent", ""+NetworkUtils.isWifi(this));
        Log.e("initDataFromIntent", ""+NetworkUtils.isWifiEnabled(this));
        Log.e("initDataFromIntent", "数据网络"+NetworkUtils.isMobileNetEnabled(this));

        NetworkUtils.getNetCanConnectBaiDu(this, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ToastUtils.showToast(PreviewActivity.this,"连接网络成功");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                ToastUtils.showToast(PreviewActivity.this,"连接网络失败");
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        btActPreview.setOnClickListener(this);
        btActPreview1.setOnClickListener(this);
        btActPreview2.setOnClickListener(this);
        btActPreview3.setOnClickListener(this);
        btActPreview4.setOnClickListener(this);
        btActPreview5.setOnClickListener(this);
        btActPreview6.setOnClickListener(this);
        btActPreview7.setOnClickListener(this);
        btActPreview8.setOnClickListener(this);
        btActPreview9.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
