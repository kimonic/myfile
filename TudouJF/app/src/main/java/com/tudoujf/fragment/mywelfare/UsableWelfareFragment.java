package com.tudoujf.fragment.mywelfare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.adapter.UsableWelfareFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.databean.WelfareListBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

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
    @BindView(R.id.tv_frag_usable_count)
    TextView tvCount;
    @BindView(R.id.iv_frag_usable_baoquan)
    ImageView ivBaoQuan;
    @BindView(R.id.tv_frag_usable_description)
    TextView tvDescription;
    @BindView(R.id.ll_frag_usable_nothing)
    LinearLayout llNothing;
    @BindView(R.id.srl_frag_usablewelfare)
    SmartRefreshLayout srl;

    private UsableWelfareFragLvAdapter adapter;
    /**
     * 当前activity的类型,是红包还是加息券
     */
    private int actType = 1;
    private String status;
    private String url;
    private WelfareListBean bean;
    private List<WelfareListBean.ItemsBean> listItem;
    private  int  page=1;

    @Override
    public int layoutRes() {
        return R.layout.frag_usablewelfare;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_frag_usable_use://跳转红包使用界面
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("flag", 555);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
        listItem = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            actType = bundle.getInt("type", 1);
        }


        switch (actType) {
            case 1://可用红包-2过期 -1可用 1已用
                status = "-1";
                url = Constants.BOUNTY;
                break;
            case 2://已用红包-2过期 -1可用 1已用
                status = "1";
                url = Constants.BOUNTY;

                llFragUsableUse.setVisibility(View.GONE);
                break;
            case 3://过期红包-2过期 -1可用 1已用
                status = "-2";
                url = Constants.BOUNTY;
                llFragUsableUse.setVisibility(View.GONE);
                break;
            case 4://可用加息券-2过期 -1可用 1已用
                status = "-1";
                url = Constants.COUPOM;
                break;
            case 5://已用加息券
                status = "1";
                url = Constants.COUPOM;
                llFragUsableUse.setVisibility(View.GONE);
                break;
            case 6://过期加息券-2过期 -1可用 1已用
                status = "-2";
                url = Constants.COUPOM;
                llFragUsableUse.setVisibility(View.GONE);
                break;
            case 7://可用返现券-2过期 -1可用 1已用
                url = Constants.CASH_BACK;
                status = "-1";
                break;
            case 8://已用返现券-2过期 -1可用 1已用
                url = Constants.CASH_BACK;
                status = "1";
                llFragUsableUse.setVisibility(View.GONE);
                break;
            case 9://过期返现券-2过期 -1可用 1已用
                url = Constants.CASH_BACK;
                status = "-2";
                llFragUsableUse.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void initView() {
        if (actType==1||actType==2||actType==3){
            srl.setEnableRefresh(false);
            srl.setEnableLoadmore(false);
        }else {
            srl.setPrimaryColorsId(R.color.global_theme_background_color);
//        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
            srl.setEnableRefresh(false);
            srl.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        }

        initDataFromInternet();
    }

    @Override
    public void initListener() {
        llFragUsableUse.setOnClickListener(this);

        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (page<=bean.getTotal_pages()){
                    initDataFromInternet();
                }else {
                    finishRl();
                    ToastUtils.showToast(getActivity(), R.string.meiyougengduola);
                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        map.put("status", status);
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(getActivity(), url, map, "UsableWelfareFragment", new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        finishRl();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess: -----------请求我的福利返回的json数据-------" + actType + "---------" + result);
                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<WelfareListBean>() {
                        }.getType(), WelfareListBean.class, getActivity());
                        if (bean1 != null) {
                            bean = (WelfareListBean) bean1;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(getActivity(), getResources().getString(R.string.shujujiazaichucuo));
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        finishRl();
                        ToastUtils.showToast(getActivity(), R.string.huoquhongbaoxinxishibai);
                    }
                }
        );


    }

    private void finishRl(){
        if (srl!=null&&srl.isLoading()){
            srl.finishLoadmore();
        }
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {

            if (bean.getItems() != null && actType < 4) {
                listItem.addAll(bean.getItems());
                llFragUsableUse.setVisibility(View.VISIBLE);
                lvFragUsableInfo.setVisibility(View.VISIBLE);
                llNothing.setVisibility(View.GONE);

                if (actType==1){
                    llFragUsableUse.setVisibility(View.VISIBLE);
                }else {
                    llFragUsableUse.setVisibility(View.GONE);
                }

                if (listItem.size() > 0) {
                    tvCount.setText(("您有" + listItem.size() + "个红包可使用,立即使用>>"));
                    for (int i = 0; i < listItem.size(); i++) {
                        listItem.get(i).setType(actType);
                    }

                    if (adapter == null) {
                        adapter = new UsableWelfareFragLvAdapter(listItem, getActivity(), 1);
                        lvFragUsableInfo.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    llFragUsableUse.setVisibility(View.GONE);
                    lvFragUsableInfo.setVisibility(View.GONE);
                    llNothing.setVisibility(View.VISIBLE);
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan2_redpackage);
                    tvDescription.setText("暂无红包");
                }
            } else if (bean.getItems() != null && actType < 7 && actType > 3) {
                listItem.addAll(bean.getItems());
                llFragUsableUse.setVisibility(View.VISIBLE);
                lvFragUsableInfo.setVisibility(View.VISIBLE);
                llNothing.setVisibility(View.GONE);

                if (actType==4){
                    llFragUsableUse.setVisibility(View.VISIBLE);
                }else {
                    llFragUsableUse.setVisibility(View.GONE);
                }

                if (listItem.size() > 0) {
                    tvCount.setText(("您有" + listItem.size() + "个加息券可使用,立即使用>>"));
                    for (int i = 0; i < listItem.size(); i++) {
                        listItem.get(i).setType(actType);
                    }

                    if (adapter == null) {
                        adapter = new UsableWelfareFragLvAdapter(listItem, getActivity(), 2);
                        lvFragUsableInfo.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    llFragUsableUse.setVisibility(View.GONE);
                    lvFragUsableInfo.setVisibility(View.GONE);
                    llNothing.setVisibility(View.VISIBLE);
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                    tvDescription.setText("暂无加息券");
                }
            } else if (bean.getItems() != null && actType > 6) {
                listItem.addAll(bean.getItems());
                llFragUsableUse.setVisibility(View.VISIBLE);
                lvFragUsableInfo.setVisibility(View.VISIBLE);
                llNothing.setVisibility(View.GONE);

                if (actType==7){
                    llFragUsableUse.setVisibility(View.VISIBLE);
                }else {
                    llFragUsableUse.setVisibility(View.GONE);
                }

                if (listItem.size() > 0) {
                    tvCount.setText(("您有" + listItem.size() + "个返现券可使用,立即使用>>"));
                    for (int i = 0; i < listItem.size(); i++) {
                        listItem.get(i).setType(actType);
                    }

                    if (adapter == null) {
                        adapter = new UsableWelfareFragLvAdapter(listItem, getActivity(), 3);
                        lvFragUsableInfo.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    llFragUsableUse.setVisibility(View.GONE);
                    lvFragUsableInfo.setVisibility(View.GONE);
                    llNothing.setVisibility(View.VISIBLE);
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                    tvDescription.setText("暂无返现券");
                }

            }


        } else {
            llFragUsableUse.setVisibility(View.GONE);
            lvFragUsableInfo.setVisibility(View.GONE);
            llNothing.setVisibility(View.VISIBLE);
            switch (actType) {
                case 1:
                case 2:
                case 3:
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan2_redpackage);
                    tvDescription.setText("暂无红包");
                    break;
                case 4:
                case 5:
                case 6:
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                    tvDescription.setText("暂无加息券");
                    break;
                case 7:
                case 8:
                case 9:
                    ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                    tvDescription.setText("暂无返现券");
                    break;
            }
        }
    }


}
