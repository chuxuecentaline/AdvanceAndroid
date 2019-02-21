package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 线程调度
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 20:35
 */

public abstract class Scheduler {
    public abstract void scheduleDirect(Runnable runnable);
}
