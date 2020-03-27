package com.example.wanandroid.network;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public class MyConverter<T> implements Converter<ResponseBody, T> {

    private TypeAdapter<T> typeAdapter;

    private ParseUtils<T> parseUtils;

    public MyConverter(TypeAdapter typeAdapter) {
        this.typeAdapter = typeAdapter;
        parseUtils = new ParseUtils<>(typeAdapter);
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return parseUtils.parseResult(value.string());
    }
}
