package com.tudoujf.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.discover.LuckyLotteryActivity;
import com.tudoujf.activity.home.IntegralShopActivity;
import com.tudoujf.adapter.DiscoverFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.DiscoverFragBean;
import com.tudoujf.bean.databean.DiscoverBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
    @BindView(R.id.srl_frag_discover)
    SmartRefreshLayout srl;

    private List<DiscoverBean.ItemsBean> list;
    private int page = 1;
    private DiscoverBean bean;

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
                ToastUtils.showToast(getActivity(), R.string.jijiangkaiqijingqingqidai);
                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

//        DiscoverFragBean bean1 = new DiscoverFragBean();
//        bean1.setTitle("100元红包买你的故事,可好?");
//        bean1.setTime("7月10日");
//        bean1.setMark("你才不是一个没有故事的同学");
//        bean1.setResId(R.drawable.frag_discover_story);
//
//        DiscoverFragBean bean2 = new DiscoverFragBean();
//        bean2.setTitle("轻松摇一摇,秒拿现金奖");
//        bean2.setTime("6月21日");
//        bean2.setMark("啊啊啊,好激动");
//        bean2.setResId(R.drawable.frag_discover_sharkitoff);
//
//        list.add(bean1);
//        list.add(bean2);

    }

    @Override
    public void initView() {

        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srl.setRefreshFooter(new BallPulseFooter(getActivity()));
        srl.setEnableLoadmore(true);

        initDataFromInternet();
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

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                finishRL();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                finishRL();
            }
        });

        lvFragDiscover.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",list.get(position).getJumpurl());
                intent.putExtra("title",list.get(position).getName());
                startActivity(intent);
//                openActivity(WebActivity.class);
            }
        });

    }

    private void finishRL() {
        if (srl.isRefreshing()) {
            srl.finishRefresh();
        } else if (srl.isLoading()) {
            srl.finishLoadmore();
        }
    }

    @Override
    public void initDataFromInternet() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", "" + page);

        HttpMethods.getInstance().POST(getActivity(), Constants.DISCOVER, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:---首页发现接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<DiscoverBean>() {
                        }.getType(), DiscoverBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (DiscoverBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {

            list.addAll(bean.getItems());
//            list.addAll(bean.getItems());

            DiscoverFragLvAdapter adapter = new DiscoverFragLvAdapter(list, getActivity());
            lvFragDiscover.setAdapter(adapter);
            HeightUtils.setListviewHeight(lvFragDiscover);

        }

    }


}
