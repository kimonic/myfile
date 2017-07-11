package com.tudoujf.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tudoujf.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * * ==================================================
 * name:            ImageGlideUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/7
 * description：   glide加载图片工具类
 * history：
 * * ==================================================
 *
 */

public class ImageGlideUtils {
    public static  void loadCircularImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher) //加载图片失败的时候显示的默认图
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }
    public static  void loadCircularImage(ImageView view, int resId) {
        Glide.with(view.getContext())
                .load(resId)
                .error(R.mipmap.ic_launcher) //加载图片失败的时候显示的默认图
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }
}
