package com.tudoujf.utils;

import com.example.encryptionpackages.CreateCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tudoujf.bean.GlobalBean;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 */

public class StringUtils {
    private static final Gson gson = new Gson();

    /**
     * 返回未解密的json对象
     */
    private static GlobalBean firstParseJson(String json) {
        return gson.fromJson(json, new TypeToken<GlobalBean>() {
        }.getType());
    }

    /**
     * @param json 第一次返回的json加密字符串
     * @return MD5验证成功后返回解密后的json字符串, MD5验证失败返回null
     */
    public static String getDecodeString(String json) {
        String temp;
        GlobalBean bean = firstParseJson(json);
        //MD5验证
        if (MD5Utils.md5(CreateCode.getRECEIVE_SiGN_KEY() + bean.getDiyou() + CreateCode.getRECEIVE_SiGN_KEY()).equals(bean.getXmdy())) {
            temp = CreateCode.s2pDiyou(bean.getDiyou());
            return temp;
        } else {
            return null;
        }
    }

    /**
     * 将请求参数进行加密再组装
     */
    public static Map<String, String> getRequestParams(TreeMap<String, String> map) {
        Map<String, String> params = new HashMap<>(2);
        params.put("diyou", CreateCode.p2sDiyou(CreateCode.GetJson(map)));
        params.put("xmdy", CreateCode.p2sXmdy(CreateCode.p2sDiyou(CreateCode.GetJson(map))));
        return params;
    }

    /**
     * 验证是否是手机号码
     *
     * @param str 手机号字符串
     * @return 是否是手机号格式
     */
    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        return !str.equals("") && matcher.matches();
    }

    /**
     * 获取六位随机验证码
     */
    public static String getRandomCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append((int) (Math.random() * 10));
        }
        return builder.toString();
    }


    /**
     * 密码规则：必须是6-16位大小写字母及数字的组合
     * 是否包含
     *
     * @param str   检验的字符串
     * @return   符合--true,不符合--false
     */
    public static boolean conformPasswordRule(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        return isDigit && isLetter && str.matches(regex);
    }

}
