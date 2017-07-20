package com.tudoujf.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.fungame.FunGameHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.SimplVAdapter;
import com.tudoujf.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            PushPullActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description： 测试自定义上拉加载下拉刷新activity
 * history：
 * ===================================================
 */

public class PushPullActivity extends BaseActivity {
    @BindView(R.id.act_pullpush_listview)
    ListView listview;
    @BindView(R.id.act_pullpush_smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    @Override
    public int getLayoutResId() {
        return R.layout.act_pullpush;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
    }

    @Override
    public void initView() {
        smartrefreshlayout.setRefreshHeader(new StoreHouseHeader(this));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(this));
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartrefreshlayout.finishRefresh(2000);

            }
        });
        smartrefreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smartrefreshlayout.finishLoadmore(2000);
            }
        });
        List<String>  list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("这个item的序号是"+i);
        }
        SimplVAdapter adapter=new SimplVAdapter(list,this);
        listview.setAdapter(adapter);
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
