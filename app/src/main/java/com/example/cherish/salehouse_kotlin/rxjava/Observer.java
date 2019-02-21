package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 观察者
 *
 * @Author: cherish
 * @CreateDate: 2019/1/23 20:59
 */

public interface Observer<T> {
    void onSubscribe();

    void onCompleted();

    void onError(Throwable e);

    void onNext(T t);
}
