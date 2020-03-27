package com.example.wanandroid.network;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.config.BaseConfig;
import com.example.wanandroid.config.SpConfig;
import com.example.wanandroid.utils.MD5;
import com.example.wanandroid.utils.log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public class MyOkHttpClient {

    public static String token;
    public static long TIME_OUT = 30;

    public HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((message) ->
        log.e("RetrofitLog" + "retrofitBack = " + message)
    );

    public OkHttpClient getOkHttpClient(final Context context, final Map<String, String> params) {
        File sdCache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;

        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(), cacheSize))
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        long time = getSecondTimestamp();
                        String sign = GetSign(context, time, request.url().toString());
                        StringBuilder builder = new StringBuilder(request.url().toString());
                        builder.append("?");
                        RequestBody body = request.body();
                        if (body instanceof FormBody) {
                            FormBody.Builder formBodyBuilder = new FormBody.Builder();
                            FormBody formBody = (FormBody) body;
                            int size = formBody.size();
                            for (int index = 0; index < size; index++) {
                                String name = formBody.name(index);
                                String value = formBody.value(index);
                                if (!TextUtils.isEmpty(value)) {
                                    formBodyBuilder.add(name, value);
                                    builder.append(name).append("=").append(value).append("&");
                                }
                            }
                            if (null != params) {
                                for (String key : params.keySet()) {
                                    String val = params.get(key);
                                    formBodyBuilder.add(key, val);
                                    builder.append(key).append("=").append(val).append("&");
                                }
                            }
                            formBodyBuilder.add("client_time", "" + time);
                            builder.append("client_time").append("=").append("" + time).append("&");

                            formBodyBuilder.add("sign", sign);
                            builder.append("sign").append("=").append(sign).append("&");

                            request = request.newBuilder()
                                    .method(request.method(), formBodyBuilder.build())
                                    .addHeader("APP_TYPE", "Android")
                                    .addHeader("APP_DEVICE_MODEL", Build.DEVICE)
                                    .addHeader("time", "" + time)
                                    .addHeader("sign", sign)
                                    .addHeader("APP_OS", String.valueOf(Build.VERSION.SDK_INT))
                                    .build();
                        } else {
                            HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
                            if (null != params) {
                                for (String key : params.keySet()) {
                                    String val = params.get(key);
                                    if (!TextUtils.isEmpty(val)) {
                                        httpUrlBuilder.addQueryParameter(key, val);
                                        builder.append(key).append("=").append(val).append("&");
                                    }
                                }
                            }

                            httpUrlBuilder.addQueryParameter("sign", sign);
                            builder.append("sign").append("=").append(sign).append("&");

                            httpUrlBuilder.addQueryParameter("client_time", ""+time);
                            builder.append("client_time").append("=").append(""+time).append("&");

                            request = request.newBuilder()
                                    .url(httpUrlBuilder.build())
                                    .addHeader("APP_TYPE", "Android")
                                    .addHeader("APP_DEVICE_MODEL", Build.DEVICE)
                                    .addHeader("time", "" + time)
                                    .addHeader("sign", sign)
                                    .addHeader("APP_OS", String.valueOf(Build.VERSION.SDK_INT))
                                    .build();
                        }
                        log.e("请求的url：" + builder.toString());
                        Response response = chain.proceed(request);
                        return response;
                    }
                });
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static int getSecondTimestamp() {
        Date date = new Date();
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    public static String GetSign(Context context, long time, String url) {
        String sign = "";
        sign = getWanAndroidSign(context, time, url);

        return sign;
    }

    private static String getWanAndroidSign(Context context, long time, String url) {
        String sign = "";
        String frontUrl = BaseConfig.BASE_URL;
        int length = frontUrl.length();
        String lastUrl = url.substring(length, url.length());
        LoginBean loginBean= SpConfig.getInstance(context).getLoginBean();
        sign = (time + "/" + lastUrl).toLowerCase();
        if (loginBean != null) {
            sign = sign + "/" + "l12Dds.2#-2SG4";
        }else {
            sign = sign + "/" + "l12Dds.2#-2SG4";
        }
        sign = MD5.Md5(sign);
        return sign;
    }
}
