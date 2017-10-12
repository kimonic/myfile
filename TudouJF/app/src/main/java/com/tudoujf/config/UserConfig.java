package com.tudoujf.config;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.encryptionpackages.AESencrypt;
import com.example.encryptionpackages.CreateCode;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.utils.SharedPreferencesUtils;
import com.tudoujf.utils.StringUtils;

/**
 * * ==================================================
 * name:            UserConfig
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/21
 * description：   用户登陆相关信息
 * history：
 * * ==================================================
 */
public class UserConfig {

    private static UserConfig mUserConfig;
    private String userId;
    private String userKey;
    private String userName;
    private String isLogin;
    private String loginToken;

    private boolean  creditorFlush=false;

    public boolean isCreditorFlush() {
        return creditorFlush;
    }

    public void setCreditorFlush(boolean creditorFlush) {
        this.creditorFlush = creditorFlush;
    }

    /**
     *
     */
    private UserConfig() {

    }

    /**
     * userkey
     *
     * @return
     */
    public synchronized String getUserKey(Context context) {
        if (null == userKey) {
            try {
                userKey = AESencrypt.decrypt2PHP(CreateCode.getSEND_AES_KEY(),
                        SharedPreferencesUtils.getInstance(context,Constants.USER_CONFIG)
                        .getString(Constants.SHARE_USERKEY, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * isLogin
     *
     * @return    "
     */
    public synchronized String getLogin(Context context) {
        if (null == isLogin)//未登录时为null
        {
            try {//AES解密?????
                isLogin = AESencrypt.decrypt2PHP(CreateCode.getSEND_AES_KEY(),
                        SharedPreferencesUtils.getInstance(context,Constants.USER_CONFIG)
                                .getString(Constants.SHARE_ISLOGIN, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isLogin;
    }

    public synchronized boolean isLogin(Context context) {
        if (StringUtils.isEmpty2(getLogin(context))) {
            return false;
        } else {
            return getLogin(context).equals("isLogin");
        }
    }

    /**
     * userid
     *
     * @return
     */
    public synchronized String getUserId(Context context) {
        if (null == userId) {
            try {
                userId = AESencrypt.decrypt2PHP(CreateCode.getSEND_AES_KEY(),
                        SharedPreferencesUtils.getInstance(context,Constants.USER_CONFIG)
                                .getString(Constants.SHARE_USERID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * loginToken
     *
     * @return
     */
    public synchronized String getLoginToken(Context context) {
        if (null == loginToken) {
            try {
                loginToken = AESencrypt.decrypt2PHP(
                        CreateCode.getSEND_AES_KEY(),
                        SharedPreferencesUtils.getInstance(context,Constants.USER_CONFIG)
                        .getString(Constants.SHARE_LOGINTOKEN, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (loginToken==null){//此处进行未登陆处理
            return "";
        }
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }


    /**
     * username
     *
     * @return  username0
     */
    public synchronized String getUserName(Context context) {
        if (null == userName) {
            try {
                userName = AESencrypt.decrypt2PHP(CreateCode.getSEND_AES_KEY(),
                        SharedPreferencesUtils.getInstance(context,Constants.USER_CONFIG)
                                .getString(Constants.SHARE_USERNAME,""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userName;
    }

    /**设置用户名*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return   UserConfig
     */
    public synchronized static UserConfig getInstance() {
        if (null == mUserConfig) {
            mUserConfig = new UserConfig();
        }
        return mUserConfig;
    }

    /**
     * 清除用户信息
     */
    public synchronized void clear() {
        this.userKey = null;
        this.userId = null;
        this.userName = null;
        this.isLogin = null;
    }
}
