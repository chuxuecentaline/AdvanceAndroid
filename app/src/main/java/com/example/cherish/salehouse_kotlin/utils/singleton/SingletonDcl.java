package com.example.cherish.salehouse_kotlin.utils.singleton;

/**
 * 单例模式- 同步锁
 * Created by cherish
 */

public class SingletonDcl {

    private volatile static SingletonDcl mSingleTon;

    private SingletonDcl() {
    }

    public static SingletonDcl getInstance() {
        if (mSingleTon == null) {
            synchronized (SingletonDcl.class) {
                if (mSingleTon == null) {
                    mSingleTon = new SingletonDcl();
                }

            }

        }
        return mSingleTon;
    }


}
