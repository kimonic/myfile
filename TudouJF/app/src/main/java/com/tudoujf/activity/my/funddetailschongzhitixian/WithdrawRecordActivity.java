package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.RechargeRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            WithdrawRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：  提现记录activity
 * history：
 * * ====================================================================
 */

public class WithdrawRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_withdrawrecord)
    MTopBarView mtbActWithdrawRecord;
    @BindView(R.id.lv_act_withdrawrecord)
    ListView lvActWithdrawRecord;
    @BindView(R.id.tv_act_withdrawrecord_loadmore)
    TextView tvLoadMore;

    private List<RechargeRecordActBean> list;


    @Override
    public int getLayoutResId() {
        return R.layout.act_withdrawrecord;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
//临时数据源
        list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            RechargeRecordActBean bean=new RechargeRecordActBean();
            bean.setJinE("000,000,000.00");
            bean.setDate("20XX-XX-XX");
            if (i%2==0){
                bean.setState("提现成功");
            }else {
                bean.setState("待审核");
            }
            bean.setContent("提现金额");

            list.add(bean);
        }
    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActWithdrawRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActWithdrawRecord.setLayoutParams(params);

        RechargeRecordActLvAdapter adapter=new RechargeRecordActLvAdapter(list,this);
        lvActWithdrawRecord.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbActWithdrawRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
}
