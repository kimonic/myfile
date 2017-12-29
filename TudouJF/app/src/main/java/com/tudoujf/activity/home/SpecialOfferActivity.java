package com.tudoujf.activity.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.adapter.InvestListFragLvAdapter;
import com.tudoujf.adapter.SpecialOfferActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.SpecialOfferActBean;
import com.tudoujf.bean.databean.MyExperienceGoldBean;
import com.tudoujf.bean.databean.SpecialOfferBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
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
 * * ================================================
 * name:            SpecialOfferActivity
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：活动页activity
 * history：
 * ===================================================
 */

public class SpecialOfferActivity extends BaseActivity {
    private static final String TAG = "SpecialOfferActivity";
    @BindView(R.id.mtb_act_specialoffer)
    MTopBarView mtbSpecialOffer;
    @BindView(R.id.lv_act_specialoffer)
    ListView lvSpecialOffer;
    @BindView(R.id.srl_specialoffer)
    SmartRefreshLayout srl;

    private List<SpecialOfferBean.ItemsBean> list;
    private int page = 1;
    private SpecialOfferBean bean;
    private SpecialOfferActLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_specialoffer;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list = new ArrayList<>();
//        SpecialOfferActBean bean1=new SpecialOfferActBean();
//        bean1.setTitle("房产抵押+履约保险业务");
//        bean1.setTime("活动长期有效");
//        bean1.setResId(R.drawable.act_specialoffer_icon1);
//
//        SpecialOfferActBean bean2=new SpecialOfferActBean();
//        bean2.setTitle("不负春光,与你同行");
//        bean2.setTime("2017年7月17日--2017年8月17日");
//        bean2.setResId(R.drawable.act_specialoffer_icon2);
//
//
//        SpecialOfferActBean bean3=new SpecialOfferActBean();
//        bean3.setTitle("聚财聚友聚人气");
//        bean3.setTime("活动已结束");
//        bean3.setResId(R.drawable.act_specialoffer_icon3);
//
//        list.add(bean1);
//        list.add(bean2);
//        list.add(bean3);

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbSpecialOffer.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbSpecialOffer.setLayoutParams(params);

        mtbSpecialOffer.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new TuDouHeader(this));
        srl.setRefreshFooter(new BallPulseFooter(this));
        srl.setEnableLoadmore(true);


    }

    @Override
    public void initListener() {
        lvSpecialOffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SpecialOfferActivity.this, WebActivity.class);
                intent.putExtra("url", list.get(position).getJumpurl());
                intent.putExtra("title", list.get(position).getName());
                startActivity(intent);
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
                if (bean != null && page < bean.getTotal_pages()) {
                    page++;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(SpecialOfferActivity.this, R.string.meiyougengduola);
                    srl.finishLoadmore();
                }

            }
        });

    }

    @Override
    public void initDataFromInternet() {
        Log.e(TAG, "onSuccess:------------活动专区请求json数据----------------- ");
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("page_count","30");
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(this, Constants.HUO_DONG_ZHUAN_QU, map, "specialofferactivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                finishRl();
                String result = StringUtils.getDecodeString(response.body());
                Log.e(TAG, "onSuccess:------------活动专区请求json数据----------------- " + result);


                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<SpecialOfferBean>() {
                }.getType(), SpecialOfferBean.class, SpecialOfferActivity.this);
                if (bean1 != null) {
                    bean = (SpecialOfferBean) bean1;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(SpecialOfferActivity.this, R.string.shujujiazaichucuo);
                }
            }

            @Override
            public void onError(Response<String> response) {
                dismissPDialog();
                finishRl();
                Log.e(TAG, "onSuccess:------------活动专区请求json数据失败----------------- " + response.code());
                super.onError(response);
            }
        });

    }

    private void finishRl() {
        if (srl!=null){
            if (srl.isRefreshing()) {
                srl.finishRefresh();
            } else if (srl.isLoading()) {
                srl.finishLoadmore();
            }
        }

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {

            list.addAll(bean.getItems());

            if (adapter == null) {
                adapter = new SpecialOfferActLvAdapter(list, this);
                lvSpecialOffer.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }

        } else {
            ToastUtils.showToast(this, R.string.meiyougengduola);
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
}
