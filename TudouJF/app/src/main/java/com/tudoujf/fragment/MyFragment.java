package com.tudoujf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.home.MyMessageActivity;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.FundDetailsActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.RechargeActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.WithdrawActivity;
import com.tudoujf.activity.my.myaccount.MyAccountActivity;
import com.tudoujf.activity.my.myearnings.MyEarningsActivity;
import com.tudoujf.activity.my.mypopularize.MyPopularizeActivity;
import com.tudoujf.activity.my.myproject.MyProjectActivity;
import com.tudoujf.activity.my.mywelfare.MyWelfareActivity;
import com.tudoujf.activity.my.set.SetActivity;
import com.tudoujf.adapter.MyFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.MyFragBean;
import com.tudoujf.bean.databean.PersonalCenterBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.Unbinder;

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
    @BindView(R.id.fl_frag_my_message)
    FrameLayout flMessage;
    @BindView(R.id.tv_frag_my)
    TextView tvFragMy;
    @BindView(R.id.tv_frag_my_realname)
    TextView tvRealName;
    @BindView(R.id.iv_frag_my)
    ImageView ivFragMy;
    @BindView(R.id.ll_frag_my_chongzhi)
    LinearLayout llChongZhi;
    @BindView(R.id.ll_frag_my_tixian)
    LinearLayout llTiXian;
    @BindView(R.id.ll_frag_my_myaccount)
    LinearLayout llMyAccount;
    @BindView(R.id.ll_frag_my_funddetails)
    LinearLayout llFundDetails;
    @BindView(R.id.srl_frag_my)
    SmartRefreshLayout srl;
    @BindView(R.id.iv_frag_my_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_frag_my_username)
    TextView tvUsername;
    @BindView(R.id.iv_frag_my_vip)
    ImageView ivVip;
    @BindView(R.id.tv_frag_my_vipapply)
    TextView tvVipapply;
    @BindView(R.id.tv_frag_my_message)
    TextView tvMessage;
    @BindView(R.id.tv_frag_my_amount)
    TextView tvAmount;
    @BindView(R.id.tv_frag_my_total)
    TextView tvTotal;
    @BindView(R.id.tv_frag_my_canuse)
    TextView tvCanuse;
    @BindView(R.id.tv_frag_my_experience)
    TextView tvExperience;
    @BindView(R.id.ll_frag_my_realname)
    LinearLayout llRealname;
    Unbinder unbinder1;

    private List<MyFragBean> list;

    private int[] titleResId = new int[]{
            R.string.wodejifen,
            R.string.wodeshouyi,
            R.string.wodetuiguang,
            R.string.wodexiangmu,
            R.string.wodefuli,
            R.string.zhuanshukefu,
            R.string.shezhi,
    };

    private int[] iconResId = new int[]{
            R.drawable.frag_my_wodejifen,
            R.drawable.frag_my_wodeshouyi,
            R.drawable.frag_my_wodetuiguang,
            R.drawable.frag_my_wodexiangmu,
            R.drawable.frag_my_wodefuli,
            R.drawable.frag_my_zhuanshukefu,
            R.drawable.frag_my_shezhi,
    };
    private PersonalCenterBean bean;

    @Override
    public int layoutRes() {
        return R.layout.frag_my;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_my://打开客服界面
                ToastUtils.showToast(getActivity(), "我将关闭客服界面!");
                break;
            case R.id.iv_frag_my://关闭客服
                ToastUtils.showToast(getActivity(), "我将打开客服界面!");
                break;
            case R.id.ll_frag_my_chongzhi://充值
                Bundle bundle = new Bundle();
                if (bean != null) {
                    bundle.putString("balance", bean.getAmount_balance());
                } else {
                    bundle.putString("balance", getResources().getString(R.string.zanwu));
                }
                openActivity(RechargeActivity.class, bundle);
                break;
            case R.id.ll_frag_my_tixian://提现
                Intent intent = new Intent(getActivity(), WithdrawActivity.class);
                if (bean != null) {
                    intent.putExtra("amount", bean.getAmount_balance());
                } else {
                    intent.putExtra("amount", getResources().getString(R.string.zanwu));
                }
                startActivity(intent);
//                openActivity(WithdrawActivity.class);
                break;
            case R.id.ll_frag_my_funddetails://资金详情
                openActivity(FundDetailsActivity.class);
                break;
            case R.id.ll_frag_my_myaccount://我的账户
                openActivity(MyAccountActivity.class);

                break;
            case R.id.tv_frag_my_realname://跳转实名认证页面
                openActivity(RealNameAuthenticationHuiFuActivity.class);
                break;
            case R.id.fl_frag_my_message://我的消息页面
                openActivity(MyMessageActivity.class);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        for (int i = 0; i < titleResId.length; i++) {
            MyFragBean bean = new MyFragBean();
            bean.setTitle(getResources().getString(titleResId[i]));
            bean.setResId1(iconResId[i]);
            if (i == 6) {
                bean.setMark(getResources().getString(R.string.weixinguanzhu));
            }
            list.add(bean);
        }

    }

    @Override
    public void initView() {
        MyFragLvAdapter adapter = new MyFragLvAdapter(list, getActivity());
        lvFragMy.setAdapter(adapter);
        HeightUtils.setListviewHeight(lvFragMy);


        //设置全区背景色
        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srl.setEnableLoadmore(false);
        initDataFromInternet();

    }

    @Override
    public void initListener() {
        lvFragMy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://我的积分
                        openActivity(SignInActivity.class);
                        break;
                    case 1://我的收益
                        openActivity(MyEarningsActivity.class);
                        break;
                    case 2://我的推广
                        openActivity(MyPopularizeActivity.class);
                        break;
                    case 3://我的项目
                        openActivity(MyProjectActivity.class);
                        break;
                    case 4://我的福利
                        openActivity(MyWelfareActivity.class);
                        break;
                    case 5://专属客服
                        break;
                    case 6://设置
                        openActivity(SetActivity.class);
                        break;
                }
            }
        });

        tvFragMy.setOnClickListener(this);
        ivFragMy.setOnClickListener(this);
        llFundDetails.setOnClickListener(this);
        llChongZhi.setOnClickListener(this);
        llTiXian.setOnClickListener(this);
        llMyAccount.setOnClickListener(this);
        tvRealName.setOnClickListener(this);
        flMessage.setOnClickListener(this);

        srl.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                page = 1;
//                initDataFromInternet();
                initDataFromInternet();

            }
        });


    }

    @Override
    public void initDataFromInternet() {
        Log.e("TAG", "initDataFromInternet: -----个人中心");


        showPDialog();

        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));


        HttpMethods.getInstance().POST(getActivity(), Constants.PERSONAL_CENTER_MAIN, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----个人中心接口返回数据--------" + result);
                        if (srl.isRefreshing()) {
                            srl.finishRefresh();
                        }

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<PersonalCenterBean>() {
                        }.getType(), PersonalCenterBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (PersonalCenterBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                        }

                    }
                });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            ImageGlideUtils.loadCircularImage(ivIcon, bean.getAvatar());
            tvUsername.setText((getResources().getString(R.string.huanyingni) + bean.getMember_name()));
            tvAmount.setText(StringUtils.getCommaDecimalsStr(bean.getInterest_award()));
            tvTotal.setText(StringUtils.getCommaDecimalsStr(bean.getAmount_all()));
            tvCanuse.setText(StringUtils.getCommaDecimalsStr(bean.getAmount_balance()));
            tvExperience.setText(StringUtils.getCommaDecimalsStr(bean.getExperience_balance()));
            tvMessage.setText(bean.getCount());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initDataFromInternet();
    }
}
