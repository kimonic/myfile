package com.tudoujf.fragment;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
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
import com.tudoujf.activity.discover.GoodGiftExchangeActivity;
import com.tudoujf.activity.discover.LuckyLotteryActivity;
import com.tudoujf.activity.home.IntegralShopActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.DiscoverFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.DiscoverFragBean;
import com.tudoujf.bean.databean.DiscoverBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.LUtils;
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


    private LinearLayout llFragDiscoverBt1;
    private LinearLayout llFragDiscoverBt2;
    private LinearLayout llFragDiscoverBt3;


    @BindView(R.id.lv_frag_discover)
    ListView lvFragDiscover;
    @BindView(R.id.srl_frag_discover)
    SmartRefreshLayout srl;

    private List<DiscoverBean.ItemsBean> list;
    private int page = 1;
    private DiscoverBean bean;
    private DiscoverFragLvAdapter adapter;

    private boolean refeshFlag=false;

    @Override
    public int layoutRes() {
        return R.layout.frag_discover;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_frag_discover_bt1:
                if (UserConfig.getInstance().getLoginToken(getActivity()).equals("")){
                    openActivity(LoginActivity.class);
                }else {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", Constants.URL + "/wap/prize/index?type=app&login_token="
                            + UserConfig.getInstance().getLoginToken(getActivity()));
                    intent.putExtra("title", getString(R.string.xingyunchoujiang));
                    intent.putExtra("flag", "my");
                    startActivity(intent);
                }

//                openActivity(LuckyLotteryActivity.class);
                break;
            case R.id.ll_frag_discover_bt2:
                openActivity(IntegralShopActivity.class);
                break;
            case R.id.ll_frag_discover_bt3://豪礼兑换
                openActivity(GoodGiftExchangeActivity.class);
//                ToastUtils.showToast(getActivity(), R.string.jijiangkaiqijingqingqidai);
                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

//        DiscoverBean.ItemsBean bean1 = new DiscoverBean.ItemsBean();
//        bean1.setName("100元红包买你的故事,可好?");
//        bean1.setAddTime("7月10日");
//        bean1.setContents("你才不是一个没有故事的同学");
//        bean1.setResId(R.drawable.frag_discover_christmas);
//
//        DiscoverBean.ItemsBean bean2 = new DiscoverBean.ItemsBean();
//        bean2.setName("轻松摇一摇,秒拿现金奖");
//        bean2.setAddTime("6月21日");
//        bean2.setContents("啊啊啊,好激动");
//        bean2.setResId(R.drawable.frag_discover_sharkitoff);
//
//        list.add(bean1);
//        list.add(bean2);

    }

    @Override
    public void initView() {
        // TODO: 2017/12/22 暂时去掉发现顶部的幸运抽奖,积分商城,豪礼兑换按钮
        //listview的固定headerview控件
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.lv_header, null, false);
        llFragDiscoverBt1 = view.findViewById(R.id.ll_frag_discover_bt1);
        llFragDiscoverBt2 = view.findViewById(R.id.ll_frag_discover_bt2);
        llFragDiscoverBt3 = view.findViewById(R.id.ll_frag_discover_bt3);
        lvFragDiscover.addHeaderView(view);


        adapter = new DiscoverFragLvAdapter(list, getActivity());
        lvFragDiscover.setAdapter(adapter);


        srl.setPrimaryColorsId(R.color.global_theme_background_color);
//        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srl.setRefreshHeader(new TuDouHeader(getActivity()));
//        srl.setRefreshFooter(new BallPulseFooter(getActivity()));
        srl.setEnableLoadmore(false);

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
//        // TODO: 2018/4/4 暂时去掉积分商城
        llFragDiscoverBt1.setOnClickListener(this);
        llFragDiscoverBt2.setOnClickListener(this);
        llFragDiscoverBt3.setOnClickListener(this);

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refeshFlag=true;
                page = 1;
                initDataFromInternet();
//                finishRL();
            }
        });
//        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                finishRL();
//            }
//        });

        lvFragDiscover.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id != -1) {
                    int realPosition = (int) id;
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", list.get(realPosition).getJumpurl());
                    intent.putExtra("title", list.get(realPosition).getName());
                    startActivity(intent);
                }
//                openActivity(WebActivity.class);

            }
        });


    }

    private void finishRL() {
        if (srl != null) {
            if (srl.isRefreshing()) {
                srl.finishRefresh();
            } else if (srl.isLoading()) {
                srl.finishLoadmore();
            }
        }

    }

    @Override
    public void initDataFromInternet() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", "" + page);

        HttpMethods.getInstance().POST(getActivity(), Constants.DISCOVER, map, "",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());

                        LUtils.e(DiscoverFragment.class, "logflag--首页发现接口返回数据-" + result);

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
                        finishRL();
                        ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {
            if (refeshFlag){
                list.clear();
                refeshFlag=false;
            }
            list.addAll(bean.getItems());
            adapter.notifyDataSetChanged();
        }

    }


}