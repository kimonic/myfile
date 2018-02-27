package com.tudoujf.activity.managemoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.RechargeActivity;
import com.tudoujf.adapter.MTextWatchAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.AffirmBuyBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.PasswordView;
import com.tudoujf.utils.KeyboardChangeListener;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.text.DecimalFormat;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            AffirmBuyActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：产品详情,确认购买activity
 * history：
 * ===================================================
 */

public class AffirmBuyActivity extends BaseActivity {
    @BindView(R.id.mtb_act_affirmbuy)
    MTopBarView mtbAffirmbuy;
    @BindView(R.id.ll_act_affirmbuy_redpackage)
    LinearLayout llRedpackage;
    @BindView(R.id.ll_act_affirmbuy_jiaxiquan)
    LinearLayout llJiaxiquan;
    @BindView(R.id.ll_act_affirmbuy_fanxianquan)
    LinearLayout llFanxianquan;
    @BindView(R.id.ll_act_affirmbuy_xieyi)
    LinearLayout llXieYi;
    @BindView(R.id.tv_act_affirmbuy_affirmbuy)
    TextView tvAffirmbuy;
    @BindView(R.id.tv_act_affirmbuy_chongzhi)
    TextView tvChongZhi;
    @BindView(R.id.tv_act_affirmbuy_earings)
    TextView tvEarings;
    @BindView(R.id.tv_act_affirmbuy_balance)
    TextView tvBalance;
    @BindView(R.id.tv_act_affirmbuy_wait_amount)
    TextView tvWaitAmount;
    @BindView(R.id.tv_act_affirmbuy_qitou)
    TextView tvQiTou;
    @BindView(R.id.tv_act_affirmbuy_max)
    TextView tvMax;
    @BindView(R.id.tv_act_affirmbuy_redpackage)
    TextView tvRedPackage;
    @BindView(R.id.tv_act_affirmbuy_jiaxiquan)
    TextView tvJiaxiquan;
    @BindView(R.id.tv_act_affirmbuy_fanxianquan)
    TextView tvFanXianQuan;
    @BindView(R.id.tv_act_affirmbuy_lixi)
    TextView tvLiXi;
    @BindView(R.id.et_act_affirm_touzijine)
    EditText etTouZiJinE;
    @BindView(R.id.iv_act_affirmbuy_sel)
    ImageView ivSel;
    @BindView(R.id.tv_act_affirmbuy_reward)
    TextView tvReward;
    @BindView(R.id.tv_touzixieyi)
    TextView tvTouZiXieYi;

    private String loan_id;
    private AffirmBuyBean bean;
    private DecimalFormat decimalFormat;
    private int count = 0;
    private boolean agree = false;
    private String is_beginner;
    private String time_limit;

    private int flag = 1;

    /**
     * 红包id
     */
    private String redId = "";
    private String jiaXiQuanId = "";
    private String fanXianQuanId = "";
    private String acount = "";
    private String paypassword = "";
    private String has_password;
    private int together_status_redbag = 1;
    private int together_status_jiaxiquan = 1;
    private int together_status_fanxianquan = 1;
    private int together_status;

    @Override
    public int getLayoutResId() {
        return R.layout.act_affirmbuy;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_affirmbuy_fanxianquan://跳转返现券页面
                if (etTouZiJinE.getText().toString().equals("")) {
                    ToastUtils.showToast(AffirmBuyActivity.this, R.string.qingxianshurutouzijine);
                } else {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("type", 3);
                    bundle2.putString("amount", etTouZiJinE.getText().toString());
                    bundle2.putString("time_limit", time_limit);
                    if (together_status_fanxianquan == -1) {
                        together_status = 1;
                    } else {
                        together_status = together_status_redbag * together_status_jiaxiquan * together_status_fanxianquan;
                    }
                    bundle2.putString("selId", fanXianQuanId);
                    bundle2.putString("together_status", "" + together_status);
                    openActivityForResult(RedPackageActivity.class, bundle2, 777);
                }

                break;
            case R.id.ll_act_affirmbuy_jiaxiquan://跳转加息券界面
                if (etTouZiJinE.getText().toString().equals("")) {
                    ToastUtils.showToast(AffirmBuyActivity.this, R.string.qingxianshurutouzijine);
                } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("time_limit", time_limit);
                    bundle1.putInt("type", 2);
                    bundle1.putString("amount", etTouZiJinE.getText().toString());
                    if (together_status_jiaxiquan == -1) {
                        together_status = 1;
                    } else {
                        together_status = together_status_redbag * together_status_jiaxiquan * together_status_fanxianquan;
                    }
                    bundle1.putString("selId", jiaXiQuanId);
                    bundle1.putString("together_status", "" + together_status);
                    openActivityForResult(RedPackageActivity.class, bundle1, 999);
                }
                break;
            case R.id.ll_act_affirmbuy_redpackage://跳转红包界面
                if (etTouZiJinE.getText().toString().equals("")) {
                    ToastUtils.showToast(AffirmBuyActivity.this, R.string.qingxianshurutouzijine);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    bundle.putString("amount", etTouZiJinE.getText().toString());
                    bundle.putString("is_beginner", is_beginner);
                    bundle.putString("time_limit", time_limit);
                    bundle.putString("selId", redId);
                    if (together_status_redbag == -1) {
                        together_status = 1;
                    } else {
                        together_status = together_status_redbag * together_status_jiaxiquan * together_status_fanxianquan;
                    }
                    bundle.putString("together_status", "" + together_status);
                    openActivityForResult(RedPackageActivity.class, bundle, 888);
                }


