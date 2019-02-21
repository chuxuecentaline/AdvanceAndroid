package com.example.cherish.salehouse_kotlin.okHttp.down;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cherish
 */

public class DownloadRunable implements Runnable {
    private final long end;
    private final long start;
    private final int threadId;
    private String url;
    private DownloadCallBack callBack;
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private int status = STATUS_DOWNLOADING;
    private int progress;

    public DownloadRunable(int threadId, String url, long start, long end, int progress, DownloadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.progress = progress;
        this.start = start;
        this.end = end;
        this.threadId=threadId;
    }

    @Override
    public void run() {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        InputStream inputStream = null;
        RandomAccessFile accessFile = null;
        try {
            Response response = httpClient.newCall(request).execute();
            Log.e("TAG", this.toString());
            inputStream = response.body().byteStream();
            // 写数据
            File file = FileManager.getManager().getFile(url);
            accessFile = new RandomAccessFile(file, "rwd");
            //从这里开始
            accessFile.seek(start);
            int len = 0;
            byte[] buffer = new byte[1024 * 10];

            while ((len = inputStream.read(buffer)) != -1) {
                if (status == STATUS_STOP) break;
                // 保存进度，做断点 , 100kb
                progress += len;
                accessFile.write(buffer, 0, len);
            }

            callBack.onResponse(file);
        } catch (IOException e) {
            e.printStackTrace();
            callBack.onFailure(e);
        } finally {
            Utils.close(inputStream);
            Utils.close(accessFile);
        }

    }

    @Override
    public String toString() {
        return "DownloadRunnable{" +
                "start=" + start +
                ", end=" + end +
                ", threadId=" + threadId +
                ", url='" + url + '\'' +
                '}';
    }
}
