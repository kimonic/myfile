package com.tudoujf.activity.other;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bolex.pressscan.ScanTools;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            TestActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：测试控件用的activity
 * history：
 * ===================================================
 */

public class TestActivity extends BaseActivity {
    @BindView(R.id.et_act_test_input)
    EditText etInput;
    @BindView(R.id.tv_act_test_translation)
    TextView tvTranslation;
    @BindView(R.id.tv_act_test_btn)
    TextView tvBtn;
    @BindView(R.id.tv_test_mm1)
    TextView tvTestMm1;
    @BindView(R.id.tv_test_mm2)
    TextView tvTestMm2;
    @BindView(R.id.tv_test_mm3)
    TextView tvTestMm3;
    @BindView(R.id.tv_test_mm4)
    TextView tvTestMm4;
    @BindView(R.id.tv_test_mm5)
    TextView tvTestMm5;
    @BindView(R.id.tv_test_mm6)
    TextView tvTestMm6;
    @BindView(R.id.tv_test_mm7)
    TextView tvTestMm7;
    @BindView(R.id.tv_test_mm8)
    TextView tvTestMm8;
    @BindView(R.id.tv_test_mm9)
    TextView tvTestMm9;
    @BindView(R.id.tv_test_mm10)
    TextView tvTestMm10;
    @BindView(R.id.tv_translation)
    TextView tvTransation;
    @BindView(R.id.et_transation)
    EditText etTransation;
    @BindView(R.id.iv_erniu)
    ImageView imageView;

    @Override
    public int getLayoutResId() {
        return R.layout.act_test;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_test_btn:
                String str = etInput.getText().toString().trim();
                String result = StringUtils.getCommaDecimalsStrZeroDot(str);
                tvTranslation.setText(result);
                break;
            case R.id.tv_translation://转换

                float temp = StringUtils.string2Float(etTransation.getText().toString()) / 750 * 1080 / 3;
                tvTranslation.setText(("" + temp));
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }
    }

    @Override
    public void initDataFromIntent() {

//        showDialog();
//        list = new ArrayList<>();
//        list.add(tvRed);
//        list.add(tvBlue);
//        list.add(tvYellow);
//        list.add(tvGreen);

    }

    @Override
    public void initView() {

        tvTestMm1.setText(("屏幕宽度:---" + ScreenSizeUtils.getScreenWidth(this)));
        tvTestMm2.setText(("屏幕高度:---" + ScreenSizeUtils.getScreenHeight(this)));
        tvTestMm3.setText(("屏幕像素密度:---" + ScreenSizeUtils.getDensity(this)));
        tvTestMm4.setText(("屏幕dpi:---" + ScreenSizeUtils.getDensityDpi(this)));
        tvTestMm5.setText(("状态栏高度:---" + ScreenSizeUtils.getStatusHeight(this)));
//        tvTestMm6.setText(("屏幕宽度:"+ ScreenSizeUtils.getScreenWidth(this)));
//        tvTestMm7.setText(("屏幕宽度:"+ ScreenSizeUtils.getScreenWidth(this)));
//        tvTestMm8.setText(("屏幕宽度:"+ ScreenSizeUtils.getScreenWidth(this)));
//        tvTestMm9.setText(("屏幕宽度:"+ ScreenSizeUtils.getScreenWidth(this)));
//        tvTestMm10.setText(("屏幕宽度:"+ ScreenSizeUtils.getScreenWidth(this)));


    }

    @Override
    public void initListener() {
        tvBtn.setOnClickListener(this);
        tvTransation.setOnClickListener(this);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ScanTools.scanCode(imageView, new ScanTools.ScanCall() {
                    @Override
                    public void getCode(String s) {
                        Log.e("TAG", "v: -----"+s);

                        ToastUtils.showToast(TestActivity.this, s);
                    }
                });
                return false;
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
    public void onBackPressed() {
//        gvActTest.setThreadFlag(false);
//        bean.setAmount(total);
//        bean.save();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
