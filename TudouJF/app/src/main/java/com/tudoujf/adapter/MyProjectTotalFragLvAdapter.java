package com.tudoujf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.activity.my.myproject.MyCreditorRightsDetailsActivity;
import com.tudoujf.activity.my.myproject.MyInvestDetailsActivity;
import com.tudoujf.fragment.myprojectfrag.MyProjectTotalFragBean;
import com.tudoujf.ui.MyProjectBidView;

import java.util.List;

/**
 * * ================================================
 * name:            MyProjectTotalFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/8
 * description：  我的项目-->全部投资项目fragment-->listview适配器
 * history：
 * ===================================================
 */

public class MyProjectTotalFragLvAdapter extends BaseAdapter {
    private Context context;
    private List<MyProjectTotalFragBean> list;

    public MyProjectTotalFragLvAdapter(Context context, List<MyProjectTotalFragBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_myprojecttotalfrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bidView= (MyProjectBidView) view.findViewById(R.id.lvitem_myprojecttotal);


            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        boolean  skip=false;
        if (!"".equals(list.get(position).getTitle7())){
            skip=true;
        }
        viewHolder.bidView.setTitle(list.get(position).getTitle());
        viewHolder.bidView.setTransfer(list.get(position).getTitle7());
        viewHolder.bidView.setZhaiQuanJiaZhi(list.get(position).getTitle1());
        viewHolder.bidView.setYuanBiaoNianHuaShouYi(list.get(position).getTitle2());
        viewHolder.bidView.setTransferPrice(list.get(position).getTitle3());
        viewHolder.bidView.setExplain1(list.get(position).getTitle4());
        viewHolder.bidView.setExplain2(list.get(position).getTitle5());
        viewHolder.bidView.setExplain3(list.get(position).getTitle6());
        viewHolder.bidView.setInvestProgress(list.get(position).getTouzijindu());
        viewHolder.bidView.setShengYuKeTou(list.get(position).getTitle8());
        viewHolder.bidView.setType(list.get(position).getType());

         final boolean finalSkip = skip;
        viewHolder.bidView.setListener(new MyProjectBidView.ClickEventListener() {
            @Override
            public void clickEvent() {
                if(finalSkip){
                    Intent intent=new Intent(context, MyCreditorRightsDetailsActivity.class);
                    context.startActivity(intent);
                }else {
                    Intent intent=new Intent(context, MyInvestDetailsActivity.class);
                    context.startActivity(intent);
                }

            }
        });



        viewHolder.bidView.invalidate();

        return view;
    }

    private class ViewHolder {

        MyProjectBidView bidView;

    }
}
