package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ===============================================================
 * name:             ElectronicSignatureActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/3/5
 * method:
 * <p>
 * <p>
 * description：   电子签章activity
 * history：
 * *==================================================================
 */

public class ElectronicSignatureActivity extends BaseActivity {
    @BindView(R.id.mtb_act_electronicsignature)
    MTopBarView mtb;
    @BindView(R.id.tv_act_electronicsgnature_phonenumber)
    TextView tvPhonenumber;
    @BindView(R.id.tv_act_electronicsgnature_email)
    TextView tvEmail;
    @BindView(R.id.tv_act_electronicsgnature_name)
    TextView tvName;
    @BindView(R.id.tv_act_electronicsgnature_shenfenzhenghao)
    TextView tvShenfenzhenghao;
    @BindView(R.id.tv_act_electronicsgnature_dalu)
    TextView tvDaLu;
    @BindView(R.id.tv_act_electronicsgnature_xianggang)
    TextView tvXiangGang;
    @BindView(R.id.tv_act_electronicsgnature_aomen)
    TextView tvAoMen;
    @BindView(R.id.tv_act_electronicsgnature_taiwan)
    TextView tvTaiWan;
    @BindView(R.id.tv_act_electronicsgnature_waiji)
    TextView tvWaiJi;
    @BindView(R.id.tv_act_electronicsgnature_xieyisel)
    TextView tvXieYiSel;
    @BindView(R.id.tv_act_electronicsgnature_xieyi)
    TextView tvXieYi;
    @BindView(R.id.tv_act_electronicsgnature_affirmopen)
    TextView tvAffirmOpen;

    private List<TextView> list;
    private int position = 0;
    private int count = 0;
    private boolean xieYiFlag = false;

    @Override
    public int getLayoutResId() {
        return R.layout.act_electronicsignature;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_electronicsgnature_dalu://大陆
                position = 0;
                showStyle(position);
                break;
            case R.id.tv_act_electronicsgnature_xianggang://香港
                position = 1;
                showStyle(position);
                break;
            case R.id.tv_act_electronicsgnature_aomen://澳门
                position = 2;
                showStyle(position);
                break;
            case R.id.tv_act_electronicsgnature_taiwan://台湾
                position = 3;
                showStyle(position);
                break;
            case R.id.tv_act_electronicsgnature_waiji://外籍
                position = 4;
                showStyle(position);
                break;
            case R.id.tv_act_electronicsgnature_xieyisel://协议选择
                if (count % 2 == 0) {
                    tvXieYiSel.setBackgroundResource(R.drawable.xvector_checkbox_sel);
                    xieYiFlag = true;
                } else {
                    tvXieYiSel.setBackgroundResource(R.drawable.xvector_checkbox_unsel);
                    xieYiFlag = false;
                }
                count++;
                break;
            case R.id.tv_act_electronicsgnature_xieyi://协议详情
                break;
            case R.id.tv_act_electronicsgnature_affirmopen://确定开通
                if (xieYiFlag){
                    //请求开通
                    ToastUtils.showToast(ElectronicSignatureActivity.this, "请求网络开通电子签章!");
                }else {
                    ToastUtils.showToast(ElectronicSignatureActivity.this, R.string.qingxiantongyishuzizhengshufuwuxieyi);
                }
                break;
//                 case R.id.:break;
        }

    }

    private void showStyle(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setBackgroundResource(R.drawable.xvector_checkbox_sel);
            } else {
                list.get(i).setBackgroundResource(R.drawable.xvector_checkbox_unsel);
            }
        }
    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        list.add(tvDaLu);
        list.add(tvXiangGang);
        list.add(tvAoMen);
        list.add(tvTaiWan);
        list.add(tvWaiJi);
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvDaLu.setOnClickListener(this);
        tvXiangGang.setOnClickListener(this);
        tvTaiWan.setOnClickListener(this);
        tvWaiJi.setOnClickListener(this);
        tvAoMen.setOnClickListener(this);
        tvXieYiSel.setOnClickListener(this);
        tvXieYi.setOnClickListener(this);
        tvAffirmOpen.setOnClickListener(this);
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
