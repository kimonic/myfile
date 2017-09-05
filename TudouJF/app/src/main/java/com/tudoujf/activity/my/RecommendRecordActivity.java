package com.tudoujf.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.RecommendRecordActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.DateFilterDialog;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * ================================================
 * name:            RecommendRecordActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广页面-->推广记录activity
 * history：
 * ===================================================
 */

public class RecommendRecordActivity extends BaseActivity {
    @BindView(R.id.mtb_act_recommendrecord)
    FrameLayout mtbActRecommendrecord;
    @BindView(R.id.lv_act_recommendrecord)
    ListView lvActRecommendrecord;
    @BindView(R.id.srl_act_recommendrecord)
    SmartRefreshLayout srlActRecommendrecord;
    @BindView(R.id.tv_act_recommendrecord)
    TextView tvActRecommendrecord;
    @BindView(R.id.tv_act_recommendrecord_bac)
    TextView tvBac;
    @BindView(R.id.ll_act_recommendrecord_filtrate)
    LinearLayout llFiltrate;


    private List<RecommendRecordActBean> list;
    private DateFilterDialog dateFilterDialog;

    @Override

    public int getLayoutResId() {
        return R.layout.act_recommendrecord;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_act_recommendrecord_bac://关闭本页面
                closeActivity();
                break;
            case R.id.ll_act_recommendrecord_filtrate://筛选按钮
                if (dateFilterDialog == null) {
                    dateFilterDialog = new DateFilterDialog(this);
                    dateFilterDialog.setLisenter(new DateFilterDialog.ClickEvent() {
                        @Override
                        public void dismiss(String startTime, String endTime) {
                            // TODO: 2017/9/4  请求网络筛选展示数据
                            ToastUtils.showToast(RecommendRecordActivity.this, startTime + "-----------" + endTime);
                        }
                    });
                }
                dateFilterDialog.show();
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

        //临时数据源

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            RecommendRecordActBean bean = new RecommendRecordActBean();
            bean.setUserName("用户XXXXXX");
            bean.setTiChengBiLi("0.125%");
            bean.setTouZiTiCheng("000,000.00");
            bean.setJieKuanTiCheng("000,000.00");
            bean.setDate("20XX/XX/XX");

            if (i % 2 == 1) {
                bean.setBacFlag(2);
            }
            list.add(bean);
        }

    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbActRecommendrecord.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbActRecommendrecord.setLayoutParams(params);

        srlActRecommendrecord.setPrimaryColorsId(R.color.global_theme_background_color);
        srlActRecommendrecord.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        srlActRecommendrecord.setRefreshFooter(new BallPulseFooter(this));

        RecommendRecordActLvAdapter adapter = new RecommendRecordActLvAdapter(this, list);
        lvActRecommendrecord.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        tvBac.setOnClickListener(this);
        llFiltrate.setOnClickListener(this);
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
