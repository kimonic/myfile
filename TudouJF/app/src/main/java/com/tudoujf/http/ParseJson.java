package com.tudoujf.http;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.utils.StringUtils;

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

    public static final int BEAN_NULL=0;
    public static final int BEAN_COMMON=1;
    public static final int BEAN_DATA=2;
    public static final int BEAN_ERROR=-1;

    /**解析json数据返回bean*/
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

                    return gson.fromJson(decodeJson, new TypeToken<CommonBean>() {}.getType());

                }


            } catch (JSONException e) {
                e.printStackTrace();
                return null;//异常返回null
            }


        }


        return null;//json为空或""返回null


    }
    /**判断bean的类型*/
    public static int getBeanType(BaseBean bean,Class clz){

        if (bean==null){
            return  BEAN_NULL;//0
        }else if (bean instanceof CommonBean){
            return BEAN_COMMON;//1
        }else if (bean.getClass()==clz ){
            return BEAN_DATA;//2
        }
        return BEAN_ERROR;//-1
    }


}
