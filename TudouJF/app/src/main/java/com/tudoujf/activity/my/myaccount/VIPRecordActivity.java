package com.tudoujf.activity.my.myaccount;

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
import com.tudoujf.adapter.VIPRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.VIPRecordBean;
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
 * * ===============================================================
 * name:             VIPRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/23
 * description：  VIP申请记录activity
 * history：
 * *==================================================================
 */

public class VIPRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_viprecord)
    MTopBarView mtbActViprecord;
    @BindView(R.id.lv_act_viprecord)
    ListView lvActViprecord;
    @BindView(R.id.srl_act_viprecord)
    SmartRefreshLayout srlActViprecord;


    private int page = 1;
    private List<VIPRecordBean.ItemsBean> list;
    private VIPRecordBean bean;
    private VIPRecordActLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_viprecord;
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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActViprecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActViprecord.setLayoutParams(params);

        srlActViprecord.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActViprecord.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActViprecord.setRefreshFooter(new BallPulseFooter(this));
        srlActViprecord.setEnableLoadmoreWhenContentNotFull(true);
    }

    @Override
    public void initListener() {
        mtbActViprecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        srlActViprecord.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initDataFromInternet();
            }
        });


        srlActViprecord.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages()) {
                    page = page + 1;
                    initDataFromInternet();
                } else {
                    ToastUtils.showToast(VIPRecordActivity.this, R.string.meiyougengduola);
                    srlActViprecord.finishLoadmore();
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

        Log.e("TAG", "initDataFromInternet:VIP申请记录 -----" + page);
        Log.e("TAG", "initDataFromInternet:VIP申请记录 -----" + UserConfig.getInstance().getLoginToken(this));


        HttpMethods.getInstance().POST(this, Constants.VIP_LOG, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishSrl();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----VIP申请记录--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<VIPRecordBean>() {
                        }.getType(), VIPRecordBean.class, VIPRecordActivity.this);
                        if (bean1 != null) {
                            bean = (VIPRecordBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(VIPRecordActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(VIPRecordActivity.this, R.string.huoquwodetuiguangxinxishibai);

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
                adapter = new VIPRecordActLvAdapter(this, list);
                lvActViprecord.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void finishSrl() {
        if (srlActViprecord.isRefreshing()) {
            srlActViprecord.finishRefresh();
        } else if (srlActViprecord.isLoading()) {
            srlActViprecord.finishLoadmore();
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
