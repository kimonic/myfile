package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.RedPackageActLvBean;
import com.tudoujf.ui.RedView;

import java.util.List;

/**
 * * ================================================
 * name:            RedPackageActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：  RedPackageActivity中listview的adapter
 * history：
 * ===================================================
 */

public class RedPackageActLvAdapter extends BaseAdapter {

    private List<RedPackageActLvBean>  list;
    private Context context;

    public RedPackageActLvAdapter(List<RedPackageActLvBean> list, Context context) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_redpackageact,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_redpackage_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_redpackage_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_redpackage_tv3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.lvitem_redpackage_tv4);
//            viewHolder.redView= (RedView) view.findViewById(R.id.lvitem_redpackage_rv5);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getContent1());
        viewHolder.textView2.setText(list.get(position).getContent2());
        viewHolder.textView3.setText(list.get(position).getContent3());
        viewHolder.textView4.setText(list.get(position).getContent4());
//        viewHolder.redView.setText(list.get(position).getContent4());





        return view;
    }

    private class ViewHolder{
        TextView textView1,textView2,textView3,textView4;
        RedView redView;
    }

}
