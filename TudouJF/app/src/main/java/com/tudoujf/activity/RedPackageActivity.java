package com.tudoujf.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.RedPackageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.RedPackageActLvBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            RedPackageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：选择红包和选择加息券activity
 * history：
 * ===================================================
 */

public class RedPackageActivity extends BaseActivity {
    @BindView(R.id.mtb_act_redpackage)
    MTopBarView mtbRedpackage;
    @BindView(R.id.tv_act_redpackage1)
    TextView tvRedpackageCount;
    @BindView(R.id.lv_act_redpackage)
    ListView lvRedpackage;
    @BindView(R.id.tv_act_redpackage2)
    TextView tvAffirmUse;

    private List<RedPackageActLvBean>  list;

    @Override
    public int getLayoutResId() {
        return R.layout.act_redpackage;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RedPackageActLvBean bean=new RedPackageActLvBean();
            bean.setContent1("单笔投资满1000元  最低");
            bean.setContent2("投资0个月可使用");
            bean.setContent3("50元 抽奖红包");
            bean.setContent4("可使用");
            list.add(bean);
        }
    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbRedpackage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbRedpackage.setLayoutParams(params);

        RedPackageActLvAdapter adapter=new RedPackageActLvAdapter(list,this);
        lvRedpackage.setAdapter(adapter);
        HeightUtils.setListviewHeight(lvRedpackage);

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
