package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import com.example.cherish.salehouse_kotlin.okHttp.HeadInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 9:10
 */

public class RetrofitClient {

    private final Retrofit mRetrofit;

    private RetrofitClient(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeadInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor
                        .Level.BODY)).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://10.4.18.33:49754/v3.1/sztest/")
                .client(client).build();

    }
    public static RetrofitClient getInstance(){
        return RetrofitClientInstance.mInstance;
    }

    private static class RetrofitClientInstance {
        public volatile static RetrofitClient mInstance=new RetrofitClient();
    }
    public <T> T create(Class<T> clazz){
       return mRetrofit.create(clazz);
    }
}
