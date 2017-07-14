package com.tudoujf.activity;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.AESUtils;
import com.tudoujf.utils.DeviceUtils;

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
        switch (view.getId()){
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
            case R.id.bt_act_preview6:break;
            case R.id.bt_act_preview7:break;
            case R.id.bt_act_preview8:break;
            case R.id.bt_act_preview9:break;
        }
    }

    @Override
    public void initDataFromIntent() {
        String s=AESUtils.encrypt("12345678","亦筝笙!");//A98E5D602656F608B9B96F8FAC3BFCAA
        Log.e("TAG", "initDataFromIntent: -----aes----"+ s);//1573864034BA55BA048634A1A6951D1F
        Log.e("TAG", "initDataFromIntent: -----aes----"+ AESUtils.decrypt("12345678",s));
        String base=new String(Base64.encode("123456789".getBytes(),Base64.DEFAULT));
        Log.e("TAG", "initDataFromIntent: -----------" +base);
        Log.e("TAG", "initDataFromIntent: -----------" +(new String(Base64.decode(base.getBytes()
                ,Base64.DEFAULT))));
//        Log.e("TAG", "initDataFromIntent: ---------"+DeviceUtils.getAndroidID(this) );
//        Log.e("TAG", "initDataFromIntent: ---------"+DeviceUtils.getMacAddress(this).replace(":","") );
//        Log.e("TAG", "initDataFromIntent: ---------"+DeviceUtils.getManufacturer() );
//        Log.e("TAG", "initDataFromIntent: ---------"+ DeviceUtils.getModel());
//        Log.e("TAG", "initDataFromIntent: ---------"+ DeviceUtils.getSDKVersion());//1498816696000
//        Log.e("TAG", "initDataFromIntent: ---------"+ DeviceUtils.isDeviceRooted());
//        Log.e("TAG", "initDataFromIntent: ---------"+ DeviceUtils.getIMET(this));
//        Log.e("TAG", "initDataFromIntent: ---------"+ Build.ID);
//        Log.e("TAG", "initDataFromIntent: ----99-----"+ Build.TIME);
        //861211344413522
//5071047551432824
//080027198f10
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
