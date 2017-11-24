package com.tudoujf.mynotebook.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.Iterator;

public class Utils
{

    /**
     * 打开拨号盘
     * @param phoneNum
     * @param activity
     */
    public static void openDial(String phoneNum, Activity activity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);// 跳到拨号界面
        intent.setData(Uri.parse("tel:" + phoneNum));
        activity.startActivity(intent);
    }

    public static String handleJson(JSONObject jo) {

        String result = "";
        Iterator<String> keys = jo.keys();
        while (keys.hasNext()) {
            String key = keys.next();
//                Log.e("TextRecordActivity", key);
            if ("submit_url".equals(key)) {
                continue;
            }
            result += "&" + key + "=" + jo.optString(key);
        }
        result=result.replaceFirst("&","");
        return result;
    }

    public static void setEditTextEnable(EditText editText, boolean isEnable){
        //键盘获取焦点
        editText.setFocusable(isEnable);
        //设置是否可用
        editText.setEnabled(isEnable);
        //触摸获取焦点
        editText.setFocusableInTouchMode(isEnable);

        if(isEnable){
            //请求焦点
            editText.requestFocus();

        }

    }
}
