package com.tudoujf.activity.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.NewcomerExperienceBidActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.ProductDetailsActBean;
import com.tudoujf.bean.databean.NewcomerExperienceBidBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            NewcomerExperienceBidActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/1
 * description：   新手体验标activity
 * history：
 * ===================================================
 */

public class NewcomerExperienceBidActivity extends BaseActivity {
    @BindView(R.id.mtb_act_newcomerexperiencebid)
    MTopBarView mtbNewcomerExperienceBid;
    @BindView(R.id.lv_act_newcomerexperiencebid)
    ListView lvNewcomerExperienceBid;
    @BindView(R.id.iv_act_newcomerexperiencebid)
    InfoView infoView;
    @BindView(R.id.tv_act_newcomerexperiencebid_yitoujine)
    TextView tvYiTouJinE;
    @BindView(R.id.tv_act_newcomerexperiencebid_touzirenshu)
    TextView tvTouZiRenShu;
    @BindView(R.id.tv_act_newcomerexperiencebid_huankuanfangshi)
    TextView tvHuanKuanFangShi;
    @BindView(R.id.tv_act_newcomerexperiencebid_loadmore)
    TextView tvLoadMore;
    @BindView(R.id.tv_act_newcomerexperiencebid_bidnow)
    TextView tvBidNow;

    private NewcomerExperienceBidBean bean;

    List<ProductDetailsActBean> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_newcomerexperiencebid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_newcomerexperiencebid_bidnow://立即投标
                break;
            case R.id.tv_act_newcomerexperiencebid_loadmore://点击加载更多
                break;
        }

    }

    @Override
    public void initDataFromIntent() {


        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductDetailsActBean bean = new ProductDetailsActBean();

            bean.setTouZiTime("20XX-XX-XX 00:00:00");
            bean.setTouBiaoRen("XXXXX");
            bean.setTouZiJinE("28888");
            list.add(bean);
        }

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbNewcomerExperienceBid.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbNewcomerExperienceBid.setLayoutParams(params);

        mtbNewcomerExperienceBid.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


        NewcomerExperienceBidActLvAdapter adapter = new NewcomerExperienceBidActLvAdapter(this, list);
        lvNewcomerExperienceBid.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        tvBidNow.setOnClickListener(this);
        tvLoadMore.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", "12267");
        map.put("page", "1");
        map.put("loan_id", "153");

        HttpMethods.getInstance().POST(this, Constants.MY_EXPERIENCE_LOAN_INFO, map, "NewcomerExperienceBidActivity",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----体验标详情接口返回数据------------------- " + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<NewcomerExperienceBidBean>() {
                        }.getType(), NewcomerExperienceBidBean.class, NewcomerExperienceBidActivity.this);
                        if (bean1 != null) {
                            bean = (NewcomerExperienceBidBean) bean1;
                            LoadInternetDataToUi();
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            tvHuanKuanFangShi.setText(bean.getRepay_type().getContents());
            tvYiTouJinE.setText(bean.getMember_loan_info().getLoan_success_amount());
            tvTouZiRenShu.setText(bean.getMember_loan_info().getLoan_success_count());
            infoView.setJieKuanQiXian((bean.getLoan_info().getPeriod()+"个月"));
            infoView.setIfNew(true);
            infoView.setNianHuaShouYi((bean.getLoan_info().getApr()+"%"));
            infoView.setUnderlineScale1(StringUtils.string2Float(bean.getLoan_info().getProgress())/100f);
            infoView.invalidate();
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
