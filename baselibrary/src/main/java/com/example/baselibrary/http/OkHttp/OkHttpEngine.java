package com.example.baselibrary.http.OkHttp;

import android.content.Context;

import com.example.baselibrary.Utils.FileUtils;
import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpConfig;
import com.example.baselibrary.http.HttpEngine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp 实现
 * Created by cherish
 */

public class OkHttpEngine extends HttpEngine {

    private final OkHttpClient mOkHttpClient;

    public OkHttpEngine(Context context) {
        File cacheDir = new File(context.getExternalCacheDir(), "okhttp_cache");
        System.out.println("path------->" + cacheDir.getAbsolutePath());
        //File cacheDir = new File(getExternalCacheDir(), "okhttp");
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);
        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(HttpConfig.TIMEOUT, TimeUnit
                .MILLISECONDS)//连接超时
                .readTimeout(HttpConfig.REDTIMEOUT, TimeUnit.MILLISECONDS)//读取超时
                .writeTimeout(HttpConfig.WRITETIMEOUT, TimeUnit.MILLISECONDS)//写入超时
                .addInterceptor(new HttpHeadInterceptor())//应用拦截器；统一添加消息头
                .addInterceptor(LoggingInterceptor.log())//应用拦截器：打印日志
                .cache(cache)//设置缓存
                .build();

    }

    //同步调用,返回Response,会抛出IO异常
    //Response response = call.execute();
    @Override
    public void get(Context context, String url, Map<String, Object> params, final EngineCallBack
            callBack) {
        //异步调用,并设置回调函数
        callBack.onPreExecute();
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //子线程 需要转换UI线程
                final String result = response.body().string();
                callBack.onSuccess(result);
            }
        });
    }

    @Override
    public void post(Context context, String url, Map<String, Object> params, final EngineCallBack
            callBack) {
        callBack.onPreExecute();
        FormBody formBody = new FormBody.Builder().add("CustomerMobile","13155555555")
                .add("CityCode","022").build();

        final Request request = new Request.Builder().url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = request.body().toString();
                callBack.onSuccess(result);
            }
        });



    }

    @Override
    public void downLoadFile(final Context context, String url, Map<String, Object> params, final String path, final String name,final EngineCallBack
            callBack) {
        callBack.onPreExecute();
        final Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //子线程 需要转换UI线程
                InputStream is = response.body().byteStream();
                long total = response.body().contentLength();
                boolean success = FileUtils.downFile(context, is, total,path, name);
                if(success){
                    callBack.onSuccess(FileUtils.getDownPath());
                }else{
                    IOException e = new IOException("文件下载失败");
                    callBack.onFailed(e);
                }



            }
        });

    }

    @Override
    public void upLoadFile(Context context, String url, Map<String, Object> params, EngineCallBack
            callBack) {

    }
}
