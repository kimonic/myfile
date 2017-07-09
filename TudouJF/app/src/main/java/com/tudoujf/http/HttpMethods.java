package com.tudoujf.http;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;

import java.util.Map;


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
    static String POST = "POST";
    static String GET = "Get";

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
    public void GET(final Context context, String url, final Map<String, String> paramsMap,
                    final String Key, Callback callback) {
        OkGo.get(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(Key)                      // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);
    }

    /**
     * post请求
     */
    public <T> void POST(final Context context, String url, final Map<String, String> paramsMap,
                         final String Key, Callback callback) {
        OkGo.post(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(Key)                      // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);
    }


    //下载
    public void Download(final Context context, String url, final Map<String, String> paramsMap,
                         Callback callback) {
        OkGo.get(url)//
                .tag(context)//
                .cacheKey("sds")
                .cacheMode(CacheMode.DEFAULT)       // 缓存模式，详细请看缓存介绍
                .params(paramsMap)
                .execute(callback);

    }


}
