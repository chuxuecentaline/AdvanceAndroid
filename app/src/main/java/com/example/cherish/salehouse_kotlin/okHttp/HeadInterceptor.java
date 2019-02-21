package com.example.cherish.salehouse_kotlin.okHttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *新增头部
 * Created by cherish
 */

public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request userRequest = chain.request();
        Request.Builder requestBuilder = userRequest.newBuilder();
        requestBuilder.addHeader("AuthToken","{\"ClientVersion\":\"3.1.0\"," +
                "\"CompanyPath\":\"001\",\"DeviceId\":\"WysLq+PU0UADAATSUnSUULwK\"," +
                "\"Platform\":\"android\",\"UpdateCode\":310}");

        return chain.proceed(requestBuilder.build());
    }
}
