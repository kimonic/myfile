package com.tudoujf.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.managemoney.CreditorRightsDetailsActivity;
import com.tudoujf.adapter.CreditorListFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.CreditorListBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            CreditorListFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  理财fragment--->债权列表
 * history：
 * * ====================================================================
 */

public class CreditorListFragment extends BaseFragment {
    @BindView(R.id.tv_frag_creditorlist_total)
    TextView tvTotal;
    @BindView(R.id.tv_frag_creditorlist11)
    TextView tvOrder11;
    @BindView(R.id.tv_frag_creditorlist1)
    TextView tvOrder1;
    @BindView(R.id.ll_frag_creditorlist1)
    LinearLayout llOrder1;
    @BindView(R.id.tv_frag_creditorlist12)
    TextView tvOrder12;
    @BindView(R.id.tv_frag_creditorlist2)
    TextView tvOrder2;
    @BindView(R.id.ll_frag_creditorlist2)
    LinearLayout llOrder2;
    @BindView(R.id.tv_frag_creditorlist13)
    TextView tvOrder13;
    @BindView(R.id.tv_frag_creditorlist3)
    TextView tvOrder3;
    @BindView(R.id.ll_frag_creditorlist3)
    LinearLayout llOrder3;
    @BindView(R.id.lv_frag_managemoneymatterstouchild)
    ListView lvFragManageMoneyMatters;
    @BindView(R.id.srl_frag_managemoneymatterstouchild)
    SmartRefreshLayout swipeRefreshLayout;


    private List<CreditorListBean.ItemsBean> listNew;

    private int type = 1;

    private int count1 = 0, count2 = 0, count3 = 0;

    private CreditorListBean bean;
    private String requestUrl;


    /**
     * 加载的页码
     */
    private int page = 1;
    /**
     * 排序类别
     */
    private String orderType = "1";
    /**
     * 排序方式,升序降序
     */
    private String sortType = "";

    /**
     * 投资列表适配器
     */
    private CreditorListFragLvAdapter actLVAdapter;
    private String TAG = "ManageMoneyChild";
    /**
     * 互斥的button按钮背景色设置的list
     */
    private List<View> list;

    /**
     * 按钮文本显示list
     */
    private List<TextView> listTV;
    /**
     * 按钮指示排序list
     */
    private List<TextView> listIndicator;


    @Override
    public int layoutRes() {
        return R.layout.frag_creditortlist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_frag_creditorlist1://按利率排序
                setBacStyle(0, "2");

                tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow3);
                tvOrder3.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count1 % 2 == 1) {
                    sortType = "2";
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow1);
                } else {
                    sortType = "1";
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count1++;
                initDataFromInternet();


                break;
            case R.id.ll_frag_creditorlist2://按转让期数排序
                setBacStyle(1, "3");

                tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow3);
                tvOrder3.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count2 % 2 == 1) {
                    sortType = "2";
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow1);
                } else {
                    sortType = "1";
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count2++;
                initDataFromInternet();


                break;
            case R.id.ll_frag_creditorlist3://按转让金额排序

                tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow3);
                tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow3);
                setBacStyle(2, "4");

                if (count3 % 2 == 1) {
                    sortType = "2";
                    tvOrder3.setBackgroundResource(R.drawable.xvector_updownarrow1);
                } else {
                    sortType = "1";
                    tvOrder3.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count3++;
                initDataFromInternet();

                break;
            case R.id.tv_frag_creditorlist_total://全部
                setBacStyle(3, "1");
                sortType = "";
                initDataFromInternet();

                break;

        }

    }

    private void setBacStyle(int position, String mOrderType) {

//        showPDialog();
        page = 1;
        orderType = mOrderType;

        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setBackgroundColor(getResources().getColor(R.color.frag_managemoneymatterschild_bac));
                listTV.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));

            } else {
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_white));
                listTV.get(i).setTextColor(getResources().getColor(R.color.color_black));
            }
        }

    }

    @Override
    public void initDataFromIntent() {

        requestUrl = Constants.CREDITOR_TRANSFER_LIST;


    }

    @Override
    public void initView() {

        list = new ArrayList<>();
        list.add(llOrder1);
        list.add(llOrder2);
        list.add(llOrder3);
        list.add(tvTotal);

        listTV = new ArrayList<>();
        listTV.add(tvOrder11);
        listTV.add(tvOrder12);
        listTV.add(tvOrder13);
        listTV.add(tvTotal);

        listIndicator = new ArrayList<>();
        listIndicator.add(tvOrder1);
        listIndicator.add(tvOrder2);
        listIndicator.add(tvOrder3);
        listIndicator.add(tvTotal);


        //设置全区背景色
        swipeRefreshLayout.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        swipeRefreshLayout.setRefreshHeader(new TuDouHeader(getActivity()));
//        swipeRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        swipeRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));


        initDataFromInternet();

    }

    @Override
    public void initListener() {
        tvTotal.setOnClickListener(this);
        llOrder1.setOnClickListener(this);
        llOrder2.setOnClickListener(this);
        llOrder3.setOnClickListener(this);


        swipeRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {//加载更多
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < StringUtils.string2Integer(bean.getTotal_pages())) {
                    page++;
                    initDataFromInternet();
                } else {
                    swipeRefreshLayout.finishLoadmore();
                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
                }

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                initDataFromInternet();
            }
        });

        lvFragManageMoneyMatters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CreditorRightsDetailsActivity.class);
                intent.putExtra("transfer_id",listNew.get(position).getId());
                intent.putExtra("loan_id",listNew.get(position).getLoan_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();

        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", page + "");
        map.put("order_type", orderType);//排序类别,1全部2利率3期限
        map.put("sort_type", sortType);//排序方式,1升序 2降序


        HttpMethods.getInstance().POST(getActivity(), requestUrl, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        finishRL();
                        dismissPDialog();



                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----理财债权列表接口返回数据-- " + type + "------" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<CreditorListBean>() {
                        }.getType(), CreditorListBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (CreditorListBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        finishRL();
                        dismissPDialog();
                        super.onError(response);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0 && page == 1) {

            if (listNew == null) {
                listNew = bean.getItems();
            } else {
                listNew.clear();
                listNew.addAll(bean.getItems());
            }

            if (actLVAdapter == null) {
                actLVAdapter = new CreditorListFragLvAdapter(getActivity(), listNew);
                lvFragManageMoneyMatters.setAdapter(actLVAdapter);
            } else {
                actLVAdapter.notifyDataSetChanged();
                lvFragManageMoneyMatters.setSelection(0);
            }

        } else if (bean != null && bean.getItems() != null && bean.getItems().size() > 0 && page > 1) {

            listNew.addAll(bean.getItems());
            actLVAdapter.notifyDataSetChanged();

        } else {
            ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
        }

    }

    private void finishRL() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.finishRefresh();
        } else if (swipeRefreshLayout.isLoading()) {
            swipeRefreshLayout.finishLoadmore();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserConfig.getInstance().isCreditorFlush()) {
            UserConfig.getInstance().setCreditorFlush(false);
            page = 1;
            initDataFromInternet();
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("TAG", "setUserVisibleHint: -----执行");
        if (isVisibleToUser) {
            Log.e("TAG", "setUserVisibleHint: -----可见");
            if (UserConfig.getInstance().isCreditorFlush()) {
                Log.e("TAG", "setUserVisibleHint: -----可见刷新");

                UserConfig.getInstance().setCreditorFlush(false);
                page = 1;
                initDataFromInternet();
            }

            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
            Log.e("TAG", "setUserVisibleHint: -----不可见");
        }
    }


}
