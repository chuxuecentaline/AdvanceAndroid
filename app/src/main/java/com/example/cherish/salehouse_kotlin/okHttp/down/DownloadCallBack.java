package com.example.cherish.salehouse_kotlin.okHttp.down;

import java.io.File;
import java.io.IOException;

/**
 * 断点续载回调
 * Created by cherish
 */

public interface DownloadCallBack {
    void onFailure(IOException e);

    void onResponse(File file);
}
