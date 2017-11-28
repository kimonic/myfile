package com.tudoujf.fragment.myearnings;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tudoujf.R;
import com.tudoujf.adapter.DueInInterestFragLvAdapterO;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * * ================================================
 * name:            DueInInterestFragment
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/30
 * description：  我的收益页面activity--->待收利息fragment
 * history：
 * ===================================================
 */

public class DueInInterestFragment extends BaseFragment {
    @BindView(R.id.lv_frag_dueininterest)
    ListView lvFragDueInInterest;
    @BindView(R.id.tv_frag_dueininterest1)
    TextView tvDate;
    @BindView(R.id.tv_frag_dueininterest2)
    TextView tvName;
    @BindView(R.id.tv_frag_dueininterest3)
    TextView tvHuoDong;
    @BindView(R.id.tv_frag_dueininterest4)
    TextView tvShouYi;


    @BindView(R.id.srl_frag_dueininterest)
    SmartRefreshLayout srl;
    Unbinder unbinder;


    private int type = 1;
    private List<DueInInterestFragBean> list;
    private int page=1;
    private String status;
    private String start_time;
    private String end_time;

    @Override
    public int layoutRes() {
        return R.layout.frag_dueininterest;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        // TODO: 2017/8/30   此处获得该fragment的类型进行相关逻辑处理
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            DueInInterestFragBean bean = new DueInInterestFragBean();
            bean.setName("老铁尊享福袋");
            bean.setDate("2017-07-31");
            bean.setJine("000,000.00");
            bean.setShouYi("+0,000.00");
            list.add(bean);


        }

        if (getArguments()!=null){
            type=getArguments().getInt("type",1);
        }

        switch (type) {
            case 1://待收利息,已收利息
            case 2:
                DueInInterestFragLvAdapterO adapterO = new DueInInterestFragLvAdapterO(getActivity(), list, 1);
                lvFragDueInInterest.setAdapter(adapterO);
                break;
            case 3:
                tvHuoDong.setVisibility(View.GONE);
                tvName.setText("活动名称");
                DueInInterestFragLvAdapterO adapterO1 = new DueInInterestFragLvAdapterO(getActivity(), list, 2);
                lvFragDueInInterest.setAdapter(adapterO1);
                break;
        }

    }

    @Override
    public void initView() {
        srl.setPrimaryColors(getResources().getColor(R.color.global_theme_background_color));
        srl.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        srl.setRefreshFooter(new BallPulseFooter(getActivity()));

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDataFromInternet() {

        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        map.put("page", ""+page);
        map.put("status", ""+status);
        map.put("start_time", ""+start_time);
        map.put("end_time", ""+end_time);
        

        HttpMethods.getInstance().POST(getActivity(), Constants.MY_EARNINGS, map, getActivity().getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess:----我的推广首页接口返回数据--------" + result);
//                        BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<MyPopularizeBean>() {
//                        }.getType(), MyPopularizeBean.class, getActivity());
//                        if (bean1 != null) {
//                            bean = (MyPopularizeBean) bean1;
//                            LoadInternetDataToUi();
//                        } else {
//                            ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
//                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(getActivity(), R.string.huoquwodeshouyixinxishibai);
                    }
                });

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
