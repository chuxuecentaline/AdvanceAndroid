package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.BuildConfig;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.okHttp.DiskCacheInterceptor;
import com.example.cherish.salehouse_kotlin.okHttp.HeadInterceptor;
import com.example.cherish.salehouse_kotlin.okHttp.NoNetCacheInterceptor;
import com.example.cherish.salehouse_kotlin.okHttp.UploadProgressListener;
import com.example.cherish.salehouse_kotlin.okHttp.down.DownLoadTask;
import com.example.cherish.salehouse_kotlin.okHttp.down.DownloadCallBack;
import com.example.cherish.salehouse_kotlin.okHttp.down.FileManager;
import com.example.cherish.salehouse_kotlin.okHttp.upload.ExMultipartBody;
import com.morgoo.helper.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_ok_http;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("okHttp 详解").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    public void cache(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        Cache cache = new Cache(file, 10 * 1024 * 1024);
        String url = "http://10.4.18.33:49754/v3.1/sz/api/ConfigManager/GetAppSearchConfig";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).addInterceptor(new
                HeadInterceptor())
                // 加载最前 过期时间缓存多少秒
                .addInterceptor(new NoNetCacheInterceptor(this))
                // 加载最后,数据缓存 过期时间 30s
                .addNetworkInterceptor(new DiskCacheInterceptor()).build();
        final Request request = new Request.Builder().url(url).get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println("result:" + result);
                System.out.println("cache:" + response.cacheResponse());
                System.out.println("net:" + response.networkResponse());
            }
        });


    }

    /**
     * 多线程断点下载
     *
     * @param view
     */
    public void down(View view) {
     String url="http://gyxz.ukdj3d.cn/a31/rj_xqf1/toutiaobashi.apk";
     OkHttpClient httpClient=new OkHttpClient();
     final Request request=new Request.Builder()
             .url(url).get().build();
     httpClient.newCall(request).enqueue(new Callback() {
         @Override
         public void onFailure(Call call, IOException e) {

         }

         @Override
         public void onResponse(Call call, Response response) throws IOException {
             // 不断的读写文件，单线程
             ResponseBody body = response.body();
             InputStream inputStream =body .byteStream();

             File file = new File(getCacheDir(),"daToaBao.apk");

             OutputStream outputStream = new FileOutputStream(file);

             int len = 0;
             byte[] buffer = new byte[1024*10];

             while ((len = inputStream.read(buffer))!=-1){
                 outputStream.write(buffer,0,len);
             }
             Log.e("Progress",len+"");
             inputStream.close();
             outputStream.close();

             installFile(file);

         }
     });
    }

    /**
     * 安装 apk
     * @param file
     */
    private void installFile(File  file) {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file );
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    /**
     * 上传
     *
     * @param view
     */
    public void upload(View view) {
        String url = "http://10.58.8.228:706/json/reply/UpdateUserImgRequest";
        File file = new File(Environment.getExternalStorageDirectory() + File.separator +
                "Download", "cat.png");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("UserId", "U100052505");
        builder.addFormDataPart("UserToken", "ehgkxgXn0wFT4C5UDN5f-xtJsKD7IRv0va1PjC0deoEZ8e0TjikRVBQZl9jYsjBnIDbJDulxkawOpx90aRcwfdlg/W8sSa4RAbuUU1YwroUGJhqoxG9jGgeQR5gKrPROsW9gei-3MQzQ20YiIDuoIQ__");
        builder.addFormDataPart("Img", file.getName(), RequestBody.create(MediaType.parse
                (guessMimeType(file.getAbsolutePath())), file));
        ExMultipartBody exMultipartBody = new ExMultipartBody(builder.build()
                ,new UploadProgressListener(){

            @Override
            public void onProgress(final long total, final long current) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OkHttpActivity.this,current+"/"+total,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(url).post(exMultipartBody).addHeader
                ("cityen", "tj").addHeader("platform", "android").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(""+response.body());

            }
        });
    }

    /**
     * 获取文件的格式
     *
     * @param filePath
     * @return
     */
    private String guessMimeType(String filePath) {
        String mimType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
        if (TextUtils.isEmpty(mimType)) {
            return "application/octet-stream";
        }
        return mimType;
    }

    /**
     * 断点下载
     * @param view
     */
    public void multiDown(View view) {
        FileManager.getManager().init(this);
        String url="http://gyxz.ukdj3d.cn/a31/rj_xqf1/toutiaobashi.apk";
       new  DownLoadTask().downFile(url, new DownloadCallBack() {
           @Override
           public void onFailure(IOException e) {

           }

           @Override
           public void onResponse(File file) {
               System.out.println("下载完成"+file.getAbsolutePath());
               installFile(file);
           }
       });
    }
}
