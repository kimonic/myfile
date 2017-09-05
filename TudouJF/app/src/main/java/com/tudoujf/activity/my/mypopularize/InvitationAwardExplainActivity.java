package com.tudoujf.activity.my.mypopularize;

import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广-->推广奖励说明activity
 * history：
 * * ====================================================================
 */

public class InvitationAwardExplainActivity extends BaseActivity {
    @BindView(R.id.mtb_act_invitationawardexplain)
    MTopBarView mtbActInvitationAwardExplain;

    @Override
    public int getLayoutResId() {
        return R.layout.act_invitationawardexplain;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActInvitationAwardExplain.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActInvitationAwardExplain.setLayoutParams(params);
    }

    @Override
    public void initListener() {
        mtbActInvitationAwardExplain.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
