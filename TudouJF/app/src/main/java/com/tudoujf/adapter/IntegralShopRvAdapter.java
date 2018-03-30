package com.tudoujf.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.IntegralShopBean;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.LUtils;

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
    //-----------------------------------20180330---------------------------------------------------
    private int resHeader;
    private int headerCount = 1;
    private View headerView;

    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }
    //-----------------------------------20180330---------------------------------------------------

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //适配器初始化
    public IntegralShopRvAdapter(Context context, List<IntegralShopBean.GoodsBean.ItemsBean> list, @LayoutRes int resHeader) {
        mContext = context;
        this.list = list;
        this.resHeader=resHeader;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rvitem_integralshopact, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        } else if (viewType == TYPE_HEAD) {//返回header头
//            if (headerView == null) {
//                headerView = LayoutInflater.from(mContext).inflate(resHeader, null, false);
//            }
            //处理点击事件
            return new IntegralShopRvAdapter.HeadHolder(headerView);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LUtils.e(IntegralShopRvAdapter.class,"logflag--adapter-"+position);

        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tvTitle.setText(list.get(position-1).getName());
            ((MyViewHolder) holder).tvResidue.setText(("剩余:  " + list.get(position-1).getStock()));
            ((MyViewHolder) holder).tvIntegral.setText(list.get(position-1).getCredit());
            ((MyViewHolder) holder).tvDescription.setText(list.get(position-1).getBewrite());
            ImageGlideUtils.loadImageFitCenter(((MyViewHolder) holder).imageView, list.get(position-1).getImages());

            holder.itemView.setTag(position-1);//设置点击的位置
        } else if (holder instanceof HeadHolder) {

        }


    }


    @Override
    public int getItemCount() {
        //----------20180330添加头部view---------------------------------------------------------------
        return list.size() + 1;
    }

    //----------20180330添加头部view---------------------------------------------------------------
    @Override
    public int getItemViewType(int position) {

        if (isHead(position)) { // 头部
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
        }
    }

    public boolean isHead(int position) {
        return resHeader != -1 && position == 0;
    }
    //----------20180330添加头部view---------------------------------------------------------------


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvResidue, tvIntegral, tvDescription;
        private ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_rvitem_title);
            tvResidue = (TextView) view.findViewById(R.id.tv_rvitem_residue);
            tvIntegral = (TextView) view.findViewById(R.id.tv_rvitem_integral);
            tvDescription = (TextView) view.findViewById(R.id.tv_rvitem_description);
            imageView = (ImageView) view.findViewById(R.id.iv_rvitem);
        }


    }

    //----------20180330添加头部view---------------------------------------------------------------
    // 头部
    private class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
        }
    }
    //----------20180330添加头部view---------------------------------------------------------------
}