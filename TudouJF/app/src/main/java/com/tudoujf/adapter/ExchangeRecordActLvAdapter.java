package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.ExchangeRecordBean;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            RecommendRecordActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/6
 * description：  积分商城-->兑换记录activity-->listview适配器
 * history：
 * ===================================================
 */

public class ExchangeRecordActLvAdapter extends BaseAdapter {
    private Context context;
    private List<ExchangeRecordBean.ItemsBean> list;

    public ExchangeRecordActLvAdapter(Context context, List<ExchangeRecordBean.ItemsBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_exchangerecoact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.linearLayout= (LinearLayout) view.findViewById(R.id.ll_lvitem_exchangerecord);
            viewHolder.textView1= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord3);
            viewHolder.textView4= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord4);
            viewHolder.textView5= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord5);
            viewHolder.textView6= (TextView) view.findViewById(R.id.tv_lvitem_exchangerecord6);
            viewHolder.imageView= (ImageView) view.findViewById(R.id.iv_lvitem_exchangerecord);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

//        if (list.get(position).getBacFlag()==2){
//            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lvbac));
//        }else {
//            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_white));
//        }

        viewHolder.textView1.setText(list.get(position).getGoods_name());
        viewHolder.textView2.setText(list.get(position).getCredit());
        viewHolder.textView3.setText(list.get(position).getOrder_count());
        viewHolder.textView4.setText(list.get(position).getDelivery_status());
        viewHolder.textView5.setText(StringUtils.getStrTime(list.get(position).getAdd_time()));
        viewHolder.textView6.setText(list.get(position).getRemark());

        if (list.get(position).getImages()!=null){
            ImageGlideUtils.loadImageFromUrl(viewHolder.imageView,list.get(position).getImages());
        }
        return view;
    }

    private class ViewHolder {
        TextView textView1, textView2, textView3,textView4,textView5,textView6;
        LinearLayout linearLayout;
        ImageView imageView;
    }
}
