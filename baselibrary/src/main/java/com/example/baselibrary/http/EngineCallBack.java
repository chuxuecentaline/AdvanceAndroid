package com.example.baselibrary.http;

/**
 * 回调
 * Created by cherish
 */

public interface EngineCallBack {

    void onPreExecute();

    void onSuccess(String result);

    void onFailed(Exception e);


}
