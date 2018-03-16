package com.tudoujf.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.tudoujf.adapter.ProductDetailsActLvAdapter;

/**
 * * ===============================================================
 * name:             BitmapUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/9/14
 * description： 图像压缩工具类
 * history：
 * *==================================================================
 */

public class BitmapUtils {


    private BitmapUtils() {
    }

    /**
     * 压缩图片-->尺寸压缩-->将图片压缩到指定宽高,同时缩小bitmap的存储大小
     *
     * @param inBitmap  要压缩的bitmap
     * @param newWidth  新的宽度
     * @param newHeight 新的高度
     * @return 指定宽高的bitmap, 原bitmap已回收
     */
    public static Bitmap getReduceBitmap(Bitmap inBitmap, int newWidth, int newHeight) {
        Bitmap outBitmap;
        float width = inBitmap.getWidth();
        float height = inBitmap.getHeight();

        Matrix matrix = new Matrix();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        matrix.postScale(scaleWidth, scaleHeight);

        outBitmap = Bitmap.createBitmap(inBitmap, 0, 0, (int) width,
                (int) height, matrix, true);

        inBitmap.recycle();
        return outBitmap;

    }

    /**
     * 压缩图片-->质量压缩-->压缩图片的保存体积,图片本身的宽高不变
     *
     * 质量压缩适合将图片保存到本地,再次读取压缩后的图片时,bitmap的大小没有变化,所占内存与原图片相同
     *
     * @param inBitmap 需要压缩的图片
     * @return 压缩后的图片, 原图片已回收
     */

    public static Bitmap getCompressBitmap(Bitmap inBitmap) {
        Bitmap outBitmap;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        inBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中



        int options = 100*100 * 1024 / (baos.toByteArray().length);//将图片压缩到100kb的压缩比
        baos.reset();
        inBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

        outBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);


        inBitmap.recycle();
        return outBitmap;

    }

    /**
     * 从指定的图像路径获取位图
     *
     * @param imgPath 图片路径
     * @return bitmap
     */
    public static Bitmap getBitmap(String imgPath) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 1;
        newOpts.inPreferredConfig = Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    @SuppressLint("ObsoleteSdkInt")
    public static int getBitmapSize(Bitmap bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

}

