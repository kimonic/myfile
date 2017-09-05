package com.tudoujf.fragment.myprojectfrag;

import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.MyProjectTotalFragLvAdapter;
import com.tudoujf.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyProjectTotalFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目--->全部,投资项目列表
 * history：
 * * ====================================================================
 */

public class MyProjectTotalFragment extends BaseFragment {
    @BindView(R.id.lv_frag_myprojecttotal)
    ListView lvTotal;
    @BindView(R.id.srl_frag_myprojecttotal)
    SmartRefreshLayout srlTotal;

    private List<MyProjectTotalFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_myprojecttotal;
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
            bean.setTitle4("投资金额(元)");
            bean.setTitle5("预期收益(元)");
            bean.setTitle6("回款时间");
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
