package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.CreditorListBean;
import com.tudoujf.bean.databean.DaiHouGuanLiBean;
import com.tudoujf.ui.ClaimsBidView;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.List;

/**
 * * ================================================
 * name:            CreditorListFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  DaiHouGuanLiActivity中listview的adapter
 * history：
 * ===================================================
 */

public class DaiHouGuanLiLvAdapter extends BaseAdapter {
    private Context context;
    private List<DaiHouGuanLiBean.DataBean> list;
    private String TAG = "ManageMoneyFragAda";

    public DaiHouGuanLiLvAdapter(Context context, List<DaiHouGuanLiBean.DataBean> list) {
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
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_daihouguanliact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.tv_lvitem_daihouguanli_title);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.tv_lvitem_daihouguanli_starttime);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.tv_lvitem_daihouguanli_content);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(list.get(position).getAdmin_name());
        viewHolder.textView2.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView3.setText(("        "+list.get(position).getContents()));



        return view;
    }

    private class ViewHolder {
        TextView textView1,textView2,textView3;
    }
}
