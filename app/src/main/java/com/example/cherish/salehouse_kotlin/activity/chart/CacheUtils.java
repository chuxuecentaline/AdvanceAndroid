package com.example.cherish.salehouse_kotlin.activity.chart;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存
 * Created by cherish
 */

public class CacheUtils {
    private LruCache<String, List<String>> mLruCache;
    private volatile static CacheUtils mInstance;
    private  List<String> mCache;
    private CacheUtils() {
        mLruCache = new LruCache<>(10 * 1024 * 1024);
        mCache=new ArrayList<>();
    }

    public static CacheUtils getInstance() {
        if (mInstance == null) {
            synchronized (CacheUtils.class) {
                if (mInstance == null) {
                    mInstance = new CacheUtils();
                }
            }
        }
        return mInstance;
    }

    public void setData(String key, List<String> value) {
        mLruCache.put(key, value);
    }

    public List<String> getData(String key) {
        return  mLruCache.get(key);
    }
    public void setData(List<String> value) {
        mCache.addAll(value);
    }

    public List<String> getData() {
        return  mCache;
    }
}
