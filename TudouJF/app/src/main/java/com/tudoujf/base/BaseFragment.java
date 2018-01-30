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
import com.tudoujf.utils.DialogUtils;

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

    private boolean isProgressing = false;

    private MyHandler handler;

    private class MyHandler extends Handler {
        // 弱引用 ，防止内存泄露
        private WeakReference<FragmentActivity> weakReference;

        public MyHandler(FragmentActivity handlerMemoryActivity) {
            weakReference = new WeakReference<FragmentActivity>(handlerMemoryActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentActivity handlerMemoryActivity = weakReference.get();
            if (handlerMemoryActivity != null && isProgressing && msg.what == 1) {
                OkGo.getInstance().cancelAll();
//                ToastUtils.showToast(getActivity(), R.string.shujujiazaichaoshi);
                dismissPDialog();
            } else {
                dismissPDialog();
            }
        }
    }


    //------------------------------------------联网请求计时--------------------------------------------------------


    public void showPDialog() {
        isProgressing = true;
        timeThread();
        try {
            if (bDialog == null) {
                bDialog = DialogUtils.showProgreessDialog(getActivity(), getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
            } else {
                bDialog.show();
            }
        } catch (Exception e) {
            Log.e("TAG", "showPDialog: -----窗体泄露");
        }

    }

    public void dismissPDialog() {
        isProgressing = false;
        try {
            if (bDialog != null && bDialog.isShowing()) {
                bDialog.dismiss();
            }
        } catch (Exception e) {
            Log.e("TAG", "showPDialog: -----窗体泄露");
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
        handler = new MyHandler(getActivity());
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


    @Override
    public void onDestroyView() {
        OkGo.cancelAll(OkGo.getInstance().getOkHttpClient());
        unbinder.unbind();
        super.onDestroyView();
    }

    private void timeThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = Message.obtain();
                msg.what = 1;
                if (handler != null) {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }
}
