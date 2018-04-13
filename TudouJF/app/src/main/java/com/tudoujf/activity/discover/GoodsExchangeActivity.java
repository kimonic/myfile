package com.tudoujf.activity.discover;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             GoodsExchangeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/9
 * description：  积分商城商品确认兑换activity
 * history：
 * *==================================================================
 */
public class GoodsExchangeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_goodsexchange)
    MTopBarView mtb;
    @BindView(R.id.et_act_goodsexchange_lianxiren)
    EditText etLianxiren;
    @BindView(R.id.et_act_goodsexchange_phone)
    EditText etPhone;
    @BindView(R.id.et_act_goodsexchange_adress)
    EditText etAdress;
    @BindView(R.id.iv_act_goodsexchange)
    ImageView iv;
    @BindView(R.id.tv_act_goodsexchange_number)
    TextView tvNumber;
    @BindView(R.id.tv_act_goodsexchange_needintegral)
    TextView tvNeedIntegral;
    @BindView(R.id.tv_act_goodsexchange_myintegral)
    TextView tvMyIntegral;
    @BindView(R.id.tv_act_goodsexchange_affirmexchange)
    TextView tvAffirmExchange;

    private String number;
    private String imagesUrl;
    private String needIntegral;
    private String myIntegral;
    private String lipinhao;

    @Override
    public int getLayoutResId() {
        return R.layout.act_goodsexchange;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_goodsexchange_affirmexchange:
                if (check()) {
                    affirm();
                }
                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }
    }

    /**检测输入内容是否符合规则*/
    private boolean check() {
        if ("".equals(etLianxiren.getText().toString())) {
            ToastUtils.showToast(GoodsExchangeActivity.this, R.string.lianxirenxingmingbunengweikong);

            return false;
        } else if (etPhone.getText().toString().trim().length() != 11) {
            ToastUtils.showToast(GoodsExchangeActivity.this, R.string.qingtianxiezhengquedeshoujihaoma);
            return false;
        } else if ("".equals(etAdress.getText().toString())) {
            ToastUtils.showToast(GoodsExchangeActivity.this, R.string.xiangxidizhibunengweikong);
            return false;
        }
//        else if (){
//            return false;
//        }
//        else if (){
//            return false;
//        }
//        else if (){
//            return false;
//        }
        return true;
    }

    private void affirm() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("num", lipinhao);
        map.put("mobile", etPhone.getText().toString());
        map.put("order_count", number);
        map.put("consignee", etLianxiren.getText().toString());
        map.put("address", etAdress.getText().toString());



        HttpMethods.getInstance().POST(this, Constants.SUBMIT_ORDER, map, "GoodsExchangeActivity", new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(GoodsExchangeActivity.class,"logflag-积分商城--商品确认兑换返回的json数据--"+result);

                        if (result != null && result.contains("200")) {
                            DialogUtils.showPromptDialogConfirm(GoodsExchangeActivity.this, "提示", "兑换成功", new DialogUtils.DialogUtilsClickListener() {
                                @Override
                                public void onClick() {


                                    Intent intent = new Intent(GoodsExchangeActivity.this, ExchangeRecordActivity.class);
                                    intent.putExtra("flag", 555);
                                    startActivity(intent);
                                    closeActivity();

                                }
                            });
                        } else {
                            ToastUtils.showToast(GoodsExchangeActivity.this, R.string.duihuanshibai);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(GoodsExchangeActivity.this, R.string.shangpinduihuanshibai);
                    }
                }
        );
    }

    @Override
    public void initDataFromIntent() {
        number = getIntent().getStringExtra("number");
        imagesUrl = getIntent().getStringExtra("url");
        needIntegral = getIntent().getStringExtra("needintegral");
        myIntegral = getIntent().getStringExtra("myintegral");
        lipinhao = getIntent().getStringExtra("lipinhao");
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
        int totalNeedIntegral = StringUtils.string2Integer(number) * StringUtils.string2Integer(needIntegral);
        ImageGlideUtils.loadImage(iv, imagesUrl);
        tvNumber.setText(number);
        tvNeedIntegral.setText(("" + totalNeedIntegral));
        tvMyIntegral.setText(myIntegral);
    }

    @Override
    public void initListener() {

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvAffirmExchange.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

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
