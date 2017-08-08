package com.tudoujf.utils;

import com.example.encryptionpackages.CreateCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tudoujf.bean.GlobalBean;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * * ==================================================
 * name:            StringUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/8
 * description：   字符串辅助工具类
 * history：
 * * ==================================================
 *
 */

public class StringUtils {
    private static final Gson gson=new Gson();

    /**返回未解密的json对象*/
    private static GlobalBean firstParseJson(String  json){
        return gson.fromJson(json,new TypeToken<GlobalBean>(){}.getType());
    }

    /**
     *
     * @param json   第一次返回的json加密字符串
     * @return   MD5验证成功后返回解密后的json字符串,MD5验证失败返回null
     */
    public static String getDecodeString(String  json){
        String temp;
        GlobalBean bean=firstParseJson(json);
        //MD5验证
        if (MD5Utils.md5(CreateCode.getRECEIVE_SiGN_KEY() +bean.getDiyou()+CreateCode.getRECEIVE_SiGN_KEY() ).equals(bean.getXmdy())){
            temp= CreateCode.s2pDiyou(bean.getDiyou());
            return temp;
        }else {
            return null;
        }
    }
    /**将请求参数进行加密再组装*/
    public static Map<String,String> getRequestParams(TreeMap<String, String> map) {
        Map<String,String> params = new HashMap<>(2);
        params.put("diyou", CreateCode.p2sDiyou(CreateCode.GetJson(map)));
        params.put("xmdy", CreateCode.p2sXmdy(CreateCode.p2sDiyou(CreateCode.GetJson(map))));
        return params;
    }
}
