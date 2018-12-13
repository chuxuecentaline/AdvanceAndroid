package com.example.cherish.salehouse_kotlin.utils.singleton;

/**
 * 单例模式- 静态内部类
 * Created by cherish
 */

public class SingletonInternalStaticClass {

    private SingletonInternalStaticClass() {

    }

    public static SingletonInternalStaticClass getInstance() {
        return SingletonInternalHolder.mInstance;
    }

    public static class SingletonInternalHolder {
        // 加上 volatile 的用处是什么？ 防止jvm 重排序已经多线程之间的可见性
        private static volatile SingletonInternalStaticClass mInstance = new SingletonInternalStaticClass();
    }
}
