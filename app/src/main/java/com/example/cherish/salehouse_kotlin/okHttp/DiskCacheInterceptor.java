package com.example.cherish.salehouse_kotlin.okHttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 网络缓存磁盘
 * Created by cherish
 */

public class DiskCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        response= response.newBuilder().removeHeader("cache-control").removeHeader
                ("Pragma").addHeader("cache-control", "max-age=" + 30).build();

        return response;
    }
}
