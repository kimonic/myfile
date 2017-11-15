package com.tudoujf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.activity.my.myproject.MyCreditorRightsDetailsActivity;
import com.tudoujf.activity.my.myproject.MyInvestDetailsActivity;
import com.tudoujf.bean.databean.MyCreditorProjectBean;
import com.tudoujf.bean.databean.MyInvestProjectBean;
import com.tudoujf.ui.MyProjectBidView;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            MyProjectTotalFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目-->债权投资项目fragment-->listview适配器
 * history：
 * ===================================================
 */

public class MyProjectCreditorFragLvAdapter extends BaseAdapter {
    private Context context;
    private List<MyCreditorProjectBean.TransferListBean.ItemsBean> list;

    public MyProjectCreditorFragLvAdapter(Context context, List<MyCreditorProjectBean.TransferListBean.ItemsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_myprojecttotalfrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bidView = (MyProjectBidView) view.findViewById(R.id.lvitem_myprojecttotal);


            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        boolean skip = true;

        viewHolder.bidView.setTitle("1111111");//标题
        viewHolder.bidView.setTransfer("");//右标题
        viewHolder.bidView.setZhaiQuanJiaZhi(list.get(position).getWait_principal());//投资金额
        viewHolder.bidView.setYuanBiaoNianHuaShouYi(list.get(position).getWait_principal_interest());//预期收益
        viewHolder.bidView.setTransferPrice(list.get(position).getPeriod());//回款时间
        viewHolder.bidView.setExplain1("待收本息(元)");
        viewHolder.bidView.setExplain2("债权价值(元)");
        viewHolder.bidView.setExplain3("还款期限");
        viewHolder.bidView.setInvestProgress(1);//投资进度
        viewHolder.bidView.setShengYuKeTou("00,000.00");
        viewHolder.bidView.setStatus_name(list.get(position).getTransfer_status_name());

        final boolean finalSkip = skip;
        viewHolder.bidView.setListener(new MyProjectBidView.ClickEventListener() {
            @Override
            public void clickEvent() {
                if (finalSkip) {
                    Intent intent = new Intent(context, MyCreditorRightsDetailsActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MyInvestDetailsActivity.class);
                    context.startActivity(intent);
                }

            }
        });


        viewHolder.bidView.invalidate();

        return view;
    }

    private class ViewHolder {

        MyProjectBidView bidView;

    }
}
