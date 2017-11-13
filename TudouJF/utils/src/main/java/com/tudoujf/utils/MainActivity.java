package com.tudoujf.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(),0);
            Log.e("TAG", "onCreate:packageName-----"+pi.packageName);
            Log.e("TAG", "onCreate: versionCode-----"+pi.versionCode);
            Log.e("TAG", "onCreate:versionName -----"+pi.versionName);
            Log.e("TAG", "onCreate: baseRevisionCode-----"+pi.baseRevisionCode);
            Log.e("TAG", "onCreate: sharedUserId-----"+pi.sharedUserId);
            Log.e("TAG", "onCreate: sharedUserLabel-----"+pi.sharedUserLabel);
            Log.e("TAG", "onCreate: firstInstallTime-----"+pi.firstInstallTime);
            Log.e("TAG", "onCreate: lastUpdateTime-----"+pi.lastUpdateTime);
            Log.e("TAG", "onCreate: overlayTarget-----"+pi.toString());
            Log.e("TAG", "onCreate: -----"+pi.packageName);
            Log.e("TAG", "onCreate: -----"+pi.packageName);
            Log.e("TAG", "onCreate: -----"+pi.packageName);




        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
