package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.IntegralRankingListBean;
import com.tudoujf.utils.ImageGlideUtils;

import java.util.List;

/**
 * * ===============================================================
 * name:             ClassificationOfGoodsLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/19
 * description：  IntegralRankingListActivity中的lv  adapter
 * history：
 * *==================================================================
 */

public class IntegralRankingListLvAdapter extends BaseAdapter {
    private List<IntegralRankingListBean.ItemsBean>  list;
    private Context context;

    public IntegralRankingListLvAdapter(List<IntegralRankingListBean.ItemsBean> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_integralrankinglist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.tv_lvitem_integralrankinglist1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.tv_lvitem_integralrankinglist2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.tv_lvitem_integralrankinglist3);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_lvitem_integralrankinglist);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (position<3){
            viewHolder.textView1.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setImageResource(list.get(position).getResId());
        }else {
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.textView1.setVisibility(View.VISIBLE);
            viewHolder.textView1.setText(list.get(position).getPosition());
        }

        viewHolder.textView2.setText(list.get(position).getName());
        viewHolder.textView3.setText(list.get(position).getCredit_point());


        return view;
    }

    private class ViewHolder{

        private TextView  textView1,textView2,textView3;
        private ImageView imageView;
    }
}
