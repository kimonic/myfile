package com.tudoujf.http;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.tudoujf.utils.StringUtils;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;


/**
 * * ================================================
 * name:            HttpMethods
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/9
 * description：   网络连接工具类
 * history：
 * ===================================================
 */

public class HttpMethods {

    //创建单例
    private static class SingleonHolder {
        private static final HttpMethods instance = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingleonHolder.instance;
    }


    /**
     * get请求
     */
    @SuppressWarnings("unchecked")
    public void GET(final Context context, String url, final Map<String, String> paramsMap,
                    final String Key, Callback callback) {
        OkGo.get(url)   // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(Key)                      // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);
    }

    /**
     * get请求
     */
    @SuppressWarnings("unchecked")
    public void GET(final Context context, String url, Callback callback) {
        OkGo.get(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .execute(callback);
    }



    /**
     * post请求
     */
    @SuppressWarnings("unchecked")
    public void POST(Context context, String url, TreeMap<String, String> paramsMap,
                      String Key, Callback callback) {


        OkGo.post(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(Key)                      // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .params(StringUtils.getRequestParams(paramsMap))
                .execute(callback);
    }

    //下载http://imtt.dd.qq.com/16891/8C3E058EAFBFD4F1EFE0AAA815250716.apk?fsname=com.tencent.mobileqq_7.1.0_692.apk&csr=1bbd
    @SuppressWarnings("unchecked")
    public void Download(Context context, String url, Map<String, String> paramsMap,
                         Callback callback) {
        OkGo.get(url)//
                .tag(context)//
                .cacheKey("sds")
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);

    }

    public void postFile(Context context, String url, String loginToken, String filePath,
                         Callback callback) {
        OkGo.post(url)//
                .tag(context)
//                .isMultipart(true)//
                .params("login_token", loginToken)
                .params("headImage", new File(filePath))
                .cacheKey("sds")
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .execute(callback);

    }
    /**
     * post请求
     */
    @SuppressWarnings("unchecked")
    public void POST1(Context context, String url, TreeMap<String, String> paramsMap,
                     String Key, Callback callback) {


        OkGo.post(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(Key)                      // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.NO_CACHE)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);
    }

}
