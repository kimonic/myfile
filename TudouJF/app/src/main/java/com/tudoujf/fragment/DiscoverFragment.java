package com.tudoujf.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.ui.MTopBarView;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            DiscoverFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：  首页activity中的发现fragment
 * history：
 * * ====================================================================
 */

public class DiscoverFragment extends BaseFragment {
    @BindView(R.id.mtb_frag_discover)
    MTopBarView mtbFragDiscover;
    @BindView(R.id.ll_frag_discover_bt1)
    LinearLayout llFragDiscoverBt1;
    @BindView(R.id.ll_frag_discover_bt2)
    LinearLayout llFragDiscoverBt2;
    @BindView(R.id.ll_frag_discover_bt3)
    LinearLayout llFragDiscoverBt3;
    @BindView(R.id.lv_frag_discover)
    ListView lvFragDiscover;

    @Override
    public int layoutRes() {
        return R.layout.frag_discover;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mtbFragDiscover.getLeftTV().setOnClickListener(new View.OnClickListener() {
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


}
