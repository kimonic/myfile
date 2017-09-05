package com.tudoujf.activity.my.mypopularize;

import android.view.View;
import android.widget.LinearLayout;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import butterknife.BindView;

/**
 * * ================================================
 * name:            MyPopularizeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/4
 * description：  我的推广页面activity
 * history：
 * ===================================================
 */

public class MyPopularizeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_mypopularize)
    MTopBarView mtbActMyPopularize;
    @BindView(R.id.ll_act_mypopularize_succeedinvitation)
    LinearLayout llSucceedInvitation;
    @BindView(R.id.ll_act_mypopularize_recommendrecord)
    LinearLayout llRecommendRecord;
    @BindView(R.id.ll_act_mypopularize_accountrecord)
    LinearLayout llAccountRecord;
    @BindView(R.id.ll_act_mypopularize_recommendaward)
    LinearLayout llRecommendAward;

    @Override
    public int getLayoutResId() {
        return R.layout.act_mypopularize;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_act_mypopularize_accountrecord://结算记录
                openActivity(AccountRecordActivity.class);
                break;
            case R.id.ll_act_mypopularize_recommendaward://推广奖励说明
                openActivity(InvitationAwardExplainActivity.class);
                break;
            case R.id.ll_act_mypopularize_recommendrecord://推广记录
                openActivity(RecommendRecordActivity.class);
                break;
            case R.id.ll_act_mypopularize_succeedinvitation://成功邀请
                openActivity(SucceedInvitationActivity.class);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActMyPopularize.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActMyPopularize.setLayoutParams(params);

    }

    @Override
    public void initListener() {
        mtbActMyPopularize.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        mtbActMyPopularize.getRightTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(NowRecommendActivity.class);
            }
        });
        llSucceedInvitation.setOnClickListener(this);
        llAccountRecord.setOnClickListener(this);
        llRecommendAward.setOnClickListener(this);
        llRecommendRecord.setOnClickListener(this);

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
