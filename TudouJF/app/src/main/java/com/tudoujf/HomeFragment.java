package com.tudoujf;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tudoujf.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/10.
 *
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_frag_home)
    TextView tvFragHome;


    @Override
    public int layoutRes() {
        return R.layout.frag_home;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle=getArguments();
        if (bundle!=null){
            tvFragHome.setText(bundle.getString("temp"));
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
