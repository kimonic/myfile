package com.tudoujf.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.lzy.okgo.callback.Callback;
import com.tudoujf.http.HttpMethods;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * * ================================================
 * name:            NetworkUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：  网络状态相关工具类
 * history：
 *
 * -----------getConnectivityManager(Context context)---获取ConnectivityManager
 * -----------isNetworkAvailable(Context context)---检测网络状态是否可用
 * -----------getLocalIpAddress()----获取本地IP地址
 * -----------getNetState(Context context)----返回当前网络状态
 * -----------getNetCanConnectBaiDu(Context context, Callback callback)---判断连接百度是否成功
 * -----------is3G(Context context)---检测是否为3g网络
 * -----------isWifi(Context context)----检测网络是否为WiFi
 * -----------is2G(Context context)-----检测网络是否为2g网络
 * -----------isWifiEnabled(Context context)------检测WiFi网络是否可用
 * -----------isMobileNetEnabled(Context context)----检测手机数据网络是否可用
 * -----------isConnected(Context context) -----判断网络连接是否有效（此时可传输数据）
 * -----------isConnectedOrConnecting(Context context)----判断有无网络正在连接中（查找网络、校验、获取IP等）
 * -----------getConnectedType(Context context)----获取当前网络连接的类型
 * -----------isWifiConnected(Context context)----是否存在有效的WIFI连接
 * -----------isMobileConnected(Context context)-----是否存在有效的移动连接
 * -----------isAvailable(Context context)----检测网络是否为可用状态
 * -----------isWifiAvailable(Context context)----判断是否有可用状态的Wifi，以下情况返回false
 * -----------isMobileAvailable(Context context)---判断有无可用状态的移动网络
 * -----------isMobileEnabled(Context context)---设备是否打开移动网络开关
 * -----------getTelNetworkTypeINT(Context context)---获取网络类型
 * -----------
 *
 * ===================================================
 */
public class NetworkUtils {
    /**
     * 网络连接已准备好,不确定是否能连接到互联网
     */
    private static int NET_PREPARE = 3; // Net no ready
    /**
     * 网络连接未准备好
     */
    private static int NET_NOT_PREPARE = 4; // Net no ready
    /**
     * 网络连接错误
     */
    private static int NET_ERROR = 5; //net error
    /**
     * 连接超时
     */
    private static int TIMEOUT = 3000; // TIMEOUT

    /**
     * 获取ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    /**
     * 检测网络状态是否可用
     *
     * @param context 上下文
     * @return 可用---true,不可用--false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager)
            return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        return !(null == info || !info.isAvailable());
    }

    /**
     * 获取本地IP地址
     *
     * @return 本地IP地址
     */
    public static String getLocalIpAddress() {
        String ret = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {

                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ret = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 返回当前网络状态
     *
     * @param context 上下文
     * @return 网路状态
     */
    public static int getNetState(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo networkinfo = connectivity.getActiveNetworkInfo();
//                        if (!connectionNetwork())//进图子线程去了,有延时,继续执行下面的代码,所以总是返回neterror
//                            return NET_CNNT_BAIDU_TIMEOUT;//连接百度超时
//                        else
//                            return NET_CNNT_BAIDU_OK;//连接百度成功,网络可用
                if ((networkinfo != null) && (networkinfo.isAvailable()) && (networkinfo.isConnected())) {
                    return NET_PREPARE;
                } else {
                    return NET_NOT_PREPARE;//网络未准备好
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NET_ERROR;//网络未知错误
    }

    /**
     * 判断网络是否有网速
     */
    public static void getNetCanConnectBaiDu(Context context, Callback callback) {
        HttpMethods.getInstance().GET(context, "http://www.baidu.com",  callback);
    }



    /**
     * 检测是否为3g网络
     *
     * @param context 上下文
     * @return 是否
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetInfo != null) && (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * 检测网络是否为WiFi
     *
     * @param context 上下文
     * @return 是否
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetInfo != null) && (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 检测网络是否为2g网络
     *
     * @param context 上下文
     * @return 是否
     */
    public static boolean is2G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && (activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                || activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                .getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA);
    }

    /**
     * 检测WiFi网络是否可用
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return ((mgrConn.getActiveNetworkInfo() != null
                && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED));
    }

    /**
     * 检测手机数据网络是否可用
     */
    public static boolean isMobileNetEnabled(Context context) {
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        return mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS;
    }

    /**
     * 判断网络连接是否有效（此时可传输数据）。
     *
     * @return boolean 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     */
    public static boolean isConnected(Context context) {
        NetworkInfo net = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }


    /**
     * 判断有无网络正在连接中（查找网络、校验、获取IP等）。
     *
     * @return boolean 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     * <p>
     */
    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }
    /**获取当前网络连接的类型*/
    public static String getConnectedType(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        if (net != null) {
            switch (net.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return "WIFI";
                case ConnectivityManager.TYPE_MOBILE:
                    return "MOBILE";
                default:
                    return "OTHER";
            }
        }
        return "NONE";
    }

    /**
     * 是否存在有效的WIFI连接
     */
    public static boolean isWifiConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == ConnectivityManager.TYPE_WIFI && net.isConnected();
    }

    /**
     * 是否存在有效的移动连接
     *
     * @return boolean
     */
    public static boolean isMobileConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == ConnectivityManager.TYPE_MOBILE && net.isConnected();
    }

    /**
     * 检测网络是否为可用状态
     */
    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || (isMobileAvailable(context) && isMobileEnabled(context));
    }

    /**
     * 判断是否有可用状态的Wifi，以下情况返回false：
     * 1. 设备wifi开关关掉;
     * 2. 已经打开飞行模式；
     * 3. 设备所在区域没有信号覆盖；
     * 4. 设备在漫游区域，且关闭了网络漫游。
     *
     * @return boolean wifi为可用状态（不一定成功连接，即Connected）即返回ture
     */
    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_WIFI) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 判断有无可用状态的移动网络，注意关掉设备移动网络直接不影响此函数。
     * 也就是即使关掉移动网络，那么移动网络也可能是可用的(彩信等服务)，即返回true。
     * 以下情况它是不可用的，将返回false：
     * 1. 设备打开飞行模式；
     * 2. 设备所在区域没有信号覆盖；
     * 3. 设备在漫游区域，且关闭了网络漫游。
     *
     * @return boolean
     */
    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 设备是否打开移动网络开关
     *
     * @return boolean 打开移动网络返回true，反之false
     */
    public static boolean isMobileEnabled(Context context) {
        try {
            Method getMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            getMobileDataEnabledMethod.setAccessible(true);
            return (Boolean) getMobileDataEnabledMethod.invoke(getConnectivityManager(context));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 反射失败，默认开启
        return false;
    }

/**
 * 获取网络类型 {@link TelephonyManager}
 * 2G, 3G, 4G, 等.
 * @return {@link TelephonyManager#NETWORK_TYPE_CDMA}, {@link TelephonyManager#NETWORK_TYPE_GPRS},
 * {@link TelephonyManager#NETWORK_TYPE_LTE},{@link TelephonyManager#NETWORK_TYPE_UMTS}...
 */
 public static int getTelNetworkTypeINT(Context context) {
     TelephonyManager mgrTel = (TelephonyManager) context
             .getSystemService(Context.TELEPHONY_SERVICE);
 return mgrTel.getNetworkType();
 }

}
