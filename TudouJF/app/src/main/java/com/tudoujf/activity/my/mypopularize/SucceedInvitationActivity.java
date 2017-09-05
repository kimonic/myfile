package com.tudoujf.activity.my.mypopularize;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.SucceedInvitationActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * * ================================================
 * name:            SucceedInvitationActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/5
 * description：  我的推广页面-->成功邀请activity
 * history：
 * ===================================================
 */

public class SucceedInvitationActivity extends BaseActivity {
    @BindView(R.id.mtb_act_succeedinvitation)
    MTopBarView mtbActSucceedInvitation;
    @BindView(R.id.lv_act_succeedinvitation)
    ListView lvActSucceedInvitation;
    @BindView(R.id.srl_act_succeedinvitation)
    SmartRefreshLayout srlActSucceedInvitation;

    private List<SucceedInvitationActBean>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_succeedinvitation;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        //临时数据源
        list=new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            SucceedInvitationActBean bean=new SucceedInvitationActBean();
            bean.setUserName("用户XXXXXX");
            bean.setTouZiZongE("000,000.00");
            bean.setHuanKuanZongE("000,000.00");

            if (i%2==1){
                bean.setBacFlag(2);
            }
            list.add(bean);
        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActSucceedInvitation.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActSucceedInvitation.setLayoutParams(params);

        srlActSucceedInvitation.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActSucceedInvitation.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActSucceedInvitation.setRefreshFooter(new BallPulseFooter(this));

        SucceedInvitationActLvAdapter adapter=new SucceedInvitationActLvAdapter(this,list);
        lvActSucceedInvitation.setAdapter(adapter);



    }

    @Override
    public void initListener() {
        mtbActSucceedInvitation.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
