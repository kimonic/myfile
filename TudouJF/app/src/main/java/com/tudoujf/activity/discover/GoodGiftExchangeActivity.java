package com.tudoujf.activity.discover;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.VerificationCodeView;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             GoodGiftExchangeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/25
 * description：  豪礼兑换activity
 * history：
 * *==================================================================
 */
public class GoodGiftExchangeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_goodgiftexcahnge)
    MTopBarView mtb;
    @BindView(R.id.et_act_goodgiftexchange_duihuanma)
    EditText etDuiHuanMa;
    @BindView(R.id.et_act_goodgiftexchange_yanzhengma)
    EditText etYanZhengMa;
    @BindView(R.id.vcv_act_goodgiftexchange_yanzhengma)
    VerificationCodeView vcvYanZhengMa;
    @BindView(R.id.tv_act_goodgiftexchange_duihuan)
    TextView tvDuiHuan;
    private String redeem_code="";

    @Override
    public int getLayoutResId() {
        return R.layout.act_goodgiftexchange;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_goodgiftexchange_duihuan:
                redeem_code=etDuiHuanMa.getText().toString().trim();
                if (etYanZhengMa.getText().toString().equals(vcvYanZhengMa.getShowCode())) {
                    if (!"".equals(redeem_code)){

                        vcvYanZhengMa.setShowCode();
                        vcvYanZhengMa.invalidate();
                        exchange();

                    }else {
                        ToastUtils.showToast(GoodGiftExchangeActivity.this, getString(R.string.qingshuruduihuanma));
                    }

                } else {

                    ToastUtils.showToast(GoodGiftExchangeActivity.this, getString(R.string.qingshuruzhengquedetuxingyanzhengma));

                }
                break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
//                 case R.id.:break;
        }

    }

    @Override
    public void initDataFromIntent() {
//Verification Code
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        tvDuiHuan.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {//REDEEM_CODE


    }

    @Override
    public void LoadInternetDataToUi() {

    }

    /**
     * 兑换
     */
    private void exchange(){
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("redeem_code", redeem_code);

        HttpMethods.getInstance().POST(this, Constants.REDEEM_CODE, map, "",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
//                        finishRL();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(GoodGiftExchangeActivity.class,"logflag-好礼兑换接口返回数据--"+result);

                        Gson gson=new Gson();
                        CommonBean bean=gson.fromJson(result,CommonBean.class);
                        if (bean!=null&&"200".equals(bean.getCode())){
                            ToastUtils.showToast(GoodGiftExchangeActivity.this, R.string.duihuanchenggong);
                        }else if (bean!=null){
                            if (bean.getDescription()!=null){
                                ToastUtils.showToast(GoodGiftExchangeActivity.this, bean.getDescription().toString());
                            }else {
                                ToastUtils.showToast(GoodGiftExchangeActivity.this, R.string.duihuanshibai);
                            }
                        }else {
                            ToastUtils.showToast(GoodGiftExchangeActivity.this, R.string.duihuanshibai);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
//                        finishRL();
                        ToastUtils.showToast(GoodGiftExchangeActivity.this, R.string.shujujiazaichucuo);
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
