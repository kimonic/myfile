package com.tudoujf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.ProductDetailsActBean;
import com.tudoujf.bean.databean.InvestDetailsBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            ProductDetailsActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/26
 * description：  ProductDetailsActivity中listview的adapter
 * history：
 * ===================================================
 */

public class ProductDetailsActLvAdapter extends BaseAdapter {

    private Context context;
    private List<InvestDetailsBean.TenderListBean> list;

    public ProductDetailsActLvAdapter(Context context, List<InvestDetailsBean.TenderListBean> list) {
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
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_productdetailsact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.lvitem_productdetails_tv1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.lvitem_productdetails_tv2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.lvitem_productdetails_tv3);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getMember_name());
        viewHolder.textView2.setText(list.get(position).getAmount());
        if (position == 0) {
            viewHolder.textView3.setText(list.get(position).getAdd_time());
        } else {
            viewHolder.textView3.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        }

        if (position == 0) {
            viewHolder.textView1.setTextColor(context.getResources().getColor(R.color.act_productdetails_tvcolor1));
            viewHolder.textView2.setTextColor(context.getResources().getColor(R.color.act_productdetails_tvcolor1));
        }


        return view;
    }

    private class ViewHolder {
        TextView textView1, textView2, textView3;
    }
}
