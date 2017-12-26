package com.tudoujf.activity.my.myproject;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.DaiHouGuanLiLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.DaiHouGuanLiBean;
import com.tudoujf.bean.databean.WithdrawRecordBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             DaiHouGuanLiActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/26
 * description： 贷后管理activity
 * history：
 * *==================================================================
 */

public class DaiHouGuanLiActivity extends BaseActivity {
    @BindView(R.id.mtb_act_daihouguanli)
    MTopBarView mtb;
    @BindView(R.id.lv_act_daihouguanli)
    ListView lv;
    private String loan_id;
    private DaiHouGuanLiBean bean;
    private List<DaiHouGuanLiBean.DataBean> list;
    private DaiHouGuanLiLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.act_daihouguanli;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();
        loan_id = getIntent().getStringExtra("loan_id");
        if (loan_id == null) {
            loan_id = "";
        }

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

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
//        map.put("login_token", "12267");
        map.put("loan_id", loan_id);


        HttpMethods.getInstance().POST(this, Constants.DAI_HOU_GUAN_LI, map, getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                dismissPDialog();

                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------贷后管理接口请求返回数据-----------------" + result);
                Gson gson = new Gson();
                bean = gson.fromJson(result, DaiHouGuanLiBean.class);
                if (bean != null && bean.getCode() == 200) {
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(DaiHouGuanLiActivity.this, R.string.shujujiazaichucuo);
                }


//                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<WithdrawRecordBean>() {
//                }.getType(), WithdrawRecordBean.class, DaiHouGuanLiActivity.this);
//                if (bean1 != null) {
//                    bean = (WithdrawRecordBean) bean1;
//                    LoadInternetDataToUi();
//                } else {
//                    ToastUtils.showToast(DaiHouGuanLiActivity.this, R.string.shujujiazaichucuo);
//                }


            }


        });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && bean.getData() != null) {
            list.addAll(bean.getData());
            if (adapter==null){
                adapter =new DaiHouGuanLiLvAdapter(this,list);
                lv.setAdapter(adapter);
            }else {
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
}
