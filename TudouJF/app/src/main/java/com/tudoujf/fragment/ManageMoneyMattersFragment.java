package com.tudoujf.fragment;

import android.view.View;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.ManageMoneyMattersActLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.ManageMoneyMattersFragBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            ManageMoneyMattersFragment
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  理财fragment
 * history：
 * * ====================================================================
 */

public class ManageMoneyMattersFragment extends BaseFragment {
    @BindView(R.id.lv_frag_managemoneymatters)
    ListView lvFragManageMoneyMatters;

    private List<ManageMoneyMattersFragBean>  list;

    @Override
    public int layoutRes() {
        return R.layout.frag_managemoneymatters;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ManageMoneyMattersFragBean bean=new ManageMoneyMattersFragBean();

            bean.setInvestTime(i+"个月");
            bean.setInvestProgress(0.1f*i);
            if (i%2==0){
                bean.setInvestNow(false);
                bean.setAward(true);
            }else {
                bean.setInvestNow(true);
                bean.setAward(false);
            }


            bean.setInvestSum("10,000,000.00");
            bean.setAwardValue(i*0.01f);

            bean.setNianHuaShouYi(i+".00%");
            bean.setShengYuKeTou(i*123456+".00元");
            bean.setTitle("房产抵押贷款20170327003");



            list.add(bean);
        }

    }

    @Override
    public void initView() {
        ManageMoneyMattersActLvAdapter actLVAdapter=new ManageMoneyMattersActLvAdapter(getActivity(),list);
        lvFragManageMoneyMatters.setAdapter(actLVAdapter);

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
