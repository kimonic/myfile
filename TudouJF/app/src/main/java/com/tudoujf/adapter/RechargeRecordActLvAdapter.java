package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.WithdrawRecordBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            RechargeRecordActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/12
 * description：    RechargeRecordActivity中listview的adapter
 * history：
 * ===================================================
 */

public class RechargeRecordActLvAdapter extends BaseAdapter {
    private List<WithdrawRecordBean.ItemsBean> list;
    private Context context;

    public RechargeRecordActLvAdapter(List<WithdrawRecordBean.ItemsBean> list, Context context) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_rechargerecordact,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_act_rechargerecord_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_act_rechargerecord_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_act_rechargerecord_tv3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.lvitem_act_rechargerecord_tv4);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getAmount());
        viewHolder.textView2.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView3.setText(list.get(position).getStatus_name());
        viewHolder.textView4.setText(R.string.tixianjine);









        return view;
    }

    private class ViewHolder{
        private TextView textView1,textView2,textView3,textView4;
    }

}
