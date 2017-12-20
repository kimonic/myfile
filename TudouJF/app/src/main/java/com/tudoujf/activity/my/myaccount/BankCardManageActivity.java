package com.tudoujf.activity.my.myaccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.managemoney.AddBankCardHuiFuActivity;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.adapter.BankCardManageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.BankCardManageBean;
import com.tudoujf.bean.databean.IdentityCheckBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
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
 * * ====================================================================
 * name:            BankCardManageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  银行卡管理activity
 * history：
 * * ====================================================================
 */

public class BankCardManageActivity extends BaseActivity {


    @BindView(R.id.mtb_act_bankcardmanage)
    MTopBarView mtbActBankCardManage;
    @BindView(R.id.lv_act_bankcardmanage_info)
    ListView lvInfo;
//    @BindView(R.id.ll_act_bankcardmanage_add)
//    LinearLayout llAdd;


    private BankCardManageBean  bean;

    private  String  loginToken;


    private List<BankCardManageBean.BankInfoBean>  list;
    private IdentityCheckBean identityCheckBean;
    private View footerView;


    @Override
    public int getLayoutResId() {
        return R.layout.act_bankcardmanage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_act_bankcardmanage_add://添加银行卡
                //需要先验证是否已实名
                checkIdentity();
                break;

        }

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();

        loginToken=UserConfig.getInstance().getLoginToken(this);
//        for (int i = 0; i < 30; i++) {
//            BankCardManageActBean bean=new BankCardManageActBean();
//            bean.setImageResId(R.drawable.act_lock_icon);
//            bean.setBankName("中国农业银行");
//            bean.setCardNumber("622848************0544");
//
//            list.add(bean);
//
//        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActBankCardManage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActBankCardManage.setLayoutParams(params);

        footerView= LayoutInflater.from(this).inflate(R.layout.view_addbank,null);
        lvInfo.addFooterView(footerView);




    }

    @Override
    public void initListener() {
        mtbActBankCardManage.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

//        llAdd.setOnClickListener(this);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要先验证是否已实名
                checkIdentity();
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", loginToken);

        HttpMethods.getInstance().POST(this, Constants.BANK_CARD_INFO, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----绑定的银行卡列表接口返回数据--------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<BankCardManageBean>() {
                        }.getType(), BankCardManageBean.class, BankCardManageActivity.this);
                        if (bean1 != null) {
                            bean = (BankCardManageBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(BankCardManageActivity.this, R.string.shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(BankCardManageActivity.this, R.string.huoquzhanghuxinxishibai);

                    }
                });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean!=null){
            if (bean.getBank_info()!=null){
                list.addAll(bean.getBank_info());
                BankCardManageActLvAdapter adapter=new BankCardManageActLvAdapter(list,this);
                lvInfo.setAdapter(adapter);
            }

        }

    }


    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
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
                }.getType(), IdentityCheckBean.class, BankCardManageActivity.this);
                if (bean1 != null) {
                    identityCheckBean = (IdentityCheckBean) bean1;
                    if (identityCheckBean.getIs_trust().equals("1")) {//已实名
                        openActivityForResult(AddBankCardHuiFuActivity.class,null,61);
                    } else {
                        DialogUtils.showDialog(BankCardManageActivity.this, R.string.qingxianshimingrenzheng,
                                R.string.queding, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        openActivity(RealNameAuthenticationHuiFuActivity.class);
                                    }
                                });
//                        DialogUtils.showHuiFuDialog(BankCardManageActivity.this);
                    }
                } else {
                    ToastUtils.showToast(BankCardManageActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
                ToastUtils.showToast(BankCardManageActivity.this, R.string.yanzhengshimingxinxishibai);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==61){
            initDataFromInternet();
        }
    }
}
