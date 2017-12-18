package com.tudoujf.activity.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.managemoney.CreditorRightsDetailsActivity;
import com.tudoujf.adapter.IntegralShopRvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.bean.databean.CreditorRightsDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.FullyGridLayoutManager;
import com.tudoujf.ui.GridSpacingItemDecoration;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            IntegralShopActivity
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity-->HomeFragment-->SignInActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/11
 * description：  首页activity中的首页fragment中的签到activity中的积分商城
 * history：
 * * ====================================================================
 */

public class IntegralShopActivity extends BaseActivity {
    @BindView(R.id.mtb_act_integralshop)
    MTopBarView mtbActIntegralShop;
    @BindView(R.id.rv_act_integralshop)
    RecyclerView rvActIntegralShop;
    @BindView(R.id.srl_act_integralshop)
    SmartRefreshLayout srl;
    private IntegralShopBean bean;


    private IntegralShopRvAdapter adapter;

    private List<IntegralShopBean.GoodsBean.ItemsBean> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralshop;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActIntegralShop.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActIntegralShop.setLayoutParams(params);

        srl.setRefreshHeader(new TuDouHeader(this));


        FullyGridLayoutManager layoutManager = new FullyGridLayoutManager(this, 2);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
//        rvActIntegralShop.setLayoutManager(layoutManager1);
        rvActIntegralShop.setLayoutManager(layoutManager);
        rvActIntegralShop.setNestedScrollingEnabled(false);
        rvActIntegralShop.addItemDecoration(new GridSpacingItemDecoration());
//        rvActIntegralShop.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL));
        //        rvActIntegralShop.setAdapter();

    }

    @Override
    public void initListener() {
        mtbActIntegralShop.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDataFromInternet();
            }
        });


    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("transfer_id", transfer_id);
//        map.put("loan_id", loan_id);
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_SHOP, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess: -----------请求积分商城返回的json数据----------------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IntegralShopBean>() {
                        }.getType(), IntegralShopBean.class, IntegralShopActivity.this);
                        if (bean1 != null) {
                            bean = (IntegralShopBean) bean1;
                            LoadInternetDataToUi();

                        } else {
                            ToastUtils.showToast(IntegralShopActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        finishRL();
                        ToastUtils.showToast(IntegralShopActivity.this, R.string.huoquzhaiquanxiangqignshujushibai);

                    }
                }
        );
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getGoods() != null && bean.getGoods().getItems() != null && bean.getGoods().getItems().size() > 0) {
            list.addAll(bean.getGoods().getItems());
            if (adapter == null) {
                adapter = new IntegralShopRvAdapter(this, list);
                rvActIntegralShop.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            ToastUtils.showToast(IntegralShopActivity.this, R.string.meiyoukexianshishuju);
        }

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    private void finishRL() {
        if (srl.isLoading()) {
            srl.finishLoadmore();
        } else if (srl.isRefreshing()){
            srl.finishRefresh();
        }

    }
}



