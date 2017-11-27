package com.tudoujf.activity.my.mypopularize;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.tudoujf.adapter.RecommendRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.RecommendRecordBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.DateFilterDialog;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            SucceedInvitationRecommendRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广页面-->成功邀请-->推广记录activity
 * history：
 * ===================================================
 */

public class SucceedInvitationRecommendRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_recommendrecord)
    FrameLayout mtbActRecommendrecord;
    @BindView(R.id.lv_act_recommendrecord)
    ListView lvActRecommendrecord;
    @BindView(R.id.srl_act_recommendrecord)
    SmartRefreshLayout srlActRecommendrecord;
    @BindView(R.id.tv_act_recommendrecord)
    TextView tvActRecommendrecord;
    @BindView(R.id.tv_act_recommendrecord_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_recommendrecord_filtrate)
    LinearLayout llFiltrate;


    private List<RecommendRecordBean.PageObjBean.ItemsBean> list;
    private DateFilterDialog dateFilterDialog;

    private int page = 1;
    private RecommendRecordBean bean;
    private RecommendRecordActLvAdapter adapter;
    private String name = "";
    private String start_time = "";
    private String end_time = "";
    private boolean selFlag = false;

    @Override

    public int getLayoutResId() {
        return R.layout.act_recommendrecord;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_act_recommendrecord_bac://关闭本页面
                closeActivity();
                break;
            case R.id.ll_act_recommendrecord_filtrate://筛选按钮
                if (dateFilterDialog == null) {
                    dateFilterDialog = new DateFilterDialog(this);
                    dateFilterDialog.setLisenter(new DateFilterDialog.ClickEvent() {
                        @Override
                        public void dismiss(String startTime, String endTime) {
                            start_time = startTime;
                            end_time = endTime;
                            page = 1;
                            selFlag = true;
//                            list.clear();
                            initDataFromInternet();
                            // TODO: 2017/9/4  请求网络筛选展示数据
//                            ToastUtils.showToast(SucceedInvitationRecommendRecordActivity.this, startTime + "-----------" + endTime);
                        }
                    });
                }
                dateFilterDialog.show();
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

        //临时数据源
        name = getIntent().getStringExtra("name");
        list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            RecommendRecordBean bean = new RecommendRecordBean();
//            bean.setUserName("用户XXXXXX");
//            bean.setTiChengBiLi("0.125%");
//            bean.setTouZiTiCheng("000,000.00");
//            bean.setJieKuanTiCheng("000,000.00");
//            bean.setDate("20XX/XX/XX");
//
//            if (i % 2 == 1) {
//                bean.setBacFlag(2);
//            }
//            list.add(bean);
//        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRecommendrecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRecommendrecord.setLayoutParams(params);

        srlActRecommendrecord.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActRecommendrecord.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActRecommendrecord.setRefreshFooter(new BallPulseFooter(this));


    }

    @Override
    public void initListener() {
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);


        srlActRecommendrecord.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                start_time = "";
                end_time = "";
                list.clear();
                initDataFromInternet();
            }
        });


        srlActRecommendrecord.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getPageObj().getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(SucceedInvitationRecommendRecordActivity.this, R.string.meiyougengduola);
                    srlActRecommendrecord.finishLoadmore();
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
        map.put("name", name);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        Log.e("TAG", "initDataFromInternet:我的推广--推广记录--接口返回数据 -----" + page);
        Log.e("TAG", "initDataFromInternet:我的推广--推广记录--接口返回数据 -----" + UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(this, Constants.RECOMMEND_LOG, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSrl();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广--推广记录--接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<RecommendRecordBean>() {
                        }.getType(), RecommendRecordBean.class, SucceedInvitationRecommendRecordActivity.this);
                        if (bean1 != null) {
                            bean = (RecommendRecordBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(SucceedInvitationRecommendRecordActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(SucceedInvitationRecommendRecordActivity.this, R.string.huoquwodetuiguangxinxishibai);

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getPageObj().getItems() != null && bean.getPageObj().getItems().size() > 0) {
            tvActRecommendrecord.setText(("总计(元):"+StringUtils.getCommaDecimalsStr(bean.getAward())));
            if (selFlag) {
                list.clear();
                selFlag = false;
            }

            list.addAll(bean.getPageObj().getItems());

            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 0) {
                    list.get(i).setBacFlag(1);
                } else {
                    list.get(i).setBacFlag(2);
                }
            }

            if (adapter == null) {
                adapter = new RecommendRecordActLvAdapter(this, list);
                lvActRecommendrecord.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
        if (selFlag) {
            list.clear();
            selFlag = false;
            adapter.notifyDataSetChanged();
            ToastUtils.showToast(SucceedInvitationRecommendRecordActivity.this, R.string.meiyousousuoshuju);
        }
    }

    private void finishSrl() {
        if (srlActRecommendrecord.isRefreshing()) {
            srlActRecommendrecord.finishRefresh();
        } else if (srlActRecommendrecord.isLoading()) {
            srlActRecommendrecord.finishLoadmore();
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
