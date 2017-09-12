package com.tudoujf.fragment.mywelfare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.RedPackageActLvAdapter;
import com.tudoujf.adapter.UsableWelfareFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.RedPackageActBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ================================================================
 * name:            UsableWelfareFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/18
 * description： 我的福利-->我的福利fragment-->可用福利fragment
 * history：
 * ==================================================================
 */

public class UsableWelfareFragment extends BaseFragment {
    @BindView(R.id.ll_frag_usable_use)
    LinearLayout llFragUsableUse;
    @BindView(R.id.lv_frag_usable_info)
    ListView lvFragUsableInfo;
    Unbinder unbinder;

    private List<RedPackageActBean> list;
    private UsableWelfareFragLvAdapter adapter;
    /**
     * 当前activity的类型,是红包还是加息券
     */
    private int actType = 1;

    @Override
    public int layoutRes() {
        return R.layout.frag_usablewelfare;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_frag_usable_use://跳转红包使用界面
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            actType = bundle.getInt("type", 1);
        }

        list = new ArrayList<>();

        switch (actType) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("元");
                    bean.setBackground(1);
                    bean.setType(actType);
                    bean.setQuanOrRedPackage(true);
                    bean.setValid(true);
                    list.add(bean);
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setValid(false);
                    bean.setContent7("元");
                    bean.setBackground(1);
                    bean.setQuanOrRedPackage(true);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("元");
                    bean.setBackground(1);
                    bean.setValid(false);
                    bean.setQuanOrRedPackage(true);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
            case 4:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("8.88");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("%");
                    bean.setBackground(1);
                    bean.setType(actType);
                    bean.setQuanOrRedPackage(false);
                    bean.setValid(true);
                    list.add(bean);
                }
                break;
            case 5:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("8.88");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("%");
                    bean.setBackground(1);
                    bean.setQuanOrRedPackage(false);
                    bean.setValid(false);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
            case 6:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("8.88");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("%");
                    bean.setBackground(1);
                    bean.setValid(false);
                    bean.setQuanOrRedPackage(false);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
            case 7:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("元");
                    bean.setBackground(1);
                    bean.setValid(true);
                    bean.setQuanOrRedPackage(false);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
            case 8:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("元");
                    bean.setBackground(1);
                    bean.setType(actType);
                    bean.setQuanOrRedPackage(false);
                    bean.setValid(false);
                    list.add(bean);
                }
                break;
            case 9:
                for (int i = 0; i < 10; i++) {
                    RedPackageActBean bean = new RedPackageActBean();
                    bean.setContent1("单笔投资满1000元  最低");
                    bean.setContent2("投资0个月可使用");
                    bean.setContent3("50元 抽奖红包");
                    bean.setContent4("可使用");
                    bean.setContent5("500");
                    bean.setContent6("2017/07/08");
                    bean.setContent7("元");
                    bean.setQuanOrRedPackage(false);
                    bean.setBackground(1);
                    bean.setValid(false);
                    bean.setType(actType);
                    list.add(bean);
                }
                break;
        }

    }

    @Override
    public void initView() {
        adapter = new UsableWelfareFragLvAdapter(list, getActivity());
        lvFragUsableInfo.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        llFragUsableUse.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
