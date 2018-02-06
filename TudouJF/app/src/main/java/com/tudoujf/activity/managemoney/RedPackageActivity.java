package com.tudoujf.activity.managemoney;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.adapter.FanXianQuanActLvAdapter;
import com.tudoujf.adapter.JiaXiQuanActLvAdapter;
import com.tudoujf.adapter.RedPackageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.FanXianQuanBean;
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
    //-------------------------2017.12.08-----------------------------------------------------------
    @BindView(R.id.ll_have)
    LinearLayout llHave;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;

    @BindView(R.id.iv_baoquan)
    ImageView ivBaoQuan;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    //-------------------------2017.12.08-----------------------------------------------------------

    private List<RedBagBean.ListBean> listRed;
    private List<JiaXiQuanBean.ListBean> listJiaXiQuan;
    private List<FanXianQuanBean.ListBean> listFanXianQuan;
    private RedPackageActLvAdapter adapter;
    private JiaXiQuanActLvAdapter adapterJiaXiQuan;
    private FanXianQuanActLvAdapter adapterFanXianQuan;
    /**
     * 0之前点击的item位置
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
    private FanXianQuanBean fanXianQuanBean;

    /**
     * 红包id
     */
    private String redId = "";
    private String acount = "";
    private String together_status = "";
    /**
     * 请求的url连接
     */
    private String url;
    //--------------------------------------已选中的红包加息券返现券id------------------------------
    private String selId;


    //--------------------------------------已选中的红包加息券返现券id------------------------------

    @Override
    public int getLayoutResId() {
        return R.layout.act_redpackage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_redpackage2:
                skip();
                break;
        }
    }

    private void skip() {
        Intent intent = new Intent();
        intent.putExtra("redId", redId);
        intent.putExtra("acount", acount);
        Log.e("TAG", "skip: -----" + together_status);

        intent.putExtra("together_status", together_status);
        switch (actType) {
            case 1://红包
                setResult(888, intent);
                break;
            case 2://加息券
                setResult(999, intent);
                break;
            case 3://返现券
                setResult(777, intent);
                break;
        }
        closeActivity();
    }

    @Override
    public void initDataFromIntent() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actType = bundle.getInt("type", 1);
            is_beginner = bundle.getString("is_beginner");
            time_limit = bundle.getString("time_limit");
            amount = bundle.getString("amount");
            together_status = bundle.getString("together_status");
            selId = bundle.getString("selId");//已选中的红包id
            //----------------------------------------------------------------

            //--------------------------传递的参数--------------------------------------------------
            Log.e("TAG", "initDataFromIntent: -----actType" + actType);
            Log.e("TAG", "initDataFromIntent: -----is_beginner" + is_beginner);
            Log.e("TAG", "initDataFromIntent: -----time_limit" + time_limit);
            Log.e("TAG", "initDataFromIntent: -----amount" + amount);
            Log.e("TAG", "initDataFromIntent: -----together_status" + together_status);


            //--------------------------传递的参数--------------------------------------------------


            if (actType == 1) {
                url = Constants.RED_BAG;
            } else if (actType == 2) {
                url = Constants.JIA_XI_QUAN;
                tvAffirmUse.setText(R.string.qingxuanzeyaoshiyongdejiaxiquan);
            } else if (actType == 3) {//---------------------------------------------------------------------------------------------------
                url = Constants.FAN_XIAN_QUAN;
                tvAffirmUse.setText(R.string.qingxuanzeyaoshiyongdefanxianquan);
            }
        }


    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbRedpackage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbRedpackage.setLayoutParams(params);
        if (actType == 2) {
            mtbRedpackage.setCenterTitle(R.string.act_redpackage_jiaxiquan);
        } else if (actType == 3) {
            mtbRedpackage.setCenterTitle(R.string.xuanzefanxianquan);
        }
        mtbRedpackage.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redId = "";
                acount = "";
                together_status = "";
                skip();
            }
        });
    }

    @Override
    public void onBackPressed() {
        redId = "";
        acount = "";
        together_status = "";
        skip();
    }

    @Override
    public void initListener() {
        tvAffirmUse.setOnClickListener(this);
        lvRedpackage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == beforePosition) {
                    count++;
//                    if (actType == 2) {//加息券activity时
                    if (count % 2 == 1) {
                        setItemStyle(view, position, 2, "#E7FAFF", true);
                        itemSel = true;
                    } else {
                        setItemStyle(view, position, 1, "#FFFFFF", false);
                        itemSel = false;
                    }//--------------------------------------------------------------------------------------------------------------
//                    } else {//红包activity时
//                        if (count % 2 == 1) {
//                            setItemStyle(view, position, 2, "#E7FAFF", true);
//                            itemSel = true;
//                        } else {
//                            setItemStyle(view, position, 1, "#FFFFFF", false);
//                            itemSel = false;
//                        }
//                    }
                } else {//点击的不是同一个item时
//                    if (actType == 2) {//加息券activity时
                    setItemStyle(view, position, 2, "#E7FAFF", true);
//                    } else {//红包activity时
//                        setItemStyle(view, position, 2, "#E7FAFF", true);
//                    }//-------------------------------------------------------------------------------------------------------------------------------
                    if (beforePosition != -1) {

                        if (lvRedpackage.getChildAt(beforePosition) != null) {
//                            if (actType == 2) {//加息券activity时
                            setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", false);
//                            } else {//红包activity时
//                                setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", false);
//                            }
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
                        together_status = listRed.get(position).getTogether_status();
                    } else if (actType == 2) {
                        redId = listJiaXiQuan.get(position).getId();
                        acount = listJiaXiQuan.get(position).getInterest();
                        together_status = listJiaXiQuan.get(position).getTogether_status();
                    } else if (actType == 3) {
                        redId = listFanXianQuan.get(position).getId();
                        acount = listFanXianQuan.get(position).getProportion();
                        together_status = listFanXianQuan.get(position).getTogether_status();
                    }
                    tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
                    tvAffirmUse.setBackgroundColor(getResources().getColor(R.color.calendar_selbac));
//                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_mblue);
                } else {
                    redId = "";
                    acount = "";
                    together_status = "";
                    if (actType == 1) {
                        tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_qingxuanze));
                    } else if (actType == 2) {
                        tvAffirmUse.setText(getResources().getString(R.string.qingxuanzeyaoshiyongdejiaxiquan));
                    } else if (actType == 3) {
                        tvAffirmUse.setText(getResources().getString(R.string.qingxuanzeyaoshiyongdefanxianquan));
                    }//----------------------------------------------------------------------------------------------------------------------------------
                    tvAffirmUse.setBackgroundColor(getResources().getColor(R.color.color_gray3));
