package com.tudoujf.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tudoujf.bean.databean.HomeBean;
import com.tudoujf.utils.ImageGlideUtils;

import java.util.List;

/**
 * * ================================================
 * name:            BannerVPAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/6
 * description：首页HomeFragment中banner的viewpager的适配器
 * history：
 * ===================================================
 */

public class BannerVPAdapter extends PagerAdapter {

    private List<ImageView> list;
    private List<HomeBean.BannerBean> listBanner;

    public BannerVPAdapter(List<ImageView> list, List<HomeBean.BannerBean> listBanner) {
        this.list = list;
        this.listBanner = listBanner;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 实例化 一个 页卡
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 添加一个 页卡
        ImageGlideUtils.loadImageFromUrl(list.get(position),listBanner.get(position).getImage());
        container.addView(list.get(position));
        return list.get(position);
    }

    /**
     * 销毁 一个 页卡
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 删除
        container.removeView(list.get(position));
    }



}
