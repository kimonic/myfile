package com.tudoujf.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tudoujf.R;
import com.tudoujf.activity.PreviewActivity;
/**服务*/
public class MyService extends Service {
    private String TAG = "myservice";
    private DownLoadBinder binder=new DownLoadBinder();
    /**通信用的自定义binder*/
   public   class DownLoadBinder extends Binder{
            public void startDownload(){
                Log.e(TAG, "startDownload: " );

            }
            public int getProgress(){
                Log.e(TAG, "getProgress: " );
                return  0;
            }
    }

    public MyService() {
    }
    /**初次启动服务时执行一次*/
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: " );
        super.onCreate();
        //执行前台服务
        Intent intent=new Intent(this,PreviewActivity.class);
        PendingIntent pintent=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("123456789")
                .setContentText("746545456")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.act_home_icon1)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pintent)
                .build();
        startForeground(1,notification);

    }
    /**每start一次执行一次*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: " );
        return super.onStartCommand(intent, flags, startId);
    }
    /**销毁服务*/
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }
    /**返回binder,该方法用于与服务绑定的活动进行通信*/
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );

        return binder;
    }
}
