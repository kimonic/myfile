package com.tudoujf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.databean.CalendarBean;

import java.util.List;

/**
 * * ================================================
 * name:            CalendarSelDialogRvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/25
 * description： CalendarSelDialog中的RvAdapter
 * history：
 * ===================================================
 */

public class CalendarSelDialogRvAdapter extends RecyclerView.Adapter<CalendarSelDialogRvAdapter.MyViewHolder> {
    private Context context;
    private List<CalendarBean> list;

    private RVItemListener listener;

    /**
     * item点击事件
     */
    public RVItemListener getListener() {
        return listener;
    }

    public void setListener(RVItemListener listener) {
        this.listener = listener;
    }

    public CalendarSelDialogRvAdapter(Context context, List<CalendarBean> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rvitem_dialog_calendarsel, parent, false));
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(("" + list.get(position).getDay()));
        holder.tv.setTag(position);


        switch (list.get(position).getFlag()) {
            case 1:
                holder.tv.setBackgroundColor(context.getResources().getColor(R.color.calendar_bac));
                break;
            case 2:
                holder.tv.setBackgroundColor(context.getResources().getColor(R.color.dialog_integralrecode_bac));
                break;
            case 3:
                holder.tv.setBackgroundColor(context.getResources().getColor(R.color.calendar_selbac));
                break;
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public interface RVItemListener {
        void itemClick(View v);
    }
}
