package com.tudoujf.activity.my.mypopularize;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.MyPopularizeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyPopularizeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/4
 * description：  我的推广页面activity
 * history：
 * ===================================================
 */

public class MyPopularizeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_mypopularize)
    MTopBarView mtbActMyPopularize;
    @BindView(R.id.ll_act_mypopularize_succeedinvitation)
    LinearLayout llSucceedInvitation;
    @BindView(R.id.ll_act_mypopularize_recommendrecord)
    LinearLayout llRecommendRecord;
    @BindView(R.id.ll_act_mypopularize_accountrecord)
    LinearLayout llAccountRecord;
    @BindView(R.id.ll_act_mypopularize_recommendaward)
    LinearLayout llRecommendAward;
    @BindView(R.id.tv_act_mypopularize_ti_cheng_zong_e)
    TextView tvTiChengZongE;
    @BindView(R.id.tv_act_mypopularize_tou_zi_ti_cheng)
    TextView tvTouZiTiCheng;
    @BindView(R.id.tv_act_mypopularize_jie_huan_kuan_ti_cheng)
    TextView tvJieHuanKuanTiCheng;
    @BindView(R.id.tv_act_mypopularize_cheng_gong_yao_qing)
    TextView tvChengGongYaoQing;
    @BindView(R.id.tv_act_mypopularize_man_jie_suan)
    TextView tvManJieSuan;
    @BindView(R.id.tv_act_mypopularize_sheng_yu_jie_suan_jin_e)
    TextView tvShengYuJieSuanJinE;
    @BindView(R.id.tv_act_mypopularize_jie_suan_zhong)
    TextView tvJieSuanZhong;
    @BindView(R.id.tv_act_mypopularize_btn_jie_suan)
    TextView tvBtnJieSuan;
    private MyPopularizeBean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_mypopularize;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_mypopularize_accountrecord://结算记录
                openActivity(AccountRecordActivity.class);
                break;
            case R.id.ll_act_mypopularize_recommendaward://推广奖励说明
                openActivity(InvitationAwardExplainActivity.class);
                break;
            case R.id.ll_act_mypopularize_recommendrecord://推广记录
                openActivity(SucceedInvitationRecommendRecordActivity.class);
                break;
            case R.id.ll_act_mypopularize_succeedinvitation://成功邀请
                openActivity(SucceedInvitationActivity.class);
                break;
            case R.id.tv_act_mypopularize_btn_jie_suan://立即结算
                settleNow();
                break;
        }

    }

    /**
     * 立即结算
     */
    private void settleNow() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.SETTLEMENT_NOW, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广首页--立即结算--接口返回数据--------" + result);

                        if (result!=null&&result.contains("200")){
                            ToastUtils.showToast(MyPopularizeActivity.this, R.string.jiesuanchenggong);
                            openActivity(AccountRecordActivity.class);
                        }else {
                            ToastUtils.showToast(MyPopularizeActivity.this, R.string.jiesuanshibai);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyPopularizeActivity.this, R.string.jisuanshibai);
                    }
                });

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActMyPopularize.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActMyPopularize.setLayoutParams(params);

        tvBtnJieSuan.setBackgroundResource(R.drawable.xshape_roundrect_mgray1);
        tvBtnJieSuan.setClickable(false);

    }

    @Override
    public void initListener() {
        mtbActMyPopularize.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        mtbActMyPopularize.getRightTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyPopularizeActivity.this,NowRecommendActivity.class);
                intent.putExtra("share_url",bean.getShare_url());
                startActivity(intent);


//                openActivity(NowRecommendActivity.class);
            }
        });
        llSucceedInvitation.setOnClickListener(this);
        llAccountRecord.setOnClickListener(this);
        llRecommendAward.setOnClickListener(this);
        llRecommendRecord.setOnClickListener(this);
        tvBtnJieSuan.setOnClickListener(this);



    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.MY_POPULARIZE, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广首页接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyPopularizeBean>() {
                        }.getType(), MyPopularizeBean.class, MyPopularizeActivity.this);
                        if (bean1 != null) {
                            bean = (MyPopularizeBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(MyPopularizeActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyPopularizeActivity.this, R.string.huoquwodetuiguangxinxishibai);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            tvTiChengZongE.setText(bean.getIncome());
            tvTouZiTiCheng.setText(bean.getTender_income());
            tvJieHuanKuanTiCheng.setText(bean.getRepay_income());
            tvShengYuJieSuanJinE.setText(bean.getTotal().getUnAaccount());
            tvJieSuanZhong.setText(bean.getTotal().getAccounting());
            tvManJieSuan.setText((bean.getLimit() + "元"));
            tvChengGongYaoQing.setText(("成功邀请好友" + bean.getCountInfo().getPerson_count() + "位"));

            if ("Y".equals(bean.getTotal().getTodayAccounted()) &&
                    StringUtils.string2Float(bean.getTotal().getUnAaccount()) > StringUtils.string2Float(bean.getLimit())) {
                tvBtnJieSuan.setBackgroundResource(R.drawable.xshape_roundrect_themecolor);
                tvBtnJieSuan.setClickable(true);
            } else {
                tvBtnJieSuan.setBackgroundResource(R.drawable.xshape_roundrect_mgray1);
                tvBtnJieSuan.setClickable(false);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initDataFromInternet();
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
