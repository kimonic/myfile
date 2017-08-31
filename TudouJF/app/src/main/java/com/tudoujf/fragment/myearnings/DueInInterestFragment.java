package com.tudoujf.fragment.myearnings;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.DueInInterestFragLvAdapterO;
import com.tudoujf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ================================================
 * name:            DueInInterestFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/30
 * description：  我的收益页面activity--->待收利息fragment
 * history：
 * ===================================================
 */

public class DueInInterestFragment extends BaseFragment {
    @BindView(R.id.lv_frag_dueininterest)
    ListView lvFragDueInInterest;
    @BindView(R.id.tv_frag_dueininterest1)
    TextView tvDate;
    @BindView(R.id.tv_frag_dueininterest2)
    TextView tvName;
    @BindView(R.id.tv_frag_dueininterest3)
    TextView tvHuoDong;
    @BindView(R.id.tv_frag_dueininterest4)
    TextView tvShouYi;
    Unbinder unbinder;


    private int type = 1;
    private List<DueInInterestFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_dueininterest;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        // TODO: 2017/8/30   此处获得该fragment的类型进行相关逻辑处理
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            DueInInterestFragBean bean = new DueInInterestFragBean();
            bean.setName("老铁尊享福袋");
            bean.setDate("2017-07-31");
            bean.setJine("000,000.00");
            bean.setShouYi("+0,000.00");
            list.add(bean);


        }

        if (getArguments()!=null){
            type=getArguments().getInt("type",1);
        }
        switch (type) {
            case 1://待收利息,已收利息
            case 2:
                DueInInterestFragLvAdapterO adapterO = new DueInInterestFragLvAdapterO(getActivity(), list, 1);
                lvFragDueInInterest.setAdapter(adapterO);
                break;
            case 3:
                tvHuoDong.setVisibility(View.GONE);
                tvName.setText("活动名称");
                DueInInterestFragLvAdapterO adapterO1 = new DueInInterestFragLvAdapterO(getActivity(), list, 2);
                lvFragDueInInterest.setAdapter(adapterO1);
                break;
        }

    }

    @Override
    public void initView() {

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
