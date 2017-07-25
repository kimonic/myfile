package com.tudoujf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.UnderlineTextView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            ProductDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/21
 * description：产品详情界面activity
 * history：
 * ===================================================
 */

public class ProductDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_productdetails)
    MTopBarView mtbProductdetails;
    @BindView(R.id.utv_act_productdetails1)
    UnderlineTextView utv1;
    @BindView(R.id.utv_act_productdetails2)
    UnderlineTextView utv2;
    @BindView(R.id.utv_act_productdetails3)
    UnderlineTextView utv3;
    @BindView(R.id.ll_act_productdetails_xiangmuxiangqing)
    LinearLayout llXiangMuXiangQing;
    @BindView(R.id.ll_act_productdetails_touziliebiao)
    LinearLayout llTouZiLieBiao;
    @BindView(R.id.ll_act_productdetails_changjianwenti)
    LinearLayout llChangJianWenTi;
    @BindView(R.id.tv_act_productdetails_buynow)
    TextView tvBuyNow;

    private List<UnderlineTextView> list;
    private List<LinearLayout> listLL;

    @Override
    public int getLayoutResId() {
        return R.layout.act_productdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.utv_act_productdetails1:
                setUTVStyle(0);

                break;
            case R.id.utv_act_productdetails2:
                setUTVStyle(1);

                break;
            case R.id.utv_act_productdetails3:
                setUTVStyle(2);
                break;
            case R.id.tv_act_productdetails_buynow:
                openActivity(AffirmBuyActivity.class);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbProductdetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbProductdetails.setLayoutParams(params);

        mtbProductdetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        utv1.setUnderlinecolor(R.color.act_productdetails_tvcolor);

        list = new ArrayList<>();
        list.add(utv1);
        list.add(utv2);
        list.add(utv3);

        listLL = new ArrayList<>();
        listLL.add(llXiangMuXiangQing);
        listLL.add(llChangJianWenTi);
        listLL.add(llTouZiLieBiao);
    }

    @Override
    public void initListener() {
        utv1.setOnClickListener(this);
        utv2.setOnClickListener(this);
        utv3.setOnClickListener(this);
        tvBuyNow.setOnClickListener(this);
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

    /**
     * 设置underllinetextview的样式
     */
    private void setUTVStyle(int i) {

        for (int j = 0; j < list.size(); j++) {
            if (i == j) {
                list.get(j).setUnderlinecolor(R.color.act_productdetails_tvcolor);
                list.get(j).setTextColor(getResources().getColor(R.color.act_productdetails_tvcolor));
                listLL.get(j).setVisibility(View.VISIBLE);
            } else {
                list.get(j).setUnderlinecolor(R.color.color_black);
                list.get(j).setTextColor(getResources().getColor(R.color.color_black));
                listLL.get(j).setVisibility(View.GONE);
            }
        }


    }



}
