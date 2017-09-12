package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.RedPackageActBean;
import com.tudoujf.ui.MRedView;
import com.tudoujf.ui.RedView;

import java.util.List;

/**
 * * ================================================
 * name:            UsableWelfareFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   UsableWelfareFragment中listview的adapter
 * history：
 * ===================================================
 */

public class UsableWelfareFragLvAdapter extends BaseAdapter {

    private List<RedPackageActBean>  list;
    private Context context;

    public UsableWelfareFragLvAdapter(List<RedPackageActBean> list, Context context) {
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
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_usablewelfarefrag,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv4);
            viewHolder.redView= (MRedView) view.findViewById(R.id.lvitem_usablewelfarefrag_mredview);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        Log.e("TAG", "getView:----------usable----- "+list.get(position).getContent1() );
        viewHolder.textView1.setText(list.get(position).getContent1());
        viewHolder.textView2.setText(list.get(position).getContent2());
        viewHolder.textView3.setText(list.get(position).getContent3());
        viewHolder.textView4.setText(list.get(position).getContent4());
        viewHolder.redView.setOneText(list.get(position).getContent5());
        viewHolder.redView.setFourText(list.get(position).getContent6());
        viewHolder.redView.setTwoText(list.get(position).getContent7());
        viewHolder.redView.setValid(list.get(position).isValid());
        viewHolder.redView.setRedPackageOrQuan(list.get(position).isQuanOrRedPackage());

        viewHolder.redView.invalidate();









        return view;
    }

    public class ViewHolder{
        public TextView textView1,textView2,textView3,textView4;
        public MRedView redView;
    }

}
