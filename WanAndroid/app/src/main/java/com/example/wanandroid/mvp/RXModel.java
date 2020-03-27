package com.example.wanandroid.mvp;

import android.content.Context;

import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.config.BaseConfig;
import com.example.wanandroid.config.Constants;
import com.example.wanandroid.config.SpConfig;
import com.example.wanandroid.network.MyOkHttpClient;
import com.example.wanandroid.utils.ApplicationUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hwq
 * @date 2019/12/29.
 * GitHub：
 * Email：
 * Description：
 */
public abstract class RXModel {

    public Context context;

    public RXModel(Context context) {
        this.context = context;
    }

    public enum PROJECT_TYPE {
        WANANDROID // 玩安卓
    }

    public RXModel with(Context context) {
        this.context = context;
        return this;
    }

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    public <V> V getServices(Class<V> service, PROJECT_TYPE type) {
        return retrofit(type).create(service);
    }

    private Retrofit retrofit(PROJECT_TYPE type) {
        return new Retrofit.Builder()
                .baseUrl(getUrl(type))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new MyOkHttpClient().getOkHttpClient(context, getParam(type)))
                .build();
    }

    private String getUrl(PROJECT_TYPE type) {
        String url = null;
        if (type == PROJECT_TYPE.WANANDROID) {
            url = BaseConfig.BASE_URL;
        }
        return url;
    }

    private HashMap<String, String> getParam(PROJECT_TYPE type) {
        if (type == PROJECT_TYPE.WANANDROID) {
            return getWAParam();
        }
        return new HashMap<>();
    }

    private HashMap<String, String> getWAParam() {
        HashMap<String, String> params = new HashMap<>();
        params.put("systems_os", Constants.SYSTEM_OS);
        params.put("version_os", Constants.PUBLIC_PARAM);
        params.put("app_version", "android_" + ApplicationUtil.getLocalVersionCode(context));
        params.put("version_type", "1");
        return params;
    }

}
