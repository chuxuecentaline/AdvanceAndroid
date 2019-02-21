package com.example.cherish.salehouse_kotlin.okHttp;

import android.content.Context;

import com.example.cherish.salehouse_kotlin.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 没有网络只读取缓存
 * Created by cherish
 */

public class NoNetCacheInterceptor implements Interceptor {
    private final Context mContext;

    public NoNetCacheInterceptor(Context context) {
        mContext=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetUtils.isNetworkConnected(mContext)){
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }

        return chain.proceed(request);

    }
}
