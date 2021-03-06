package com.tudoujf.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.discover.ClassificationOfGoodsActivity;
import com.tudoujf.activity.discover.ExchangeRecordActivity;
import com.tudoujf.activity.discover.GoodsDetailsActivity;
import com.tudoujf.activity.discover.HotExchangeActivity;
import com.tudoujf.activity.discover.IntegralRankingListActivity;
import com.tudoujf.adapter.IntegralShopRvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.bean.databean.IntegralShopMoreBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.GridSpacingItemDecoration;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private TextView tvTouzi;
    private TextView myIntegral;
    private LinearLayout classificationOfGoods;
    private LinearLayout integralScreen;
    private LinearLayout hot;
    private LinearLayout rankingList;

    /**
     * 头部视图
     */
    private View headerView;

    private IntegralShopBean bean;
    private IntegralShopMoreBean beanMore;


    private IntegralShopRvAdapter adapter;

    private List<IntegralShopBean.GoodsBean.ItemsBean> list;
    private int page = 1;
    private GridLayoutManager manager;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralshop;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_integralshop_classification_of_goods://分类筛选
                Map<String, String> map = new TreeMap<>();
                map.put("type", "fenlei");
                if (bean != null) {
                    map.put("integral", bean.getMyPoint());
                }
                openActivityParams(ClassificationOfGoodsActivity.class, map);
                break;
            case R.id.ll_act_integralshop_integral_screen://积分筛选
                Map<String, String> map1 = new TreeMap<>();
                map1.put("type", "jifen");
                if (bean != null) {
                    map1.put("integral", bean.getMyPoint());
                }
                openActivityParams(ClassificationOfGoodsActivity.class, map1);
                break;
            case R.id.ll_act_integralshop_hot://热门兑换
                Intent intent = new Intent(this, HotExchangeActivity.class);
                intent.putExtra("integral", bean.getMyPoint());
                startActivity(intent);
//                openActivity(HotExchangeActivity.class);
                break;
            case R.id.ll_act_integralshop_ranking_list://排行榜
                openActivity(IntegralRankingListActivity.class);
                break;
            case R.id.tv_act_myaccount_touzi://跳转投资
                Intent intent1 = new Intent(this, HomeActivity.class);
                intent1.putExtra("flag", 555);
                startActivity(intent1);
                break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActIntegralShop.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActIntegralShop.setLayoutParams(params);
//
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new TuDouHeader(this));

        manager = new GridLayoutManager(this, 2);
        rvActIntegralShop.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHead(position) ? manager.getSpanCount() : 1;
            }
        });
        rvActIntegralShop.addItemDecoration(new GridSpacingItemDecoration());


        headerView = LayoutInflater.from(this).inflate(R.layout.rv_header_integralshop, null, false);
        tvTouzi = headerView.findViewById(R.id.tv_act_myaccount_touzi);
        myIntegral = headerView.findViewById(R.id.tv_act_integralshop_myintegral);
        classificationOfGoods = headerView.findViewById(R.id.ll_act_integralshop_classification_of_goods);
        integralScreen = headerView.findViewById(R.id.ll_act_integralshop_integral_screen);
        hot = headerView.findViewById(R.id.ll_act_integralshop_hot);
        rankingList = headerView.findViewById(R.id.ll_act_integralshop_ranking_list);


    }

    @Override
    public void initListener() {
        mtbActIntegralShop.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        mtbActIntegralShop.getRightTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ExchangeRecordActivity.class);
            }
        });

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initDataFromInternet();
            }
        });

        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (page < bean.getGoods().getTotal_pages()) {
                    page++;
                    loadMoreGoods();
                } else {
                    ToastUtils.showToast(IntegralShopActivity.this, R.string.meiyougengduola);
                    srl.finishLoadmore();
                }
            }
        });

        classificationOfGoods.setOnClickListener(this);
        integralScreen.setOnClickListener(this);
        hot.setOnClickListener(this);
        rankingList.setOnClickListener(this);
        tvTouzi.setOnClickListener(this);


    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_SHOP, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(IntegralShopActivity.class, "logflag-请求积分商城返回的json数据--" + result);
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
                        ToastUtils.showToast(IntegralShopActivity.this, R.string.huoqujifenshangchengxinxishibai);
                    }
                }
        );
    }

    private void loadMoreGoods() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("type_id", "");
        map.put("point_id", "");
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_SHOP_MORE, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(IntegralShopActivity.class, "logflag-请求积分商城加载更多商品返回的json数据--" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IntegralShopMoreBean>() {
                        }.getType(), IntegralShopMoreBean.class, IntegralShopActivity.this);
                        if (bean1 != null) {
                            beanMore = (IntegralShopMoreBean) bean1;
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
                        ToastUtils.showToast(IntegralShopActivity.this, R.string.huoqujifenshangchengxinxishibai);
                    }
                }
        );
    }

    @Override
    public void LoadInternetDataToUi() {
        if (page == 1 && bean != null && bean.getGoods() != null && bean.getGoods().getItems() != null && bean.getGoods().getItems().size() > 0) {
            myIntegral.setText(bean.getMyPoint());
            list.addAll(bean.getGoods().getItems());

            if (adapter == null) {
                adapter = new IntegralShopRvAdapter(this, list, R.layout.rv_header_integralshop);
                adapter.setHeaderView(headerView);
                rvActIntegralShop.setAdapter(adapter);
                adapter.setOnItemClickListener(new IntegralShopRvAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openGoodsDetailsActivity(list.get(position).getId());
                    }
                });
            } else {
                adapter.notifyDataSetChanged();
            }
        } else if (beanMore != null && page > 1 && beanMore.getItems() != null) {
            LUtils.e(IntegralShopActivity.class, "logflag--shop-" + list.size());
            list.addAll(beanMore.getItems());
            if (adapter == null) {
                adapter = new IntegralShopRvAdapter(this, list, R.layout.rv_header_integralshop);
                adapter.setHeaderView(headerView);
                rvActIntegralShop.setAdapter(adapter);
                adapter.setOnItemClickListener(new IntegralShopRvAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openGoodsDetailsActivity(list.get(position).getId());
//                        openActivity(GoodsDetailsActivity.class);
                    }
                });
            } else {
                LUtils.e(IntegralShopActivity.class, "logflag--shop-刷新");
                adapter.notifyDataSetChanged();
            }
        } else {
            ToastUtils.showToast(IntegralShopActivity.this, R.string.meiyoukexianshishuju);
        }

    }

    private void openGoodsDetailsActivity(String id) {
        Intent intent = new Intent(this, GoodsDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("integral", bean.getMyPoint());
        startActivity(intent);
    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }


    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int flag = intent.getIntExtra("flag", 0);
        if (flag == 555) {
            page = 1;
            list.clear();
            initDataFromInternet();
        }

    }

    private void finishRL() {

        if (srl != null) {
            if (srl.isLoading()) {
                srl.finishLoadmore();
            } else if (srl.isRefreshing()) {
                srl.finishRefresh();
            }
        }
    }
}