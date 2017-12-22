package com.tudoujf.fragment.myprojectfrag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             MyProjectInvestFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/22
 * description：
 * history：
 * *==================================================================
 */

public class MyProjectInvestFragment extends BaseFragment {
    @BindView(R.id.tv_frag_myprojectinvest1)
    TextView tvBtn1;
    @BindView(R.id.tv_frag_myprojectinvest2)
    TextView tvBtn2;
    @BindView(R.id.tv_frag_myprojectinvest3)
    TextView tvBtn3;
    @BindView(R.id.vp_frag_myprojectinvest)
    ViewPager vp;

    private List<TextView> list;
    private List<Fragment> listFrag;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectinvest;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_myprojectinvest1:
                break;
            case R.id.tv_frag_myprojectinvest2:
                break;
            case R.id.tv_frag_myprojectinvest3:
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        listFrag = new ArrayList<>();

    }

    @Override
    public void initView() {
        list.add(tvBtn1);
        list.add(tvBtn2);
        list.add(tvBtn3);

        MyProjectInvestChildFragment fragment1=new MyProjectInvestChildFragment();
        MyProjectInvestChildFragment fragment2=new MyProjectInvestChildFragment();
        MyProjectInvestChildFragment fragment3=new MyProjectInvestChildFragment();

        listFrag.add(fragment1);
        listFrag.add(fragment2);
        listFrag.add(fragment3);

        vp.setAdapter(new HomeFragVPAdapter(getChildFragmentManager(),listFrag));



    }

    @Override
    public void initListener() {
        tvBtn1.setOnClickListener(this);
        tvBtn2.setOnClickListener(this);
        tvBtn3.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
