package com.tudoujf.activity.my.set;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.HelpCenterCommonExpanableAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.SafetyControlActBean;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.elv_act_helpcentercommon)
    ExpandableListView lvActHelpcenterCommon;

    private List<SafetyControlActBean> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_helpcentercommon;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SafetyControlActBean bean=new SafetyControlActBean();
            bean.setImaUrl(R.drawable.act_safetycontrol_icon1);
            bean.setTitle("百里挑一,优质借款人");
            bean.setContent("百里挑一,优质借款人百里挑一,优质借款人百里挑一,优质借款人百里挑一," +
                    "优质借款人百里挑一,优质借款人百里挑一,优质借款人百里挑一," +
                    "优质借款人百里挑一,优质借款人v百里挑一,优质借款人");
            list.add(bean);
        }
    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvActHelpcenterCommon.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        tvActHelpcenterCommon.setLayoutParams(params);


        lvActHelpcenterCommon.setGroupIndicator(null);
        HelpCenterCommonExpanableAdapter adapter=new HelpCenterCommonExpanableAdapter(this,list);
        lvActHelpcenterCommon.setAdapter(adapter);
    }

    @Override
    public void initListener() {

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
