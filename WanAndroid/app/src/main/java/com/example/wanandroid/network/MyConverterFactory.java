package com.example.wanandroid.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public class MyConverterFactory extends Converter.Factory {

    private static MyConverterFactory myConverterFactory;

    private MyConverterFactory() {}

    public static MyConverterFactory create() {
        if (null == myConverterFactory) {
            myConverterFactory = new MyConverterFactory();
        }
        return myConverterFactory;
    }

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> _typeAdapter = gson.getAdapter(TypeToken.get(type));

        if (type instanceof Class) {
            return new MyConverter<>(_typeAdapter);
        }
        return null;
    }
}
