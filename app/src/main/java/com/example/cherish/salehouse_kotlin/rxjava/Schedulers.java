package com.example.cherish.salehouse_kotlin.rxjava;

import android.support.annotation.NonNull;

/**
 * 线程调度
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 20:23
 */

public class Schedulers {
    @NonNull
    static final Scheduler IO;

    @NonNull
    static final Scheduler MAIN;
    static {

        IO = new IOScheduler();
        MAIN = new MainScheduler();
    }

    public static Scheduler io() {

        return IO;
    }

    public static Scheduler mainThread() {
        return MAIN;
    }
}
