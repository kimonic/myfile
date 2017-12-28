package com.tudoujf.fragment.myprojectfrag;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
import com.tudoujf.activity.my.myproject.MyCreditorRightsDetailsActivity;
import com.tudoujf.adapter.MyProjectCreditorFragLvAdapter;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.MyCreditorProjectBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyProjectCreditorFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目--->债权项目列表
 * history：
 * * ====================================================================
 */

public class MyProjectCreditorFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojectcreditor)
    ListView lvTotal;
    @BindView(R.id.srl_frag_myprojectcreditor)
    SmartRefreshLayout srlTotal;
    @BindView(R.id.tv_frag_myprojectcreditor_zhuan_rang_jin_e)
    TextView zhuanRangJinE;
    @BindView(R.id.tv_frag_myprojectcreditor_zhuan_rang_ying_kui)
    TextView zhuanRangYingkui;
    @BindView(R.id.tv_frag_myprojectcreditor_gou_ru_jin_e)
    TextView gouRuJinE;
    @BindView(R.id.tv_frag_myprojectcreditor_gou_ru_ying_kui)
    TextView gouRuYingKui;
    @BindView(R.id.tv_frag_myprojectcreditor_gou_mai_ji_lu)
    TextView gouMaiJiLu;
    @BindView(R.id.tv_frag_myprojectcreditor_kzr_hsz)
    TextView tvKzrHsz;


    private List<MyProjectTotalFragBean> list;
    private int page = 1;
    private MyCreditorProjectBean bean;
    private List<MyCreditorProjectBean.TransferListBean.ItemsBean> listBean;
    private MyProjectCreditorFragLvAdapter adapter;
    private String start_time = "", end_time = "";
    /**
     * 筛选条件标识
     */
    private boolean flag = false;

//    private int beforePage;
//    private int beforeTotalPage;
    private String status_nid = "transfer";
    private int count = 0;
    private int type = 2;

    public void setStart_time(String start_time) {
        initSearch();
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        initSearch();
        this.end_time = end_time;
    }

    private void initSearch() {
//        if (bean != null) {
//            beforePage = page;
//            beforeTotalPage = bean.getTransfer_list().getTotal_pages();
//        }
        page = 1;
        flag = true;
    }


    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectcreditor;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_myprojectcreditor_gou_mai_ji_lu:
                page = 1;
                bean = null;
                if (count % 2 == 0) {
                    status_nid = "buy";
                    gouMaiJiLu.setText(R.string.chakanzhuanrangjilu);
                    tvKzrHsz.setText(R.string.huishouzhong);
                    type = 1;//1,债权购买记录
                } else {
                    status_nid = "transfer";
                    gouMaiJiLu.setText(R.string.chakangoumaijilu);
                    tvKzrHsz.setText(R.string.kezhuanrang);
                    type = 2;//2,债权转让记录
                }
                listBean.clear();
                initDataFromInternet();
                count++;

                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }

    }

    @Override
    public void initDataFromIntent() {

        listBean = new ArrayList<>();
//        list = new ArrayList<>();
//
//        for (int i = 0; i < 50; i++) {
//            MyProjectTotalFragBean bean = new MyProjectTotalFragBean();
//            bean.setTitle("房产抵押贷款201703270003");
//            bean.setTitle1("00,000.00");
//            bean.setTitle2("00,000.00");
//            bean.setTitle3("20XX-XX-XX");
//            bean.setTitle4("待收本息(元)");
//            bean.setTitle5("债权价值(元)");
//            bean.setTitle6("还款期限");
//            bean.setTitle7("转让期数:7/12");
//            bean.setTouzijindu(i % 10 * 0.1f);
//            bean.setTitle8("00,000.00");
//            bean.setType(i % 5 + 1);
//            list.add(bean);
//        }
    }

    @Override
    public void initView() {

//        MyProjectTotalFragLvAdapter  adapter=new MyProjectTotalFragLvAdapter(getActivity(),list);
//        lvTotal.setAdapter(adapter);


        srlTotal.setPrimaryColorsId(R.color.global_theme_background_color);
        srlTotal.setRefreshHeader(new TuDouHeader(getActivity()));
//        srlTotal.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srlTotal.setRefreshFooter(new BallPulseFooter(getActivity()));
        initDataFromInternet();
    }

    @Override
    public void initListener() {
        srlTotal.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                start_time = "";
                end_time = "";
                listBean.clear();
                initDataFromInternet();
            }
        });

        srlTotal.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTransfer_list().getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    srlTotal.finishLoadmore();
                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
                }
            }
        });

        gouMaiJiLu.setOnClickListener(this);

        lvTotal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MyCreditorRightsDetailsActivity.class);
                intent.putExtra("tender_id",listBean.get(position).getTransfer_id());

                int   type=listBean.get(position).getType();
//                1,债权转让记录,2,债权购买记录
                intent.putExtra("type",type);
                if (type==2){
                    intent.putExtra("tender_id",listBean.get(position).getId());
                }else {
                    intent.putExtra("tender_id",listBean.get(position).getTransfer_id());
                }
                startActivityForResult(intent,111);
            }
        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        map.put("page", "" + page);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        map.put("status_nid", status_nid);


        Log.e("TAG", "initDataFromInternet: status_nid-----" + status_nid);

        HttpMethods.getInstance().POST(getActivity(), Constants.MY_CREDITOR, map, "",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSRL();

                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的债权项目接口返回数据--------" + status_nid + "----" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyCreditorProjectBean>() {
                        }.getType(), MyCreditorProjectBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (MyCreditorProjectBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        finishSRL();
                        ToastUtils.showToast(getActivity(), R.string.huoqutouzixiangmuxinxishiabai);

                    }
                });

    }

    private void finishSRL() {
        if (srlTotal.isRefreshing()) {
            srlTotal.finishRefresh();
        } else if (srlTotal.isLoading()) {
            srlTotal.finishLoadmore();
        }
    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getTransfer_list() != null) {
//                && bean.getTransfer_list().getItems().size() > 0) {
//
//            beforeTotalPage = bean.getTransfer_list().getTotal_pages();
            if (flag) {
                listBean.clear();
                flag = false;
            }

            zhuanRangJinE.setText(bean.getTransfer_total());
            gouRuJinE.setText(bean.getTransfer_buy_total());
            zhuanRangYingkui.setText(bean.getTransfer_interest_total());
            gouRuYingKui.setText(bean.getTransfer_buy_interest_total());


            listBean.addAll(bean.getTransfer_list().getItems());

            for (int i = 0; i < listBean.size(); i++) {
                listBean.get(i).setType(type);
            }

            if (adapter == null) {
                adapter = new MyProjectCreditorFragLvAdapter(getActivity(), listBean);
                lvTotal.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            HeightUtils.setListviewHeight(lvTotal);

        } else if (bean != null&&adapter!=null) {
//            page = beforePage;
            listBean.clear();
            adapter.notifyDataSetChanged();
            ToastUtils.showToast(getActivity(), R.string.meiyousousuoshuju);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==111){
//            Log.e("TAG", "onActivityResult: ----我的债权项目刷新-");
            page = 1;
            start_time = "";
            end_time = "";
            listBean.clear();
            initDataFromInternet();
        }
    }
}















