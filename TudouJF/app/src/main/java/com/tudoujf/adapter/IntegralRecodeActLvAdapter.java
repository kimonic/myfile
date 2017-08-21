package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.MyFragBean;
import com.tudoujf.bean.databean.IntegralRecodeBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            IntegralRecodeActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：  首页activity签到activity积分记录中listview的adapter
 * history：
 * ===================================================
 */

public class IntegralRecodeActLvAdapter extends BaseAdapter {
    private List<IntegralRecodeBean.ItemsBean> list;
    private Context context;

    public IntegralRecodeActLvAdapter(List<IntegralRecodeBean.ItemsBean> list, Context context) {
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
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_integralrecodeact,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.tv_lvitem_integralrecodeact1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.tv_lvitem_integralrecodeact2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.tv_lvitem_integralrecodeact3);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getType());
        viewHolder.textView2.setText(list.get(position).getPoint());
        viewHolder.textView3.setText(StringUtils.getStrTime(list.get(position).getTime()));
        return view;
    }

    private class ViewHolder{
        private TextView textView1,textView2,textView3;
    }
}
