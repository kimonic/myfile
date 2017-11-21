package com.tudoujf.activity.my.mypopularize;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

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
import com.tudoujf.adapter.SucceedInvitationActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.MyPopularizeBean;
import com.tudoujf.bean.databean.SucceedInvitationBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;


/**
 * * ================================================
 * name:            SucceedInvitationActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/5
 * description：  我的推广页面-->成功邀请activity
 * history：
 * ===================================================
 */

public class SucceedInvitationActivity extends BaseActivity {
    @BindView(R.id.mtb_act_succeedinvitation)
    MTopBarView mtbActSucceedInvitation;
    @BindView(R.id.lv_act_succeedinvitation)
    ListView lvActSucceedInvitation;
    @BindView(R.id.srl_act_succeedinvitation)
    SmartRefreshLayout srlActSucceedInvitation;

    private List<SucceedInvitationBean.ItemsBean> list;
    private int page = 1;
    private SucceedInvitationBean bean;
    private SucceedInvitationActLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_succeedinvitation;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        //临时数据源
        list = new ArrayList<>();

//        for (int i = 0; i < 50; i++) {
//            SucceedInvitationActBean bean=new SucceedInvitationActBean();
//            bean.setUserName("用户XXXXXX");
//            bean.setTouZiZongE("000,000.00");
//            bean.setHuanKuanZongE("000,000.00");
//
//            if (i%2==1){
//                bean.setBacFlag(2);
//            }
//            list.add(bean);
//        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActSucceedInvitation.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActSucceedInvitation.setLayoutParams(params);

        srlActSucceedInvitation.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActSucceedInvitation.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActSucceedInvitation.setRefreshFooter(new BallPulseFooter(this));
        srlActSucceedInvitation.setEnableLoadmoreWhenContentNotFull(true);


    }

    @Override
    public void initListener() {
        mtbActSucceedInvitation.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        srlActSucceedInvitation.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initDataFromInternet();
            }
        });


        srlActSucceedInvitation.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(SucceedInvitationActivity.this, R.string.meiyougengduola);
                    srlActSucceedInvitation.finishLoadmore();
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

        Log.e("TAG", "initDataFromInternet:我的推广成功邀请好友接口返回数据 -----" + page);
        Log.e("TAG", "initDataFromInternet:我的推广成功邀请好友接口返回数据 -----" + UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(this, Constants.SUCCEED_INVITATION, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSrl();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广成功邀请好友接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<SucceedInvitationBean>() {
                        }.getType(), SucceedInvitationBean.class, SucceedInvitationActivity.this);
                        if (bean1 != null) {
                            bean = (SucceedInvitationBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(SucceedInvitationActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(SucceedInvitationActivity.this, R.string.huoquwodetuiguangxinxishibai);

                    }
                });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {
            list.addAll(bean.getItems());

            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 0) {
                    list.get(i).setBacFlag(1);
                } else {
                    list.get(i).setBacFlag(2);
                }
            }

            if (adapter == null) {
                adapter = new SucceedInvitationActLvAdapter(this, list);
                lvActSucceedInvitation.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void finishSrl() {
        if (srlActSucceedInvitation.isRefreshing()) {
            srlActSucceedInvitation.finishRefresh();
        } else if (srlActSucceedInvitation.isLoading()) {
            srlActSucceedInvitation.finishLoadmore();
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
