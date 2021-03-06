package com.tudoujf.activity.managemoney;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.RechargeActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.AffirmBuyCreditorRightrsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.PasswordView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            AffirmBuyCreditorsRightsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/10
 * description：债权详情,确认购买activity
 * history：
 * ===================================================
 */

public class AffirmBuyCreditorsRightsActivity extends BaseActivity {


    @BindView(R.id.mtb_act_affirmbuycreditorsright)
    MTopBarView mtbAffirmBuyCreditorsRight;

    @BindView(R.id.tv_act_affirmbuycreditors_affirmbuyright)
    TextView tvAffirmBuyCreditorsRight;
    @BindView(R.id.tv_act_affirmbuycreditors_earnings)
    TextView tvEarnings;//预计获得收益
    @BindView(R.id.tv_act_affirmbuycreditors_investamount)
    TextView tvInvestamount;//可投资金额
    @BindView(R.id.tv_act_affirmbuycreditors_balance)
    TextView tvBalance;//账户余额
    @BindView(R.id.tv_act_affirmbuycreditors_recharge)
    TextView tvRecharge;//充值
    @BindView(R.id.tv_act_affirm_agree)
    TextView tvAgree;//同意协议
    @BindView(R.id.tv_zhaiquanxieyi)
    TextView tvZhaiQuanXieYi;//同意协议

    private String transfer_id = "";
    private AlertDialog dialog;
    private AffirmBuyCreditorRightrsBean bean;
    private int count = 0;
    private boolean agree = false;

    @Override
    public int getLayoutResId() {
        return R.layout.act_affirmbuycreditorsright;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_affirmbuycreditors_affirmbuyright://确认购买按钮

                if (agree) {
                    if (bean != null) {
                        if ((StringUtils.string2Float(bean.getBalance_amount()) >= StringUtils.string2Float(bean.getAmount()))) {
//                        showPasswordDialog();
                            Intent intent = new Intent(this, CreditorRightsHuiFuBuyActivity.class);
                            intent.putExtra("transferId", transfer_id);
                            startActivity(intent);
                        } else {
                            DialogUtils.showDialog(this, getResources().getString(R.string.zhanghuyuebuzu), R.string.queren, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //201808061504修复余额不足进入充值页面时显示余额异常
                                    jumpRechargeActivity();
//                                    Bundle bundle=new Bundle();
//                                    if (bean!=null){
//                                        bundle.putString("balance",bean.getBalance_amount());
//                                    }
//                                    Intent intent=new Intent(AffirmBuyCreditorsRightsActivity.this,RechargeActivity.class);
//                                    intent.putExtras(bundle);
//                                    startActivity(intent);
//                                    openActivity(RechargeActivity.class);
                                }
                            });
                        }
                    } else {
                        ToastUtils.showToast(AffirmBuyCreditorsRightsActivity.this, R.string.shujujiazaichucuoqtchcxjr);
                    }
                } else {
                    ToastUtils.showToast(AffirmBuyCreditorsRightsActivity.this, R.string.qingxiantongyipingtaijujianfuwuxieyi);
                }


                break;
            case R.id.tv_act_affirmbuycreditors_recharge:
                jumpRechargeActivity();
//                openActivity(RechargeActivity.class);
                break;
            case R.id.tv_act_affirm_agree://同意协议
                count++;
                if (count % 2 == 0) {
                    tvAgree.setBackgroundResource(R.drawable.xvector_checkbox_unsel);
                    agree = false;
                } else {
                    tvAgree.setBackgroundResource(R.drawable.xvector_checkbox_sel);
                    agree = true;
                }
                break;
            case R.id.tv_zhaiquanxieyi:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", Constants.TOU_ZI_XIE_YI);
                intent.putExtra("title","土豆金服服务协议");
                startActivity(intent);
                break;
        }
    }

    private void jumpRechargeActivity(){
        Bundle bundle=new Bundle();
        if (bean!=null){
            bundle.putString("balance",bean.getBalance_amount());
        }
        openActivity(RechargeActivity.class,bundle);
    }

//    private void showPasswordDialog() {
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_act_affirmbuy, null);
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
//        final PopupWindow pop = new PopupWindow(view, ScreenSizeUtils.getScreenWidth(this) - 180, 500);
//        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//
//        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
//        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
//        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
//        pop.setFocusable(true);//获取焦点
//        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置
//
//
//        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
//            }
//        });
//
//        final PasswordView passwordView = (PasswordView) view.findViewById(R.id.pv_act_affirmbuy_dialog);
//        TextView textView = (TextView) view.findViewById(R.id.tv_act_affirmbuy_dialog);
//        passwordView.setBtnText(textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//            }
//        });
//        textView.setClickable(false);
//
//
//    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            transfer_id = intent.getStringExtra("id");
        }

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbAffirmBuyCreditorsRight.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbAffirmBuyCreditorsRight.setLayoutParams(params);

        mtbAffirmBuyCreditorsRight.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


    }

    @Override
    public void initListener() {
        tvAffirmBuyCreditorsRight.setOnClickListener(this);
        tvRecharge.setOnClickListener(this);
        tvAgree.setOnClickListener(this);
        tvZhaiQuanXieYi.setOnClickListener(this);


    }

    @Override
    public void initDataFromInternet() {
        showDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("transfer_id", transfer_id);
        HttpMethods.getInstance().POST(this, Constants.CREDITOR_BUY, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dialog.dismiss();
                String result = StringUtils.getDecodeString(response.body());
                LUtils.e(AffirmBuyCreditorsRightsActivity.class,"logflag-请求债权购买接口返回的json数据--"+result);
//                Gson gson = new Gson();
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<AffirmBuyCreditorRightrsBean>() {
                }.getType(), AffirmBuyCreditorRightrsBean.class, AffirmBuyCreditorsRightsActivity.this);
                if (bean1 != null) {
                    bean = (AffirmBuyCreditorRightrsBean) bean1;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(AffirmBuyCreditorsRightsActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }
        });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            if (bean.getLoan_name().length()<19){
                mtbAffirmBuyCreditorsRight.setCenterTitle(bean.getLoan_name());
            }else {
                mtbAffirmBuyCreditorsRight.setCenterTitle(bean.getLoan_name().substring(0,19)+"...");
            }
            tvEarnings.setText(bean.getIncome());
            tvBalance.setText(StringUtils.getCommaDecimalsStr(bean.getBalance_amount()));
            tvInvestamount.setText(bean.getAmount());
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void showDialog() {
        if (dialog == null) {
            dialog = DialogUtils.showProgreessDialog(this, getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
        } else {
            dialog.show();
        }
    }
}
