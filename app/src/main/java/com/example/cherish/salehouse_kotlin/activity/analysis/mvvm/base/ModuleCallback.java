package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base;

/**
 * 请求接口回调
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 16:16
 */

public interface ModuleCallback<R> {
    void loading();
    void empty();
    void onSuccess(R result);
    void onError(Throwable e);
}
