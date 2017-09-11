package com.tudoujf.activity.my.myaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.BankCardManageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.BankCardManageActBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            BankCardManageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  银行卡管理activity
 * history：
 * * ====================================================================
 */

public class BankCardManageActivity extends BaseActivity {


    @BindView(R.id.mtb_act_bankcardmanage)
    MTopBarView mtbActBankCardManage;
    @BindView(R.id.lv_act_bankcardmanage_info)
    ListView lvInfo;
    @BindView(R.id.ll_act_bankcardmanage_add)
    LinearLayout llAdd;


    private List<BankCardManageActBean>  list;


    @Override
    public int getLayoutResId() {
        return R.layout.act_bankcardmanage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_bankcardmanage_add://添加银行卡
                break;

        }

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            BankCardManageActBean bean=new BankCardManageActBean();
            bean.setImageResId(R.drawable.act_lock_icon);
            bean.setBankName("中国农业银行");
            bean.setCardNumber("622848************0544");

            list.add(bean);

        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActBankCardManage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActBankCardManage.setLayoutParams(params);



        BankCardManageActLvAdapter adapter=new BankCardManageActLvAdapter(list,this);
        lvInfo.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbActBankCardManage.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        llAdd.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
