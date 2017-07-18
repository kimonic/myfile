package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 * listview的简单测试适配器
 */

public class SimplVAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public SimplVAdapter(List<String> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_simple,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) view.findViewById(R.id.tv_simplelvadapter);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(list.get(position));



        return view;
    }

    private class ViewHolder{
        private TextView textView;
    }
}
