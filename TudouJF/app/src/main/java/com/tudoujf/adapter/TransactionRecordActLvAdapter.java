package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.TransactionRecordBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            TransactionRecordActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/11
 * description：  TransactionRecordActivity中listview的adapter
 * history：
 * ===================================================
 */

public class TransactionRecordActLvAdapter extends BaseAdapter {
    private List<TransactionRecordBean.ItemsBean> list;
    private Context context;

    public TransactionRecordActLvAdapter(List<TransactionRecordBean.ItemsBean> list, Context context) {
        this.list = list;
        this.context = context;
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

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_trsactiondetailsact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.lvitem_transactiondetails_tv1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.lvitem_transactiondetails_tv2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.lvitem_transactiondetails_tv3);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.lvitem_transactiondetails_tv4);
            viewHolder.textView5 = (TextView) view.findViewById(R.id.lvitem_transactiondetails_tv5);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getFee_name());
        viewHolder.textView2.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView3.setText(list.get(position).getLoan_name());

        viewHolder.textView4.setText(StringUtils.getCommaDecimalsStr(list.get(position).getBalance()));
        String temp;
        if ("income".equals(list.get(position).getMoney_type())) {
            temp = "￥  +" + StringUtils.getCommaDecimalsStr(list.get(position).getAmount_money());
            viewHolder.textView5.setTextColor(context.getResources().getColor(R.color.color_orange));
        } else {
            temp = "￥  -" + StringUtils.getCommaDecimalsStr(list.get(position).getAmount_money());
            viewHolder.textView5.setTextColor(context.getResources().getColor(R.color.global_theme_background_color));
        }
        viewHolder.textView5.setText(temp);


        return view;
    }

    private class ViewHolder {
        private TextView textView1, textView2, textView3, textView4, textView5;
    }

}
