package com.example.cherish.salehouse_kotlin.rxjava;


import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 子线程
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 20:26
 */

public class IOScheduler extends Scheduler {
    private static ExecutorService sExecutorService;
    static  {
        sExecutorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "IO");
            }
        });

    }


    @Override
    public void scheduleDirect(Runnable runnable) {
        sExecutorService.execute(runnable);
    }
}
