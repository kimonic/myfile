package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.JiaXiQuanActLvAdapter;
import com.tudoujf.adapter.RedPackageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.JiaXiQuanBean;
import com.tudoujf.bean.databean.RedBagBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.MRedView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ================================================
 * name:            RedPackageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：选择红包和选择加息券activity
 * history：
 * ===================================================
 */

public class RedPackageActivity extends BaseActivity {
    @BindView(R.id.mtb_act_redpackage)
    MTopBarView mtbRedpackage;
    @BindView(R.id.tv_act_redpackage1)
    TextView tvRedpackageCount;
    @BindView(R.id.lv_act_redpackage)
    ListView lvRedpackage;
    @BindView(R.id.tv_act_redpackage2)
    TextView tvAffirmUse;

    private List<RedBagBean.ListBean> listRed;
    private List<JiaXiQuanBean.ListBean> listJiaXiQuan;
    private RedPackageActLvAdapter adapter;
    private JiaXiQuanActLvAdapter adapterJiaXiQuan;
    /**
     * 之前点击的item位置
     */
    private int beforePosition = -1;
    /**
     * 点击次数
     */
    private int count = 0;
    /**
     * 当前activity的类型,是红包还是加息券
     */
    private int actType = 1;
    /**
     * 判断是否有item被选中
     */
    private boolean itemSel = false;

    private String is_beginner;
    private String time_limit;
    private String amount;
    private RedBagBean bean;
    private JiaXiQuanBean jiaXiQuanBean;

    /**
     * 红包id
     */
    private String redId = "";
    private String acount = "";
    /**
     * 请求的url连接
     */
    private String url;

