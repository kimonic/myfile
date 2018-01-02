package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.AccountRecordBean;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            SucceedInvitationActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/5
 * description：  我的推广页面-->结算记录activity-->listview适配器
 * history：
 * ===================================================
 */

public class AcountRecordActLvAdapter extends BaseAdapter {

    private Context context;
    private List<AccountRecordBean.ItemsBean> list;

    public AcountRecordActLvAdapter(Context context, List<AccountRecordBean.ItemsBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_acountrecord, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.ll_lvitem_succeedinvitation);
            viewHolder.textView1 = (TextView) view.findViewById(R.id.tv_lvitem_succeedinvitation1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.tv_lvitem_succeedinvitation2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.tv_lvitem_succeedinvitation3);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (list.get(position).getBacFlag() == 2) {
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lvbac));
        } else {
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_white));
        }

        viewHolder.textView1.setText(StringUtils.getStrTime(list.get(position).getVerify_time()));
        viewHolder.textView2.setText(list.get(position).getMoney());
        viewHolder.textView3.setText(list.get(position).getStatus());


        return view;
    }

    private class ViewHolder {
        TextView textView1, textView2, textView3;
        LinearLayout linearLayout;
    }
}
