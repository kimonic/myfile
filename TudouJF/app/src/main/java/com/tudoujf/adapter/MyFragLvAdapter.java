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

import java.util.List;

/**
 * * ================================================
 * name:            MyFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/9
 * description：  首页activity我的fragment中listview的adapter
 * history：
 * ===================================================
 */

public class MyFragLvAdapter extends BaseAdapter {
    private List<MyFragBean> list;
    private Context context;

    public MyFragLvAdapter(List<MyFragBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_myfrag,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.tv_frag_my_title);
            viewHolder.textView2= (TextView) view.findViewById(R.id.tv_frag_my_description);
            viewHolder.imageView1= (ImageView) view.findViewById(R.id.iv_frag_my_icon);
            viewHolder.imageView2= (ImageView) view.findViewById(R.id.iv_frag_my_in);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getTitle());
        viewHolder.textView2.setText(list.get(position).getMark());
        viewHolder.imageView1.setImageResource(list.get(position).getResId1());



        return view;
    }

    private class ViewHolder{
        private TextView textView1,textView2;
        private ImageView imageView1,imageView2;
    }

}
