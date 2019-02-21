package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import okhttp3.Response;

/**
 * okHttp 网络请求
 *
 * @Author: cherish
 * @CreateDate: 2019/2/18 11:16
 */

public class OkHttpCall<T> implements  Call<T>{
    private ServiceMethod serviceMethod;
    private Object[] args;

    public OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(final Callback<T> callback) {
        // 发起一个请求，给一个回调就完结了
        Log.e("TAG","正式发起请求");
        okhttp3.Call call = serviceMethod.createNewCall(args);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                callback.onFailure(call,e);
            }

            @Override
            public void onResponse(final okhttp3.Call call, Response response) throws IOException {

                final T rResponse = serviceMethod.convertBody(response.body());
                Handler handler=new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(call,rResponse);
                    }
                });

            }
        });

    }
}
