package com.tudoujf.mynotebook.api;

import android.app.Application;

import org.litepal.LitePal;

/**
 * * ===============================================================
 * name:             MyApplication
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/24
 * description：
 * history：
 * *==================================================================
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
