package com.example.cherish.salehouse_kotlin.activity.analysis;

import com.example.cherish.salehouse_kotlin.okHttp.HeadInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit+okHttp+rxJava
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 13:15
 */

public class RetrofitClient {
    private static volatile RetrofitClient mClient;
    private final Retrofit mRetrofit;

    private RetrofitClient() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeadInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor
                        .Level.BODY)).build();
         mRetrofit = new Retrofit.Builder().baseUrl("http://10.4.18.33:49754/v3.1/sztest/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (mClient == null) {
            synchronized (RetrofitClient.class) {
                if (mClient == null) {
                    mClient = new RetrofitClient();
                }
            }
        }
        return mClient;
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