                break;
            case R.id.tv_act_affirmbuy_affirmbuy://确认购买按钮
                if (bean != null && checkInput() && "true".equals(has_password)) {
                    showPasswordDialog();
                } else if (bean != null && checkInput()) {
                    openNextActivity();
                }
                break;
            case R.id.tv_act_affirmbuy_chongzhi://充值按钮
                Bundle bundle = new Bundle();
                bundle.putString("balance", bean.getMember().getBalance_amount());
                openActivity(RechargeActivity.class, bundle);
                break;
            case R.id.ll_act_affirmbuy_xieyi://同意居间服务协议
                count++;
                if (count % 2 == 0) {
                    ivSel.setImageResource(R.drawable.xvector_checkbox_sel);
                    agree = true;
                } else {
                    ivSel.setImageResource(R.drawable.xvector_checkbox_unsel);
                    agree = false;
                }
                break;

            case R.id.tv_touzixieyi:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", Constants.TOU_ZI_XIE_YI);
                intent.putExtra("title","土豆金服服务协议");
                startActivity(intent);
                break;
        }
    }

    private void openNextActivity() {
        Intent intent = new Intent(this, InvestHuiFuBuyActivity.class);
        intent.putExtra("amount", etTouZiJinE.getText().toString());//金额
        intent.putExtra("id", loan_id);//标的id
        intent.putExtra("coupon", jiaXiQuanId);//加息券id
        intent.putExtra("redBag", redId);//红包id
        intent.putExtra("cashback ", fanXianQuanId);//返现券id
        intent.putExtra("paypassword", paypassword);//投资密码
        startActivity(intent);
    }

    private void showPasswordDialog() {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.dialog_act_affirmbuy, null);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        final PopupWindow pop = new PopupWindow(view, ScreenSizeUtils.getScreenWidth(this) - 180, 500);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点
        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置


        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

        final PasswordView passwordView = (PasswordView) view.findViewById(R.id.pv_act_affirmbuy_dialog);
        TextView textView = (TextView) view.findViewById(R.id.tv_act_affirmbuy_dialog);
        passwordView.setBtnText(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paypassword = passwordView.getContent();
                openNextActivity();
                pop.dismiss();
            }
        });
        textView.setClickable(false);
    }

    @Override
    public void initDataFromIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            loan_id = bundle.getString("loan_id");
            is_beginner = bundle.getString("is_beginner");
            time_limit = bundle.getString("time_limit");
            has_password = bundle.getString("has_password");

        }

    }

    @Override
    public void initView() {

        decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbAffirmbuy.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbAffirmbuy.setLayoutParams(params);

        mtbAffirmbuy.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


        Log.e("TAG1212", "isShow = -----------------------");

        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.e("TAG1212", "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
            }
        });
        etTouZiJinE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
// 可捕捉右下角的Return按钮,监听回车按钮
//添加抛出收起事件代码
                Log.e("TAG1212", "???????????????????????????????????????");
                return false;
            }
        });

        etTouZiJinE.addTextChangedListener(new MTextWatchAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (bean != null && StringUtils.string2Float(editable.toString()) >= 10) {
                    //计算预期收益
                    float liXiShouYi=(StringUtils.string2Float(bean.getApr()) /
                            100 * StringUtils.string2Float(editable.toString()) / 12) * StringUtils.string2Float(bean.getPeriod());
                    float  jiangLiShouYi=StringUtils.string2Float(editable.toString())
                            * StringUtils.string2Float(bean.getAward_proportion().toString()) / 100;
                    float zongShouYi=liXiShouYi+jiangLiShouYi;
                    String temp = decimalFormat.format(liXiShouYi);
                    //尚未测试无奖励金额的
                    tvReward.setText(decimalFormat.format(jiangLiShouYi));


                    tvEarings.setText(decimalFormat.format(zongShouYi));
                    tvLiXi.setText(temp);
                    redId = "";
                    jiaXiQuanId = "";
                    fanXianQuanId = "";
                    together_status_redbag = 1;
                    together_status_jiaxiquan = 1;
                    together_status_fanxianquan = 1;
                    tvRedPackage.setText(R.string.dianjixuanzekeyonghongbao);
                    tvJiaxiquan.setText(R.string.dianjixuanzekeyongjiaxiquan);
                    tvFanXianQuan.setText(R.string.dianjixuanzefanxianquan);

                } else {
                    tvEarings.setText(R.string.ling);
                    tvLiXi.setText(R.string.ling);
                    tvReward.setText(R.string.ling);
                }
            }
        });

