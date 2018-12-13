package com.example.baselibrary.singleton;

import android.util.LruCache;

/**
 * LruCache -单例模式
 * Created by cherish
 */

public class LruCacheUtils {
    public static volatile LruCacheUtils mInstance;
    LruCache<String, Object> mLruCache;

    private LruCacheUtils() {
        //获取到应用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //设置LruCache的缓存大小
        int cacheSize = maxMemory / 8;

        mLruCache = new LruCache<>(cacheSize);
    }

    public static LruCacheUtils getInstance() {
        if (mInstance == null) {
            synchronized (LruCacheUtils.class) {
                if (mInstance == null) {
                    mInstance = new LruCacheUtils();
                }
            }
        }
        return mInstance;
    }


}
