package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.SystemMessageFragBean;
import com.tudoujf.bean.databean.TypeInfoBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            BankCardManageActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  listview的简单适配器
 * history：
 * * ====================================================================
 */
public class DialogLVAdapter extends BaseAdapter {
    private List<TypeInfoBean.ItemsBean> list;
    private Context context;

    public DialogLVAdapter(List<TypeInfoBean.ItemsBean> list, Context context) {
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
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listdialog, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_listdialog);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.textView.setText(list.get(position).getName());
        if (list.get(position).getBacFlag()==1) {
            view.setBackgroundColor(Color.parseColor("#F4F4F4"));
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.color_gray3));
        } else {
            view.setBackgroundColor(Color.parseColor("#E3FBFE"));
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.global_theme_background_color));
        }

        return view;
    }

    private class ViewHolder {
        private TextView textView;
    }
}
