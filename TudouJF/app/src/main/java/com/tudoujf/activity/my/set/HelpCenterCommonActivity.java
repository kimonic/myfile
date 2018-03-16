package com.tudoujf.activity.my.set;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.HelpCenterCommonExpanableAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.databean.HelpCenterCommonBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;


/**
 * * ====================================================================
 * name:            HelpCenterCommonActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/13
 * description：  帮助中心通用activity
 * history：
 * * ====================================================================
 */

public class HelpCenterCommonActivity extends BaseActivity {
    @BindView(R.id.tv_act_helpcentercommon)
    TextView tvActHelpcenterCommon;
    @BindView(R.id.tv_act_helpcentercommon_bac)
    TextView tvBac;
     @BindView(R.id.fl_act_helpcentercommon)
     FrameLayout fl;
    @BindView(R.id.elv_act_helpcentercommon)
    ExpandableListView lvActHelpcenterCommon;

    private List<HelpCenterCommonBean.DataBean> list;
    private int page = 1;
    private String category_id = "";
    private HelpCenterCommonBean bean;
    private String title;

    @Override
    public int getLayoutResId() {
        return R.layout.act_helpcentercommon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_helpcentercommon_bac:
                closeActivity();
                break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
//            case R.id.: break;
        }
    }

    @Override
    public void initDataFromIntent() {

        category_id = getIntent().getStringExtra("category_id");
        title = getIntent().getStringExtra("title");


        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            SafetyControlActBean bean = new SafetyControlActBean();
//            bean.setImaUrl(R.drawable.act_safetycontrol_icon1);
//            bean.setTitle("百里挑一,优质借款人");
//            bean.setContent("百里挑一,优质借款人百里挑一,优质借款人百里挑一,优质借款人百里挑一," +
//                    "优质借款人百里挑一,优质借款人百里挑一,优质借款人百里挑一," +
//                    "优质借款人百里挑一,优质借款人v百里挑一,优质借款人");
//            list.add(bean);
//        }
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fl.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        fl.setLayoutParams(params);

        tvActHelpcenterCommon.setText(title);


    }

    @Override
    public void initListener() {
        lvActHelpcenterCommon.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = lvActHelpcenterCommon.getExpandableListAdapter().getGroupCount();
                for (int j = 0; j < count; j++) {
                    if (j != groupPosition) {
                        lvActHelpcenterCommon.collapseGroup(j);
                    }
                }
            }
        });

        tvBac.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("category_id", category_id);
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(this, Constants.HELP_CENTER, map, this.getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(HelpCenterCommonActivity.class,"logflag--帮助中心通用接口返回数据---" + category_id + "------" + result);

                        Gson gson = new Gson();
                        bean = gson.fromJson(result, HelpCenterCommonBean.class);
                        if (bean != null && "200".equals(bean.getCode())) {
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(HelpCenterCommonActivity.this, R.string.shujujiazaichucuo);
                        }


//                            if (srl.isRefreshing()) {
//                                srl.finishRefresh();
//                            }
//
//                            BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<PersonalCenterBean>() {
//                            }.getType(), PersonalCenterBean.class, HelpCenterCommonActivity.this);
//                            if (bean1 != null) {
//                                bean = (PersonalCenterBean) bean1;
//                                LoadInternetDataToUi();
//                            } else {
//                                ToastUtils.showToast(HelpCenterCommonActivity.this, R.string.shujujiazaichucuo);
//                            }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(HelpCenterCommonActivity.this, R.string.huoqubangzhuxinxishiabai);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && "200".equals(bean.getCode())) {
            if (bean.getData().size() > 0) {
                list.addAll(bean.getData());

                lvActHelpcenterCommon.setGroupIndicator(null);
                HelpCenterCommonExpanableAdapter adapter = new HelpCenterCommonExpanableAdapter(this, list);
                lvActHelpcenterCommon.setAdapter(adapter);


            } else {
                ToastUtils.showToast(HelpCenterCommonActivity.this, R.string.meiyoukexianshishuju);
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
