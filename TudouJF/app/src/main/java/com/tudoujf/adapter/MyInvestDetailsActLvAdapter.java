package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.MyInvestDetailsBean;

import java.util.List;

/**
 * * ================================================
 * name:            InvestListFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  我的项目--我的投资项目--项目详情--还款详情adapter
 * history：
 * ===================================================
 */

public class MyInvestDetailsActLvAdapter extends BaseAdapter {
    private Context context;
    private List<MyInvestDetailsBean.RecoverInfoBean> list;

    public MyInvestDetailsActLvAdapter(Context context, List<MyInvestDetailsBean.RecoverInfoBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_myinvestdetails, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_should_amount);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_actual_amount);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_actual_interest);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_pay_date);
            viewHolder.textView5 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_qishu);
            viewHolder.textView6 = (TextView) view.findViewById(R.id.tv_act_myinvestdetails_status);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(list.get(position).getAmount());
        viewHolder.textView2.setText(list.get(position).getPrincipal_yes());
        viewHolder.textView3.setText(list.get(position).getInterest_yes());
        viewHolder.textView4.setText(list.get(position).getRecover_time());
        viewHolder.textView5.setText(("第"+list.get(position).getPeriod_no()+"/"+list.get(position).getPeriod()+"期"));
        viewHolder.textView6.setText(list.get(position).getStatus_name());

        return view;
    }

    private class ViewHolder {
        TextView textView1,textView2,textView3,textView4,textView5,textView6;
    }
}
