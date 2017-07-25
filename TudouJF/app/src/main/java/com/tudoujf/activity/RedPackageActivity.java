package com.tudoujf.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.adapter.RedPackageActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.bean.RedPackageActLvBean;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.ui.RedView;
import com.tudoujf.utils.HeightUtils;
import com.tudoujf.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;

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

    private List<RedPackageActLvBean> list;
    private RedPackageActLvAdapter adapter;
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
    /**判断是否有item被选中*/
    private boolean  itemSel=false;


    @Override
    public int getLayoutResId() {
        return R.layout.act_redpackage;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            actType=bundle.getInt("type",1);
        }

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RedPackageActLvBean bean = new RedPackageActLvBean();
            bean.setContent1("单笔投资满1000元  最低");
            bean.setContent2("投资0个月可使用");
            bean.setContent3("50元 抽奖红包");
            bean.setContent4("可使用");
            bean.setContent5("500");
            bean.setContent6("2017/07/08");
            bean.setBackground(1);
            bean.setType(actType);
            list.add(bean);
        }
    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbRedpackage.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbRedpackage.setLayoutParams(params);
        if (actType==2){
            mtbRedpackage.setCenterTitle(R.string.act_redpackage_jiaxiquan);
        }
        mtbRedpackage.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        adapter = new RedPackageActLvAdapter(list, this);
        lvRedpackage.setAdapter(adapter);
        HeightUtils.setListviewHeight(lvRedpackage);

    }

    @Override
    public void initListener() {
        lvRedpackage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == beforePosition) {
                    count++;
                    if (actType == 2) {//加息券activity时
                        if (count % 2 == 1) {
                            setItemStyle(view, position, 2, "#E7FAFF", R.drawable.act_redpackage2_quansel);
                            itemSel=true;
                        } else {
                            setItemStyle(view, position, 1, "#FFFFFF", R.drawable.act_redpackage2_quanunsel);
                            itemSel=false;
                        }
                    } else {//红包activity时
                        if (count % 2 == 1) {
                            setItemStyle(view, position, 2, "#E7FAFF", R.drawable.act_redpackage2_sel);
                            itemSel=true;
                        } else {
                            setItemStyle(view, position, 1, "#FFFFFF", R.drawable.act_redpackage2_unsel);
                            itemSel=false;
                        }
                    }
                } else {//点击的不是同一个item时
                    if (actType == 2) {//加息券activity时
                        setItemStyle(view, position, 2, "#E7FAFF", R.drawable.act_redpackage2_quansel);
                    } else {//红包activity时
                        setItemStyle(view, position, 2, "#E7FAFF", R.drawable.act_redpackage2_sel);
                    }
                    if (beforePosition != -1) {

                        if (lvRedpackage.getChildAt(beforePosition) != null) {
                            if (actType == 2) {//加息券activity时
                                setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", R.drawable.act_redpackage2_quanunsel);
                            } else {//红包activity时
                                setItemStyle(lvRedpackage.getChildAt(beforePosition), beforePosition, 1, "#FFFFFF", R.drawable.act_redpackage2_unsel);
                            }
                        } else {
                            list.get(beforePosition).setBackground(1);
                        }
                        adapter.notifyDataSetChanged();


                    }
                    count = 1;
                    beforePosition = position;
                    itemSel=true;
                }
                if (itemSel){//改变确认按钮
                    tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_querenshiyong));
                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_mblue);
                }else {
                    tvAffirmUse.setText(getResources().getString(R.string.act_redpacksge_qingxuanze));
                    tvAffirmUse.setBackgroundResource(R.drawable.xshape_roundrect_munsel);
                }
            }
        });
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

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
    private void setItemStyle(View view, int position, int backType, String color, int resId) {
        list.get(position).setBackground(backType);
        view.setBackgroundColor(Color.parseColor(color));
        ((RedView) (view.findViewById(R.id.lvitem_redpackage_rv5))).setBitmap(resId);
    }


}
