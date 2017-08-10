package com.tudoujf.activity.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.SpecialOfferActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.SpecialOfferActBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            SpecialOfferActivity
 * guide:          WelcomeActivity-->GuideActivity--->HomeActivity
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：活动页activity
 * history：
 * ===================================================
 */

public class SpecialOfferActivity extends BaseActivity {
    @BindView(R.id.mtb_act_specialoffer)
    MTopBarView mtbSpecialOffer;
    @BindView(R.id.lv_act_specialoffer)
    ListView lvSpecialOffer;

    private List<SpecialOfferActBean>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_specialoffer;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();
        SpecialOfferActBean bean1=new SpecialOfferActBean();
        bean1.setTitle("房产抵押+履约保险业务");
        bean1.setTime("活动长期有效");
        bean1.setResId(R.drawable.act_specialoffer_icon1);

        SpecialOfferActBean bean2=new SpecialOfferActBean();
        bean2.setTitle("不负春光,与你同行");
        bean2.setTime("2017年7月17日--2017年8月17日");
        bean2.setResId(R.drawable.act_specialoffer_icon2);


        SpecialOfferActBean bean3=new SpecialOfferActBean();
        bean3.setTitle("聚财聚友聚人气");
        bean3.setTime("活动已结束");
        bean3.setResId(R.drawable.act_specialoffer_icon3);

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbSpecialOffer.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbSpecialOffer.setLayoutParams(params);

        mtbSpecialOffer.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        SpecialOfferActLvAdapter adapter=new SpecialOfferActLvAdapter(list,this);
        lvSpecialOffer.setAdapter(adapter);
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
