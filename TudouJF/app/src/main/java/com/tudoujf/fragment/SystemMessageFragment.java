package com.tudoujf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.SystemMessageFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.SystemMessageFragBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ====================================================================
 * name:            SystemMessageFragment
 * guide:          MyMessageActivity-->
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：     我的消息activity中的系统消息fragment
 * history：
 * * ====================================================================
 */
public class SystemMessageFragment extends BaseFragment {
    @BindView(R.id.lv_frag_systemmessage)
    ListView lvFragSystemMessage;
    @BindView(R.id.tv_frag_systemmessage_previous)
    TextView tvFragSystemMessagePrevious;
    @BindView(R.id.tv_frag_systemmessage_next)
    TextView tvFragSystemMessageNext;

    private List<SystemMessageFragBean> list;

    @Override
    public int layoutRes() {
        return R.layout.frag_systemmessage;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            SystemMessageFragBean bean=new SystemMessageFragBean();
            bean.setTitle("XXXXXXXXXXXXXXXXXXXXXX");
            bean.setTime("201X-XX-XX");
            list.add(bean);
        }

    }

    @Override
    public void initView() {
        SystemMessageFragLvAdapter adapter=new SystemMessageFragLvAdapter(list,getActivity());
        lvFragSystemMessage.setAdapter(adapter);

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
