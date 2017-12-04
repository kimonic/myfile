package com.tudoujf.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tudoujf.utils.utils.ScreenUtil;

public class MainActivity extends AppCompatActivity {

    private EditText etTest;
    private LinearLayout  root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTest=findViewById(R.id.et_test);
        root=findViewById(R.id.root);

        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            Log.e("TAG", "onCreate:packageName-----" + pi.packageName);
            Log.e("TAG", "onCreate: versionCode-----" + pi.versionCode);
            Log.e("TAG", "onCreate:versionName -----" + pi.versionName);
            Log.e("TAG", "onCreate: baseRevisionCode-----" + pi.baseRevisionCode);
            Log.e("TAG", "onCreate: sharedUserId-----" + pi.sharedUserId);
            Log.e("TAG", "onCreate: sharedUserLabel-----" + pi.sharedUserLabel);
            Log.e("TAG", "onCreate: firstInstallTime-----" + pi.firstInstallTime);
            Log.e("TAG", "onCreate: lastUpdateTime-----" + pi.lastUpdateTime);
            Log.e("TAG", "onCreate: overlayTarget-----" + pi.toString());
            Log.e("TAG", "onCreate: -----" + pi.packageName);
            Log.e("TAG", "onCreate: -----" + pi.packageName);
            Log.e("TAG", "onCreate: -----" + pi.packageName);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initView();

    }

    private void initView() {
        ScreenUtil screenUtil=new ScreenUtil(this);
        Log.e("TAG", "onOpen: ---输入法已打开--"+screenUtil.getAccurateScreenDpi()[0]);
        Log.e("TAG", "onOpen: ---输入法已打开--"+screenUtil.getAccurateScreenDpi()[1]);


        screenUtil.observeInputlayout(root, new ScreenUtil.OnInputActionListener() {
            @Override
            public void onOpen(int  inputHeight) {
                Log.e("TAG", "onOpen: ---输入法已打开--");
            }

            @Override
            public void onClose() {
                Log.e("TAG", "onOpen: ---输入法已关闭--");
            }
        });
    }
}
