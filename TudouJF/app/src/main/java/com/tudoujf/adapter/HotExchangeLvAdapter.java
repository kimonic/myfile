package com.tudoujf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.bean.databean.HotExchangeBean;
import com.tudoujf.utils.ImageGlideUtils;

import java.util.List;

/**
 * * ===============================================================
 * name:             hotexchangeLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：  HotExchangeActivity中的lv  adapter
 * history：
 * *==================================================================
 */

public class HotExchangeLvAdapter extends BaseAdapter {
    private List<HotExchangeBean.ItemsBean>  list;
    private Context context;

    public HotExchangeLvAdapter(List<HotExchangeBean.ItemsBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_hotexchange, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.tv_lvitem_hotexchange_title);
            viewHolder.description = (TextView) view.findViewById(R.id.tv_lvitem_hotexchange_description);
            viewHolder.integral = (TextView) view.findViewById(R.id.tv_lvitem_hotexchange_integral);
            viewHolder.count = (TextView) view.findViewById(R.id.tv_lvitem_hotexchange_count);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_lvitem_hotexchange);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(list.get(position).getName());
        viewHolder.description.setText(list.get(position).getBewrite());
        viewHolder.integral.setText(list.get(position).getCredit());
        Log.e("TAG", "getView: -----"+list.get(position).getGoods_count());

        viewHolder.count.setText((list.get(position).getGoods_count()+"份"));

        ImageGlideUtils.loadImage(viewHolder.imageView,list.get(position).getImages());


        return view;
    }

    private class ViewHolder{

        private TextView  title,description,integral,count;
        private ImageView imageView;
    }
}
