package com.tudoujf.activity.discover;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.ClassificationOfGoodsLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.bean.databean.IntegralShopMoreBean;
import com.tudoujf.bean.databean.TypeInfoBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
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
    @BindView(R.id.tv_act_classificationofgoods_content)
    TextView tvContent;

    private String type;

    private IntegralShopMoreBean bean;
    private int page = 1;
    private String point_id = "";
    private List<IntegralShopBean.GoodsBean.ItemsBean> list;
    private ClassificationOfGoodsLvAdapter adapter;

    private String requestUrl = "";
    private String type_id = "";
    private TypeInfoBean inBean;

    private AlertDialog dialog;
    private List<TypeInfoBean.ItemsBean> listType;
    private boolean show = false;
    private String integral;

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
                if (dialog == null) {
                    if (listType.size() == 0) {
                        show = true;
                        requestType();
                    } else {
                        dialog = DialogUtils.showListDialog(this, listType, new DialogUtils.ListDialogClickListener() {
                            @Override
                            public void onClick(int position) {
                                page = 1;
                                list.clear();
                                if ("fenlei".equals(type)) {//分类筛选
                                    type_id = listType.get(position).getId();
                                } else if ("jifen".equals(type)) {//积分筛选
                                    point_id = listType.get(position).getId();
                                }
                                initDataFromInternet();
                            }
                        });
                    }
                } else {
                    dialog.show();
                }
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }


    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        listType = new ArrayList<>();
        type = getIntent().getStringExtra("type");
        integral = getIntent().getStringExtra("integral");
        if ("fenlei".equals(type)) {//分类筛选
            requestUrl = Constants.GOODS_TYPES;
            tvContent.setText(R.string.fenleishaixuan);
        } else if ("jifen".equals(type)) {//积分筛选
            requestUrl = Constants.POINT_RANK;
            tvContent.setText(R.string.act_integralshop_jifenshaixuan);
        }
        requestType();


    }


    private void requestType() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
//        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("type_id", type_id);
//        map.put("point_id", point_id);
//        map.put("page", "" + page);


        HttpMethods.getInstance().POST(this, requestUrl, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
//                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ClassificationOfGoodsActivity.class, "logflag---积分商城--商品分类--分类类型返回的json数据--" + result);

                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        String rankingList = "{items:" + bean.getData().toString() + "}";
                        inBean = gson.fromJson(rankingList, TypeInfoBean.class);
                        if (inBean != null) {
                            if (inBean.getItems() != null && inBean.getItems().size() > 0) {
                                listType.addAll(inBean.getItems());
                                if (show) {
                                    dialog = DialogUtils.showListDialog(ClassificationOfGoodsActivity.this, listType, new DialogUtils.ListDialogClickListener() {
                                        @Override
                                        public void onClick(int position) {
                                            page = 1;
                                            list.clear();
                                            if ("fenlei".equals(type)) {//分类筛选
                                                type_id = listType.get(position).getId();
                                            } else if ("jifen".equals(type)) {//积分筛选
                                                point_id = listType.get(position).getId();
                                            }
                                            initDataFromInternet();

                                        }
                                    });
                                }
                            } else {
                                ToastUtils.showToast(ClassificationOfGoodsActivity.this, R.string.huoqufenleileixingshiabai);
                            }

                        } else {
                            ToastUtils.showToast(ClassificationOfGoodsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        finishRL();
                        ToastUtils.showToast(ClassificationOfGoodsActivity.this, R.string.huoqushaixuanxinxishibai);

                    }
                }
        );

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        frameLayout.setLayoutParams(params);

        srl.setRefreshHeader(new TuDouHeader(this));
        srl.setPrimaryColorsId(R.color.global_theme_background_color);

    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(this);
        llScreen.setOnClickListener(this);


        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                point_id = "";
                type_id = "";
                page = 1;
                list.clear();
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
                    ToastUtils.showToast(ClassificationOfGoodsActivity.this, R.string.meiyougengduola);
                    srl.finishLoadmore();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassificationOfGoodsActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("integral", integral);
                startActivity(intent);

            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("type_id", type_id);
        map.put("point_id", point_id);
        map.put("page", "" + page);


        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_SHOP_MORE, map, "ClassificationOfGoodsActivity", new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ClassificationOfGoodsActivity.class, "logflag--积分商城--商品分类返回的json数据--" + result);

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

            if (adapter == null) {
                adapter = new ClassificationOfGoodsLvAdapter(list, this);
                lv.setAdapter(adapter);
            } else {
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
        if (srl != null) {
            if (srl.isLoading()) {
                srl.finishLoadmore();
            } else if (srl.isRefreshing()) {
                srl.finishRefresh();
            }
        }


    }
}
