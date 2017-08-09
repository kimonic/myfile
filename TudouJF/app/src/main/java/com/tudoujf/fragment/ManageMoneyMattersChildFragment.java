package com.tudoujf.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.ManageMoneyMattersChildFragLvAdapter;
import com.tudoujf.adapter.ManageMoneyMattersFragLvAdapter;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.ManageMoneyMattersFragBean;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ====================================================================
 * name:            ManageMoneyMattersChildFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  理财fragment
 * history：
 * * ====================================================================
 */

public class ManageMoneyMattersChildFragment extends BaseFragment {
    @BindView(R.id.lv_frag_managemoneymatterstouchild)
    ListView lvFragManageMoneyMatters;
    @BindView(R.id.ll_frag_managemoneymatterschild1)
    LinearLayout llOrder1;
    @BindView(R.id.ll_frag_managemoneymatterschild2)
    LinearLayout llOrder2;
    @BindView(R.id.ll_frag_managemoneymatterschild3)
    LinearLayout llOrder3;
    @BindView(R.id.tv_frag_managemoneymatterschild1)
    TextView tvOrder1;
    @BindView(R.id.tv_frag_managemoneymatterschild2)
    TextView tvOrder2;
    @BindView(R.id.tv_frag_managemoneymatterschild3)
    TextView tvOrder3;
    Unbinder unbinder;

    private List<ManageMoneyMattersFragBean> list;

    private int type = 1;

    private int count1 = 0, count2 = 0;

    @Override
    public int layoutRes() {
        return R.layout.frag_managemoneymatterschild;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_frag_managemoneymatterschild1:
                tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count1 % 2 == 1) {
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow1);
                }else {
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count1++;
                break;
            case R.id.ll_frag_managemoneymatterschild2:
                tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count2 % 2 == 1) {
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow1);
                }else {
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count2++;
                break;
            case R.id.ll_frag_managemoneymatterschild3:
                showAlertDialog();
                break;
        }

    }

    @Override
    public void initDataFromIntent() {

        type = getArguments().getInt("type", 1);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ManageMoneyMattersFragBean bean = new ManageMoneyMattersFragBean();

            bean.setInvestTime(i + "个月");
            bean.setInvestProgress(0.1f * i);
            if (i % 2 == 0) {
                bean.setInvestNow(false);
                bean.setAward(true);
            } else {
                bean.setInvestNow(true);
                bean.setAward(false);
            }


            bean.setInvestSum("10,000,000.00");
            bean.setAwardValue(i * 0.01f);

            bean.setNianHuaShouYi(i + ".00%");
            bean.setShengYuKeTou(i * 123456 + ".00元");
            bean.setTitle("房产抵押贷款20170327003");


            list.add(bean);
        }

    }

    @Override
    public void initView() {
        if (type == 1) {
            ManageMoneyMattersFragLvAdapter actLVAdapter = new ManageMoneyMattersFragLvAdapter(getActivity(), list);
            lvFragManageMoneyMatters.setAdapter(actLVAdapter);
        } else {
            ManageMoneyMattersChildFragLvAdapter actLVAdapter = new ManageMoneyMattersChildFragLvAdapter(getActivity(), list);
            lvFragManageMoneyMatters.setAdapter(actLVAdapter);

        }

    }

    @Override
    public void initListener() {
        llOrder1.setOnClickListener(this);
        llOrder2.setOnClickListener(this);
        llOrder3.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    private void showAlertDialog(){
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_frag_managemoneymatterschild,null);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        int width=llOrder3.getWidth();
        final PopupWindow pop = new PopupWindow(view, width, ViewPager.LayoutParams.WRAP_CONTENT);
        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点
//        int[] wh=new int[2];
//        llOrder3.getLocationOnScreen(wh);

        pop.showAsDropDown(llOrder3);//显示位置

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });




    }


}
