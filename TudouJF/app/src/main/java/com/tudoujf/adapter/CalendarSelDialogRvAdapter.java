package com.tudoujf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tudoujf.R;

import java.util.ArrayList;
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
    private List<String> list;

    public CalendarSelDialogRvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        initList();
    }

    private void initList() {
        list=new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            list.add(""+i);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rvitem_dialog_calendarsel, parent,false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
