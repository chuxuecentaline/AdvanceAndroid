package com.example.baselibrary.cache;

import android.content.Context;
import android.util.LruCache;

import com.example.baselibrary.singleton.LruCacheUtils;

/**
 * 内存缓存
 * Created by cherish
 */

public class MemoryCache implements IoHandler {
    public static volatile MemoryCache mInstance;
    LruCache<String, Object> mLruCache;

    private MemoryCache() {
        //获取到应用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //设置LruCache的缓存大小
        int cacheSize = maxMemory / 8;

        mLruCache = new LruCache<>(cacheSize);
    }

    public static MemoryCache getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LruCacheUtils.class) {
                if (mInstance == null) {
                    mInstance = new MemoryCache();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void setString(String key, String value) {
        mLruCache.put(key, value);
    }

    @Override
    public void setInt(String key, int value) {
        mLruCache.put(key, value);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        mLruCache.put(key, value);
    }

    @Override
    public void setFloat(String key, float value) {
        mLruCache.put(key, value);
    }

    @Override
    public void setObject(String key, Object value) {
        mLruCache.put(key, value);
    }

    @Override
    public String getString(String key) {
        return (String) mLruCache.get(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return (int) mLruCache.get(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return (boolean) mLruCache.get(key);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return (float) mLruCache.get(key);
    }

    @Override
    public Object getObject(String key, Object value) {
        return mLruCache.get(key);
    }


}
