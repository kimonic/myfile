package com.tudoujf.activity.discover;

import android.content.Intent;
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
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.home.IntegralShopActivity;
import com.tudoujf.adapter.ExchangeRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.ExchangeRecordBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.DateFilterDialog;
import com.tudoujf.ui.TuDouHeader;
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
 * name:             ExchangeRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/16
 * description：  兑换记录activity
 * history：
 * *==================================================================
 */
public class ExchangeRecordActivity extends BaseActivity {
    @BindView(R.id.tv_act_exchnagerecord_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_exchnagerecord_filtrate)
    LinearLayout llFiltrate;
    @BindView(R.id.mtb_act_exchnagerecord)
    FrameLayout mtb;
    @BindView(R.id.lv_act_exchnagerecord)
    ListView lv;
    @BindView(R.id.srl_act_exchnagerecord)
    SmartRefreshLayout srl;


    private DateFilterDialog dateFilterDialog;
    private String start_time = "";
    private String end_time = "";
    private int page = 1;

    private List<ExchangeRecordBean.ItemsBean> list;


    private int flag;
    private ExchangeRecordBean bean;
    private boolean selFlag;
    private ExchangeRecordActLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_exchangerecord;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_act_exchnagerecord_bac:
                if (flag == 555) {
                    Intent intent = new Intent(this, IntegralShopActivity.class);
                    intent.putExtra("flag", 555);
                    startActivity(intent);
                }

                closeActivity();
                break;
            case R.id.ll_act_exchnagerecord_filtrate:
                if (dateFilterDialog == null) {
                    dateFilterDialog = new DateFilterDialog(this, "请选择您要查询的兑换时间");
                    dateFilterDialog.setLisenter(new DateFilterDialog.ClickEvent() {
                        @Override
                        public void dismiss(String startTime, String endTime) {
                            start_time = startTime;
                            end_time = endTime;
                            page = 1;
                            selFlag = true;
                            initDataFromInternet();
                        }
                    });
                } else {
                    dateFilterDialog.show();
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
        flag = getIntent().getIntExtra("flag", 0);
        list = new ArrayList<>();
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);

        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new TuDouHeader(this));


    }

    @Override
    public void initListener() {
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                start_time = "";
                end_time = "";
                list.clear();
                initDataFromInternet();
            }
        });


        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(ExchangeRecordActivity.this, R.string.meiyougengduola);
                    srl.finishLoadmore();
                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "" + page);
        map.put("start_time", start_time);
        map.put("end_time", end_time);


        HttpMethods.getInstance().POST(this, Constants.EXCHANGE_LOG, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSrl();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ExchangeRecordActivity.class, "logflag-积分商城--兑换记录--接口返回数据--" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<ExchangeRecordBean>() {
                        }.getType(), ExchangeRecordBean.class, ExchangeRecordActivity.this);
                        if (bean1 != null) {
                            bean = (ExchangeRecordBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(ExchangeRecordActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(ExchangeRecordActivity.this, R.string.huoquduihuanjiluxinxishibai);

                    }
                });
    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {

            if (selFlag) {
                list.clear();
                selFlag = false;
            }

            list.addAll(bean.getItems());

            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 1) {
                    list.get(i).setBacFlag(1);
                } else {
                    list.get(i).setBacFlag(2);
                }
            }

            if (adapter == null) {
                adapter = new ExchangeRecordActLvAdapter(this, list);
                lv.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        } else if (selFlag) {
            list.clear();
            selFlag = false;
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            ToastUtils.showToast(ExchangeRecordActivity.this, R.string.meiyousousuoshuju);
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

    @Override
    public void onBackPressed() {
        if (flag == 555) {
            Intent intent = new Intent(this, IntegralShopActivity.class);
            intent.putExtra("flag", 555);
            startActivity(intent);
        }
        closeActivity();
    }

    private void finishSrl() {
        if (srl != null) {
            if (srl.isRefreshing()) {
                srl.finishRefresh();
            } else if (srl.isLoading()) {
                srl.finishLoadmore();
            }
        }

    }
}
