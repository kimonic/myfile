package com.tudoujf.http;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tudoujf.R;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * * ================================================
 * name:            ParseJson
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/9
 * description：   json数据解析工具类
 * history：
 * ===================================================
 */

public class ParseJson {

    private static final int BEAN_NULL = 0;
    private static final int BEAN_COMMON = 1;
    private static final int BEAN_DATA = 2;
    private static final int BEAN_ERROR = -1;

    /**
     * 解析json数据返回databean或commonbean
     *
     * @param response 需要解析的json字符串
     * @param type     databean的类型
     * @return 200--返回databean,100--返回commonbean
     */
    public static BaseBean parse(String response, Type type) {

        String decodeJson = StringUtils.getDecodeString(response);//解密json


        if (decodeJson != null && !decodeJson.equals("")) {
            String code = "";
            JSONObject jsonobject = null;


            try {


                jsonobject = new JSONObject(decodeJson);
                code = jsonobject.getString("code");


                Gson gson = new Gson();

                if (code.equals("200")) {//返回databean

                    return gson.fromJson(jsonobject.getString("data"), type);

                } else if (code.equals("100")) {//返回commonbean

                    return gson.fromJson(decodeJson, new TypeToken<CommonBean>() {
                    }.getType());

                }


            } catch (JSONException e) {
                e.printStackTrace();
                return null;//异常返回null
            }


        }


        return null;//json为空或""返回null


    }

    /**
     * 判断bean的类型
     */
    public static int getBeanType(BaseBean bean, Class clz) {

        if (bean == null) {
            return BEAN_NULL;//0
        } else if (bean instanceof CommonBean) {
            return BEAN_COMMON;//1
        } else if (bean.getClass() == clz) {
            return BEAN_DATA;//2
        }
        return BEAN_ERROR;//-1
    }

    /**
     *
     * @param response   需要解析的json字符串
     * @param type    json数据解析的类型
     * @param clz     databean的class
     * @param context   上下文
     * @return     返回null或databean
     */
    public static BaseBean getJsonResult(String response, Type type, Class clz, Context context) {
        BaseBean bean = parse(response, type);
        int result = getBeanType(bean, clz);
        switch (result) {
            case BEAN_NULL:
                ToastUtils.showToast(context, "解析错误!");
                return null;
            case BEAN_COMMON:
                ToastUtils.showToast(context, (((CommonBean) bean).getDescription().toString()));
                return null;
            case BEAN_DATA:
                return bean;
            case BEAN_ERROR:
                ToastUtils.showToast(context, "参数错误!");
                return null;
        }
        return null;
    }


}
