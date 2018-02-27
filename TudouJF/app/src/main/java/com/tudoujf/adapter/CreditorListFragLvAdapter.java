package com.tudoujf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tudoujf.R;
import com.tudoujf.bean.databean.CreditorListBean;
import com.tudoujf.ui.ClaimsBidView;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            CreditorListFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/2
 * description：  CreditorListFragment中listview的adapter
 * history：
 * ===================================================
 */

public class CreditorListFragLvAdapter extends BaseAdapter {
    private Context context;
    private List<CreditorListBean.ItemsBean> list;
    private String TAG = "ManageMoneyFragAda";

    public CreditorListFragLvAdapter(Context context, List<CreditorListBean.ItemsBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_creditorfrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bidView = (ClaimsBidView) view.findViewById(R.id.lvitem_managemoneymatters_bv);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.bidView.setInvestProgress(StringUtils.string2Float(list.get(position).getProgress()) / 100);//投资进度
        if (list.get(position).getLoan_name() != null) {
            if (list.get(position).getLoan_name().length()>20){
                viewHolder.bidView.setTitle((list.get(position).getLoan_name().substring(0,17)+"..."));//标的标题过长时显示部分内容
            }else {
                viewHolder.bidView.setTitle(list.get(position).getLoan_name());//标的标题
            }
        }

        if (list.get(position).getAmount_surplus() == null) {
            viewHolder.bidView.setShengYuKeTou("0.00元");//剩余可投资金额
        } else {
            viewHolder.bidView.setShengYuKeTou(list.get(position).getAmount_surplus() + "元");//剩余可投资金额
        }


        viewHolder.bidView.setTransfer("转让期数:" + list.get(position).getPeriod() + "/" + list.get(position).getTotal_period());//转让期数
        viewHolder.bidView.setZhaiQuanJiaZhi(list.get(position).getAmount_money());//债权价值


        viewHolder.bidView.setYuanBiaoNianHuaShouYi(StringUtils.getTwoDecimalsStr(list.get(position).getApr()));//原标年化收益率
        viewHolder.bidView.setStatus(list.get(position).getStatus());//按钮状态标识


        viewHolder.bidView.setTransferPrice(list.get(position).getAmount());//转让金额

        final int pos = position;
//---------------------------显示贷后管理-------------------------------------------------------------

//        viewHolder.bidView.setListener(new ClaimsBidView.ClickEventListener() {
//            @Override
//            public void clickEvent() {
//                Intent intent=new Intent(context, DaiHouGuanLiActivity.class);
//                intent.putExtra("loan_id",list.get(pos).getLoan_id());
//                context.startActivity(intent);
////                ToastUtils.showToast(context, "打开贷后管理");
//            }
//        });

        //---------------------------显示贷后管理-------------------------------------------------------------

//        viewHolder.bidView.setInvestTime(list.get(position).getPeriod()+"个月");//投资期限
        viewHolder.bidView.invalidate();
//        final String  id=list.get(position).getId();
//        final String  loan_id=list.get(position).getLoan_id();

//        viewHolder.bidView.setListener(new ClaimsBidView.ClickEventListener() {
//            @Override
//            public void clickEvent() {
//                Intent intent = new Intent(context, CreditorRightsDetailsActivity.class);
//                intent.putExtra("transfer_id",id);
//                intent.putExtra("loan_id",loan_id);
//                context.startActivity(intent);
//            }
//        });


        return view;
    }

    private class ViewHolder {
        ClaimsBidView bidView;
    }
}
