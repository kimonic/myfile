package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.SpecialOfferActBean;
import com.tudoujf.bean.SystemMessageFragBean;
import com.tudoujf.bean.databean.MyMessageBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            SystemMessageFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  SystemMessageFragment中listview的adapter
 * history：
 * ===================================================
 */

public class SystemMessageFragLvAdapter extends BaseAdapter {
    private List<MyMessageBean.PageObjBean.ItemsBean> list;
    private Context context;

    public SystemMessageFragLvAdapter(List<MyMessageBean.PageObjBean.ItemsBean> list, Context context) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_systemmessagefrag,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_systemmessage_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_systemmessage_tv2);
//            viewHolder.imageView= (ImageView) view.findViewById(R.id.lvitem_specialoffer_iv);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getTitle());
        viewHolder.textView2.setText(StringUtils.getStrTime(list.get(position).getSend_time()));
        if (list.get(position).getStatus().equals("1")){
            viewHolder.textView1.setTextColor(context.getResources().getColor(R.color.color_black));
            viewHolder.textView2.setTextColor(context.getResources().getColor(R.color.color_black));
        }else {
            viewHolder.textView1.setTextColor(context.getResources().getColor(R.color.color_gray));
            viewHolder.textView2.setTextColor(context.getResources().getColor(R.color.color_gray));
        }
//        viewHolder.imageView.setImageResource(list.get(position).getResId());









        return view;
    }

    private class ViewHolder{
        public TextView textView1,textView2;
//        public ImageView imageView;
    }
}
