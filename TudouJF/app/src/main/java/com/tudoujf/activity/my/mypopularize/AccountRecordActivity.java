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
 * name:            AccountRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广页面-->结算记录activity
 * history：
 * ===================================================
 */

public class AccountRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_accountrecord)
    MTopBarView mtbActAccountRecord;
    @BindView(R.id.lv_act_accountrecord)
    ListView lvActAccountRecord;
    @BindView(R.id.srl_act_accountrecord)
    SmartRefreshLayout srlActAccountRecord;
    private List<SucceedInvitationActBean> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_accountrecord;
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
            bean.setUserName("20XX/XX/XX");
            bean.setTouZiZongE("000,000.00");
            bean.setHuanKuanZongE("结算成功");

            if (i%2==1){
                bean.setBacFlag(2);
            }
            list.add(bean);
        }
    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActAccountRecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActAccountRecord.setLayoutParams(params);

        srlActAccountRecord.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActAccountRecord.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActAccountRecord.setRefreshFooter(new BallPulseFooter(this));

        SucceedInvitationActLvAdapter adapter=new SucceedInvitationActLvAdapter(this,list);
        lvActAccountRecord.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        mtbActAccountRecord.getLeftTV().setOnClickListener(new View.OnClickListener() {
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
