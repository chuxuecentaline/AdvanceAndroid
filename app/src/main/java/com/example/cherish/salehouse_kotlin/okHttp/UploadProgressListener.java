package com.example.cherish.salehouse_kotlin.okHttp;

/**
 * 进度
 * Created by cherish
 */

public interface UploadProgressListener {
    public void onProgress(long total, long current);
}
