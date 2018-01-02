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
import com.tudoujf.adapter.AcountRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.AccountRecordBean;
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
 * name:            AccountRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广页面-->结算记录activity
 * history：
 * ===================================================
 */

public class AccountRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_accountrecord)
    MTopBarView mtbActAccountRecord;
    @BindView(R.id.lv_act_accountrecord)
    ListView lvActAccountRecord;
    @BindView(R.id.srl_act_accountrecord)
    SmartRefreshLayout srlActAccountRecord;
    private List<AccountRecordBean.ItemsBean> list;
    private int page = 1;
    private AccountRecordBean bean;
    private AcountRecordActLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_accountrecord;
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
//            bean.setUserName("20XX/XX/XX");
//            bean.setTouZiZongE("000,000.00");
//            bean.setHuanKuanZongE("结算成功");
//
//            if (i%2==1){
//                bean.setBacFlag(2);
//            }
//            list.add(bean);
//        }
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActAccountRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActAccountRecord.setLayoutParams(params);

        srlActAccountRecord.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActAccountRecord.setRefreshHeader(new TuDouHeader(this));
//        srlActAccountRecord.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActAccountRecord.setRefreshFooter(new BallPulseFooter(this));


    }

    @Override
    public void initListener() {
        mtbActAccountRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


        srlActAccountRecord.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initDataFromInternet();
            }
        });


        srlActAccountRecord.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(AccountRecordActivity.this, R.string.meiyougengduola);
                    srlActAccountRecord.finishLoadmore();
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
        Log.e("TAG", "initDataFromInternet:我的推广--推广记录--接口返回数据 -----" + page);
        Log.e("TAG", "initDataFromInternet:我的推广--推广记录--接口返回数据 -----" + UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(this, Constants.SETTLEMENT_RECORD, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSrl();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广--结算记录--接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<AccountRecordBean>() {
                        }.getType(), AccountRecordBean.class, AccountRecordActivity.this);
                        if (bean1 != null) {
                            bean = (AccountRecordBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(AccountRecordActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(AccountRecordActivity.this, R.string.huoquwodetuiguangxinxishibai);

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0) {
            list.addAll(bean.getItems());
//        SucceedInvitationActLvAdapter adapter=new SucceedInvitationActLvAdapter(this,list);
//        lvActAccountRecord.setAdapter(adapter);
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 == 1) {
                    list.get(i).setBacFlag(1);
                } else {
                    list.get(i).setBacFlag(2);
                }
            }

            if (adapter == null) {
                adapter = new AcountRecordActLvAdapter(this, list);
                lvActAccountRecord.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void finishSrl() {
        if (srlActAccountRecord != null) {

            if (srlActAccountRecord.isRefreshing()) {
                srlActAccountRecord.finishRefresh();
            } else if (srlActAccountRecord.isLoading()) {
                srlActAccountRecord.finishLoadmore();
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
}
