package com.example.baselibrary.http.OkHttp;

import okhttp3.MediaType;

/**
 * 类型
 * Created by cherish
 */

public class MediaTypeHelper {
    public static MediaType string() {
        return MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
    }
    public static MediaType file() {
        return MediaType.parse("File/*");//文件流
    }
    public static MediaType stream() {
        return MediaType.parse("application/octet-stream");//文件流
    }
}
