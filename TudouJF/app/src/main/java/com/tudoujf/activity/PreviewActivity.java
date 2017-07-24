package com.tudoujf.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.lzy.okserver.download.DownloadTask;
import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.service.MyIntentService;
import com.tudoujf.service.MyService;
import com.tudoujf.test.Book;
import com.tudoujf.utils.AESUtils;
import com.tudoujf.utils.AppInfoUtils;
import com.tudoujf.utils.L;
import com.tudoujf.utils.NetworkUtils;
import com.tudoujf.utils.ToastUtils;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.File;

import butterknife.BindView;
import okhttp3.OkHttpClient;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

/**
 * * ================================================
 * name:            PreviewActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：预览界面activity
 * history：
 * ===================================================
 */

public class PreviewActivity extends BaseActivity {
    @BindView(R.id.bt_act_preview)
    Button btActPreview;
    @BindView(R.id.bt_act_preview1)
    Button btActPreview1;
    @BindView(R.id.bt_act_preview2)
    Button btActPreview2;
    @BindView(R.id.bt_act_preview3)
    Button btActPreview3;
    @BindView(R.id.bt_act_preview4)
    Button btActPreview4;
    @BindView(R.id.bt_act_preview5)
    Button btActPreview5;
    @BindView(R.id.bt_act_preview6)
    Button btActPreview6;
    @BindView(R.id.bt_act_preview7)
    Button btActPreview7;
    @BindView(R.id.bt_act_preview8)
    Button btActPreview8;
    @BindView(R.id.bt_act_preview9)
    Button btActPreview9;


    private int oldX = 0, oldY = 0;
    private int curX, curY;
    //获取通信用binder
    private MyService.DownLoadBinder binder;
    /**绑定服务使用*/
    private ServiceConnection connection = new ServiceConnection() {
        /**绑定服务时调用*/
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.DownLoadBinder) service;
            binder.startDownload();
            binder.getProgress();
        }
        /**解绑服务时调用*/
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.act_preview;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_act_preview:
                openActivity(LoginActivity.class);
                break;
            case R.id.bt_act_preview1:
                openActivity(AffirmPasswordActivity.class);
                break;
            case R.id.bt_act_preview2:
                openActivity(FindPasswordActivity.class);
                break;
            case R.id.bt_act_preview3:
                openActivity(RegisterActivity.class);
                break;
            case R.id.bt_act_preview4:
                openActivity(LockActivity.class);
                break;
            case R.id.bt_act_preview5:
                openActivity(DrawGestureActivity.class);
                break;
            case R.id.bt_act_preview6:
                openActivity(ProductDetailsActivity.class);
                break;
            case R.id.bt_act_preview7:
                openActivity(AffirmBuyActivity.class);

//                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,null,FLAG_ONE_SHOT,null);
//                AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
//                long trig= SystemClock.elapsedRealtime()+10*1000;
//                manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,trig,pendingIntent);
                break;
            case R.id.bt_act_preview8:
                openActivity(FanXianQuanSelActivity.class);

//                /**绑定服务*/
//                Intent intent1 = new Intent(this, MyService.class);
//                bindService(intent1,connection,BIND_AUTO_CREATE);
//                /**启动服务*/
////                startService(intent1);
                break;
            case R.id.bt_act_preview9:
                openActivity(RedPackageActivity.class);
                //解绑服务,只能执行一次,多次报错
//                unbindService(connection);
//                Log.e("TAG", "onDestroy:1212 "+ Thread.currentThread().getName());
////                停止服务
//                Intent intent2 = new Intent(this, MyIntentService.class);
//                stopService(intent2);
                break;
        }
    }

    @Override
    public void initDataFromIntent() {
//        Log.e("initDataFromIntent","1231213"+ NetworkUtils.connectionNetwork());
//        Log.e("initDataFromIntent111",""+ NetworkUtils.isConnected(this));
//        Log.e("initDataFromIntent333",""+ NetworkUtils.getTelNetworkTypeINT(this));
//        Log.e("initDataFromIntent444", "" + NetworkUtils.isWifiAvailable(this));
//        Log.e("initDataFromIntent", NetworkUtils.getLocalIpAddress());
//        Log.e("initDataFromIntent", ""+NetworkUtils.getNetState(this));
//        Log.e("initDataFromIntent", ""+NetworkUtils.is2G(this));
//        Log.e("initDataFromIntent", ""+NetworkUtils.is3G(this));
//        Log.e("initDataFromIntent", ""+NetworkUtils.isNetworkAvailable(this));
//        Log.e("initDataFromIntent", ""+NetworkUtils.isWifi(this));
//        Log.e("initDataFromIntent", ""+NetworkUtils.isWifiEnabled(this));
//        Log.e("initDataFromIntent222", "数据网络"+NetworkUtils.isMobileNetEnabled(this));
        HttpMethods.getInstance().GET(this, "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });


    }

    @Override
    public void initView() {

    }

    public void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_popitem, null);
        final PopupWindow pop = new PopupWindow(view, 300, 300);
//        pop.setContentView(view);
        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点
        pop.showAtLocation(btActPreview7, Gravity.TOP, 0, 0);//显示位置
        int out[] = new int[2];
        btActPreview.getLocationInWindow(out);//获取view在Android坐标系中左上角的坐标点
        Log.e("TAG", "showPop: ----" + out[0]);
        Log.e("TAG", "showPop: -----" + out[1]);
        //pop的监听依靠承载的view来 实现
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = (int) event.getRawX();//获取Android坐标系坐标
                        oldY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = (int) (event.getRawX() - oldX);
                        curY = (int) (event.getRawY() - oldY);
                        pop.update(curX, curY, -1, -1, true);//更新pop的位置
                        break;
                    case MotionEvent.ACTION_UP:
                        pop.dismiss();//取消pop
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void initListener() {
        btActPreview.setOnClickListener(this);
        btActPreview1.setOnClickListener(this);
        btActPreview2.setOnClickListener(this);
        btActPreview3.setOnClickListener(this);
        btActPreview4.setOnClickListener(this);
        btActPreview5.setOnClickListener(this);
        btActPreview6.setOnClickListener(this);
        btActPreview7.setOnClickListener(this);
        btActPreview8.setOnClickListener(this);
        btActPreview9.setOnClickListener(this);
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


}
