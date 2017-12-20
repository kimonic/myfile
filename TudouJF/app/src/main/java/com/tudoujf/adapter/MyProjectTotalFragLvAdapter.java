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
import com.tudoujf.bean.databean.MyInvestProjectBean;
import com.tudoujf.fragment.myprojectfrag.MyProjectTotalFragBean;
import com.tudoujf.ui.MyProjectBidView;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.TimeUtils;

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
    private List<MyInvestProjectBean.UnderwayBean> list;

    public MyProjectTotalFragLvAdapter(Context context, List<MyInvestProjectBean.UnderwayBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
//        boolean  skip=false;
//        if (!"".equals(list.get(position).getFlag())){
//            skip=true;
//        }
        viewHolder.bidView.setTitle(list.get(position).getLoan_name());//标题
        viewHolder.bidView.setTransfer("");//右标题
        viewHolder.bidView.setZhaiQuanJiaZhi(list.get(position).getAmount());//投资金额
        viewHolder.bidView.setYuanBiaoNianHuaShouYi(list.get(position).getRecover_interest());//预期收益
        viewHolder.bidView.setTransferPrice(StringUtils.getStrTime(list.get(position).getExpire_time().toString()));//回款时间
        viewHolder.bidView.setExplain1("投资金额(元)");
        viewHolder.bidView.setExplain2("预期收益(元)");
        viewHolder.bidView.setExplain3("回款时间");
        viewHolder.bidView.setInvestProgress(StringUtils.string2Float(list.get(position).getProgress())/100);//投资进度
        if (list.get(position).getAmount_surplus()==null){
            viewHolder.bidView.setShengYuKeTou("此处获取值为null");
        }else {
            viewHolder.bidView.setShengYuKeTou(list.get(position).getAmount_surplus());
        }
        viewHolder.bidView.setStatus_name(list.get(position).getStatus_name());

//         final boolean finalSkip = skip;
        final int  mPosition=position;
        viewHolder.bidView.setListener(new MyProjectBidView.ClickEventListener() {
            @Override
            public void clickEvent() {
//                if(finalSkip){
//                    Intent intent=new Intent(context, MyCreditorRightsDetailsActivity.class);
//                    context.startActivity(intent);
//                }else {
                    Intent intent=new Intent(context, MyInvestDetailsActivity.class);
                    intent.putExtra("id",list.get(mPosition).getLoan_id());
                    context.startActivity(intent);
//                }

            }
        });



        viewHolder.bidView.invalidate();

        return view;
    }

    private class ViewHolder {

        MyProjectBidView bidView;

    }
}