//        etTouZiJinE.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.e("TAG1212", "onKey: -----------------------------------");
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    return true;
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void initListener() {
        llFanxianquan.setOnClickListener(this);
        llJiaxiquan.setOnClickListener(this);
        llRedpackage.setOnClickListener(this);
        tvAffirmbuy.setOnClickListener(this);
        tvChongZhi.setOnClickListener(this);
        llXieYi.setOnClickListener(this);
        tvTouZiXieYi.setOnClickListener(this);


    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("loan_id", loan_id);

        HttpMethods.getInstance().POST(this, Constants.PRODUCE_BUY, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----产品购买接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<AffirmBuyBean>() {
                        }.getType(), AffirmBuyBean.class, AffirmBuyActivity.this);
                        if (bean1 != null) {
                            bean = (AffirmBuyBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(AffirmBuyActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(AffirmBuyActivity.this, R.string.huoquchanpinxinxishibai);

                    }
                });

    }


    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            mtbAffirmbuy.setCenterTitle(bean.getName());
            tvBalance.setText(bean.getMember().getBalance_amount());
            tvWaitAmount.setText(bean.getWait_amount());
            tvQiTou.setText(bean.getTender_amount_min());
            if ("0".equals(bean.getTender_amount_max())) {
                tvMax.setText(R.string.wuxianzhi);
            } else {
                tvMax.setText(bean.getTender_amount_max());
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 检测是否能够投资
     */
    private boolean checkInput() {
        String amount = etTouZiJinE.getText().toString();
        int f = StringUtils.string2Integer(amount);
        if (amount.equals("")) {
            ToastUtils.showToast(this, R.string.qingshurutouzijine);
            return false;
        } else if (!agree) {
            ToastUtils.showToast(this, R.string.qingxiantongyipingtaijujianfuwuxieyi);
            return false;
        } else if (f > StringUtils.string2Float(bean.getMember().getBalance_amount())) {
            ToastUtils.showToast(this, R.string.zhanghuyuebuzu);
            return false;
        } else if (f % 10 != 0) {
            ToastUtils.showToast(this, R.string.touzijinebixuweishidebeishu);
            return false;
        } else if (f < StringUtils.string2Float(bean.getTender_amount_min())) {
            ToastUtils.showToast(this, R.string.touzijinebixudayudengyuqitoujine);
            return false;
        } else if (StringUtils.string2Float(bean.getTender_amount_max()) != 0 && f > StringUtils.string2Float(bean.getTender_amount_max())) {
            ToastUtils.showToast(this, R.string.touzijinebunengdayuzuidaketoujine);
            return false;
        }

        return true;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //---------------------------------红包-----------------------------------------------------
        if (requestCode == 888 && data != null) {//红包


            redId = "";
            acount = "";
            together_status_redbag = 1;

            redId = data.getStringExtra("redId");
            acount = data.getStringExtra("acount");
            String status = data.getStringExtra("together_status");
            Log.e("TAG", "onActivityResult: -----" + status);
            if (!status.equals("")) {
                together_status_redbag = StringUtils.string2Integer(status);
            }
            if (redId == null || "".equals(redId)) {
                ToastUtils.showToast(AffirmBuyActivity.this, getResources().getString(R.string.meiyouxuanzhongkeyonghongbao));
                tvRedPackage.setText(getResources().getString(R.string.meiyoukeyonghongbao));
            } else {
                tvRedPackage.setText(("已选" + acount + "元红包"));
            }
        }
        //---------------------------------红包-----------------------------------------------------


        //---------------------------------加息券---------------------------------------------------

        if (requestCode == 999 && data != null) {//加息券

            jiaXiQuanId = data.getStringExtra("redId");
            acount = data.getStringExtra("acount");
            String status = data.getStringExtra("together_status");
            if (!status.equals("")) {
                together_status_jiaxiquan = StringUtils.string2Integer(status);
            }
            if (jiaXiQuanId == null || "".equals(jiaXiQuanId)) {
                ToastUtils.showToast(AffirmBuyActivity.this, getResources().getString(R.string.meiyouxuanzhongkeyongjiaxiquan));
                tvJiaxiquan.setText(getResources().getString(R.string.meiyoukeyongjiaxiquan));
            } else {
                tvJiaxiquan.setText(("已选" + acount + "加息券"));
            }
        }

        //---------------------------------加息券---------------------------------------------------


        //---------------------------------返现券---------------------------------------------------

        if (requestCode == 777 && data != null) {//返现券
            fanXianQuanId = data.getStringExtra("redId");
            acount = data.getStringExtra("acount");
            String status = data.getStringExtra("together_status");
            if (!status.equals("")) {
                together_status_fanxianquan = StringUtils.string2Integer(status);
            }
            if (fanXianQuanId == null || "".equals(fanXianQuanId)) {
                ToastUtils.showToast(AffirmBuyActivity.this, getResources().getString(R.string.meiyouxuanzhongkeyongfanxianquan));
                tvFanXianQuan.setText(getResources().getString(R.string.meiyoukeyongfanxianquan));
            } else {
                tvFanXianQuan.setText(("已选" + acount + "%返现券"));
            }
        }

        //---------------------------------返现券---------------------------------------------------

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag != 1) {
            initDataFromInternet();
        }
        flag++;
    }

}