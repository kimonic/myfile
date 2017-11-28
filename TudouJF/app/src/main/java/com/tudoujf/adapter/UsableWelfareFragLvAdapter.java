package com.tudoujf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.WelfareListBean;
import com.tudoujf.ui.MRedView;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            UsableWelfareFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：   UsableWelfareFragment中listview的adapter
 * history：
 * ===================================================
 */

public class UsableWelfareFragLvAdapter extends BaseAdapter {

    private List<WelfareListBean.ItemsBean> list;
    private Context context;
    private int selType = 1;

    public UsableWelfareFragLvAdapter(List<WelfareListBean.ItemsBean> list, Context context, int selType) {
        this.list = list;
        this.context = context;
        this.selType = selType;
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_usablewelfarefrag, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv3);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.lvitem_usablewelfarefrag_tv4);
            viewHolder.redView = (MRedView) view.findViewById(R.id.lvitem_usablewelfarefrag_mredview);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        switch (selType) {//红包
            case 1:

                viewHolder.textView1.setText(("单笔投资满" + list.get(position).getTender_amount_min() + "元  最低"));
                viewHolder.textView2.setText(("投资" + list.get(position).getTime_limit() + "个月可使用"));
                viewHolder.textView3.setText((list.get(position).getAmount() + "元 " + list.get(position).getType_name()));

                String status = "";
                boolean isValid = false;
                switch (list.get(position).getType()) {
                    case 1:
                        status = "可使用";
                        isValid = true;
                        break;
                    case 2:
                        status = "已使用";
                        break;
                    case 3:
                        status = "已过期";
                        break;
                }
                viewHolder.textView4.setText(status);
                viewHolder.redView.setOneText(StringUtils.getCommaDecimalsStrZeroDot(list.get(position).getAmount()));
                viewHolder.redView.setFourText(StringUtils.getStrTimeFull(list.get(position).getEnd_time()));
//        viewHolder.redView.setTwoText(list.get(position).getContent7());
                viewHolder.redView.setValid(isValid);
                viewHolder.redView.setRedPackageOrQuan(true);
                break;
            case 2://加息券

                viewHolder.textView1.setText(("单笔投资满" + list.get(position).getTender_amount_min() + "元  最低"));
                viewHolder.textView2.setText(("投资" + list.get(position).getTime_limit() + "个月可使用"));
                viewHolder.textView3.setText(list.get(position).getName());

                String statusQ = "";
                boolean isValidQ = false;
                switch (list.get(position).getType()) {
                    case 4:
                        statusQ = "可使用";
                        isValidQ = true;
                        break;
                    case 5:
                        statusQ = "已使用";
                        break;
                    case 6:
                        statusQ = "已过期";
                        break;
                }
                viewHolder.textView4.setText(statusQ);
                viewHolder.redView.setOneText(list.get(position).getInterest());
                viewHolder.redView.setFourText(StringUtils.getStrTimeFull(list.get(position).getEnd_time()));
                viewHolder.redView.setTwoText("%");
                viewHolder.redView.setValid(isValidQ);
                viewHolder.redView.setRedPackageOrQuan(false);

                break;
            case 3://返现券


                viewHolder.textView1.setText(("单笔投资满" + list.get(position).getTender_amount_min() + "元  最低"));
                viewHolder.textView2.setText(("投资" + list.get(position).getTime_limit() + "个月可使用"));
                viewHolder.textView3.setText(list.get(position).getName());

                String statusF = "";
                boolean isValidF = false;
                switch (list.get(position).getType()) {
                    case 7:
                        statusF = "可使用";
                        isValidF = true;
                        break;
                    case 8:
                        statusF = "已使用";
                        break;
                    case 9:
                        statusF = "已过期";
                        break;
                }
                viewHolder.textView4.setText(statusF);
                viewHolder.redView.setOneText(list.get(position).getProportion());
                viewHolder.redView.setFourText(StringUtils.getStrTimeFull(list.get(position).getEnd_time()));
                viewHolder.redView.setTwoText("%");
                viewHolder.redView.setValid(isValidF);
                viewHolder.redView.setRedPackageOrQuan(false);

                break;
        }

//

        viewHolder.redView.invalidate();


        return view;
    }

    public class ViewHolder {
        private TextView textView1, textView2, textView3, textView4;
        private MRedView redView;
    }

}
