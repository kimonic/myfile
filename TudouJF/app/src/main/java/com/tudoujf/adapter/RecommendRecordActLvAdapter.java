package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.my.mypopularize.RecommendRecordActBean;
import com.tudoujf.bean.databean.RecommendRecordBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            RecommendRecordActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  我的推广页面-->推广记录activity-->listview适配器
 * history：
 * ===================================================
 */

public class RecommendRecordActLvAdapter extends BaseAdapter {
    private Context context;
    private List<RecommendRecordBean.ItemsBean> list;

    public RecommendRecordActLvAdapter(Context context, List<RecommendRecordBean.ItemsBean> list) {
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
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_recommendrecoact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.linearLayout= (LinearLayout) view.findViewById(R.id.ll_lvitem_recommendrecord);
            viewHolder.textView1= (TextView) view.findViewById(R.id.tv_lvitem_recommendrecord1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.tv_lvitem_recommendrecord2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.tv_lvitem_recommendrecord3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.tv_lvitem_recommendrecord4);
            viewHolder.textView5= (TextView) view.findViewById(R.id.tv_lvitem_recommendrecord5);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (list.get(position).getBacFlag()==2){
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lvbac));
        }else {
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_white));
        }

        viewHolder.textView1.setText(list.get(position).getSpreaded_member_name());
        viewHolder.textView2.setText(list.get(position).getSpread_type());
        viewHolder.textView3.setText(list.get(position).getProportion());
        viewHolder.textView4.setText(list.get(position).getAward());
        viewHolder.textView5.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));




        return view;
    }

    private class ViewHolder {
        TextView textView1, textView2, textView3,textView4,textView5;
        LinearLayout linearLayout;
    }
}
