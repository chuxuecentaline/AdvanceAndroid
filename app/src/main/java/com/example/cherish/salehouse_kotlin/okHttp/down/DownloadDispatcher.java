package com.example.cherish.salehouse_kotlin.okHttp.down;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class DownloadDispatcher {
    /**
     * Ready async calls in the order they'll be run.
     */
    private final Deque<DownLoadTask> readyTask = new ArrayDeque<>();

    /**
     * Running asynchronous calls. Includes canceled calls that haven't finished yet.
     */
    private final Deque<DownLoadTask> runningTask = new ArrayDeque<>();

    /**
     * Running synchronous calls. Includes canceled calls that haven't finished yet.
     */
    private final Deque<DownLoadTask> stopTask = new ArrayDeque<>();
    private ThreadPoolExecutor executorService;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(128),
                    new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r, "down");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }

    public void downFile(DownLoadTask task,  int i, String url, long start, long end, int progress, DownloadCallBack callBack) {

        DownloadRunable downloadRunable = new DownloadRunable(i, url, start, end, progress,
                callBack);
        executorService.execute(downloadRunable);
        runningTask.add(task);
    }

    public void recyclerTask(DownLoadTask task) {
        runningTask.remove(task);
        // 参考 OkHttp 的 Dispatcher 的源码,如果还有需要下载的开始下一个的下载
    }
}
