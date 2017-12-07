package com.tudoujf.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.DiscoverFragBean;
import com.tudoujf.bean.databean.DiscoverBean;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.StringUtils;

import java.util.List;

/**
 * * ================================================
 * name:            DiscoverFragLvAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/9
 * description：  首页activity发现fragment中listview的adapter
 * history：
 * ===================================================
 */
public class DiscoverFragLvAdapter extends BaseAdapter {
    private List<DiscoverBean.ItemsBean> list;
    private Context context;

    public DiscoverFragLvAdapter(List<DiscoverBean.ItemsBean> list, Context context) {
        this.list = list;
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
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.lvitem_discoverfrag,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1= (TextView) view.findViewById(R.id.lvitem_discover_tv1);
            viewHolder.textView2= (TextView) view.findViewById(R.id.lvitem_discover_tv2);
            viewHolder.textView3= (TextView) view.findViewById(R.id.lvitem_discover_tv3);
            viewHolder.imageView= (ImageView) view.findViewById(R.id.lvitem_discover_iv);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getName());
        viewHolder.textView2.setText(StringUtils.getStrTime(list.get(position).getAddTime()));

        if (list.get(position).getContents()!=null){
            viewHolder.textView3.setText(Html.fromHtml(list.get(position).getContents()));
        }else {
            viewHolder.textView3.setText("");
        }
        ImageGlideUtils.loadImageFromUrl(viewHolder.imageView,list.get(position).getImage());
//        viewHolder.imageView.setImageResource(list.get(position).getResId());

        return view;
    }

    private class ViewHolder{
        public TextView textView1,textView2,textView3;
        public ImageView imageView;
    }
}
