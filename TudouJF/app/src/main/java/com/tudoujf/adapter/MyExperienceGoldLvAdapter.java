package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.ProductDetailsActBean;
import com.tudoujf.bean.databean.MyExperienceGoldBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            MyExperienceGoldLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/24
 * description：  MyExperienceGoldActivity总的listview的adapter
 * history：
 * ===================================================
 */
public class MyExperienceGoldLvAdapter extends BaseAdapter {
    private Context context;
    private List<MyExperienceGoldBean.TenderListBean.ItemsBean> list;

    public MyExperienceGoldLvAdapter(Context context, List<MyExperienceGoldBean.TenderListBean.ItemsBean> list) {
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
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_myexperienceact,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_myexperience_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_myexperience_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_myexperience_tv3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.lvitem_myexperience_tv4);
            viewHolder.textView5= (TextView) view.findViewById(R.id.lvitem_myexperience_tv5);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView2.setText(list.get(position).getAmount());
        viewHolder.textView3.setText(list.get(position).getInterest_earned());
        viewHolder.textView4.setText(list.get(position).getInterest_earning());
        viewHolder.textView5.setText(list.get(position).getStatus());




        return view;
    }

    private class  ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView5;
    }
}
