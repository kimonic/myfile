package com.tudoujf.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            InfoPublishActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：   信息披露activity
 * history：
 * ===================================================
 */

public class InfoPublishActivity extends BaseActivity {
    @BindView(R.id.mtb_act_infopublish)
    MTopBarView mtbInfopublish;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_act_infopublish_gsjj)
    LinearLayout llGsjj;
    @BindView(R.id.ll_act_infopublish_csr)
    LinearLayout llCsr;
    @BindView(R.id.ll_act_infopublish_gsdz)
    LinearLayout llGsdz;
    @BindView(R.id.ll_act_infopublish_jcaqd)
    LinearLayout llJcaqd;
    @BindView(R.id.ll_act_infopublish_aqbz)
    LinearLayout llAqbz;
    @BindView(R.id.ll_act_infopublish_tdsj)
    LinearLayout llTdsj;
    @BindView(R.id.ll_act_infopublish_mtbd)
    LinearLayout llMtbd;
    @BindView(R.id.ll_act_infopublish_tddsj)
    LinearLayout llTddsj;
    @BindView(R.id.ll_act_infopublish_yyms)
    LinearLayout llYyms;
    @BindView(R.id.ll_act_infopublish_zzjg)
    LinearLayout llZzjg;
    @BindView(R.id.ll_act_infopublish_hzhb)
    LinearLayout llHzhb;

    @Override
    public int getLayoutResId() {
        return R.layout.act_infopublish;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_act_infopublish_gsjj://公司简介
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_csr://创始人
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_gsdz://公司地址
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;

            case R.id.ll_act_infopublish_jcaqd://九重安全盾
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_aqbz://安全保障
//                openActivity(SafetyControlActivity.class);
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_tdsj://土豆视角
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;

            case R.id.ll_act_infopublish_mtbd://媒体报道
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_tddsj://土豆大事记
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_yyms://运营模式
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;

            case R.id.ll_act_infopublish_zzjg://组织架构
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_hzhb://合作伙伴
                openNext(Constants.GONG_SI_JIAN_JIE,R.string.act_infopublish_gongsijianjie);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbInfopublish.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbInfopublish.setLayoutParams(params);

        mtbInfopublish.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    private void openNext(String url,int  title){
        Intent intent=new Intent(this,WebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",getResources().getString(title));
        startActivity(intent);
    }

    @Override
    public void initListener() {
        llGsjj.setOnClickListener(this);
        llCsr.setOnClickListener(this);
        llGsdz.setOnClickListener(this);


        llJcaqd.setOnClickListener(this);
        llAqbz.setOnClickListener(this);
        llTdsj.setOnClickListener(this);


        llMtbd.setOnClickListener(this);
        llTddsj.setOnClickListener(this);
        llYyms.setOnClickListener(this);


        llZzjg.setOnClickListener(this);
        llHzhb.setOnClickListener(this);

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
