package com.tudoujf.fragment;

import android.view.View;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.ManageMoneyMattersChildFragLvAdapter;
import com.tudoujf.adapter.ManageMoneyMattersFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.ManageMoneyMattersFragBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            ManageMoneyMattersChildFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  理财fragment
 * history：
 * * ====================================================================
 */

public class ManageMoneyMattersChildFragment extends BaseFragment {
    @BindView(R.id.lv_frag_managemoneymatterstouchild)
    ListView lvFragManageMoneyMatters;

    private List<ManageMoneyMattersFragBean> list;

    private int type=1;

    @Override
    public int layoutRes() {
        return R.layout.frag_managemoneymatterstouchild;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        type=getArguments().getInt("type",1);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ManageMoneyMattersFragBean bean = new ManageMoneyMattersFragBean();

            bean.setInvestTime(i + "个月");
            bean.setInvestProgress(0.1f * i);
            if (i % 2 == 0) {
                bean.setInvestNow(false);
                bean.setAward(true);
            } else {
                bean.setInvestNow(true);
                bean.setAward(false);
            }


            bean.setInvestSum("10,000,000.00");
            bean.setAwardValue(i * 0.01f);

            bean.setNianHuaShouYi(i + ".00%");
            bean.setShengYuKeTou(i * 123456 + ".00元");
            bean.setTitle("房产抵押贷款20170327003");


            list.add(bean);
        }

    }

    @Override
    public void initView() {
        if (type==1){
            ManageMoneyMattersFragLvAdapter actLVAdapter = new ManageMoneyMattersFragLvAdapter(getActivity(), list);
            lvFragManageMoneyMatters.setAdapter(actLVAdapter);
        }else {
            ManageMoneyMattersChildFragLvAdapter actLVAdapter = new ManageMoneyMattersChildFragLvAdapter(getActivity(), list);
            lvFragManageMoneyMatters.setAdapter(actLVAdapter);

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


}
