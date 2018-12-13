package com.example.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by cherish
 */

public interface HttpImpl {
    /**
     * get 请求
     *
     * @param context
     * @param url
     * @param params
     * @param callBack
     */
    void  get(Context context, String url, Map<String, Object> params, EngineCallBack callBack);

    /**
     * post 请求
     *
     * @param context
     * @param url
     * @param params
     * @param callBack
     */
    void post(Context context, String url, Map<String, Object> params, EngineCallBack callBack);

    /**
     * 下载文件 (单一、批量)
     *
     * @param context
     * @param url
     * @param params
     * @param path    存储路径
     * @param  name   文件的名称加扩展名
     */
    void downLoadFile(Context context, String url, Map<String, Object> params, String path,String name, EngineCallBack
            callBack);

    /**
     * 上传文件流 (单一、批量)
     *
     * @param context
     * @param url
     * @param params
     * @param callBack
     */
    void upLoadFile(Context context, String url, Map<String, Object> params, EngineCallBack callBack);



}
