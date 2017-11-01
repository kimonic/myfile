package com.tudoujf.fragment.mywelfare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.header.flyrefresh.MountanScenceView;
import com.tudoujf.R;
import com.tudoujf.adapter.HomeFragVPAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.config.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ================================================
 * name:            MyWelfareFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/18
 * description： 我的福利-->我的福利fragment
 * history：
 * ===================================================
 */

public class MyWelfareFragment extends BaseFragment {
    @BindView(R.id.tv_frag_redpackage_keyonghongbao)
    TextView tvKeYongHongBao;
    @BindView(R.id.tv_frag_redpackage_yiyonghongbao)
    TextView tvYiYongHongBao;
    @BindView(R.id.tv_frag_redpackage_guoqihongbao)
    TextView tvGuoQiHongBao;
    @BindView(R.id.vp_frag_redpackage_two)
    ViewPager vpTwo;
    Unbinder unbinder;

    private int type = 1;

    private  String  url;

    private int[] redPackgeTilte = new int[]{
            R.string.keyonghongbao,
            R.string.yiyonghongbao,
            R.string.guoqihongbao,
    };

    private int[] jiaXiQuanTilte = new int[]{
            R.string.keyongjiaxiquan,
            R.string.yiyongjiaxiquan,
            R.string.guoqijiaxiquan,
    };


    private int[] fanXianQuanTilte = new int[]{
            R.string.keyongfanxianquan,
            R.string.yiyongfanxianquan,
            R.string.guoqifanxianquan,
    };


    private List<TextView> listTv;
    private List<Fragment> listFrag;

    @Override
    public int layoutRes() {
        return R.layout.frag_redpackage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_redpackage_keyonghongbao://可用
                changeButtonStyle(0);
                vpTwo.setCurrentItem(0);
                break;
            case R.id.tv_frag_redpackage_yiyonghongbao://已用
                changeButtonStyle(1);
                vpTwo.setCurrentItem(1);
                break;
            case R.id.tv_frag_redpackage_guoqihongbao://过期
                changeButtonStyle(2);
                vpTwo.setCurrentItem(2);
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 1);
        }

        listTv = new ArrayList<>(3);
        listTv.add(tvKeYongHongBao);
        listTv.add(tvYiYongHongBao);
        listTv.add(tvGuoQiHongBao);


        switch (type) {
            case 1://红包
                url= Constants.RED_BAG;
                initTitle(redPackgeTilte);
                initFragment();
                break;
            case 2://加息券
                url= Constants.JIA_XI_QUAN;
                initTitle(jiaXiQuanTilte);
                initFragment();
                break;
            case 3://返现券
                initTitle(fanXianQuanTilte);
                initFragment();
                break;
        }

    }

    private void initFragment() {
        listFrag = new ArrayList<>();
        switch (type) {
            case 1:
                Bundle bundle1=new Bundle();
                bundle1.putInt("type",1);
                Bundle bundle2=new Bundle();
                bundle2.putInt("type",2);
                Bundle bundle3=new Bundle();
                bundle3.putInt("type",3);


                UsableWelfareFragment fragment1 = new UsableWelfareFragment();
                fragment1.setArguments(bundle1);
                UsableWelfareFragment fragment2 = new UsableWelfareFragment();
                fragment2.setArguments(bundle2);
                UsableWelfareFragment fragment3 = new UsableWelfareFragment();
                fragment3.setArguments(bundle3);


                listFrag.add(fragment1);
                listFrag.add(fragment2);
                listFrag.add(fragment3);
                break;
            case 2:
                Bundle bundle4=new Bundle();
                bundle4.putInt("type",4);

                Bundle bundle5=new Bundle();
                bundle5.putInt("type",5);

                Bundle bundle6=new Bundle();
                bundle6.putInt("type",6);

                UsableWelfareFragment fragment4 = new UsableWelfareFragment();
                fragment4.setArguments(bundle4);
                UsableWelfareFragment fragment5 = new UsableWelfareFragment();
                fragment5.setArguments(bundle5);
                UsableWelfareFragment fragment6 = new UsableWelfareFragment();
                fragment6.setArguments(bundle6);



                listFrag.add(fragment4);
                listFrag.add(fragment5);
                listFrag.add(fragment6);
                break;
            case 3:
                Bundle bundle7=new Bundle();
                bundle7.putInt("type",7);

                Bundle bundle8=new Bundle();
                bundle8.putInt("type",8);

                Bundle bundle9=new Bundle();
                bundle9.putInt("type",9);


                UsableWelfareFragment fragment7 = new UsableWelfareFragment();
                fragment7.setArguments(bundle7);
                UsableWelfareFragment fragment8 = new UsableWelfareFragment();
                fragment8.setArguments(bundle8);
                UsableWelfareFragment fragment9 = new UsableWelfareFragment();
                fragment9.setArguments(bundle9);

                listFrag.add(fragment7);
                listFrag.add(fragment8);
                listFrag.add(fragment9);
                break;

        }

    }

    private void initTitle(int[] title) {
        for (int i = 0; i < 3; i++) {
            listTv.get(i).setText(title[i]);
        }
    }

    @Override
    public void initView() {

        HomeFragVPAdapter adapter = new HomeFragVPAdapter(getChildFragmentManager(), listFrag);
        vpTwo.setAdapter(adapter);
        vpTwo.setOffscreenPageLimit(3);

    }

    @Override
    public void initListener() {

        vpTwo.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeButtonStyle(position);
            }
        });

        tvKeYongHongBao.setOnClickListener(this);
        tvYiYongHongBao.setOnClickListener(this);
        tvGuoQiHongBao.setOnClickListener(this);

    }

    private void changeButtonStyle(int postion) {
        for (int i = 0; i < 3; i++) {
            if (i == postion) {
                listTv.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));
                listTv.get(i).setBackgroundResource(R.color.act_infopublish_bac);
            } else {
                listTv.get(i).setTextColor(getResources().getColor(R.color.color_black));
                listTv.get(i).setBackgroundResource(R.color.color_white);
            }
        }

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
