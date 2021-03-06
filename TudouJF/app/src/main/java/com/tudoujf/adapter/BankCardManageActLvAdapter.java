package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.BankCardManageActBean;
import com.tudoujf.bean.databean.BankCardManageBean;
import com.tudoujf.utils.ImageGlideUtils;

import java.util.List;

/**
 * * ================================================
 * name:            BankCardManageActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/15
 * description：  BankCardManageActivity中listview的adapter
 * history：
 * ===================================================
 */

public class BankCardManageActLvAdapter extends BaseAdapter {
    private List<BankCardManageBean.BankInfoBean> list;
    private Context context;

    public BankCardManageActLvAdapter(List<BankCardManageBean.BankInfoBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_bankcardmanageact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.lvitem_bankcardmanage_tvname);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.lvitem_bankcardmanage_tvnumber);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.lvitem_bankcardmanage_ivicon);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getBank_name());
        viewHolder.textView2.setText(list.get(position).getAccount());
        ImageGlideUtils.loadImageFromUrl(viewHolder.imageView,list.get(position).getBank_img());
//        .setImageResource(list.get(position).getImageResId());


        return view;
    }

    private class ViewHolder {
        private TextView textView1, textView2;
        private ImageView imageView;
    }
}
