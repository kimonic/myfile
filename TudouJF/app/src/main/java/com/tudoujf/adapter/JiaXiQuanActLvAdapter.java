package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.JiaXiQuanBean;
import com.tudoujf.bean.databean.RedBagBean;
import com.tudoujf.ui.MRedView;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            RedPackageActLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/24
 * description：  RedPackageActivity中listview的adapter/加息券用
 * history：
 * ===================================================
 */

public class JiaXiQuanActLvAdapter extends BaseAdapter {

    private List<JiaXiQuanBean.ListBean> list;
    private Context context;
    private int type;

    public JiaXiQuanActLvAdapter(JiaXiQuanBean bean, Context context) {
        this.list = bean.getList();
        this.type = bean.getType();
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
            view = LayoutInflater.from(context).inflate(R.layout.lvitem_redpackageact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) view.findViewById(R.id.lvitem_redpackage_tv1);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.lvitem_redpackage_tv2);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.lvitem_redpackage_tv3);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.lvitem_redpackage_tv4);
            viewHolder.redView = (MRedView) view.findViewById(R.id.lvitem_redpackage_rv5);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText("单笔投资满" + list.get(position).getTender_amount_min() + "元、最低");
        viewHolder.textView2.setText("投资" + list.get(position).getTime_limit() + "个月可使用");
        viewHolder.textView3.setText((list.get(position).getInterest() + "%   "+list.get(position).getName()));
        viewHolder.textView4.setText(context.getResources().getString(R.string.keshiyong));

        viewHolder.redView.setRedPackageOrQuan(false);
        viewHolder.redView.setOneText(list.get(position).getInterest());
        viewHolder.redView.setFourText(StringUtils.getStrTimeBias(list.get(position).getEnd_time()));


        if (type == 2) {//加息券界面
           //加息券,fan界面
            viewHolder.redView.setTwoText("%");
            if (list.get(position).getBackground() == 1) {
                view.setBackgroundColor(Color.parseColor("#ffffff"));
                viewHolder.redView.setSel(false);
//                viewHolder.redView.setBitmap(R.drawable.act_redpackage2_quanunsel);
            } else {
                view.setBackgroundColor(Color.parseColor("#E7FAFF"));
                viewHolder.redView.setSel(true);
//                viewHolder.redView.setBitmap(R.drawable.act_redpackage2_quansel);
            }
        }


        viewHolder.redView.invalidate();
        return view;
    }

    public class ViewHolder {
        TextView textView1, textView2, textView3, textView4;
        MRedView redView;
    }

}
