package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 *网络请求参数的拼接
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 10:21
 */

public abstract class ParameterHandler<T> {
    abstract void apply( RequestBuilder requestBuilder,@Nullable T value) throws IOException;

    public static class Body<T> extends ParameterHandler<T> {

        private final TypeAdapter<T> mAdapter;
        private final Gson mGson;

        public Body(Type parameterType) {
            mGson = new Gson();
            mAdapter = (TypeAdapter<T>) mGson.getAdapter(TypeToken.get(parameterType));
        }


        @Override
        void apply(RequestBuilder requestBuilder, @Nullable T value) throws IOException {
            // 添加到request中 // value -> String 要经过一个工厂
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), Charset.forName("UTF-8"));
            JsonWriter jsonWriter = mGson.newJsonWriter(writer);
            mAdapter.write(jsonWriter, value);
            jsonWriter.close();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; " +
                    "charset=UTF-8"),buffer.readByteString());
            requestBuilder.addBody(body);
        }
    }
}
