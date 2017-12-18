package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.utils.ImageGlideUtils;

import java.util.List;

/**
 * * ===============================================================
 * name:             IntegralShopRvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/12/18
 * description：  积分商城rv adapter
 * history：
 * *==================================================================
 */

public class IntegralShopRvAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context mContext;
    private List<IntegralShopBean.GoodsBean.ItemsBean> list;//数据

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public IntegralShopRvAdapter(Context context, List<IntegralShopBean.GoodsBean.ItemsBean> list) {
        mContext = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rvitem_integralshopact, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder) holder).tvTitle.setText(list.get(position).getName());
        ((MyViewHolder) holder).tvResidue.setText(("剩余  " + list.get(position).getStock()));
        ((MyViewHolder) holder).tvIntegral.setText(list.get(position).getCredit());
        ImageGlideUtils.loadImage(((MyViewHolder) holder).imageView, list.get(position).getImages());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvResidue, tvIntegral;
        private ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_rvitem_title);
            tvResidue = (TextView) view.findViewById(R.id.tv_rvitem_residue);
            tvIntegral = (TextView) view.findViewById(R.id.tv_rvitem_integral);
            imageView = (ImageView) view.findViewById(R.id.iv_rvitem);
        }


    }


}