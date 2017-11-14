package com.tudoujf.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.activity.discover.LuckyLotteryActivity;
import com.tudoujf.activity.home.IntegralShopActivity;
import com.tudoujf.adapter.DiscoverFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.DiscoverFragBean;
import com.tudoujf.ui.MTopBarView;

import java.util.ArrayList;
import java.util.List;

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

    private List<DiscoverFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_discover;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_frag_discover_bt1:
                openActivity(LuckyLotteryActivity.class);
                break;
            case R.id.ll_frag_discover_bt2:
                openActivity(IntegralShopActivity.class);
                break;
            case R.id.ll_frag_discover_bt3:
                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

        DiscoverFragBean bean1 = new DiscoverFragBean();
        bean1.setTitle("100元红包买你的故事,可好?");
        bean1.setTime("7月10日");
        bean1.setMark("你才不是一个没有故事的同学");
        bean1.setResId(R.drawable.frag_discover_story);

        DiscoverFragBean bean2 = new DiscoverFragBean();
        bean2.setTitle("轻松摇一摇,秒拿现金奖");
        bean2.setTime("6月21日");
        bean2.setMark("啊啊啊,好激动");
        bean2.setResId(R.drawable.frag_discover_sharkitoff);

        list.add(bean1);
        list.add(bean2);

    }

    @Override
    public void initView() {
        DiscoverFragLvAdapter adapter = new DiscoverFragLvAdapter(list, getActivity());
        lvFragDiscover.setAdapter(adapter);

    }

    @Override
    public void initListener() {
        mtbFragDiscover.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        llFragDiscoverBt1.setOnClickListener(this);
        llFragDiscoverBt2.setOnClickListener(this);
        llFragDiscoverBt3.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
