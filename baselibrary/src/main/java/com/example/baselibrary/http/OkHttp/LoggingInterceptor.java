package com.example.baselibrary.http.OkHttp;

import android.graphics.Bitmap;
import android.util.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by cherish
 */

class LoggingInterceptor {
    public static Interceptor log() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);//日志级别，Body级别打印的信息最全面
        return logging;

    }
}
