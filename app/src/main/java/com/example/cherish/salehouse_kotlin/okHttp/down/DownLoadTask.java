package com.example.cherish.salehouse_kotlin.okHttp.down;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 多线程池下载
 * Created by cherish
 */

public class DownLoadTask {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private volatile int mSucceedNumber;

    public void downFile(final String url, final DownloadCallBack callBack) {
        OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                long contentLength = response.body().contentLength();
                System.out.println("----------->" + contentLength);
                for (int i = 0; i < THREAD_SIZE; i++) {
                    if (contentLength < 0) {
                        System.out.println("获取不到文件的长度");
                        return;
                    }
                    long length = contentLength / THREAD_SIZE;
                    long start = i * length;
                    long end = (i + 1) * length;
                    if (i == THREAD_SIZE - 1) {
                        end = contentLength;
                    }
                    final DownloadDispatcher dispatcher = new DownloadDispatcher();
                    dispatcher.executorService();
                    dispatcher.downFile(DownLoadTask.this, i, url, start, end, 0, new
                            DownloadCallBack() {


                        @Override
                        public void onFailure(IOException e) {
                            // 一个apk 下载里面有一个线程异常了，处理异常,把其他线程停止掉
                            callBack.onFailure(e);
                        }

                        @Override
                        public void onResponse(File file) {
                            // 线程同步一下，
                            synchronized (DownLoadTask.this) {
                                mSucceedNumber += 1;
                                if (mSucceedNumber == THREAD_SIZE) {
                                    callBack.onResponse(file);
                                    dispatcher.recyclerTask(DownLoadTask.this);

                                    // 清楚数据库的这个文件下载存储

                                }
                            }
                        }
                    });
                }


            }
        });
    }
}
