package com.tudoujf.activity.discover;


import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.ClassificationOfGoodsLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.bean.databean.IntegralShopMoreBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             ClassificationOfGoodsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：  积分商城---商品分类activity
 * history：
 * *==================================================================
 */

public class ClassificationOfGoodsActivity extends BaseActivity {
    @BindView(R.id.tv_act_classificationofgoods_back)
    TextView tvBack;
    @BindView(R.id.fl_act_classificationofgoods)
    FrameLayout frameLayout;
    @BindView(R.id.ll_act_classificationofgoods_screen)
    LinearLayout llScreen;
    @BindView(R.id.lv_act_classificationofgoods)
    ListView lv;
    @BindView(R.id.srl_act_classificationofgoods)
    SmartRefreshLayout srl;

    private IntegralShopMoreBean bean;
    private int page = 1;
    private String point_id = "";
    private List<IntegralShopBean.GoodsBean.ItemsBean>  list;
    private ClassificationOfGoodsLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_classificationofgoods;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_classificationofgoods_back:
                closeActivity();
                break;
            case R.id.ll_act_classificationofgoods_screen:
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        frameLayout.setLayoutParams(params);

        srl.setRefreshHeader(new TuDouHeader(this));
    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(this);
        llScreen.setOnClickListener(this);


        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                point_id="";
                page=1;
                initDataFromInternet();
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("type_id", "");
        map.put("point_id", point_id);
        map.put("page", "" + page);
        Log.e("TAG", "loadMoreGoods: -----" + page);


        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_SHOP_MORE, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess: -----------积分商城--商品分类返回的json数据----------------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IntegralShopMoreBean>() {
                        }.getType(), IntegralShopMoreBean.class, ClassificationOfGoodsActivity.this);
                        if (bean1 != null) {
                            bean = (IntegralShopMoreBean) bean1;
                            LoadInternetDataToUi();

                        } else {
                            ToastUtils.showToast(ClassificationOfGoodsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        finishRL();
                        ToastUtils.showToast(ClassificationOfGoodsActivity.this, R.string.huoqujifenshangchengxinxishibai);

                    }
                }
        );

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {

            list.addAll(bean.getItems());

            if (adapter==null){
                adapter=new ClassificationOfGoodsLvAdapter(list,this);
                lv.setAdapter(adapter);
            }else {
                adapter.notifyDataSetChanged();
            }


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
        } else if (srl.isRefreshing()) {
            srl.finishRefresh();
        }

    }
}
