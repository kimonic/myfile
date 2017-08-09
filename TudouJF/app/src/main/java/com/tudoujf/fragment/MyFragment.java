package com.tudoujf.fragment;

import android.view.View;
import android.widget.ListView;

import com.tudoujf.R;
import com.tudoujf.adapter.MyFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.MyFragBean;
import com.tudoujf.utils.HeightUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            MyFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/9
 * description：  首页activity中的我的fragment
 * history：
 * * ====================================================================
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.lv_frag_my)
    ListView lvFragMy;

    private List<MyFragBean> list;

    private int[]  titleResId=new int[]{
            R.string.wodejifen,
            R.string.wodeshouyi,
            R.string.wodetuiguang,
            R.string.wodexiangmu,
            R.string.wodefuli,
            R.string.zhuanshukefu,
            R.string.shezhi,
    };

    private int[]  iconResId=new int[]{
            R.drawable.frag_my_wodejifen,
            R.drawable.frag_my_wodeshouyi,
            R.drawable.frag_my_wodetuiguang,
            R.drawable.frag_my_wodexiangmu,
            R.drawable.frag_my_wodefuli,
            R.drawable.frag_my_zhuanshukefu,
            R.drawable.frag_my_shezhi,
    };

    @Override
    public int layoutRes() {
        return R.layout.frag_my;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();
        for (int i = 0; i < titleResId.length; i++) {
            MyFragBean bean=new MyFragBean();
            bean.setTitle(getResources().getString(titleResId[i]));
            bean.setResId1(iconResId[i]);
            if (i==6){
                bean.setMark(getResources().getString(R.string.weixinguanzhu));
            }
            list.add(bean);
        }

    }

    @Override
    public void initView() {
        MyFragLvAdapter    adapter=new MyFragLvAdapter(list,getActivity());
        lvFragMy.setAdapter(adapter);
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
