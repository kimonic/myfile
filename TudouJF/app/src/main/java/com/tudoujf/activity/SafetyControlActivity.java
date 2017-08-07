package com.tudoujf.activity;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.adapter.ExpanableAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.SafetyControlActBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            SafetyControlActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/4
 * description：    安全保障activity
 * history：
 * ===================================================
 */

public class SafetyControlActivity extends BaseActivity {
    @BindView(R.id.mtb_act_safetycontrol)
    MTopBarView mtbSafetyControl;
    @BindView(R.id.lv_act_safetycontrol)
    ExpandableListView lvSafetyControl;
    private List<SafetyControlActBean>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_safetycontrol;
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
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbSafetyControl.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbSafetyControl.setLayoutParams(params);

        lvSafetyControl.setGroupIndicator(null);
        ExpanableAdapter adapter=new ExpanableAdapter(this,list);
        lvSafetyControl.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbSafetyControl.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


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
