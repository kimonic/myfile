package com.tudoujf.activity.my.funddetailschongzhitixian;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.TransactionRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            TransactionRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/11
 * description：  交易记录activity
 * history：
 * * ====================================================================
 */
public class TransactionRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_transactiondetails)
    MTopBarView mtbActTransactionDetails;
    @BindView(R.id.lv_act_transactiondetails)
    ListView lvActTransactionDetails;


    private List<TransactionRecordActBean>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_transactionrecord;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        //临时数据源
        list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            TransactionRecordActBean bean=new TransactionRecordActBean();
            bean.setTitle("商户充值成功");
            bean.setTime("20XX-XX-XX");
            bean.setContent("XXXXXXXXX");
            bean.setBalance("000,000,000.00元");
            bean.setBalance1("Y+000,000,000.00");
            list.add(bean);
        }



    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActTransactionDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActTransactionDetails.setLayoutParams(params);


        TransactionRecordActLvAdapter adapter=new TransactionRecordActLvAdapter(list,this);
        lvActTransactionDetails.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbActTransactionDetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
