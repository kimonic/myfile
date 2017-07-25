package com.tudoujf.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tudoujf.R;


/**
 * * ================================================
 * name:            DialogUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/25
 * description： dialog辅助工具类
 * history：
 * ===================================================
 */

public class DialogUtils {

    /**
     * 含有取消按钮  传输资源id
     * @param activity    contrext
     * @param message    消息
     * @param positiveText   确定按钮文本
     * @param listener   确定按钮点击事件
     */
    public static void showDialog(final Activity activity,int message,int positiveText,DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText,listener)
                .setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 含取消按钮  传输文本内容
     * @param activity  context
     * @param message   消息
     * @param positiveText  确定按钮文本
     * @param listener   确定按钮点击事件
     */
    public static void showDialog(final Activity activity,String message,int positiveText,DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText,listener)
                .setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 不含取消按钮   传输资源id
     * @param activity   context
     * @param message   消息
     * @param positiveText   确定按钮文本
     * @param title    标题
     * @param listener   确定按钮点击事件
     */
    public static void showDialog(final Activity activity,int message,int positiveText,int  title,DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText,listener)
                .show();
    }

    /**
     * 含标题,取消按钮  传输资源id
     * @param activity  context
     * @param message  消息
     * @param positiveText   确定文本
     * @param title  标题
     * @param listener  确定按钮点击事件
     * @param flag   多态标识
     */
    public static void showDialog(final Activity activity,int message,int positiveText,int  title,DialogInterface.OnClickListener listener,int flag){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText,listener)
                .setNegativeButton(R.string.cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 不含取消按钮,不含标题  传输资源id
     * @param activity  context
     * @param message  消息
     * @param positiveText  确定按钮文本
     * @param listener   确定按钮点击事件
     */
    public static void showDialogNoTitle(final Activity activity,int message,int positiveText,DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText,listener)
                .show();
    }
}
