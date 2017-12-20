package com.tudoujf.fragment.myprojectfrag;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.MyInvestProjectBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyProjectTotalFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目--->全部,投资项目列表
 * history：
 * * ====================================================================
 */

public class MyProjectTotalFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojecttotal)
    ListView lvUnderway;
    @BindView(R.id.lv_frag_myprojecttotal_huikuanzhong)
    ListView lvRepayment;
    @BindView(R.id.lv_frag_myprojecttotal_yijieqing)
    ListView lvAlready;
//    @BindView(R.id.srl_frag_myprojecttotal)
//    SmartRefreshLayout srlTotal;

    private List<MyProjectTotalFragBean> list;
    private int page = 1;
    private MyInvestProjectBean bean;
    private List<MyInvestProjectBean.UnderwayBean> underway;
    private List<MyInvestProjectBean.UnderwayBean> repayment;
    private List<MyInvestProjectBean.UnderwayBean> already;
    private MyProjectTotalFragLvAdapter adapter;
    private String start_time = "", end_time = "";
    private MyProjectTotalFragLvAdapter alreadyAdapter, underwayAdapter, repaymentAdapter;
//    private boolean flag = false;
//    private int beforePage;
//    private int beforeTotalPage;

    public void setStart_time(String start_time) {
        initSearch();
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        initSearch();
        this.end_time = end_time;
    }

    private void initSearch() {
        underway.clear();
        repayment.clear();
        already.clear();
//        if (bean!=null){
//            beforePage = page;
//            beforeTotalPage = bean.getTotal_pages();
//        }
//        page = 1;
//        flag = true;
    }

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojecttotal;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        underway = new ArrayList<>();
        repayment = new ArrayList<>();
        already = new ArrayList<>();
//
//        list=new ArrayList<>();
//
//        for (int i = 0; i < 50; i++) {
//            MyProjectTotalFragBean bean=new MyProjectTotalFragBean();
//            bean.setTitle("房产抵押贷款201703270003");
//            bean.setTitle1("00,000.00");
//            bean.setTitle2("00,000.00");
//            bean.setTitle3("20XX-XX-XX");
//            bean.setTitle4("投资金额(元)");
//            bean.setTitle5("预期收益(元)");
//            bean.setTitle6("回款时间");
//            bean.setTouzijindu(i%10*0.1f);
//            bean.setTitle8("00,000.00");
//            bean.setType(i%5+1);
//            list.add(bean);
//        }
    }

    @Override
    public void initView() {


//        srlTotal.setPrimaryColorsId(R.color.global_theme_background_color);
//        srlTotal.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
//        srlTotal.setRefreshFooter(new BallPulseFooter(getActivity()));
        initDataFromInternet();
    }

    @Override
    public void initListener() {
//
//        srlTotal.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                page = 1;
//                listBean.clear();
//                start_time = "";
//                end_time = "";
//                initDataFromInternet();
//            }
//        });

//        srlTotal.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                if (bean != null && page < bean.getTotal_pages()) {
//                    page = page + 1;
//                    initDataFromInternet();
//                    Log.e("TAG", "onLoadmore:我的投资项目接口返回数据 -----"+page);
//
//                } else {
//                    srlTotal.finishLoadmore();
//                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
//                }
//            }
//        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        map.put("page", "" + page);
        map.put("start_time", start_time);
        map.put("end_time", end_time);

        Log.e("TAG", "onSuccess:----我的投资项目接口返回数据-login_token------" + UserConfig.getInstance().getLoginToken(getActivity()));


        HttpMethods.getInstance().POST(getActivity(), Constants.MY_INVESTMENT, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        dismissPDialog();
//                        finishRL();


                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的投资项目接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyInvestProjectBean>() {
                        }.getType(), MyInvestProjectBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (MyInvestProjectBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
//                        finishRL();
                        ToastUtils.showToast(getActivity(), R.string.huoqutouzixiangmuxinxishiabai);

                    }
                });

    }

//    private void finishRL() {
//        if (srlTotal.isRefreshing()) {
//            srlTotal.finishRefresh();
//        } else if (srlTotal.isLoading()) {
//            srlTotal.finishLoadmore();
//        }
//    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {

            if (bean.getAlready() != null) {
                already.addAll(bean.getAlready());
            }
            if (bean.getUnderway() != null) {
                underway.addAll(bean.getUnderway());
            }
            if (bean.getRepayment() != null) {
                repayment.addAll(bean.getRepayment());
            }

            if (alreadyAdapter == null) {
                alreadyAdapter = new MyProjectTotalFragLvAdapter(getActivity(), already);
                lvAlready.setAdapter(alreadyAdapter);
            } else {
                alreadyAdapter.notifyDataSetChanged();
            }

            if (underwayAdapter == null) {
                underwayAdapter = new MyProjectTotalFragLvAdapter(getActivity(), underway);
                lvUnderway.setAdapter(underwayAdapter);
            } else {
                underwayAdapter.notifyDataSetChanged();
            }

            if (repaymentAdapter == null) {
                repaymentAdapter = new MyProjectTotalFragLvAdapter(getActivity(), repayment);
                lvRepayment.setAdapter(repaymentAdapter);
            } else {
                repaymentAdapter.notifyDataSetChanged();
            }


        }

//        if (bean != null && bean.getItems().size() > 0) {
//
//            if (flag) {
//                listBean.clear();
//                flag = false;
//            }
//
//            listBean.addAll();
//            if (adapter == null) {
//                adapter = new MyProjectTotalFragLvAdapter(getActivity(), listBean);
//                lvTotal.setAdapter(adapter);
//            } else {
//                adapter.notifyDataSetChanged();
//            }
//        } else if (bean != null) {
////            bean.setTotal_pages(beforeTotalPage);
//            listBean.clear();
//            adapter.notifyDataSetChanged();
////            page=beforePage;
//            ToastUtils.showToast(getActivity(), R.string.meiyousousuoshuju);
//        }
    }


}
