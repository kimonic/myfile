package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.bean.ManageMoneyMattersFragBean;
import com.tudoujf.ui.BidView;
import com.tudoujf.ui.ClaimsBidView;

import java.util.List;

/**
 * * ================================================
 * name:            ManageMoneyMattersChildFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：  ManageMoneyMattersFragment中子fragment中listview的adapter
 * history：
 * ===================================================
 */

public class ManageMoneyMattersChildFragLvAdapter extends BaseAdapter {
    private Context context;
    private List<ManageMoneyMattersFragBean> list;

    public ManageMoneyMattersChildFragLvAdapter(Context context, List<ManageMoneyMattersFragBean> list) {
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
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_managemoneymatterschildfrag,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.bidView= (ClaimsBidView) view.findViewById(R.id.lvitem_managemoneymatterschild_cbv);

            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }


//        viewHolder.bidView.setAward(list.get(position).isAward());
//        viewHolder.bidView.setAwardValue(list.get(position).getAwardValue());
        viewHolder.bidView.setInvestNow(list.get(position).isInvestNow());
//        viewHolder.bidView.setInvestProgress(list.get(position).getInvestProgress());
//
//        viewHolder.bidView.setTitle(list.get(position).getTitle());
//        viewHolder.bidView.setNianHuaShouYi(list.get(position).getNianHuaShouYi());
//
//        viewHolder.bidView.setShengYuKeTou(list.get(position).getShengYuKeTou());
//        viewHolder.bidView.setInvestSum(list.get(position).getInvestSum());
//        viewHolder.bidView.setInvestTime(list.get(position).getInvestTime());






        return view;
    }

    private class  ViewHolder{
        ClaimsBidView bidView;
    }
}
