package com.tudoujf.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.tudoujf.R;
import com.tudoujf.activity.home.HomeActivity;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.LUtils;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * * ================================================
 * name:            BaseActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/10
 * description：     fragment基类
 * history：
 * ===================================================
 */

public abstract class BaseFragment extends Fragment implements BaseMethod, View.OnClickListener {
    Unbinder unbinder;

    private AlertDialog bDialog;


    //-----------------------------------------联网请求计时---------------------------------------------------------

    // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
//    private boolean isProgressing = false;
//
//    private MyHandler handler;
//
//    private class MyHandler extends Handler {
//        // 弱引用 ，防止内存泄露
//        private WeakReference<FragmentActivity> weakReference;
//
//        public MyHandler(FragmentActivity handlerMemoryActivity) {
//            weakReference = new WeakReference<FragmentActivity>(handlerMemoryActivity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            FragmentActivity handlerMemoryActivity = weakReference.get();
//            if (handlerMemoryActivity != null && isProgressing && msg.what == 1) {
//                OkGo.getInstance().cancelAll();
////                ToastUtils.showToast(getActivity(), R.string.shujujiazaichaoshi);
//                dismissPDialog();
//            } else {
//                dismissPDialog();
//            }
//        }
//    }

// ---------------------------20180411--去掉线程控制取消dialog--------------------------------------

    //------------------------------------------联网请求计时--------------------------------------------------------


    public void showPDialog() {
        // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
//        isProgressing = true;
//        timeThread();
        // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
        try {
            if (bDialog == null) {
                bDialog = DialogUtils.showProgreessDialog(getActivity(), getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
            } else {
                bDialog.show();
            }
        } catch (Exception e) {
        }

    }

    public void dismissPDialog() {
        // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
//        isProgressing = false;
        // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
        try {
            // TODO: 2018/2/26 窗体泄露异常修正
            if (getActivity() != null && !(getActivity().isFinishing() || getActivity().isDestroyed())) {
                if (bDialog != null && bDialog.isShowing()) {
                    bDialog.dismiss();
                }
            }
        } catch (Exception e) {
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initDataFromIntent();
        initView();
        initListener();
// ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
//        handler = new MyHandler(getActivity());
// ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
        return view;
    }

    /**
     * 设置fragment的布局资源id
     */
    public abstract int layoutRes();


    /**
     * 启动下一个activity
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    /**
     * 启动下一个activity
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(getActivity(), toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    /**
     * 启动下一个activity
     */
    protected void openActivityForResult(Class<? extends BaseActivity> toActivity, int requestCode) {
        Intent intent = new Intent(getActivity(), toActivity);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭activity
     */
    protected void closeActivity() {
        getActivity().finish();
    }


    /**
     * 销毁方法可能在返回上一页执行一段时间之后才会被调用
     * 影响全局的方法不宜在其内调用
     */
    @Override
    public void onDestroyView() {
        unbinder.unbind();
        // TODO: 2018/2/2   dialog异常尝试处理,未确认
        dismissPDialog();
        bDialog = null;
        super.onDestroyView();

//        OkGo.cancelAll(OkGo.getInstance().getOkHttpClient());
    }

    // ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
//    private void timeThread() {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(15000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Message msg = Message.obtain();
//                msg.what = 1;
//                if (handler != null) {
//                    handler.sendMessage(msg);
//                }
//            }
//        }.start();
//    }
// ---------------------------20180411--去掉线程控制取消dialog--------------------------------------
}
