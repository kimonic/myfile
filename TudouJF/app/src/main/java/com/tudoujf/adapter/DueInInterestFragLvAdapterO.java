package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.DueInInterestBean;
import com.tudoujf.fragment.myearnings.DueInInterestFragBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            DueInInterestFragLvAdapterO
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/30
 * description：  我的收益页面activity--->待收利息fragment,lv适配器
 * history：
 * ===================================================
 */

public class DueInInterestFragLvAdapterO extends BaseAdapter {
    private Context context;
    private List<DueInInterestBean.PageObjBean.ItemsBean> list;
    private int flag=1;

    public DueInInterestFragLvAdapterO(Context context, List<DueInInterestBean.PageObjBean.ItemsBean> list, int flag) {
        this.context = context;
        this.list = list;
        this.flag=flag;
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
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_dueininterestfrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.tv_lvitem_frag_dueininterest1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.tv_lvitem_frag_dueininterest2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.tv_lvitem_frag_dueininterest3);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.tv_lvitem_frag_dueininterest4);
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.ll__lvitem_frag_dueininterest);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView2.setText(list.get(position).getLoan_name());

        if (flag==1){
            viewHolder.textView3.setText(list.get(position).getPrincipal());
        }else {
            viewHolder.textView3.setVisibility(View.GONE);
        }

        if (list.get(position).getColorFlag()==1){
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#F6F5EB"));
        }

        viewHolder.textView4.setText(list.get(position).getInterest());
        return view;
    }

    private class ViewHolder {
        public TextView textView1, textView2, textView3, textView4;
        private LinearLayout linearLayout;
    }
}
