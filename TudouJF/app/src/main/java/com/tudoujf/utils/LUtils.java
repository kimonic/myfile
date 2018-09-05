package com.tudoujf.utils;

import android.util.Log;

/**
 * Created by 12348 on 2017/5/12 0012.
 * 打印自定义信息
 */

public class LUtils {
    // TODO: 2018/8/15 是否打开日志
//    private static final boolean  DEBUG=false;
    private static final boolean  DEBUG=true;
    /**
     * 显示--类名,方法名,自定义信息
     *
     * @param clz 打印log所在的类
     * @param str 自定义打印的字符串
     * mark:Thread.currentThread().getStackTrace()可以获得方法调用顺序
     */
    public static void e(Class clz, String str) {
        if (DEBUG){
            Log.e("------"+clz.getSimpleName(), Thread.currentThread().getStackTrace()[3].getMethodName() + "------" + str);
        }
    }
}

