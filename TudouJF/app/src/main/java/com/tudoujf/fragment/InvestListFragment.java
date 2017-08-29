package com.tudoujf.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.adapter.ManageMoneyMattersFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.ManageMoneyMattersBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            InvestListFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  理财fragment--->投资列表
 * history：
 * * ====================================================================
 */

public class InvestListFragment extends BaseFragment {
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
    @BindView(R.id.tv_frag_managemoneymatterschild11)
    TextView tvOrder11;
    @BindView(R.id.tv_frag_managemoneymatterschild12)
    TextView tvOrder12;
    @BindView(R.id.tv_frag_managemoneymatterschild13)
    TextView tvOrder13;
    @BindView(R.id.srl_frag_managemoneymatterstouchild)
    SmartRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_frag_managemoneymatterschild_total)
    TextView tvTotal;

    private List<ManageMoneyMattersBean.ItemsBean> listNew;

    private int type = 1;

    private int count1 = 0, count2 = 0;

    private ManageMoneyMattersBean bean;
    private String requestUrl;

    /**
     * 加载dialog
     */
    private AlertDialog dialog;

    /**
     * 加载的页码
     */
    private int page = 1;
    /**
     * 排序类别
     */
    private String orderType = "1";
    /**
     * 排序方式,升序降序
     */
    private String sortType = "";
    /**
     * 还款方式
     */
    private String repayType = "";

    /**
     * 投资列表适配器
     */
    private ManageMoneyMattersFragLvAdapter actLVAdapter;
    private String TAG = "ManageMoneyChild";
    /**
     * 互斥的button按钮背景色设置的list
     */
    private List<View> list;
    private List<TextView> listTV;

    private PopupWindow pop;

    @Override
    public int layoutRes() {
        return R.layout.frag_investlist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_frag_managemoneymatterschild1://按利率排序
                setBacStyle(0, "2");

                tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count1 % 2 == 1) {
                    sortType = "2";
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow1);
                } else {
                    sortType = "1";
                    tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count1++;
                initDataFromInternet();


                break;
            case R.id.ll_frag_managemoneymatterschild2://按期限排序
                setBacStyle(1, "3");

                tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow3);
                if (count2 % 2 == 1) {
                    sortType = "2";
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow1);
                } else {
                    sortType = "1";
                    tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow2);
                }
                count2++;
                initDataFromInternet();


                break;
            case R.id.ll_frag_managemoneymatterschild3://按还款方式排序

                tvOrder1.setBackgroundResource(R.drawable.xvector_updownarrow3);
                tvOrder2.setBackgroundResource(R.drawable.xvector_updownarrow3);
                setBacStyle(2, "");
                showPop();


                break;
            case R.id.tv_frag_managemoneymatterschild_total:
                setBacStyle(3, "");


                sortType = "";
                initDataFromInternet();

                break;
            case R.id.tv_dialog_dengebenxi:
                pop.dismiss();
                dialog.show();

                repayType="1";
                initDataFromInternet();
                break;
            case R.id.tv_dialog_daoqihuanbenxi:
                pop.dismiss();
                dialog.show();
                repayType="3";
                initDataFromInternet();
                break;
            case R.id.tv_dialog_anyuefuxi:
                pop.dismiss();
                dialog.show();
                repayType="4";
                initDataFromInternet();
                break;
        }

    }

    private void setBacStyle(int position, String mOrderType) {
        if (position != 2) {
            dialog.show();
        }

        page = 1;
        orderType = mOrderType;
        repayType = "";

        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setBackgroundColor(getResources().getColor(R.color.frag_managemoneymatterschild_bac));
                listTV.get(i).setTextColor(getResources().getColor(R.color.global_theme_background_color));

            } else {
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_white));
                listTV.get(i).setTextColor(getResources().getColor(R.color.color_black));
            }
        }

    }

    @Override
    public void initDataFromIntent() {

        type = getArguments().getInt("type", 1);
        if (type == 1) {
            requestUrl = Constants.INVESTMENT_LIST;
        } else {
            requestUrl = Constants.CREDITOR_TRANSFER_LIST;
        }


    }

    @Override
    public void initView() {

        list = new ArrayList<>();
        list.add(llOrder1);
        list.add(llOrder2);
        list.add(llOrder3);
        list.add(tvTotal);

        listTV = new ArrayList<>();
        listTV.add(tvOrder11);
        listTV.add(tvOrder12);
        listTV.add(tvOrder13);
        listTV.add(tvTotal);


        //设置全区背景色
        swipeRefreshLayout.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
//        swipeRefreshLayout.setEnableRefresh(true);
        swipeRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        swipeRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));


        dialog = DialogUtils.showProgreessDialog(getActivity(), getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));

        initDataFromInternet();

    }

    @Override
    public void initListener() {
        tvTotal.setOnClickListener(this);
        llOrder1.setOnClickListener(this);
        llOrder2.setOnClickListener(this);
        llOrder3.setOnClickListener(this);


        swipeRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {//加载更多
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < StringUtils.string2Integer(bean.getTotal_pages())) {
                    page++;
                    initDataFromInternet();
                } else {
                    swipeRefreshLayout.finishLoadmore();
                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
                }

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                initDataFromInternet();
            }
        });
    }

    @Override
    public void initDataFromInternet() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", page + "");
        map.put("order_type", orderType);//排序类别,1全部2利率3期限
        map.put("sort_type", sortType);//排序方式,1升序 2降序
        map.put("repay_type", repayType);//还款方式,1等额本息--3到期本息---4按月付息----5按天计息到期还本息

        Log.e(TAG, "onLoadmore: ------page--------===" + page);
        Log.e(TAG, "onLoadmore: ------orderType--------===" + orderType);
        Log.e(TAG, "onLoadmore: ------sortType--------===" + sortType);
        Log.e(TAG, "onLoadmore: ------repayType--------===" + repayType);


        HttpMethods.getInstance().POST(getActivity(), requestUrl, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dialog.dismiss();

                        if (page > 1) {
                            swipeRefreshLayout.finishLoadmore();
                        } else {
                            swipeRefreshLayout.finishRefresh();
                        }


                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----理财投资列表接口返回数据-- " + type + "------" + result);

                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<ManageMoneyMattersBean>() {
                        }.getType(), ManageMoneyMattersBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (ManageMoneyMattersBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), "数据加载出错!");
                        }

                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

        if (bean != null && bean.getItems() != null && bean.getItems().size() > 0 && page == 1) {

            if (listNew == null) {
                listNew = bean.getItems();
            } else {
                listNew.clear();
                listNew.addAll(bean.getItems());
            }

            if (actLVAdapter == null) {
                actLVAdapter = new ManageMoneyMattersFragLvAdapter(getActivity(), listNew);
                lvFragManageMoneyMatters.setAdapter(actLVAdapter);
            } else {
                actLVAdapter.notifyDataSetChanged();
                lvFragManageMoneyMatters.setSelection(0);
            }

        } else if (bean != null && bean.getItems() != null && bean.getItems().size() > 0 && page > 1) {

            listNew.addAll(bean.getItems());
            actLVAdapter.notifyDataSetChanged();

        } else {
            ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
        }

    }

    private void showAlertDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_frag_managemoneymatterschild, null);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        int width = llOrder3.getWidth();
        pop = new PopupWindow(view, width, ViewPager.LayoutParams.WRAP_CONTENT);
        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点


        view.findViewById(R.id.tv_dialog_dengebenxi).setOnClickListener(this);
        view.findViewById(R.id.tv_dialog_daoqihuanbenxi).setOnClickListener(this);
        view.findViewById(R.id.tv_dialog_anyuefuxi).setOnClickListener(this);

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

    private void showPop() {
        if (pop == null) {
            showAlertDialog();
        } else {
            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.alpha = 0.5f;
            getActivity().getWindow().setAttributes(params);
            pop.showAsDropDown(llOrder3);//显示位置
        }
    }


}
