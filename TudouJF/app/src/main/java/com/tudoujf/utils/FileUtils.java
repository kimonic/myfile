package com.tudoujf.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * * ==================================================
 * name:            FileUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/19
 * description：   文件存储工具类
 * history：
 *
 *
 * -----------existSDCard()-----判断是否存在外部SD卡
 * -----------getFilepath(Context context)----获取文件的保存路径--优先外部SD卡缓存目录
 * -----------saveJsonToSDCard(Context context, String fileName, String content)---
 * 保存传递的字符串到本地--存储到外部SD卡缓存目录(优先)或内部缓存目录
 * -----------isExists(String strPath)------判断文件是否存在
 * -----------readFileContent(Context context, String fileName)---读取文件
 * -----------
 * -----------
 * * ==================================================
 *
 */

public class FileUtils {
    /**
     * 判断是否存在外部SD卡
     *
     * @return 存在  ture ,不存在  false
     */
    private static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取文件的保存路径--优先外部SD卡缓存目录
     */
    private static String getFilepath(Context context) {
        String filePath;
        if (existSDCard()) {
            filePath = context.getExternalCacheDir().getPath() + "/";
        } else {
            filePath = context.getCacheDir().getPath() + "/";
        }
        return filePath;
    }

    /**
     * 保存传递的字符串到本地--存储到外部SD卡缓存目录(优先)或内部缓存目录
     *
     * @param context  上下文
     * @param fileName 文件存储名称
     * @param content  存储内容
     */
    public static void saveJsonToSDCard(Context context, String fileName, String content) {


        File file = new File(getFilepath(context) + fileName);
        Log.e("TAG", "saveJsonToSDCard: "+(getFilepath(context) + fileName ));

        FileOutputStream os = null;
        try {
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            os.write(content.getBytes(), 0, content.getBytes().length);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }

    /**
     * 判断文件是否存在
     *
     * @param strPath 文件路径
     * @return 存在  ture,不存在  false
     */
    public static boolean isExists(String strPath) {
        if (strPath == null) {
            return false;
        }

        final File strFile = new File(strPath);

        return strFile.exists();

    }

    /**
     * 读取文件
     * @param context  上下文
     * @param fileName  文件名称
     * @return   内容字符串
     */
    public static String readFileContent(Context context, String fileName) {
        File file = new File(getFilepath(context) + fileName);
        if (file.exists()) {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, "UTF-8");

                char[] input = new char[fis.available()];
                isr.read(input);
                String in = new String(input);
                return in.trim();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (isr != null) {
                        isr.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "";

    }


    /**保存图片到本地*/
    public static String saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");

        Log.e("TAG", "saveImageToGallery: --图片存储路径---"+appDir.getAbsolutePath());

        if (!appDir.exists()) {
            appDir.mkdir();
        }
//        String fileName = System.currentTimeMillis() + ".jpg";
        String fileName =  "icon.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return appDir.getAbsolutePath()+"/icon.jpg";
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }

    public static void fileDir(Context context) {
        Log.e("TAG", "fileDir:1---------------" + context.getExternalCacheDir());//应用内缓存目录
        Log.e("TAG", "fileDir:5---------------" + context.getExternalCacheDir().getPath());
        Log.e("TAG", "fileDir: 2---------------" + context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));//SD卡内应用缓存目录
        Log.e("TAG", "fileDir: -3--------------" + context.getFilesDir());//SD卡内应用缓存目录
        Log.e("TAG", "fileDir: ---4------------" + context.getCacheDir());//应用内缓存目录
        Log.e("TAG", "fileDir: ---6------------" + context.getCacheDir().getPath());
        //以上目录都会在卸载时清空数据
    }

}
