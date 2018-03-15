package com.tudoujf.activity.my.myproject;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.managemoney.AffirmBuyCreditorsRightsActivity;
import com.tudoujf.adapter.MyInvestDetailsActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.MyInvestDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyInvestDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目-->我的投资详情activity
 * history：
 * * ====================================================================
 */

public class MyInvestDetailsActivity extends BaseActivity {


    @BindView(R.id.mtb_act_myinvestdetails)
    MTopBarView mtb;
    @BindView(R.id.tv_act_myinvestdetails_bid_name)
    TextView tvBidName;
    @BindView(R.id.iv_act_myinvestdetails)
    InfoView infoView;
    @BindView(R.id.tv_act_myinvestdetails_invest_amount)
    TextView tvInvestAmount;
    @BindView(R.id.tv_act_myinvestdetails_total_earnings)
    TextView tvTotalEarnings;
    @BindView(R.id.tv_act_myinvestdetails_old_earnings)
    TextView tvOldEarnings;
    @BindView(R.id.tv_act_myinvestdetails_new_capital)
    TextView tvNewCapital;
    @BindView(R.id.tv_act_myinvestdetails_award_amount)
    TextView tvAwardAmount;
    @BindView(R.id.ll_act_myinvestdetails_see_protocol)
    LinearLayout llSeeProtocol;
    @BindView(R.id.lv_act_myinvestdetails)
    ListView lv;

    private String id;
    private MyInvestDetailsBean bean;
    private List<MyInvestDetailsBean.RecoverInfoBean> list;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myinvestdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_creditorsrightsdetails_buynow:
                openActivity(AffirmBuyCreditorsRightsActivity.class);
                break;
            case R.id.ll_act_myinvestdetails_see_protocol:
//                Intent intent = new Intent(this, WebActivity.class);
//                intent.putExtra("url", Constants.LOAN_AGREEMENT+"?id="+bean.getLoan_info().getId()
//                +"&tenderId="+id);
//                intent.putExtra("title","土豆金服服务协议");
//                startActivity(intent);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        id = getIntent().getStringExtra("id");
        list = new ArrayList<>();

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {
//        llSeeProtocol.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("id", id);
        Log.e("TAG", "initDataFromInternet: 我的投资详情id-----"+id);


        HttpMethods.getInstance().POST(this, Constants.MY_INVESTMENT_DETAIL, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        dismissPDialog();

                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的投资项目详情接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyInvestDetailsBean>() {
                        }.getType(), MyInvestDetailsBean.class, MyInvestDetailsActivity.this);
                        if (bean1 != null) {
                            bean = (MyInvestDetailsBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(MyInvestDetailsActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                     @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyInvestDetailsActivity.this, R.string.huoquwodexiangmuxiangqingshibai);

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            tvBidName.setText(bean.getLoan_info().getName());
            tvInvestAmount.setText(bean.getTender_info().getAmount());

            tvTotalEarnings.setText(bean.getTender_info().getRecover_income_all());
            tvOldEarnings.setText(bean.getTender_info().getRecover_income());


            tvNewCapital.setText(bean.getTender_info().getWait_principal());
            tvAwardAmount.setText(bean.getTender_info().getAward_amount());
            infoView.setJieKuanQiXian(bean.getLoan_info().getPeriod_name());
            infoView.setNianHuaShouYi(StringUtils.getTwoDecimalsStr(bean.getLoan_info().getApr()) + "%");

            infoView.setUnderlineScale1(0.5f * 100);
            infoView.setIfNew(false);
            infoView.setCloseBottomLine(true);

//            infoView.setUnderlineScale1(StringUtils.string2Float(bean.getLoan_info().getProgress()));
//            infoView.setIfNew("1".equals(bean.getLoan_info().getAdditional_status()));


            infoView.invalidate();

            if (bean.getRecover_info().size() > 0) {
                list.addAll(bean.getRecover_info());
                MyInvestDetailsActLvAdapter adapter = new MyInvestDetailsActLvAdapter(this, list);
                lv.setAdapter(adapter);
                HeightUtils.setListviewHeight(lv);
            }


        }

    }

    /**
     *
     * @return
     */
    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
