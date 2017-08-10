package com.tudoujf.activity.managemoney;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            FanXianQuanSelActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description： 选择返现券activity
 * history：
 * ===================================================
 */

public class FanXianQuanSelActivity extends BaseActivity {
    @BindView(R.id.mtb_act_fanxianquansel)
    MTopBarView mtbFanxianquansel;
    @BindView(R.id.iv_act_fanxianquansel)
    ImageView ivFanxianquansel;
    @BindView(R.id.tv_act_fanxianquansel)
    TextView tvFanxianquansel;

    private int type=2;

    @Override
    public int getLayoutResId() {
        return R.layout.fanxianquansel;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            type=bundle.getInt("type",2);
        }
        // TODO: 2017/7/26   获取传输过来的数据,来确定要展示的标题和展示的图片,内容
    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbFanxianquansel.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbFanxianquansel.setLayoutParams(params);

        mtbFanxianquansel.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        switch (type){
            case 1:
                ivFanxianquansel.setImageResource(R.drawable.act_fanxianquan2_redpackage);
                tvFanxianquansel.setText(R.string.act_fanxianquansel_zanwuredpackage);
                mtbFanxianquansel.setCenterTitle(R.string.act_fanxianquansel_titleredpackage);
                break;
            case 2:
                ivFanxianquansel.setImageResource(R.drawable.act_fanxianquan2_quan);
                tvFanxianquansel.setText(R.string.act_fanxianquansel_zanwu);
                mtbFanxianquansel.setCenterTitle(R.string.act_fanxianquansel_title);
                break;
        }

    }

    @Override
    public void initListener() {

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



}
