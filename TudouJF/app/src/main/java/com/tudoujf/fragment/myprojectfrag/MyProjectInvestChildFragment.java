package com.tudoujf.fragment.myprojectfrag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.my.myproject.MyInvestDetailsActivity;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.MyInvestProjectBean;
import com.tudoujf.bean.databean.MyProjectInvestChildBean;
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
    SmartRefreshLayout srl;
    Unbinder unbinder;
    private int page = 1;
    private String start_time = "";
    private String end_time = "";
    private String type = "0";
    private MyProjectInvestChildBean bean;
    private List<MyProjectInvestChildBean.ItemsBean> list;
    private MyProjectTotalFragLvAdapter adapter;


    public void setStartEndTime(String startTime, String endTime){

        Log.e("TAG", "setStartEndTime: --startTime---"+startTime);
        Log.e("TAG", "setStartEndTime: --endTime---"+endTime);


        start_time = startTime;
        end_time = endTime;
        page=1;
        list.clear();
        initDataFromInternet();
    }

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectinvestchild;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type", "");
        }

    }

    @Override
    public void initView() {
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new TuDouHeader(getActivity()));
        initDataFromInternet();
    }

    @Override
    public void initListener() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                list.clear();
                start_time = "";
                end_time = "";
                initDataFromInternet();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page++;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
                    srl.finishLoadmore();
                }
            }
        });

        lvFragMyprojectinvest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), MyInvestDetailsActivity.class);
                intent.putExtra("id",list.get(position).getTenderId());
                startActivity(intent);
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
        map.put("type", type);// 0投标中； 1回款中； 2已回款

        Log.e("TAG", "onSuccess:----我的投资项目接口返回数据-login_token------" + UserConfig.getInstance().getLoginToken(getActivity()));


        HttpMethods.getInstance().POST(getActivity(), Constants.MY_INVESTMENT, map, "",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        dismissPDialog();
                        finishRL();


                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的项目-投资项目接口返回数据--------" + type + "-------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyProjectInvestChildBean>() {
                        }.getType(), MyProjectInvestChildBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (MyProjectInvestChildBean) bean1;
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
                        ToastUtils.showToast(getActivity(), R.string.huoqutouzixiangmuxinxishiabai);

                    }
                });


    }

    private void finishRL() {
        if (srl.isRefreshing()){
            srl.finishRefresh();
        }else if (srl.isLoading()){
            srl.finishLoadmore();
        }
    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null) {

            list.addAll(bean.getItems());
            if (adapter == null) {
                adapter = new MyProjectTotalFragLvAdapter(getActivity(), list);
                lvFragMyprojectinvest.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        } else if (bean != null) {
            ToastUtils.showToast(getActivity(), R.string.meiyoukexianshishuju);
        }

    }


}
