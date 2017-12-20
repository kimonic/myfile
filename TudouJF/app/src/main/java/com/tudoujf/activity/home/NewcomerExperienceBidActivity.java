package com.tudoujf.activity.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.adapter.NewcomerExperienceBidActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.ExperienceListBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.bean.databean.NewcomerExperienceBidBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.InfoView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

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

    List<NewcomerExperienceBidBean.TenderListBean> list;
    private String loan_id;
    private NewcomerExperienceBidActLvAdapter adapter;
    private String loginToken;
    private boolean isLogin;
    private IdentityCheckBean identityCheckBean;
    private boolean isLogin1;

    private int page = 1;
    private ExperienceListBean beanE;
    private AlertDialog promptDialog1;

    @Override
    public int getLayoutResId() {
        return R.layout.act_newcomerexperiencebid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_newcomerexperiencebid_bidnow://立即投标
                checkLogin();

//                if ("".equals(UserConfig.getInstance().getLogin(this))){
//                openActivity(LoginActivity.class);
//                }else if (){//判断是否实名
//
//                }else {
//                    openActivity(MyExperienceGoldActivity.class);
//                }
                break;
            case R.id.tv_act_newcomerexperiencebid_loadmore://点击加载更多
//                page++;
//                initDataFromInternet();
                loadMore();
                break;
        }

    }

    /**
     * 加载更多
     */
    private void loadMore() {

        if (beanE != null && !(page < beanE.getTotal_pages())) {

            ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.meiyougengduola);

        } else {

            page++;
            showPDialog();
            TreeMap<String, String> map = new TreeMap<>();
            map.put("loan_id", loan_id);
            map.put("page", "" + page);

            Log.e("TAG", "initDataFromInternet: -体验标的loan_id----" + loan_id);


            HttpMethods.getInstance().POST(this, Constants.EXPERIENCE_TENDERLIST, map, getLocalClassName(),
                    new StringCallback() {
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            dismissPDialog();
                            ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.shujujiazaichucuo);

                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            dismissPDialog();
                            String result = StringUtils.getDecodeString(response.body());
                            Log.e("TAG", "onSuccess:----体验标投资详情列表接口返回数据------------------- " + result);
                            BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<ExperienceListBean>() {
                            }.getType(), ExperienceListBean.class, NewcomerExperienceBidActivity.this);
                            if (bean1 != null) {
                                beanE = (ExperienceListBean) bean1;
                                LoadData();
                            } else {
                                ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.shujujiazaichucuo);
                            }

                        }
                    });
        }


    }

    private void LoadData() {
        if (bean != null) {

            list.addAll(beanE.getItems());
            if (adapter == null) {
                adapter = new NewcomerExperienceBidActLvAdapter(this, list);
                lvNewcomerExperienceBid.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void initDataFromIntent() {
        loan_id = getIntent().getStringExtra("loan_id");
        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ProductDetailsActBean bean = new ProductDetailsActBean();
//
//            bean.setTouZiTime("20XX-XX-XX 00:00:00");
//            bean.setTouBiaoRen("XXXXX");
//            bean.setTouZiJinE("28888");
//            list.add(bean);
//        }

    }

    @Override
    public void initView() {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbNewcomerExperienceBid.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbNewcomerExperienceBid.setLayoutParams(params);
        mtbNewcomerExperienceBid.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

    }

    @Override
    public void initListener() {
        tvBidNow.setOnClickListener(this);
        tvLoadMore.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "" + page);

        Log.e("TAG", "initDataFromInternet: -----" + loan_id);
        Log.e("TAG", "initDataFromInternet: --page---" + page);

        map.put("loan_id", loan_id);

        HttpMethods.getInstance().POST(this, Constants.MY_EXPERIENCE_LOAN_INFO, map, "NewcomerExperienceBidActivity",
                new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.shujujiazaichucuo);

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----体验标详情接口返回数据------------------- " + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<NewcomerExperienceBidBean>() {
                        }.getType(), NewcomerExperienceBidBean.class, NewcomerExperienceBidActivity.this);
                        if (bean1 != null) {
                            bean = (NewcomerExperienceBidBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.shujujiazaichucuo);
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            Log.e("TAG", "LoadInternetDataToUi: -----" + bean.getAttaList());

            tvHuanKuanFangShi.setText(bean.getRepay_type().getContents());
            tvTouZiRenShu.setText(bean.getLoan_info().getTender_count());
            tvYiTouJinE.setText(bean.getLoan_info().getCredited_amount());
            infoView.setJieKuanQiXian((bean.getLoan_info().getPeriod() + "天"));
            infoView.setIfNew(true);
            infoView.setDrawImage(false);
            infoView.setCloseBottomLine(true);
            infoView.setNianHuaShouYi((bean.getLoan_info().getApr() + "%"));
            infoView.setUnderlineScale1(StringUtils.string2Float(bean.getLoan_info().getProgress()) / 100f);
            infoView.invalidate();


            list.addAll(bean.getTender_list());
            if (adapter == null) {
                adapter = new NewcomerExperienceBidActLvAdapter(this, list);
                lvNewcomerExperienceBid.setAdapter(adapter);
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


    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------


    private void checkLogin() {
        loginToken = UserConfig.getInstance().getLoginToken(this);
        if ("".equals(loginToken)) {
            isLogin = false;
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("type", 888);
            startActivityForResult(intent, 888);
        } else {
            isLogin = true;
            checkIdentity();
        }

    }

    private void checkIdentity() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);
        HttpMethods.getInstance().POST(this, Constants.IDENTITY_CHECK, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------请求身份是否实名返回的json数据----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IdentityCheckBean>() {
                }.getType(), IdentityCheckBean.class, NewcomerExperienceBidActivity.this);
                if (bean1 != null) {
                    identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        touZi();//确认投资
                    } else {//未实名
                        if (promptDialog1==null){
                            promptDialog1=DialogUtils.showPromptDialog(NewcomerExperienceBidActivity.this, "提示", "您还没有实名，请先实名!", new DialogUtils.DialogUtilsClickListener() {
                                @Override
                                public void onClick() {
                                    promptDialog1.dismiss();
                                    openActivity(RealNameAuthenticationHuiFuActivity.class);
                                }
                            });
                        }else {
                            promptDialog1.show();
                        }
//                        DialogUtils.showDialog(NewcomerExperienceBidActivity.this, R.string.xitongjiancedaoninweishiming, R.string.queding, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                openActivity(RealNameAuthenticationHuiFuActivity.class);//打开实名注册
//                            }
//                        });
                    }
                } else {
                    ToastUtils.showToast(NewcomerExperienceBidActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.yanzhengshimingxinxishibai);
            }
        });

    }

    /**
     * 投资
     */
    private void touZi() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);
        map.put("loan_id", loan_id);

        HttpMethods.getInstance().POST(this, Constants.EXPERIENCE_INVEST, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: -----------确认投资返回的json数据----------------" + result);
                if (result != null && result.contains("200")) {
                    openActivity(MyExperienceGoldActivity.class);//打开我体验金页面
                } else {
                    ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.tiyanjintouzishiabi);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(NewcomerExperienceBidActivity.this, R.string.yanzhengshimingxinxishibai);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888 && resultCode == 888) {
            isLogin1 = true;
            checkLogin();
        }
    }

    //-----------------------------检测用户是否登陆与身份是否已实名-------------------------------------


}
