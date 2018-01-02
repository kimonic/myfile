package com.tudoujf.activity.my.myaccount;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             VIPActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/21
 * description：   申请VIPactivity
 * history：
 * *==================================================================
 */

public class VIPActivity extends BaseActivity {
    @BindView(R.id.mtb_act_vip)
    MTopBarView mtbActVip;
    @BindView(R.id.ll_act_vip_one)
    LinearLayout llActVipOne;
    @BindView(R.id.ll_act_vip_three)
    LinearLayout llActVipThree;
    @BindView(R.id.ll_act_vip_six)
    LinearLayout llActVipSix;
    @BindView(R.id.ll_act_vip_year)
    LinearLayout llActVipYear;
    @BindView(R.id.tv_act_vip_amount)
    TextView tvActVipAmount;
    @BindView(R.id.tv_act_vip_apply_now)
    TextView tvActVipApplyNow;
    @BindView(R.id.tv_act_vip_description)
    TextView tvDescription;

    private List<LinearLayout> list;
    private String money = "30";
    private String cycle = "1";
    private String categoryInd = "vip1";
    private String balance;

    @Override
    public int getLayoutResId() {
        return R.layout.act_vip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_vip_one:
                money = "30";
                cycle = "1";
                categoryInd = "vip1";
                setBac(0);
                break;
            case R.id.ll_act_vip_three:
                money = "50.00";
                cycle = "3";
                categoryInd = "vip3";
                setBac(1);
                break;
            case R.id.ll_act_vip_six:
                money = "80.00";
                cycle = "6";
                categoryInd = "vip6";
                setBac(2);
                break;
            case R.id.ll_act_vip_year:
                money = "120.00";
                cycle = "12";
                categoryInd = "vip";
                setBac(3);
                break;
            case R.id.tv_act_vip_apply_now:
//                applyVip();
                if (StringUtils.string2Float(money) > StringUtils.string2Float(balance)) {

                    ToastUtils.showToast(VIPActivity.this, R.string.yuebuzuqing);

                } else {
                    Intent intent = new Intent(this, VIPHuiFuBuyActivity.class);
                    intent.putExtra("money", money);
                    intent.putExtra("cycle", cycle);
                    intent.putExtra("categoryInd", categoryInd);
                    startActivity(intent);
                }

                break;
//                 case R.id.:break;
        }

    }

    private void setBac(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setBackgroundResource(R.drawable.act_vip_sel);
            } else {
                list.get(i).setBackgroundResource(R.drawable.act_vip_unsel);
            }
        }
    }

    @Override
    public void initDataFromIntent() {
        balance = getIntent().getStringExtra("balance");
        tvActVipAmount.setText(balance);

        String isvip = getIntent().getStringExtra("isvip");
        if ("1".equals(isvip)) {
            tvDescription.setText(R.string.qingxuanzeninxuyaoxufeidetaocan);
            tvActVipApplyNow.setText(R.string.lijixufei);
            mtbActVip.setCenterTitle(R.string.xufeivip);
        } else {
            tvDescription.setText(R.string.qingxuanzeninyaoshenqingdetaocan);
            tvActVipApplyNow.setText(R.string.lijishenqing);
            mtbActVip.setCenterTitle(R.string.frag_my_shenqingvip);
        }
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActVip.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActVip.setLayoutParams(params);


        list = new ArrayList<>();
        list.add(llActVipOne);
        list.add(llActVipThree);
        list.add(llActVipSix);
        list.add(llActVipYear);

    }

    @Override
    public void initListener() {
        mtbActVip.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        mtbActVip.getRightTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(VIPRecordActivity.class);
            }
        });

        llActVipOne.setOnClickListener(this);
        llActVipSix.setOnClickListener(this);
        llActVipThree.setOnClickListener(this);
        llActVipYear.setOnClickListener(this);
        tvActVipApplyNow.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {


    }

//    private void applyVip() {
//        showPDialog();
//        TreeMap<String, String> map = new TreeMap<>();
//        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("money", money);
//        map.put("cycle", cycle);
//        map.put("categoryInd", categoryInd);
//
//
//        HttpMethods.getInstance().POST(this, Constants.VIP_APPLY, map, this.getLocalClassName(),
//                new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        dismissPDialog();
//
//                        String result = StringUtils.getDecodeString(response.body());
//                        Log.e("TAG", "onSuccess:----VIP申请接口返回数据------------" + result);
////                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<TransferableDetailsBean>() {
////                        }.getType(), TransferableDetailsBean.class, VIPActivity.this);
////                        if (bean1 != null) {
////                            bean = (TransferableDetailsBean) bean1;
////                            LoadInternetDataToUi();
////                        } else {
////                            ToastUtils.showToast(VIPActivity.this, R.string.shujujiazaichucuo);
////                        }
//                    }
//
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        dismissPDialog();
//                        ToastUtils.showToast(VIPActivity.this, R.string.shenqingvipshibai);
//                    }
//                });
//    }

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
