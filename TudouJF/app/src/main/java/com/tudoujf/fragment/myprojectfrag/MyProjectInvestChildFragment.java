package com.tudoujf.fragment.myprojectfrag;

import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tudoujf.R;
import com.tudoujf.base.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * * ===============================================================
 * name:             MyProjectInvestChildFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/22
 * description：  MyProjectInvestFragment中的子fragment
 * history：
 * *==================================================================
 */

public class MyProjectInvestChildFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojectinvest)
    ListView lvFragMyprojectinvest;
    @BindView(R.id.srl_frag_myprojectinvest)
    SmartRefreshLayout srlFragMyprojectinvest;
    Unbinder unbinder;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectinvestchild;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        initDataFromInternet();
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
