package com.example.cherish.salehouse_kotlin.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 主线程
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 21:05
 */

public class MainScheduler extends Scheduler{
    private static Handler sHandler;
    static {
        sHandler = new Handler(Looper.getMainLooper());
    }



    @Override
    public void scheduleDirect(Runnable runnable) {
        Message obtain = Message.obtain(sHandler, runnable);
        obtain.obj=this;
        sHandler.sendMessage(obtain);

    }
}
