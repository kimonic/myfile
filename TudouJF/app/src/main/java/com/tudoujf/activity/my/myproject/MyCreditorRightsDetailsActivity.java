package com.tudoujf.activity.my.myproject;

import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.adapter.MyCreditorDetailsActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.TransferableDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
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
 * name:            MyCreditorRightsDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目-->我的债权详情activity
 * history：
 * * ====================================================================
 */

public class MyCreditorRightsDetailsActivity extends BaseActivity {


    @BindView(R.id.mtb_act_mycreditorrightsdetails)
    MTopBarView mtb;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_buynow)
    TextView tvBuyNow;
    @BindView(R.id.ll_act_mycreditorrightsdetails_buy_details)
    LinearLayout llBuyDetails;
    @BindView(R.id.ll_act_mycreditorrightsdetails_transfer_details)
    LinearLayout llTransferDetails;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_bid_name)
    TextView tvBidName;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_creditors_value)
    TextView tvCreditorsValue;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_return_date)
    TextView tvReturnDate;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_periods)
    TextView tvPeriods;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_transfer_scale)
    EditText etTransferScale;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_transfer_price)
    TextView tvTransferPrice;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_predict_earings)
    TextView tvPredictEarings;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_bid_name_buy)
    TextView tvBidNameBuy;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_creditor_value_buy)
    TextView tvCreditorValueBuy;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_expire_date_buy)
    TextView tvExpireDateBuy;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_periods_buy)
    TextView tvPeriodsBuy;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_transfer_value_buy)
    TextView tvTransferValueBuy;
    @BindView(R.id.tv_act_mycreditorsrightsdetails_earings_buy)
    TextView tvEaringsBuy;
    @BindView(R.id.tv_zhaiquanzhuanrangxieyi)
    TextView tvXieYi;
