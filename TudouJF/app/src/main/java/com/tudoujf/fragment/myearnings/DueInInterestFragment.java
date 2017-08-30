package com.tudoujf.fragment.myearnings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseFragment;

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


        switch (type) {
            case 1:

                break;
            case 2:
                break;
            case 3:
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
