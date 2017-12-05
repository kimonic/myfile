package com.tudoujf.activity.home;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.MyExperienceGoldLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.MyExperienceGoldBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyExperienceGoldActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/3
 * description：  我的体验金activity
 * history：
 * * ====================================================================
 */

public class MyExperienceGoldActivity extends BaseActivity {
    @BindView(R.id.mtb_act_myexperiencegold)
    MTopBarView mtbMyExperienceGold;
    @BindView(R.id.tv_act_myexperiencegold_amount)
    TextView tvAmount;
    @BindView(R.id.tv_act_myexperiencegold_amount_all)
    TextView tvAmountAll;
    @BindView(R.id.tv_act_myexperiencegold_amount_balance)
    TextView tvAmountBalance;
    @BindView(R.id.lv_act_myexperience)
    ListView listview;
    private List<MyExperienceGoldBean.TenderListBean.ItemsBean> list;


    private MyExperienceGoldBean bean;


    @Override
    public int getLayoutResId() {
        return R.layout.act_myexperiencegold;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbMyExperienceGold.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbMyExperienceGold.setLayoutParams(params);

        mtbMyExperienceGold.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "1");

        HttpMethods.getInstance().POST(this, Constants.MY_EXPERIENCE, map, "MyExperienceGoldActivity",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的体验金接口返回数据------------------- " + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyExperienceGoldBean>() {
                        }.getType(), MyExperienceGoldBean.class, MyExperienceGoldActivity.this);
                        if (bean1 != null) {
                            bean = (MyExperienceGoldBean) bean1;
                            LoadInternetDataToUi();
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            tvAmount.setText(bean.getExperience_amount());
            tvAmountAll.setText(bean.getAmount_all());
            tvAmountBalance.setText(bean.getAmount_balance());
            if (bean.getTender_list().getItems() != null && bean.getTender_list().getItems().size() > 0) {
                list = bean.getTender_list().getItems();
                MyExperienceGoldLvAdapter adapter = new MyExperienceGoldLvAdapter(this, list);
                listview.setAdapter(adapter);
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
