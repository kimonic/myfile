package com.tudoujf.activity.my.set;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
 * name:            FeedbackActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/13
 * description：  意见反馈activity
 * history：
 * * ====================================================================
 */

public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.mtb_act_feedback)
    MTopBarView mtbActFeedback;
    @BindView(R.id.et_act_feedback_content)
    EditText etContent;
    @BindView(R.id.et_act_feedback_contact)
    EditText etContact;
    @BindView(R.id.tv_act_feedback_submit)
    TextView tvSubmit;

    @Override
    public int getLayoutResId() {
        return R.layout.act_feedback;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_act_feedback_submit://提交
                break;

        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActFeedback.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActFeedback.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActFeedback.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        tvSubmit.setOnClickListener(this);

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
