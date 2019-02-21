package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

/**
 * @Author: cherish
 * @CreateDate: 2019/2/1 10:49
 */

public interface Call<T> {

    void  enqueue(Callback<T> callback);
}
