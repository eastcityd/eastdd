package com.example.wanandroid.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wanandroid.bean.LoginBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kotlin.jvm.Synchronized;

public class SpConfig {

    private static SharedPreferences sp;
    private static SpConfig instance;
    private Context context;
    private Gson gson;

    public final static String LOGIN_BEAN="login_bean"; //登录信息

    public static SpConfig getInstance(Context context) {
        if (instance == null) {
            synchronized (SpConfig.class) {
                if (instance == null) {
                    instance = new SpConfig(context);
                    sp = context.getSharedPreferences("spconfig", 0);
                }
            }
        }
        return instance;
    }

    private SpConfig(Context context) {
        this.context = context;
        gson = new Gson();
    }

    /**
     * 保存登录信息
     * @param loginBean
     * @return
     */
    public synchronized boolean saveLoginBean(LoginBean loginBean) {
        sp = context.getSharedPreferences(LOGIN_BEAN, 0);
        if (null == loginBean) {
            return false;
        }
        Type type = new TypeToken<LoginBean>(){}.getType();
        String json = gson.toJson(loginBean,type);
        return sp.edit().putString(LOGIN_BEAN,json).commit();
    }

    /**
     * 获得登录信息
     * @return
     */
    public LoginBean getLoginBean(){
        sp = context.getSharedPreferences(LOGIN_BEAN, 0);
        String json=sp.getString(LOGIN_BEAN,null);
        if (json == null) {
            return null;
        }
        Type type = new TypeToken<LoginBean>(){}.getType();
        return gson.fromJson(json,type);
    }

}
