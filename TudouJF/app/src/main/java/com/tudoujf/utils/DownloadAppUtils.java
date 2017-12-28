package com.tudoujf.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tudoujf.R;


/**
 * * ================================================
 * name:            DownloadAppUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/25
 * description：app更新下载辅助工具类
 * history：
 * ===================================================
 */
public class DownloadAppUtils {
    private static final String TAG = DownloadAppUtils.class.getSimpleName();
    public static long downloadUpdateApkId = -1;//下载更新Apk 下载任务对应的Id
    public static String downloadUpdateApkFilePath;//下载更新Apk 文件路径


    /**
     * 通过浏览器下载APK包
     * @param context
     * @param url
     */
    public static void downloadForWebView(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            ToastUtils.showToast(context, R.string.xiazailianjiechucuo);
        }
    }



//    public static void download(final Context context, String url,final String serverVersionName) {
//
//        String packageName = context.getPackageName();
//        String filePath = null;
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//外部存储卡
//            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        } else {
//            Log.i(TAG, "没有SD卡");
//            return;
//        }
//
//        String apkLocalPath= filePath + File.separator + packageName + "_"+serverVersionName+".apk";
//
//        downloadUpdateApkFilePath = apkLocalPath;
//
//        FileDownloader.setup(context);
//
//        FileDownloader.getImpl().create(url)
//                .setPath(apkLocalPath)
//                .setListener(new FileDownloadLargeFileListener() {
//                    @Override
//                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                    }
//
//                    @Override
//                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                        send(context, (int) (soFarBytes*100.0/totalBytes),serverVersionName);
//                    }
//
//                    @Override
//                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
//                    }
//
//                    @Override
//                    protected void completed(BaseDownloadTask task) {
//                        send(context,100,serverVersionName);
//                    }
//
//                    @Override
//                    protected void error(BaseDownloadTask task, Throwable e) {
//                        Toast.makeText(context, "下载出错", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    protected void warn(BaseDownloadTask task) {
//                    }
//                }).start();
//    }




//    private static void send(Context context,int progress,String serverVersionName) {
//        Intent intent = new Intent("teprinciple.update");
//        intent.putExtra("progress",progress);
//        intent.putExtra("title",serverVersionName);
//        context.sendBroadcast(intent);
//    }


}
