package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.ProductDetailsActBean;
import com.tudoujf.bean.databean.NewcomerExperienceBidBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;


/**
 * * ================================================
 * name:            ProductDetailsActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  NewcomerExperienceBidActivity中listview的adapter
 * history：
 * ===================================================
 */

public class NewcomerExperienceBidActLvAdapter extends BaseAdapter {
    private Context context;
    private List<NewcomerExperienceBidBean.TenderListBean> list;

    public NewcomerExperienceBidActLvAdapter(Context context, List<NewcomerExperienceBidBean.TenderListBean> list) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_newcomerexperienceact,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_productdetails_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_productdetails_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_productdetails_tv3);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getMember_name());
        viewHolder.textView2.setText(list.get(position).getAmount());
        viewHolder.textView3.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));




        return view;
    }

    private class  ViewHolder{
        TextView textView1,textView2,textView3;
    }
}