    @Override
    public int getLayoutResId() {
        return R.layout.act_redpackage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_redpackage2:
                if (actType == 1) {//红包
                    Intent intent = new Intent();
                    intent.putExtra("redId", redId);
                    intent.putExtra("acount", acount);
                    setResult(888, intent);
                    closeActivity();
                } else if (actType == 2) {//加息券
                    Intent intent = new Intent();
                    intent.putExtra("redId", redId);
                    intent.putExtra("acount", acount);
                    setResult(999, intent);
                    closeActivity();
                }
                break;
//                     case R.id.:break;
//                     case R.id.:break;
//                     case R.id.:break;
//                     case R.id.:break;
//                     case R.id.:break;
        }
    }

    @Override
    public void initDataFromIntent() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actType = bundle.getInt("type", 1);
            is_beginner = bundle.getString("is_beginner");
            time_limit = bundle.getString("time_limit");
            amount = bundle.getString("amount");
            if (actType == 1) {
                url = Constants.RED_BAG;
            } else if (actType == 2) {
                url = Constants.JIA_XI_QUAN;
            }
        }


    }

    @Override
    public void initView() {
//        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbRedpackage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbRedpackage.setLayoutParams(params);
        if (actType == 2) {
            mtbRedpackage.setCenterTitle(R.string.act_redpackage_jiaxiquan);
        }
        mtbRedpackage.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    public void initListener() {
        tvAffirmUse.setOnClickListener(this);
        lvRedpackage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == beforePosition) {
                    count++;
                    if (actType == 2) {//加息券activity时
                        if (count % 2 == 1) {
                            setItemStyle(view, position, 2, "#E7FAFF", true);
                            itemSel = true;
                        } else {
                            setItemStyle(view, position, 1, "#FFFFFF", false);
                            itemSel = false;
                        }
                    } else {//红包activity时
                        if (count % 2 == 1) {
                            setItemStyle(view, position, 2, "#E7FAFF", true);
                            itemSel = true;
                        } else {
                            setItemStyle(view, position, 1, "#FFFFFF", false);
                            itemSel = false;
                        }
                    }
                } else {//点击的不是同一个item时
                    if (actType == 2) {//加息券activity时
                        setItemStyle(view, position, 2, "#E7FAFF", true);
                    } else {//红包activity时
                        setItemStyle(view, position, 2, "#E7FAFF", true);
                    }
                    if (beforePosition != -1) {

                        if (lvRedpackage.getChildAt(beforePosition) != null) {
                            if (actType == 2) {//加息券activity时
                                setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", false);
                            } else {//红包activity时
                                setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", false);
                            }
                        } else {
                            listRed.get(beforePosition).setBackground(1);
                        }
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                        if (adapterJiaXiQuan != null) {
                            adapterJiaXiQuan.notifyDataSetChanged();
                        }
                    }
                    count = 1;
                    beforePosition = position;
                    itemSel = true;
                }
                if (itemSel) {//改变确认按钮
                    if (actType == 1) {
                        redId = listRed.get(position).getId();
                        acount = listRed.get(position).getAmount();
                    } else if (actType == 2) {
                        redId = listJiaXiQuan.get(position).getId();
                        acount = listJiaXiQuan.get(position).getInterest();
                    }
                    tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_mblue);
                } else {
                    redId = "";
                    acount = "";
                    tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_qingxuanze));
                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_munsel);
                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        map.put("is_beginner", is_beginner);
        map.put("time_limit", time_limit);
        map.put("amount", amount);//该值不能为空


        HttpMethods.getInstance().POST(this, url, map, getLocalClassName(),
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        if (actType == 1) {
                            Log.e("TAG", "onSuccess:----获取可用红包接口返回数据--------" + result);
                        } else if (actType == 2) {
                            Log.e("TAG", "onSuccess:----获取可用加息券接口返回数据--------" + result);
                        }
                        if (actType == 1) {
                            BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<RedBagBean>() {
                            }.getType(), RedBagBean.class, RedPackageActivity.this);
                            if (bean1 != null) {
                                bean = (RedBagBean) bean1;
                                bean.setType(actType);
                                LoadInternetDataToUi();
                            } else {
                                ToastUtils.showToast(RedPackageActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                            }
                        } else if (actType == 2) {

                            BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<JiaXiQuanBean>() {
                            }.getType(), JiaXiQuanBean.class, RedPackageActivity.this);
                            if (bean1 != null) {
                                jiaXiQuanBean = (JiaXiQuanBean) bean1;
                                jiaXiQuanBean.setType(actType);
                                LoadInternetDataToUi();
                            } else {
                                ToastUtils.showToast(RedPackageActivity.this, getResources().getString(R.string.shujujiazaichucuo));
                            }
                        }

                    }

                });

    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null && actType == 1) {
            listRed = bean.getList();
            tvRedpackageCount.setText("您有" + listRed.size() + "个红包可使用");
            adapter = new RedPackageActLvAdapter(bean, this);
            lvRedpackage.setAdapter(adapter);
            HeightUtils.setListviewHeight(lvRedpackage);
        }

        if (jiaXiQuanBean != null && actType == 2) {
            listJiaXiQuan = jiaXiQuanBean.getList();
            tvRedpackageCount.setText("您有" + listJiaXiQuan.size() + "个加息券可使用");
            adapterJiaXiQuan = new JiaXiQuanActLvAdapter(jiaXiQuanBean, this);
            lvRedpackage.setAdapter(adapterJiaXiQuan);
            HeightUtils.setListviewHeight(lvRedpackage);
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

    /**
     * 设置listview的item的显示状态
     */
    private void setItemStyle(View view, int position, int backType, String color, boolean sel) {
        if (actType == 1) {
            listRed.get(position).setBackground(backType);
        } else if (actType == 2) {
            listJiaXiQuan.get(position).setBackground(backType);
        }
        view.setBackgroundColor(Color.parseColor(color));
        ((MRedView) (view.findViewById(R.id.lvitem_redpackage_rv5))).setSel(sel);//红包view的选中状态
        (view.findViewById(R.id.lvitem_redpackage_rv5)).invalidate();
    }


}
