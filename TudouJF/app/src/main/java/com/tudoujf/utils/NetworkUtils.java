package com.tudoujf.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.http.HttpMethods;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
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
 * ===================================================
 */
public class NetworkUtils {
    /**
     * 网络连接可用
     */
    private static int NET_CNNT_BAIDU_OK = 1; // NetworkAvailable
    /**
     * 没有可用网络连接
     */
    private static int NET_CNNT_BAIDU_TIMEOUT = 2;
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
                if ((networkinfo != null)&&(networkinfo.isAvailable()) &&(networkinfo.isConnected())){
                    return  NET_PREPARE;
                }else {
                    return NET_NOT_PREPARE;//网络未准备好
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NET_ERROR;//网络未知错误
    }
    /**判断网络是否有网速*/
    public static void getNetCanConnectBaiDu(Context context,Callback callback){
        HttpMethods.getInstance().GET(context, "http://www.baidu.com", null, "123", callback);
    }



    /**
     * ping "http://www.baidu.com"
     *
     * @return 连接百度是否成功
     */
    private static boolean connectionNetwork() {
        boolean result = false;
        HttpURLConnection httpUrl = null;
        try {
            httpUrl = (HttpURLConnection) new URL("http://www.baidu.com")
                    .openConnection();
            httpUrl.setConnectTimeout(TIMEOUT);
            httpUrl.connect();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpUrl) {
                httpUrl.disconnect();
            }
            httpUrl = null;
        }
        return result;
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
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null
                && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)
                || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

}