//    @BindView(R.id.tv_act_mycreditorsrightsdetails_zhuangtai)
//    TextView tvTransferStatus;
    @BindView(R.id.lv_act_mycreditorsrightsdetails_buy)
    ListView lvBuy;


    private String url;
    private String tender_id;
    private int type;
    private TransferableDetailsBean bean;
    private List<TransferableDetailsBean.RecoverBean.ItemsBean> list;
    private boolean canCansel = false;

    @Override
    public int getLayoutResId() {
        return R.layout.act_mycreditorrightsdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_mycreditorsrightsdetails_buynow:
                if (canCansel) {
                    cancelCreditor();
                } else {
                    transferNow();
                }
                break;
            case R.id.tv_zhaiquanzhuanrangxieyi://债权转让协议
                Intent intent = new Intent(this, WebActivity.class);
                Log.e("TAG", "onClick:tender_idtender_idtender_id -----" + tender_id);
                intent.putExtra("url", Constants.ZHAI_QUAN_XIE_YI + tender_id);
                intent.putExtra("title", getResources().getString(R.string.zhaiquanzhuanrangxieyi));
                startActivity(intent);
                break;
        }

    }

    private void cancelCreditor() {
        DialogUtils.showCreditorCancel(this, bean.getCancel_count(), new DialogUtils.DialogUtilsClickListener() {
            @Override
            public void onClick() {
                requestCancel();
            }
        });
    }

    /**债权转让撤销*/
    private void requestCancel() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("transfer_id", bean.getTransfer_id());

        HttpMethods.getInstance().POST(this, Constants.CANCEL_TRANSFER, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----请求债权转让撤销接口返回数据------------" + result);
                        Gson gson = new Gson();
                        CommonBean bean1 = gson.fromJson(result, CommonBean.class);
                        if (bean1 != null && "200".equals(bean1.getCode())) {
                            ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.zhaiquanchexiaochenggong);
                            setResult(111);
                            closeActivity();//返回上一页并刷新
                        } else {
                            if (bean1 != null && bean1.getDescription() != null) {
                                ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, bean1.getDescription().toString());
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.zhaiquanzhuanrangchexiaoshibai);
                    }
                });
    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

        type = getIntent().getIntExtra("type", 1);
        tender_id = getIntent().getStringExtra("tender_id");

        if (type == 1) {
            url = Constants.CREDITER_BUY_DETAILS;//1,债权购买详情
            tvBuyNow.setVisibility(View.GONE);
            llTransferDetails.setVisibility(View.GONE);
            llBuyDetails.setVisibility(View.VISIBLE);
        } else {
            url = Constants.CREDITER_TRANSFER_DETAILS;//2.债权转让记录
        }

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
        tvBuyNow.setOnClickListener(this);
        tvXieYi.setOnClickListener(this);
        etTransferScale.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {

                if (bean != null) {
                    String temp = "" + StringUtils.string2Float(editable.toString()) * StringUtils.string2Float(bean.getAmount_money()) / 100;
                    String temp1 = "" + StringUtils.string2Float(bean.getTransfer_fee().split(",")[0]) * StringUtils.string2Float(editable.toString()) * StringUtils.string2Float(bean.getAmount_money()) / 100;
                    tvTransferPrice.setText(temp);
                    tvPredictEarings.setText(StringUtils.getTwoDecimalsStrUD(temp1));

                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        if (type == 1) {
            map.put("transfer_id", tender_id);
        } else {
            map.put("tender_id", tender_id);
        }

        Log.e("TAG", "我的债权项目详情接口返回数据initDataFromInternet: -login_token----" + UserConfig.getInstance().getLoginToken(this));
        Log.e("TAG", "我的债权项目详情接口返回数据initDataFromInternet: -tender_id----" + tender_id);
        Log.e("TAG", "我的债权项目详情接口返回数据initDataFromInternet: -url----" + url);


        HttpMethods.getInstance().POST(this, url, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的债权项目详情接口返回数据--------" + type + "----" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<TransferableDetailsBean>() {
                        }.getType(), TransferableDetailsBean.class, MyCreditorRightsDetailsActivity.this);
                        if (bean1 != null) {
                            bean = (TransferableDetailsBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.shujujiazaichucuo);
                        }
                    }


                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.huoquzhaiquanxiangqignshujushibai);
                    }
                });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {

            if (type == 1) {
                tvBidNameBuy.setText(bean.getLoan_name());
                tvCreditorValueBuy.setText(bean.getAmount_money());
                tvCreditorValueBuy.setText(bean.getAmount_money());

                tvExpireDateBuy.setText(bean.getExpire_time());
                tvPeriodsBuy.setText((bean.getPeriod() + "期/共" + bean.getTotal_period() + "期"));

                tvTransferValueBuy.setText(bean.getAmount());
                tvEaringsBuy.setText(bean.getIncome());


                if (bean.getRecover().getItems() != null && bean.getRecover().getItems().size() > 0) {
                    list.addAll(bean.getRecover().getItems());
                    MyCreditorDetailsActLvAdapter adapter = new MyCreditorDetailsActLvAdapter(this, list);
                    lvBuy.setAdapter(adapter);
                    HeightUtils.setListviewHeight(lvBuy);
                }


            } else {

                Log.e("TAG", "LoadInternetDataToUi: ---类型2--");

                tvBidName.setText(bean.getLoan_name());//标的名称
                tvCreditorsValue.setText(bean.getAmount_money());//债权价值
                tvReturnDate.setText(bean.getRecover_time());//还款期限
                tvPeriods.setText((bean.getPeriod() + "期/共" + bean.getTotal_period() + "期"));//转让/总期数

                //转让系数
                etTransferScale.setHint(("请输入转让系数:" + bean.getTransfer_coefficient_min() + "-" + bean.getTransfer_coefficient_max()));


//                // TODO: 2017/12/22  转让状态调整,待核实
//                if (bean.getCoefficient() != null) {//转让中状态
//                    etTransferScale.setText(bean.getCoefficient());//转让中状态系数不变为固定值
//                    etTransferScale.setKeyListener(null);
////                    tvBuyNow.setVisibility(View.GONE);
////                    tvBuyNow.setText("转让中");
////                    tvBuyNow.setClickable(false);
////                    tvBuyNow.setBackgroundColor(getResources().getColor(R.color.color_gray3));
//                }

                if ("1".equals(bean.getTransfer_status())&&StringUtils.string2Integer(bean.getCancel_count())<3) {
//                    tvTransferStatus.setText("转让中");
                    if (StringUtils.string2Integer(bean.getCancel_count()) < 3) {
                        etTransferScale.setText(bean.getCoefficient());//转让中状态系数不变为固定值
                        etTransferScale.setKeyListener(null);
                        tvBuyNow.setText("撤销");
                        tvBuyNow.setBackgroundColor(getResources().getColor(R.color.global_theme_background_color));
                        canCansel=true;
                        Log.e("TAG", "LoadInternetDataToUi: -撤销-11111---"+bean.getService_charge());
                        tvPredictEarings.setText(bean.getService_charge());//转让系数不可改变时的手续费
                    }
                } else if ("-1".equals(bean.getTransfer_status())&&StringUtils.string2Integer(bean.getCancel_count())<3) {
                    Log.e("TAG", "LoadInternetDataToUi: -已撤销-2222---"+bean.getService_charge());
                    tvPredictEarings.setText(bean.getService_charge());//转让系数不可改变时的手续费
                    tvBuyNow.setText("立即转让");
                    tvBuyNow.setClickable(true);
                    tvBuyNow.setBackgroundColor(getResources().getColor(R.color.global_theme_background_color));
                    canCansel=false;
//                    tvTransferStatus.setText("已撤销");
                } else if ("2".equals(bean.getTransfer_status())){
                    etTransferScale.setText(bean.getCoefficient());//转让中状态系数不变为固定值
                    etTransferScale.setKeyListener(null);
                    Log.e("TAG", "LoadInternetDataToUi: -转让成功-3333---"+bean.getService_charge());
//                    tvTransferStatus.setText("转让成功");
                    tvBuyNow.setText("转让成功");
                    tvBuyNow.setClickable(false);
                    tvBuyNow.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    canCansel=false;
                }
            }


        }

    }


    private void transferNow() {
        String scale = etTransferScale.getText().toString();
        float scaleInt = StringUtils.string2Float(scale);
        float max = StringUtils.string2Float(bean.getTransfer_coefficient_max());
        float min = StringUtils.string2Float(bean.getTransfer_coefficient_min());
        if (scaleInt > max || scaleInt < min) {
            ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, ("转让系数只能是" + min + "到" + max + "之间的数字"));
        } else {
            requestTransfer(scaleInt);
        }
    }

    private void requestTransfer(float scale) {
        showPDialog();
        int temp = (int) scale;
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("tender_id", bean.getTender_id());
        map.put("coefficient", "" + temp);

        HttpMethods.getInstance().POST(this, Constants.IMMEDIATE_TRANSFER, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----请求债权转让接口返回数据------------" + result);
                        Gson gson = new Gson();
                        CommonBean bean1 = gson.fromJson(result, CommonBean.class);
                        if (bean1 != null && "200".equals(bean1.getCode())) {
                            ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.zhaiquanzhuanrangchenggong);
                            setResult(111);
                            closeActivity();//返回上一页
                        } else {
                            if (bean1 != null && bean1.getDescription() != null) {
                                ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, bean1.getDescription().toString());
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(MyCreditorRightsDetailsActivity.this, R.string.zhaiquanzhuanrangshibai);
                    }
                });
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
