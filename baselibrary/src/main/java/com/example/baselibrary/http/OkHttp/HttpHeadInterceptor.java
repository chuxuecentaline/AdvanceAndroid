package com.example.baselibrary.http.OkHttp;

import android.os.Build;
import android.text.TextUtils;

import com.example.baselibrary.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 全局头信息：设备id、平台、app版本
 * Created by cherish
 */

class HttpHeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.header("cityen","tj").header("platform", "android").header("OSVersion", String.valueOf(Build.VERSION
                .SDK_INT)).header("appVersion", String.valueOf(BuildConfig.VERSION_CODE));
        return chain.proceed(builder.build());
    }
}
