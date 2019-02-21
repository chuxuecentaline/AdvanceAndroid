package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/18 14:04
 */


public interface Callback<T> {
    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link BaseResponse#isSuccessful()} Tto determine if the response indicates success.
     */
    void onResponse(okhttp3.Call call, T response);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    void onFailure(okhttp3.Call  call, Throwable t);
}