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
 * history：  本页包含的所有按钮点击打开
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
//    @BindView(R.id.ll_act_infopublish_gsdz)
//    LinearLayout llGsdz;
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
    @BindView(R.id.ll_act_infopublish_ptsj)
    LinearLayout llPtsj;
    @BindView(R.id.ll_act_infopublish_tdgy)
    LinearLayout llTdgy;
    @BindView(R.id.ll_act_infopublish_jgxx)
    LinearLayout llJgxx;
    @BindView(R.id.ll_act_infopublish_wzgg)
    LinearLayout llWzgg;
    @BindView(R.id.ll_act_infopublish_lxwm)
    LinearLayout llLxwm;

    @Override
    public int getLayoutResId() {
        return R.layout.act_infopublish;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_act_infopublish_gsjj://公司简介
                openNext(Constants.GONG_SI_JIAN_JIE, R.string.act_infopublish_gongsijianjie);
                break;
            case R.id.ll_act_infopublish_csr://主要人员
                openNext(Constants.ZHU_YAO_REN_YUAN, R.string.act_infopublish_zhuyaorenyuan);
                break;
//            case R.id.ll_act_infopublish_gsdz://公司地址
//                openNext(Constants.LIAN_XI_WO_MEN, R.string.act_infopublish_gongsidizhi);
//                break;

            case R.id.ll_act_infopublish_jcaqd://荣誉资质
                openNext(Constants.RONG_YU_ZI_ZHI, R.string.act_infopublish_rongyuzizhi);
                break;
            case R.id.ll_act_infopublish_aqbz://安全措施
//                openActivity(SafetyControlActivity.class);
                openNext(Constants.AN_QUAN_CUO_SHI, R.string.act_infopublish_anquancuoshi);
                break;
            case R.id.ll_act_infopublish_tdsj://土豆视角
                openNext(Constants.TU_DOU_SHI_JIAO, R.string.act_infopublish_tudoushijiao);
                break;

            case R.id.ll_act_infopublish_mtbd://媒体报道
                openNext(Constants.MEI_TI_BAO_DAO, R.string.act_infopublish_meitibaodao);
                break;
            case R.id.ll_act_infopublish_tddsj://土豆大事记
                openNext(Constants.TU_DOU_DA_SHI_JI, R.string.act_infopublish_tudoudashiji);
                break;
            case R.id.ll_act_infopublish_yyms://运营模式
                openNext(Constants.YUN_YIGN_MO_SHI, R.string.act_infopublish_yunyingmoshi);
                break;

            case R.id.ll_act_infopublish_zzjg://组织架构
                openNext(Constants.ZU_ZHI_JIA_GOU, R.string.act_infopublish_zuzhijiagou);
                break;
            case R.id.ll_act_infopublish_hzhb://合作伙伴
                openNext(Constants.HE_ZUO_HUO_BAN, R.string.act_infopublish_hezuohuoban);
                break;
            case R.id.ll_act_infopublish_ptsj://平台数据
                openNext(Constants.PING_TAI_SHU_JU, R.string.act_infopublish_pingtaishuju);
                break;
            case R.id.ll_act_infopublish_tdgy://土豆公益
                openNext(Constants.TU_DOU_GONG_YI, R.string.act_infopublish_tudougongyi);
                break;
            case R.id.ll_act_infopublish_jgxx://机构信息
                openNext(Constants.JI_GOU_XIN_XI, R.string.act_infopublish_jigouxinxi);
                break;
            case R.id.ll_act_infopublish_wzgg://网站公告
                openNext(Constants.WANG_ZHAN_GONG_GAO, R.string.act_infopublish_wangzhangonggao);
                break;
            case R.id.ll_act_infopublish_lxwm://联系我们
                openNext(Constants.LIAN_XI_WO_MEN, R.string.act_infopublish_lianxiwomen);
                break;


        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
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

    private void openNext(String url, int title) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", getResources().getString(title));
        startActivity(intent);
    }

    @Override
    public void initListener() {
        llGsjj.setOnClickListener(this);
        llCsr.setOnClickListener(this);
//        llGsdz.setOnClickListener(this);


        llJcaqd.setOnClickListener(this);
        llAqbz.setOnClickListener(this);
        llTdsj.setOnClickListener(this);


        llMtbd.setOnClickListener(this);
        llTddsj.setOnClickListener(this);
        llYyms.setOnClickListener(this);


        llZzjg.setOnClickListener(this);
        llHzhb.setOnClickListener(this);
        llPtsj.setOnClickListener(this);

        llTdgy.setOnClickListener(this);
        llJgxx.setOnClickListener(this);
        llWzgg.setOnClickListener(this);
        llLxwm.setOnClickListener(this);

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
