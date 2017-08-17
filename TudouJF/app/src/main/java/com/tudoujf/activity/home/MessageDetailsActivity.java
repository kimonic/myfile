package com.tudoujf.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ====================================================================
 * name:            MessageDetailsActivity
 * guide:          MyMessageActivity-->
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/17
 * description：     我的消息activity中的消息详情
 * history：
 * * ====================================================================
 */

public class MessageDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_messagedetails)
    MTopBarView mtbMessageDetails;
    @BindView(R.id.tv_act_messagedetails_title)
    TextView tvTitle;
    @BindView(R.id.tv_act_messagedetails_time)
    TextView tvTime;
    @BindView(R.id.tv_act_messagedetails_name)
    TextView tvName;
    @BindView(R.id.tv_act_messagedetails_content)
    TextView tvContent;

    @Override
    public int getLayoutResId() {
        return R.layout.act_messagedetails;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbMessageDetails.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbMessageDetails.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbMessageDetails.getLeftTV().setOnClickListener(new View.OnClickListener() {
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