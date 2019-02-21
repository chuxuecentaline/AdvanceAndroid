package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 订阅
 *
 * @Author: cherish
 * @CreateDate: 2019/1/23 20:59
 */

public interface ObservableSource<T>{
    void subscribe(Observer<T> observer);

}
