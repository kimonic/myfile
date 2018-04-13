package com.tudoujf.activity.discover;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.HotExchangeLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.HotExchangeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             HotExchangeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：  热门兑换activity
 * history：
 * *==================================================================
 */
public class HotExchangeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_hotexchange)
    MTopBarView mtb;
    @BindView(R.id.lv_act_hotexchange)
    ListView lv;
    private HotExchangeBean inBean;
    private List<HotExchangeBean.ItemsBean> list;
    private HotExchangeLvAdapter adapter;
    private String integral;
//    @BindView(R.id.srl_act_hotexchange)
//    SmartRefreshLayout srl;

    @Override
    public int getLayoutResId() {
        return R.layout.act_hotexchange;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {
        list=new ArrayList<>();
        integral=getIntent().getStringExtra("integral");
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HotExchangeActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("integral",integral);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        HttpMethods.getInstance().POST(this, Constants.HOT_GOODS, map, getLocalClassName(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());

                        LUtils.e(HotExchangeActivity.class,"logflag-热门商品接口返回的json数据--"+result);

                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(result, CommonBean.class);
                        String rankingList = "{items:" + bean.getData().toString() + "}";
                        inBean = gson.fromJson(rankingList, HotExchangeBean.class);
                        if (inBean != null) {
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(HotExchangeActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissPDialog();
                        ToastUtils.showToast(HotExchangeActivity.this, R.string.huoquremenshangpinxinxishibai);
                    }
                }
        );
    }

    @Override
    public void LoadInternetDataToUi() {
        if (inBean!=null&&inBean.getItems()!=null&&inBean.getItems().size()>0){
            list.addAll(inBean.getItems());

            if (adapter==null){
                adapter=new HotExchangeLvAdapter(list,this);
                lv.setAdapter(adapter);
            }
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
