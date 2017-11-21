package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ===============================================================
 * name:             VIPActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/21
 * description：   申请VIPactivity
 * history：
 * *==================================================================
 */

public class VIPActivity extends BaseActivity {
    @BindView(R.id.mtb_act_vip)
    MTopBarView mtbActVip;
    @BindView(R.id.ll_act_vip_one)
    LinearLayout llActVipOne;
    @BindView(R.id.ll_act_vip_three)
    LinearLayout llActVipThree;
    @BindView(R.id.ll_act_vip_six)
    LinearLayout llActVipSix;
    @BindView(R.id.ll_act_vip_year)
    LinearLayout llActVipYear;
    @BindView(R.id.tv_act_vip_amount)
    TextView tvActVipAmount;
    @BindView(R.id.tv_act_vip_apply_now)
    TextView tvActVipApplyNow;

    private List<LinearLayout> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_vip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_vip_one:
                setBac(0);
                break;
            case R.id.ll_act_vip_three:
                setBac(1);
                break;
            case R.id.ll_act_vip_six:
                setBac(2);
                break;
            case R.id.ll_act_vip_year:
                setBac(3);
                break;
            case R.id.tv_act_vip_apply_now:
                break;
//                 case R.id.:break;
        }

    }

    private void setBac(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setBackgroundResource(R.drawable.act_vip_sel);
            } else {
                list.get(i).setBackgroundResource(R.drawable.act_vip_unsel);
            }
        }
    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActVip.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActVip.setLayoutParams(params);


        list = new ArrayList<>();
        list.add(llActVipOne);
        list.add(llActVipThree);
        list.add(llActVipSix);
        list.add(llActVipYear);

    }

    @Override
    public void initListener() {
        mtbActVip.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        llActVipOne.setOnClickListener(this);
        llActVipSix.setOnClickListener(this);
        llActVipThree.setOnClickListener(this);
        llActVipYear.setOnClickListener(this);
        tvActVipApplyNow.setOnClickListener(this);

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