//                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_munsel);
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
        map.put("together_status", together_status);//该值不能为空


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
                        } else if (actType == 3) {
                            Log.e("TAG", "onSuccess:----获取可用返现券接口返回数据--------" + result);
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
                        } else if (actType == 3) {
                            BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<FanXianQuanBean>() {
                            }.getType(), FanXianQuanBean.class, RedPackageActivity.this);
                            if (bean1 != null) {
                                fanXianQuanBean = (FanXianQuanBean) bean1;
                                fanXianQuanBean.setType(actType);
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

            if (listRed.size() > 0) {
                //--------------------------------之前已选中的红包----------------------------------
                if (selId != null && !"".equals(selId)) {
                    for (int i = 0; i < listRed.size(); i++) {
                        if (selId.equals(listRed.get(i).getId())) {
                            listRed.get(i).setBackground(2);
                            beforePosition = i;
                            tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
                            tvAffirmUse.setBackgroundColor(getResources().getColor(R.color.calendar_selbac));
//                            tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_mblue);
                            count++;
                        }
                    }
                }
                //--------------------------------之前已选中的红包----------------------------------


                tvRedpackageCount.setText(("您有" + listRed.size() + "个红包可使用"));
                adapter = new RedPackageActLvAdapter(bean, this);
                lvRedpackage.setAdapter(adapter);
                HeightUtils.setListviewHeight(lvRedpackage);
            } else {
                llHave.setVisibility(View.GONE);
                llNothing.setVisibility(View.VISIBLE);
                ivBaoQuan.setImageResource(R.drawable.act_fanxianquan2_redpackage);
                tvDescription.setText("暂无红包");
            }

        } else if (jiaXiQuanBean != null && actType == 2) {
            listJiaXiQuan = jiaXiQuanBean.getList();
            if (listJiaXiQuan.size() > 0) {


                //--------------------------------之前已选中的加息券----------------------------------
                if (selId != null && !"".equals(selId)) {
                    for (int i = 0; i < listRed.size(); i++) {
                        if (selId.equals(listRed.get(i).getId())) {
                            listRed.get(i).setBackground(2);
                            beforePosition = i;
                            tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
//                            tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_mblue);
                            tvAffirmUse.setBackgroundColor(getResources().getColor(R.color.calendar_selbac));
                            count++;
                        }
                    }
                }
                //--------------------------------之前已选中的加息券----------------------------------


                tvRedpackageCount.setText(("您有" + listJiaXiQuan.size() + "个加息券可使用"));
                adapterJiaXiQuan = new JiaXiQuanActLvAdapter(jiaXiQuanBean, this);
                lvRedpackage.setAdapter(adapterJiaXiQuan);
                HeightUtils.setListviewHeight(lvRedpackage);
            } else {
                llHave.setVisibility(View.GONE);
                llNothing.setVisibility(View.VISIBLE);
                ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                tvDescription.setText("暂无加息券");
            }

        } else if (fanXianQuanBean != null && actType == 3) {
            listFanXianQuan = fanXianQuanBean.getList();

            if (listFanXianQuan.size() > 0) {

                //--------------------------------之前已选中的返现券----------------------------------
                if (selId != null && !"".equals(selId)) {
                    for (int i = 0; i < listRed.size(); i++) {
                        if (selId.equals(listRed.get(i).getId())) {
                            listRed.get(i).setBackground(2);
                            beforePosition = i;
                            tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
                            tvAffirmUse.setBackgroundColor(getResources().getColor(R.color.calendar_selbac));
                            count++;
                        }
                    }
                }
                //--------------------------------之前已选中的返现券----------------------------------


                tvRedpackageCount.setText(("您有" + listFanXianQuan.size() + "个返现券可使用"));
                adapterFanXianQuan = new FanXianQuanActLvAdapter(fanXianQuanBean, this);
                lvRedpackage.setAdapter(adapterFanXianQuan);
                HeightUtils.setListviewHeight(lvRedpackage);
            } else {
                llHave.setVisibility(View.GONE);
                llNothing.setVisibility(View.VISIBLE);
                ivBaoQuan.setImageResource(R.drawable.act_fanxianquan_quan);
                tvDescription.setText("暂无返现券");
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

    /**
     * 设置listview的item的显示状态
     */
    private void setItemStyle(View view, int position, int backType, String color, boolean sel) {
        if (actType == 1) {
            listRed.get(position).setBackground(backType);
        } else if (actType == 2) {
            listJiaXiQuan.get(position).setBackground(backType);
        } else if (actType == 3) {
            listFanXianQuan.get(position).setBackground(backType);
        }
        view.setBackgroundColor(Color.parseColor(color));
        ((MRedView) (view.findViewById(R.id.lvitem_redpackage_rv5))).setSel(sel);//红包view的选中状态
        (view.findViewById(R.id.lvitem_redpackage_rv5)).invalidate();
    }


}
