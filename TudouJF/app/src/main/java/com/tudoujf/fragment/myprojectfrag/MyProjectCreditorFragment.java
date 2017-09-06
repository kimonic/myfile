package com.tudoujf.fragment.myprojectfrag;

import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.utils.HeightUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyProjectCreditorFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目--->债权项目列表
 * history：
 * * ====================================================================
 */

public class MyProjectCreditorFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojectcreditor)
    ListView lvTotal;
    @BindView(R.id.srl_frag_myprojectcreditor)
    SmartRefreshLayout srlTotal;

    private List<MyProjectTotalFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojectcreditor;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        list=new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            MyProjectTotalFragBean bean=new MyProjectTotalFragBean();
            bean.setTitle("房产抵押贷款201703270003");
            bean.setTitle1("00,000.00");
            bean.setTitle2("00,000.00");
            bean.setTitle3("20XX-XX-XX");
            bean.setTitle4("待收本息(元)");
            bean.setTitle5("债权价值(元)");
            bean.setTitle6("还款期限");
            bean.setTitle7("转让期数:7/12");
            bean.setTouzijindu(i%10*0.1f);
            bean.setTitle8("00,000.00");
            bean.setType(i%5+1);
            list.add(bean);
        }
    }

    @Override
    public void initView() {

        MyProjectTotalFragLvAdapter  adapter=new MyProjectTotalFragLvAdapter(getActivity(),list);
        lvTotal.setAdapter(adapter);

        HeightUtils.setListviewHeight(lvTotal);

        srlTotal.setPrimaryColorsId(R.color.global_theme_background_color);
        srlTotal.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srlTotal.setRefreshFooter(new BallPulseFooter(getActivity()));
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


}















