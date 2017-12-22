package com.tudoujf.fragment;

import android.animation.ValueAnimator;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.home.MyExperienceGoldActivity;
import com.tudoujf.activity.home.MyMessageActivity;
import com.tudoujf.activity.home.NewcomerExperienceBidActivity;
import com.tudoujf.activity.home.SignInActivity;
import com.tudoujf.activity.managemoney.ProductDetailsActivity;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.FundDetailsActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.RechargeActivity;
import com.tudoujf.activity.my.funddetailschongzhitixian.WithdrawActivity;
import com.tudoujf.activity.my.myaccount.BankCardManageActivity;
import com.tudoujf.activity.my.myaccount.MyAccountActivity;
import com.tudoujf.activity.my.myaccount.VIPActivity;
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
import com.tudoujf.bean.databean.WithDrawBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import cn.udesk.UdeskConst;
import cn.udesk.UdeskSDKManager;

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
    @BindView(R.id.fl_frag_my)
    FrameLayout flFragMy;
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
    @BindView(R.id.ll_frag_my_myexperience)
    LinearLayout llMyExperience;
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

    private List<MyFragBean> list;

    private boolean hide = false;

    //我的收益暂时取消
    private int[] titleResId = new int[]{
            R.string.wodejifen,
//            R.string.wodeshouyi,
            R.string.wodetuiguang,
            R.string.wodexiangmu,
            R.string.wodefuli,
            R.string.zhuanshukefu,
            R.string.shezhi,
    };

    private int[] iconResId = new int[]{
            R.drawable.frag_my_wodejifen,
//            R.drawable.frag_my_wodeshouyi,
            R.drawable.frag_my_wodetuiguang,
            R.drawable.frag_my_wodexiangmu,
            R.drawable.frag_my_wodefuli,
            R.drawable.frag_my_zhuanshukefu,
            R.drawable.frag_my_shezhi,
    };
    private PersonalCenterBean bean;
    private FrameLayout.LayoutParams params;
    private AlertDialog promptDialog;
    private AlertDialog promptDialog1;

    @Override
    public int layoutRes() {
        return R.layout.frag_my;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frag_my://关闭客服
                retractAnim(24 * ScreenSizeUtils.getDensity(getActivity()), -50 * ScreenSizeUtils.getDensity(getActivity()));
                hide = true;
                break;
            case R.id.iv_frag_my://打开客服界面
                if (hide) {
                    hide = false;
                    retractAnim(-50 * ScreenSizeUtils.getDensity(getActivity()), 24 * ScreenSizeUtils.getDensity(getActivity()));
                } else {
                    UdeskSDKManager.getInstance().initApiKey(getActivity(), getResources().getString(R.string.domain),
                            getResources().getString(R.string.appkey), getResources().getString(R.string.appid));
                    String sdktoken = UserConfig.getInstance().getLoginToken(getActivity());
                    Map<String, String> info = new HashMap<>();
                    info.put(UdeskConst.UdeskUserInfo.USER_SDK_TOKEN, sdktoken);
                    info.put(UdeskConst.UdeskUserInfo.NICK_NAME, bean.getMember_name());
//                info.put(UdeskConst.UdeskUserInfo.EMAIL, "0631@163.com");
//                info.put(UdeskConst.UdeskUserInfo.CELLPHONE, "15651818750");
//                info.put(UdeskConst.UdeskUserInfo.DESCRIPTION, "描述信息");
                    UdeskSDKManager.getInstance().setUserInfo(getActivity(), sdktoken, info);
                    UdeskSDKManager.getInstance().entryChat(getActivity());
                }

                break;
            case R.id.ll_frag_my_chongzhi://充值
                if (bean != null && "-1".equals(bean.getIs_trust())) {
                    if (promptDialog1 == null) {
                        promptDialog1 = DialogUtils.showPromptDialog(getActivity(), "提示", "您还没有实名，请先实名!", new DialogUtils.DialogUtilsClickListener() {
                            @Override
                            public void onClick() {
                                promptDialog1.dismiss();
                                openActivity(RealNameAuthenticationHuiFuActivity.class);
                            }
                        });
                    } else {
                        promptDialog1.show();
                    }
                } else {
                    Bundle bundle = new Bundle();
                    if (bean != null) {
                        bundle.putString("balance", bean.getAmount_balance());
                    } else {
                        bundle.putString("balance", getResources().getString(R.string.zanwu));
                    }
                    openActivity(RechargeActivity.class, bundle);
                }
                break;
            case R.id.ll_frag_my_tixian://提现
                if (bean != null && "-1".equals(bean.getIs_trust())) {
                    if (promptDialog1 == null) {
                        promptDialog1 = DialogUtils.showPromptDialog(getActivity(), "提示", "您还没有实名，请先实名!", new DialogUtils.DialogUtilsClickListener() {
                            @Override
                            public void onClick() {
                                promptDialog1.dismiss();
                                openActivity(RealNameAuthenticationHuiFuActivity.class);
                            }
                        });
                    } else {
                        promptDialog1.show();
                    }
                } else {
                    withdraw();//判断是否绑卡
                }

                break;
            case R.id.ll_frag_my_funddetails://资金详情
                Intent intent = new Intent(getActivity(), FundDetailsActivity.class);
                intent.putExtra("is_trust", bean.getIs_trust());
                startActivity(intent);

//                openActivity(FundDetailsActivity.class);
                break;
            case R.id.ll_frag_my_myaccount://我的账户
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", bean.getMember_name());
                bundle1.putString("iconurl", bean.getAvatar());
                openActivity(MyAccountActivity.class, bundle1);
                break;
            case R.id.tv_frag_my_realname://跳转实名认证页面
                openActivity(RealNameAuthenticationHuiFuActivity.class);
                break;
            case R.id.fl_frag_my_message://我的消息页面
                openActivity(MyMessageActivity.class);
                break;
            case R.id.tv_frag_my_vipapply://申请vip
                if (bean != null && "1".equals(bean.getIs_trust())) {
                    Intent intent1 = new Intent(getActivity(), VIPActivity.class);
                    intent1.putExtra("balance", StringUtils.getCommaDecimalsStr(bean.getAmount_balance()));
                    startActivity(intent1);
                } else {
                    DialogUtils.showDialog(getActivity(), R.string.qingxianshimingrenzheng, R.string.queding, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openActivity(RealNameAuthenticationHuiFuActivity.class);
                        }
                    });
                }
                break;
            case R.id.ll_frag_my_myexperience://我的体验金入口
                if (StringUtils.string2Float(bean.getExperience_balance()) > 0) {
                    Intent intent1 = new Intent(getActivity(), NewcomerExperienceBidActivity.class);
                    intent1.putExtra("loan_id", bean.getExperience_loan_id());
                    startActivity(intent1);
                } else {
                    if ("1".equals(bean.getIs_trust())) {//已实名
                        openActivity(MyExperienceGoldActivity.class);//打开我体验金页面
                    } else {//未实名
                        Intent intent1 = new Intent(getActivity(), NewcomerExperienceBidActivity.class);
                        intent1.putExtra("loan_id", bean.getExperience_loan_id());
                        startActivity(intent1);
                    }
                }

                break;
            case R.id.ll_frag_my_realname://实名认证
                openActivity(RealNameAuthenticationHuiFuActivity.class);
                break;
        }
    }


    private void retractAnim(int start, int marginEnd) {
        params = (FrameLayout.LayoutParams) flFragMy.getLayoutParams();
        ValueAnimator animator;
        animator = ValueAnimator.ofInt(start, marginEnd);
        animator.setDuration(1000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.setMarginEnd((Integer) animation.getAnimatedValue());
                flFragMy.setLayoutParams(params);
            }
        });
    }

    public void showService() {
        if (flFragMy != null) {
            flFragMy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        for (int i = 0; i < titleResId.length; i++) {
            MyFragBean bean = new MyFragBean();
            bean.setTitle(getResources().getString(titleResId[i]));
            bean.setResId1(iconResId[i]);
            if (i == 5) {
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


//        srl.setPrimaryColorsId(R.color.global_theme_background_color);
        srl.setRefreshHeader(new TuDouHeader(getActivity()));
//        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
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
//                    case 1://我的收益暂时去掉
//                        openActivity(MyEarningsActivity.class);
//                        break;
                    case 1://我的推广
                        openActivity(MyPopularizeActivity.class);
                        break;
                    case 2://我的项目
                        openActivity(MyProjectActivity.class);
                        break;
                    case 3://我的福利
                        openActivity(MyWelfareActivity.class);
                        break;
                    case 4://专属客服

                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        if (cm != null) {
                            cm.setText(bean.getWeChatID());
                            DialogUtils.showDialog(getActivity(), R.string.gzhyfz, R.string.quzhantie, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isWeixinAvilible(getActivity())) {
                                        Intent intent = new Intent();
                                        ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                                        intent.setAction(Intent.ACTION_MAIN);
                                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setComponent(cmp);
                                        startActivity(intent);
                                    } else {
                                        ToastUtils.showToast(getActivity(), R.string.ninweianzhuangweixin);
                                    }
                                }
                            });
                        } else {
                            ToastUtils.showToast(getActivity(), R.string.copyerror);
                        }
                        break;
                    case 5://设置
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
        tvVipapply.setOnClickListener(this);
        llMyExperience.setOnClickListener(this);
        llRealname.setOnClickListener(this);

        srl.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDataFromInternet();

            }
        });


    }

    @Override
    public void initDataFromInternet() {
        String loginToken = UserConfig.getInstance().getLoginToken(getActivity());
        if (!"".equals(loginToken)) {
            showPDialog();
            TreeMap<String, String> map = new TreeMap<>();
            map.put("login_token", loginToken);
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

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            dismissPDialog();
                            if (srl.isRefreshing()) {
                                srl.finishRefresh();
                            }
                            ToastUtils.showToast(getActivity(), R.string.huoqugerenzhongxinshujushibai);
                        }
                    });
        }
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            ImageGlideUtils.loadCircularImage(ivIcon, bean.getAvatar() + "?aa=" + System.currentTimeMillis());
            tvUsername.setText((getResources().getString(R.string.huanyingni) + bean.getMember_name()));
            tvAmount.setText(StringUtils.getCommaDecimalsStr(bean.getInterest_award()));
            tvTotal.setText(StringUtils.getCommaDecimalsStr(StringUtils.changeScientificNotation(bean.getAmount_all())));
            tvCanuse.setText(StringUtils.getCommaDecimalsStr(bean.getAmount_balance()));
            tvExperience.setText(StringUtils.getCommaDecimalsStr(bean.getExperience_balance()));


            if ("1".equals(bean.getIsVip())) {
                ivVip.setImageResource(R.drawable.frag_my_vipt);
            } else {
                ivVip.setImageResource(R.drawable.frag_my_vip);
            }

            if ("1".equals(bean.getIs_trust())) {
                llRealname.setVisibility(View.GONE);
            } else {
                llRealname.setVisibility(View.VISIBLE);
            }

            int count = StringUtils.string2Integer(bean.getCount());
            if (count == 0) {
                setImageSize(16);
                flMessage.setBackgroundResource(R.drawable.frag_home_noinfo);
                tvMessage.setText("");
            } else if (count < 100) {
                setImageSize(18);
                flMessage.setBackgroundResource(R.drawable.frag_home_info);
                tvMessage.setText(bean.getCount());
            } else {
                setImageSize(18);
                flMessage.setBackgroundResource(R.drawable.frag_home_info);
                tvMessage.setText(getResources().getString(R.string.ninenine));
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        initDataFromInternet();
    }

    private boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 调用提现接口
     */
    private void withdraw() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        HttpMethods.getInstance().POST(getActivity(), Constants.WITHDRAW, map, getActivity().getLocalClassName(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------提现接口请求返回数据-----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<WithDrawBean>() {
                }.getType(), WithDrawBean.class, getActivity());
                if (bean1 != null) {
                    WithDrawBean withDrawBean = (WithDrawBean) bean1;

                    if ("1".equals(withDrawBean.getIs_bind())) {
                        Intent intent = new Intent(getActivity(), WithdrawActivity.class);
                        if (bean != null) {
                            intent.putExtra("amount", bean.getAmount_balance());
                        } else {
                            intent.putExtra("amount", getResources().getString(R.string.zanwu));
                        }
                        startActivity(intent);
                    } else {
                        if (promptDialog == null) {
                            promptDialog = DialogUtils.showPromptDialog(getActivity(), "提示", "请绑定银行卡!", new DialogUtils.DialogUtilsClickListener() {
                                @Override
                                public void onClick() {
                                    promptDialog.dismiss();
                                    openActivity(BankCardManageActivity.class);
                                }
                            });
                        } else {
                            promptDialog.show();
                        }

//                        DialogUtils.showDialog(getActivity(), R.string.qingxianbangdingyinhangka,
//                                R.string.queding, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        openActivity(BankCardManageActivity.class);
//                                    }
//                                });
                    }
                } else {
                    ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                }


            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissPDialog();
            }
        });

    }

    private void setImageSize(int  size){
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) flMessage.getLayoutParams();
        params.width=size*ScreenSizeUtils.getDensity(getActivity());
        params.height=size*ScreenSizeUtils.getDensity(getActivity());
        flMessage.setLayoutParams(params);
    }

}
