package com.tudoujf.activity.home;

import android.content.Intent;
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
import com.tudoujf.utils.LUtils;
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
                Intent intent=new Intent(MyExperienceGoldActivity.this,HomeActivity.class);
                intent.putExtra("flag",666);
                startActivity(intent);
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

        showPDialog();

        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("page", "1");

        HttpMethods.getInstance().POST(this, Constants.MY_EXPERIENCE, map, "MyExperienceGoldActivity",
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(MyExperienceGoldActivity.class,"logflag--我的体验金接口返回数据-"+result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyExperienceGoldBean>() {
                        }.getType(), MyExperienceGoldBean.class, MyExperienceGoldActivity.this);
                        if (bean1 != null) {
                            bean = (MyExperienceGoldBean) bean1;
                            LoadInternetDataToUi();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        dismissPDialog();
                        super.onError(response);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null) {
            tvAmount.setText(StringUtils.getCommaDecimalsStr(bean.getMy_experience_balance()));
            tvAmountAll.setText(StringUtils.getCommaDecimalsStr(
                    StringUtils.changeScientificNotation(bean.getMy_account_amount())));


            tvAmountBalance.setText(StringUtils.getCommaDecimalsStr(bean.getMy_account_balance()));
            if (bean.getPageObj().getItems() != null && bean.getPageObj().getItems().size() > 0) {
                List<MyExperienceGoldBean.PageObjBean.ItemsBean> list = bean.getPageObj().getItems();
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


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,HomeActivity.class);
        intent.putExtra("flag",666);
        startActivity(intent);
        closeActivity();
    }
}
