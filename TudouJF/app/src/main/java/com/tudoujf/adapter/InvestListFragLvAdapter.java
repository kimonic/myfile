package com.tudoujf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.activity.managemoney.ProductDetailsActivity;
import com.tudoujf.bean.databean.InvestListBean;
import com.tudoujf.ui.BidView;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            InvestListFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  InvestListFragment中listview的adapter
 * history：
 * ===================================================
 */

public class InvestListFragLvAdapter extends BaseAdapter {
    private Context context;
    private List<InvestListBean.ItemsBean> list;
    private String TAG="ManageMoneyFragAda";

    public InvestListFragLvAdapter(Context context, List<InvestListBean.ItemsBean> list) {
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
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_investfrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bidView = (BidView) view.findViewById(R.id.lvitem_managemoneymatters_bv);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (!"-1".equals(list.get(position).getAward_status())) {//是否额外奖励 -1否 1按金额，2按比例
            viewHolder.bidView.setAward(true);//奖0.02%是否显示
        }else {
            viewHolder.bidView.setAward(false);//奖0.02%是否显示
        }


//        viewHolder.bidView.setAwardValue(list.get(position).getAwardValue());//奖励的数值

        if (!"借款中".equals(list.get(position).getStatus_name())){//是否时立即还款按钮
            viewHolder.bidView.setInvestNow(false);
        }else {
            viewHolder.bidView.setInvestNow(true);
        }
        if (list.get(position).getStatus_name()!=null){
            viewHolder.bidView.setButtonText(list.get(position).getStatus_name());//按钮文本
        }




        viewHolder.bidView.setInvestProgress(StringUtils.string2Float(list.get(position).getProgress())/100);//投资进度
        if (list.get(position).getName()!=null){
            viewHolder.bidView.setTitle((list.get(position).getCategory_name()+list.get(position).getSerialno()));//标的标题
        }

        if ("-1".equals(list.get(position).getAdditional_status())){//是否显示新手专享
            viewHolder.bidView.setIsNewer("");
        }else {
            viewHolder.bidView.setIsNewer(context.getResources().getString(R.string.xinshouzhuanxiang));
        }

        viewHolder.bidView.setNianHuaShouYi(StringUtils.getTwoDecimalsStr(list.get(position).getApr())+"%");//预期年化收益率

        viewHolder.bidView.setShengYuKeTou(list.get(position).getAmount_surplus()+"元");//剩余可投资金额

        viewHolder.bidView.setInvestSum(list.get(position).getAmount()+"元");//总投资金额
        viewHolder.bidView.setInvestTime(list.get(position).getPeriod()+"个月");//投资期限
        viewHolder.bidView.invalidate();

        viewHolder.bidView.setListener(new BidView.ClickEventListener() {
            @Override
            public void clickEvent() {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("loan_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });


        return view;
    }

    private class ViewHolder {
        BidView bidView;
    }
}
