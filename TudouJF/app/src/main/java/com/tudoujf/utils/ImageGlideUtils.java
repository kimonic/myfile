package com.tudoujf.utils;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
 */

public class ImageGlideUtils {
    /**
     * 加载圆形网络图片
     */
    public static void loadCircularImage(ImageView view, String url) {

        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.act_lock_icon) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.act_lock_icon)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }


    /**
     * 加载圆形网络图片
     */
    public static void loadImage(final ImageView view, String url) {

        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.act_lock_icon) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出

//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        Log.e("TAG", "onResourceReady: ---resource--"+resource);
//                       view.setImageDrawable(resource);
//                        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                        return false;
//                    }
//                })
                .into(view);
    }


    /**
     * 加载圆形网络图片
     */
    public static void loadImageFitCenter(final ImageView view, String url) {

        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.act_lock_icon) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出

                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        view.setImageDrawable(resource);
                        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        return false;
                    }
                })
                .into(view);

    }

    /**
     * 加载圆形网络图片无缓存模式
     */
    public static void loadCircularImageNoCache(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.act_lock_icon) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.act_lock_icon)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//图片缓存策略,这个一般必须有
                .skipMemoryCache(true)
                .crossFade()//淡入淡出
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }

    /**
     * 加载圆形本地资源图片
     */
    public static void loadCircularImage(ImageView view, int resId) {
        Glide.with(view.getContext())
                .load(resId)
                .error(R.drawable.act_lock_icon) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.act_lock_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }

    /**
     * 加载网络图片
     */
    public static void loadImageFromUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.ic_error) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .into(view);
    }

    /**
     * 加载网络图片
     */
    public static void loadImageFromUrl(ImageView view, int resId) {
        Glide.with(view.getContext())
                .load(resId)
                .error(R.drawable.ic_error) //加载图片失败的时候显示的默认图
                .placeholder(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//图片缓存策略,这个一般必须有
                .crossFade()//淡入淡出
                .into(view);
    }


}
