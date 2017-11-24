package com.tudoujf.mynotebook.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.tudoujf.mynotebook.R;
import com.tudoujf.mynotebook.utils.DialogUtils;

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
 *
 */

public abstract class BaseFragment extends Fragment implements BaseMethod, View.OnClickListener {
    Unbinder unbinder;

    private AlertDialog bDialog;

    public void showPDialog() {
        if (bDialog==null){
            bDialog= DialogUtils.showProgreessDialog(getActivity(),getResources().getString(R.string.zaicidianjijinagtuichugaiyemian));
        }else {
            bDialog.show();
        }
    }

    public void dismissPDialog(){
        if (bDialog!=null){
            bDialog.dismiss();
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
        return view;
    }
    /**设置fragment的布局资源id*/
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
     * 关闭activity
     */
    protected void closeActivity() {
        getActivity().finish();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.cancelAll(OkGo.getInstance().getOkHttpClient());
        unbinder.unbind();
    }
}
