package com.tudoujf.activity.my.myaccount;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.SignatureBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             ElectronicSignatureActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/3/5
 * method:
 * <p>
 * <p>
 * description：   电子签章activity
 * history：
 * *==================================================================
 */

public class ElectronicSignatureActivity extends BaseActivity {
    @BindView(R.id.mtb_act_electronicsignature)
    MTopBarView mtb;
    //    @BindView(R.id.tv_act_electronicsgnature_phonenumber)
//    TextView tvPhonenumber;
//    @BindView(R.id.tv_act_electronicsgnature_email)
//    TextView tvEmail;
    @BindView(R.id.tv_act_electronicsgnature_name)
    TextView tvName;
    @BindView(R.id.tv_act_electronicsgnature_shenfenzhenghao)
    TextView tvShenfenzhenghao;
    @BindView(R.id.tv_act_electronicsgnature_xieyisel)
    TextView tvXieYiSel;
    @BindView(R.id.tv_act_electronicsgnature_xieyi)
    TextView tvXieYi;
    @BindView(R.id.tv_act_electronicsgnature_affirmopen)
    TextView tvAffirmOpen;
    @BindView(R.id.tv_temp)
    TextView tvTemp;

    private int count = 0;
    private boolean xieYiFlag = false;
    private SignatureBean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_electronicsignature;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_act_electronicsgnature_dalu://大陆
//                position = 0;
//                showStyle(position);
//                break;
//            case R.id.tv_act_electronicsgnature_xianggang://香港
//                position = 1;
//                showStyle(position);
//                break;
//            case R.id.tv_act_electronicsgnature_aomen://澳门
//                position = 2;
//                showStyle(position);
//                break;
//            case R.id.tv_act_electronicsgnature_taiwan://台湾
//                position = 3;
//                showStyle(position);
//                break;
//            case R.id.tv_act_electronicsgnature_waiji://外籍
//                position = 4;
//                showStyle(position);
//                break;
            case R.id.tv_act_electronicsgnature_xieyisel://协议选择
                if (count % 2 == 0) {
                    tvXieYiSel.setBackgroundResource(R.drawable.xvector_checkbox_sel);
                    xieYiFlag = true;
                } else {
                    tvXieYiSel.setBackgroundResource(R.drawable.xvector_checkbox_unsel);
                    xieYiFlag = false;
                }
                count++;
                break;
            case R.id.tv_act_electronicsgnature_xieyi://协议详情
                requestAgreement();
                break;
            case R.id.tv_act_electronicsgnature_affirmopen://确定开通
                if (xieYiFlag) {
                    //请求开通
                    requestOpen();
//                    ToastUtils.showToast(ElectronicSignatureActivity.this, "请求网络开通电子签章!");
                } else {
                    ToastUtils.showToast(ElectronicSignatureActivity.this, R.string.qingxiantongyishuzizhengshufuwuxieyi);
                }
                break;
//                 case R.id.:break;
        }

    }

    /**
     * 请求数字证书服务协议
     */
    private void requestAgreement() {
        Intent intent=new Intent(this, WebActivity.class);
        intent.putExtra("url", Constants.SINATURE_AGREEMENT+UserConfig.getInstance().getLoginToken(this));
        intent.putExtra("title",getString(R.string.shuzizhengshufuwuxieyi1));
        startActivity(intent);
    }

    /**
     * 请求开通电子签章
     */
    private void requestOpen() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.SINATURE_AFFIRM, map, "ElectronicSignatureActivity",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ElectronicSignatureActivity.class,"logflag-创建电子签章是否成功的返回信息--"+result);

                        if (result.contains("\"code\":200")) {
                            DialogUtils.showPromptDialog(ElectronicSignatureActivity.this,
                                    "提示", "电子签章开通成功", new DialogUtils.DialogUtilsClickListener() {
                                        @Override
                                        public void onClick() {
                                            //20180521修改,开通成功后返回我的账户页面
                                            closeActivity();
                                        }
                                    });
                            tvAffirmOpen.setClickable(false);
                            tvAffirmOpen.setBackgroundColor(getResources().getColor(R.color.color_gray));
                        } else {
                            DialogUtils.showPromptDialog(ElectronicSignatureActivity.this,
                                    "提示", "电子签章开通失败", new DialogUtils.DialogUtilsClickListener() {
                                        @Override
                                        public void onClick() {
                                            //20180521修改,开通成功后返回我的账户页面
                                            closeActivity();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(ElectronicSignatureActivity.this, R.string.shujujiazaichaoshi);
                    }
                });
    }

//    private void showStyle(int position) {
//        for (int i = 0; i < list.size(); i++) {
//            if (position == i) {
//                list.get(i).setBackgroundResource(R.drawable.xvector_checkbox_sel);
//            } else {
//                list.get(i).setBackgroundResource(R.drawable.xvector_checkbox_unsel);
//            }
//        }
//    }

    @Override
    public void initDataFromIntent() {
//        list = new ArrayList<>();
//        list.add(tvDaLu);
//        list.add(tvXiangGang);
//        list.add(tvAoMen);
//        list.add(tvTaiWan);
//        list.add(tvWaiJi);
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

//        tvDaLu.setOnClickListener(this);
//        tvXiangGang.setOnClickListener(this);
//        tvTaiWan.setOnClickListener(this);
//        tvWaiJi.setOnClickListener(this);
//        tvAoMen.setOnClickListener(this);
        tvXieYiSel.setOnClickListener(this);
        tvXieYi.setOnClickListener(this);
        tvAffirmOpen.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));

        HttpMethods.getInstance().POST(this, Constants.SINATURE_INIT, map, "ElectronicSignatureActivity",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(ElectronicSignatureActivity.class,"logflag-创建电子签章的初始化信息--"+result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<SignatureBean>() {
                        }.getType(), SignatureBean.class, ElectronicSignatureActivity.this);
                        if (bean1 != null) {
                            bean = (SignatureBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(ElectronicSignatureActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(ElectronicSignatureActivity.this, R.string.huoquyonghuxinxishibai);
                    }
                });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            tvName.setText(bean.getRealname());
            tvShenfenzhenghao.setText(bean.getCard_id());
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
