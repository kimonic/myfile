package com.tudoujf.activity.discover;


import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.IntegralRankingListLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.IntegralRankingListBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MListView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             IntegralRankingListActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：
 * history：
 * *==================================================================
 */

public class IntegralRankingListActivity extends BaseActivity {
    @BindView(R.id.mtb_act_integralrankinglist)
    MTopBarView mtb;
    @BindView(R.id.lv_integralrankinglist)
    MListView lv;

    private List<IntegralRankingListBean.ItemsBean> list;
    private IntegralRankingListBean inBean;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralrankinglist;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();

    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);
    }

    @Override
    public void initListener() {

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_RANKING_LIST, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        Log.e("TAG", "onSuccess: -----------积分排行榜返回的json数据----------------" + result);
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        String rankingList = "{items:" + bean.getData().toString() + "}";
                        inBean = gson.fromJson(rankingList, IntegralRankingListBean.class);
                        if (inBean != null) {
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(IntegralRankingListActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(IntegralRankingListActivity.this, R.string.huoqujifenpaihangbangshujushibai);

                    }
                }
        );
    }

    @Override
    public void LoadInternetDataToUi() {

        if (inBean!=null&&inBean.getItems()!=null&&inBean.getItems().size()>0){
            list.addAll(inBean.getItems());

            for (int i = 0; i < list.size(); i++) {
                list.get(i).setPosition(""+(i+1));
                if (i==0){
                    list.get(0).setResId(R.drawable.first);
                }else if (i==1){
                    list.get(1).setResId(R.drawable.second);
                }else if (i==2){
                    list.get(2).setResId(R.drawable.third);
                }

            }

            IntegralRankingListLvAdapter   adapter=new IntegralRankingListLvAdapter(list,this);
            lv.setAdapter(adapter);


        }

    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

}
