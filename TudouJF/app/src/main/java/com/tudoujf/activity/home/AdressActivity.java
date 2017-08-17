package com.tudoujf.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * * ====================================================================
 * name:            AdressActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：  公司地址activity
 * history：
 * * ====================================================================
 */

public class AdressActivity extends BaseActivity {
    @BindView(R.id.mtb_act_adress)
    MTopBarView mtbActAdress;

    @Override
    public int getLayoutResId() {
        return R.layout.act_adress;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActAdress.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActAdress.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActAdress.setOnClickListener(new View.OnClickListener() {
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
