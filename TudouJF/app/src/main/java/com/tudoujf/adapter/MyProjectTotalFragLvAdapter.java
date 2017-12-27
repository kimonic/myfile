package com.tudoujf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.activity.my.myproject.DaiHouGuanLiActivity;
import com.tudoujf.activity.my.myproject.MyCreditorRightsDetailsActivity;
import com.tudoujf.activity.my.myproject.MyInvestDetailsActivity;
import com.tudoujf.bean.databean.MyInvestProjectBean;
import com.tudoujf.bean.databean.MyProjectInvestChildBean;
import com.tudoujf.fragment.myprojectfrag.MyProjectTotalFragBean;
import com.tudoujf.ui.MyProjectBidView;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.TimeUtils;
import com.tudoujf.utils.ToastUtils;

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
    private List<MyProjectInvestChildBean.ItemsBean> list;

    public MyProjectTotalFragLvAdapter(Context context, List<MyProjectInvestChildBean.ItemsBean> list) {
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
        viewHolder.bidView.setTitle(list.get(position).getName());//标题
        viewHolder.bidView.setTransfer("");//右标题
        viewHolder.bidView.setZhaiQuanJiaZhi(list.get(position).getAmount());//投资金额
        viewHolder.bidView.setYuanBiaoNianHuaShouYi(list.get(position).getRecover_interest());//预期收益
        viewHolder.bidView.setTransferPrice(StringUtils.getStrTime(list.get(position).getNext_repay_time()));//回款时间
        viewHolder.bidView.setExplain1("投资金额(元)");
        viewHolder.bidView.setExplain2("预期收益(元)");
        viewHolder.bidView.setExplain3("回款时间");
        viewHolder.bidView.setInvestProgress(StringUtils.string2Float(list.get(position).getProgress())/100);//投资进度
        if (list.get(position).getAmount_surplus()==null){
            viewHolder.bidView.setShengYuKeTou("0 元");
        }else {
            viewHolder.bidView.setShengYuKeTou((list.get(position).getAmount_surplus()+" 元"));
        }
        viewHolder.bidView.setStatus_name(list.get(position).getStatus_name());



        final int pos=position;

        if (!"投标中".equals(list.get(position).getStatus_name())){

            viewHolder.bidView.setShowDaiHouGuanLi(true);
            viewHolder.bidView.setListener(new MyProjectBidView.ClickEventListener() {
                @Override
                public void clickEvent() {
                    Intent intent=new Intent(context, DaiHouGuanLiActivity.class);
                    intent.putExtra("loan_id",list.get(pos).getLoanId());
                    context.startActivity(intent);
//                    ToastUtils.showToast(context, "打开贷后管理");

                }
            });

        }else {
            viewHolder.bidView.setShowDaiHouGuanLi(false);
        }




        viewHolder.bidView.invalidate();

        return view;
    }

    private class ViewHolder {

        MyProjectBidView bidView;

    }
}
