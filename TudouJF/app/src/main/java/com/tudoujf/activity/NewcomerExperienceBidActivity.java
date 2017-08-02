package com.tudoujf.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.NewcomerExperienceBidActLVAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.ProductDetailsActLvBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            NewcomerExperienceBidActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/1
 * description：   新手体验标activity
 * history：
 * ===================================================
 */

public class NewcomerExperienceBidActivity extends BaseActivity {
    @BindView(R.id.mtb_act_newcomerexperiencebid)
    MTopBarView mtbNewcomerExperienceBid;
    @BindView(R.id.lv_act_newcomerexperiencebid)
    ListView lvNewcomerExperienceBid;

    List<ProductDetailsActLvBean> list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_newcomerexperiencebid;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductDetailsActLvBean bean=new ProductDetailsActLvBean();

            bean.setTouZiTime("20XX-XX-XX 00:00:00");
            bean.setTouBiaoRen("XXXXX");
            bean.setTouZiJinE("28888");
            list.add(bean);
        }

    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbNewcomerExperienceBid.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbNewcomerExperienceBid.setLayoutParams(params);

        mtbNewcomerExperienceBid.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });


        NewcomerExperienceBidActLVAdapter adapter=new NewcomerExperienceBidActLVAdapter(this,list);
        lvNewcomerExperienceBid.setAdapter(adapter);
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
