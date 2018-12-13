package com.example.cherish.salehouse_kotlin.utils.singleton;

/**
 * 单例模式- 懒汉模式
 * Created by cherish
 */

public class Singleton1 {
    private static Singleton1 mSingleTon;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (mSingleTon == null) {
            mSingleTon = new Singleton1();
        }
        return mSingleTon;
    }
}
