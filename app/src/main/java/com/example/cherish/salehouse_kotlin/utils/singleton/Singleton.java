package com.example.cherish.salehouse_kotlin.utils.singleton;

/**
 * 单例模式-恶汉模式
 * Created by cherish
 */

public class Singleton {
    private final static Singleton mSingleTon = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return mSingleTon;
    }
}
